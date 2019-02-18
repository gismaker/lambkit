/**
 * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.server.jetty;

import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import com.jfinal.core.JFinalFilter;
import com.jfinal.kit.FileKit;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.PathKit;
import com.jfinal.server.Scanner;
import com.lambkit.Lambkit;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.server.LambkitServer;
import com.lambkit.server.LambkitServerConfig;

public class JettyServer extends LambkitServer {

	private LambkitServerConfig config;

	private boolean running = false;
	private Server server;
	private WebAppContext webApp;

	public JettyServer() {
		// TODO Auto-generated constructor stub
		setConfig(ConfigManager.me().get(LambkitServerConfig.class));
	}

	@Override
	public boolean start() {
		if (!running) {
			try {
				running = true;
				return doStart();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				LogKit.error(e.getMessage(), e);
			}
		}
		return false;
	}

	public boolean doStart() {
		int port = config.getPort();
		
		if (!available(port)) {
			throw new IllegalStateException("port: " + config.getPort() + " already in use!");
		}

		deleteSessionData();

		EnumSet<DispatcherType> all = EnumSet.of(DispatcherType.ASYNC, DispatcherType.ERROR, DispatcherType.FORWARD,
				DispatcherType.INCLUDE, DispatcherType.REQUEST);
		server = new Server();
		try {
			SelectChannelConnector connector = new SelectChannelConnector();
			connector.setPort(port);
			server.addConnector(connector);
			// 设置在JVM退出时关闭Jetty的钩子。
			server.setStopAtShutdown(true);

			webApp = new WebAppContext();
			webApp.setThrowUnavailableOnStartupException(true);	// 在启动过程中允许抛出异常终止启动并退出 JVM
			webApp.setContextPath(config.getContextPath());
			System.out.println("jetty current dir: " + System.getProperty("user.dir"));
			webApp.setResourceBase(config.getWebAppDir());	// webApp.setWar(webAppDir);
			if(!config.isWebXml()) {
				if(config.isShiro()) {
					webApp.addEventListener(new EnvironmentLoaderListener());
					FilterHolder shiorfilter = new FilterHolder(new ShiroFilter());
					shiorfilter.setInitParameter("shiro", "org.apache.shiro.web.servlet.ShiroFilter");
					webApp.addFilter(shiorfilter, "/*", all);
				}
				FilterHolder filter = new FilterHolder(new JFinalFilter());
				filter.setInitParameter("configClass", Lambkit.me().getLambkitConfig().getJfinalConfig());
				webApp.addFilter(filter, "/*", all);
			}

			webApp.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
			webApp.setInitParameter("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false"); 
			persistSession(webApp);

			server.setHandler(webApp);

			// configureScanner
			int scanIntervalSeconds = config.getScanIntervalSeconds();
			if (scanIntervalSeconds > 0) {
				// only need to change classloader when scanIntervalSeconds > 0
				changeClassLoader(webApp); 

				Scanner scanner = new Scanner(PathKit.getRootClassPath(), scanIntervalSeconds) {
					public void onChange() {
						try {
							System.err.println("\nLoading changes ......");
							webApp.stop();
							JettyClassLoader loader = new JettyClassLoader(webApp, getClassPath());
							webApp.setClassLoader(loader);
							webApp.start();
							System.err.println("Loading complete.");
						} catch (Exception e) {
							System.err.println("Error reconfiguring/restarting webapp after change in watched files");
							LogKit.error(e.getMessage(), e);
						}
					}
				};
				System.out.println("Starting scanner at interval of " + scanIntervalSeconds + " seconds.");
				scanner.start();
			}

			System.out.println("Starting web server on port: " + port);
			server.start();
			Lambkit.me().printServerUrl();
			System.out.println("Starting Complete. Welcome To The Lambkit World :)");
		} catch (Exception e) {
			e.printStackTrace();
			// 打印dump时的信息
			//System.out.println(server.dump());
			stop();
			return false;
		}
		return true;
	}

	@Override
	public boolean startAfter() {
		// TODO Auto-generated method stub
		try {
			server.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 打印dump时的信息
			//System.out.println(server.dump());
			stop();
			return false;
		}
		return true;
	}

	@Override
	public boolean restart() {
		// TODO Auto-generated method stub
		try {
			stop();
			start();
			startAfter();
			System.err.println("jetty restarted!!!");
		} catch (Throwable ex) {
			return false;
		}

		return true;
	}

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		try {
			if (server != null)
				server.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public LambkitServerConfig getConfig() {
		return config;
	}

	public void setConfig(LambkitServerConfig config) {
		this.config = config;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	/**********************************/
	private void changeClassLoader(WebAppContext webApp) {
		try {
			String classPath = getClassPath();
			JettyClassLoader jfcl = new JettyClassLoader(webApp, classPath);
			// jfcl.addClassPath(classPath);
			webApp.setClassLoader(jfcl);
		} catch (IOException e) {
			LogKit.error(e.getMessage(), e);
		}
	}

	private String getClassPath() {
		return System.getProperty("java.class.path");
	}

	private void deleteSessionData() {
		try {
			FileKit.delete(new File(getStoreDir()));
		} catch (Exception e) {
			LogKit.logNothing(e);
		}
	}

	private String getStoreDir() {
		String storeDir = PathKit.getWebRootPath() + "/../../session_data" + config.getContextPath();
		if ("\\".equals(File.separator)) {
			storeDir = storeDir.replaceAll("/", "\\\\");
		}
		return storeDir;
	}

	private void persistSession(WebAppContext webApp) {
		String storeDir = getStoreDir();

		SessionManager sm = webApp.getSessionHandler().getSessionManager();
		if (sm instanceof HashSessionManager) {
			((HashSessionManager) sm).setStoreDirectory(new File(storeDir));
			return;
		}

		HashSessionManager hsm = new HashSessionManager();
		hsm.setStoreDirectory(new File(storeDir));
		SessionHandler sh = new SessionHandler();
		sh.setSessionManager(hsm);
		webApp.setSessionHandler(sh);
	}

	private boolean available(int port) {
		if (port <= 0) {
			throw new IllegalArgumentException("Invalid start port: " + port);
		}

		ServerSocket ss = null;
		DatagramSocket ds = null;
		try {
			ss = new ServerSocket(port);
			ss.setReuseAddress(true);
			ds = new DatagramSocket(port);
			ds.setReuseAddress(true);
			return true;
		} catch (IOException e) {
			LogKit.logNothing(e);
		} finally {
			if (ds != null) {
				ds.close();
			}

			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					// should not be thrown, just detect port available.
					LogKit.logNothing(e);
				}
			}
		}
		return false;
	}
}
