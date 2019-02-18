/**
 * Copyright (c) 2015-2017, York Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.rpc.motan.test;

import com.jfinal.config.Routes;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.lambkit.Lambkit;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.distributed.node.ManagerNodeService;
import com.lambkit.module.LambkitModule;
import com.lambkit.node.test.ServiceNodeServer;
import com.lambkit.web.controller.annotation.RequestMapping;
import com.weibo.api.motan.config.ProtocolConfig;
import com.weibo.api.motan.config.RefererConfig;
import com.weibo.api.motan.config.RegistryConfig;

import service.CategoryService;
import service.User;
import service.UserService;


//@RequestMapping("/rpc")
public class MotanClientDemo extends Controller {
    /**
     * 请先启动 MotanServerDemo 后，再启动
     *
     * @param args
     */
    public static void main(String[] args) {
		LambkitModule config = new LambkitModule() {
    		@Override
    		public void configRoute(Routes me) {
    			// TODO Auto-generated method stub
    			super.configRoute(me);
    			me.add("rpc", MotanClientDemo.class);
    		}
		};
		Lambkit.me().addModule(config);
		Lambkit.setBootArg("lambkit.server.webAppDir", "src/main/webapp");
		Lambkit.setBootArg("lambkit.node.type", "ServiceNode");
		Lambkit.setBootArg("lambkit.node.major", "lambkit-manager-node");
		Lambkit.setBootArg("lambkit.node.manager.id", "lambkit-manager-node");
		Lambkit.setBootArg("lambkit.node.manager.rpcPort", "8002");
		Lambkit.setBootArg("lambkit.node.manager.port", "9527");
		Lambkit.setBootArg("lambkit.server.port", 8088);
		Lambkit.run(args);
		
		/*
    	RefererConfig<UserService> motanDemoServiceReferer = new RefererConfig<UserService>();

        // 设置接口及实现类
        motanDemoServiceReferer.setInterface(UserService.class);

        // 配置服务的group以及版本号
        motanDemoServiceReferer.setGroup("lambkit");
        motanDemoServiceReferer.setVersion("1.0");
        motanDemoServiceReferer.setRequestTimeout(300);

        // 配置注册中心直连调用
         RegistryConfig directRegistry = new RegistryConfig();
         directRegistry.setRegProtocol("local");
         directRegistry.setAddress("localhost:8002");
         motanDemoServiceReferer.setRegistry(directRegistry);

        // 配置ZooKeeper注册中心
//        RegistryConfig zookeeperRegistry = new RegistryConfig();
//        zookeeperRegistry.setRegProtocol("zookeeper");
//        zookeeperRegistry.setAddress("127.0.0.1:2181");
//        motanDemoServiceReferer.setRegistry(zookeeperRegistry);

        // 配置RPC协议
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setId("motan");
        protocol.setName("motan");
        motanDemoServiceReferer.setProtocol(protocol);
         motanDemoServiceReferer.setDirectUrl("localhost:8002");  // 注册中心直连调用需添加此配置

        // 使用服务
         UserService service = motanDemoServiceReferer.getRef();
         
         User user = service.findUserById("1");

        System.out.println("get user name :" + user.getName() + ", id: "+user.getId());

        System.exit(0);
        */
    }
    
    /*
    public static <T> T serviceObtain(Class<T> serviceClass, String group, String version) {
		// TODO Auto-generated method stub
		String key = String.format("%s:%s:%s", serviceClass.getName(), group, version);
		
		System.out.println("rpc : " + key);

		RefererConfig<T> refererConfig = new RefererConfig<T>();

		// 设置接口及实现类
		refererConfig.setInterface(serviceClass);

		// 配置服务的group以及版本号
		refererConfig.setGroup(group);
		refererConfig.setVersion(version);
		refererConfig.setRequestTimeout(1000);
		
		// 配置注册中心直连调用
        RegistryConfig registry = new RegistryConfig();

        //use direct registry
        registry.setRegProtocol("local");
        registry.setAddress("localhost:8002");
        refererConfig.setRegistry(registry);
        
		// 配置RPC协议
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setId("motan");
        protocol.setName("motan");
		refererConfig.setProtocol(protocol);
//		refererConfig.setProxy("lambkit");
//		refererConfig.setCheck("false");
		
		refererConfig.setDirectUrl("localhost:8002");
		
		return refererConfig.getRef();
	}
	*/


    public void index() {
    	renderText("rpc controller.");
    }
    
    public void node() {
    	ManagerNodeService service = RpcKit.obtain(ManagerNodeService.class, "lambkit", "1.0", "127.0.0.1:8002");
		if(service!=null) {
			System.out.println(service.getToken());
		} else {
			System.out.println("fail");
		}
    }
    
    public void helloUser() {
    	//Rpc rpc = RpcManager.me().getRpc();

        long time = System.currentTimeMillis();
        UserService service = RpcKit.obtain(UserService.class, "lambkit", "1.0");
        System.out.println("obtain:" + (System.currentTimeMillis() - time) + "---" + service);


        for (int i = 0; i < 10; i++) {
            // 使用服务
            System.out.println(service.hello("lambkit" + i));
        }


        renderText("ok");
    }
    
    public void hello() {
        CategoryService service = RpcKit.obtain(CategoryService.class, "lambkit", "1.0");
        System.out.println(service.hello("lambkit"));
    }


    public void exception() {
    	//Rpc rpc = RpcManager.me().getRpc();
    	
        long time = System.currentTimeMillis();
        UserService service = RpcKit.obtain(UserService.class, "lambkit", "1.0");

//        try {
        String string = service.exception("1");
//        } catch (LambkitException e) {
//            System.out.println("exception : " + e.getMessage());
//        }

        renderText("exception:" + string);

    }
    
    public void find() {
    	//Rpc rpc = RpcManager.me().getRpc();
    	
        long time = System.currentTimeMillis();
        UserService service = RpcKit.obtain(UserService.class, "lambkit", "1.0");
        System.out.println("obtain:" + (System.currentTimeMillis() - time) + "---" + service);

        User user = service.findUserById("1");

        renderText("get user name :" + user.getName() + ", id: "+user.getId());
    }



}
