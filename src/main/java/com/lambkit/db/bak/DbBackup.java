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
package com.lambkit.db.bak;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.core.JFinal;
import com.lambkit.db.datasource.DataSourceConfig;
import com.lambkit.db.datasource.DataSourceConfigManager;

public abstract class DbBackup {

	private String username;
	private String password;
	private String host;
	private String PORT;
	private String dbname;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPORT() {
		return PORT;
	}

	public void setPORT(String pORT) {
		PORT = pORT;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	
	//配置信息
	public void config() {
		DataSourceConfig dataSourceConfig = DataSourceConfigManager.me().getDefaultDatasourceConfigs();
		setUsername(dataSourceConfig.getUser());
		setPassword(dataSourceConfig.getPassword());
		String url = dataSourceConfig.getUrl();
		url = url.substring(13, url.length());
		String[] temp = url.split("/");
		String[] temp1 = temp[0].split(":");
		setHost(temp1[0]);
		setPORT(temp1[1]);
		for (int i = 0; i < temp[1].length(); i++) {
			String temp2 = temp[1].charAt(i) + "";
			if (temp2.equals("?")) {
				setDbname(temp[1].substring(0, i));
			}
		}
	}

	//备份
	public abstract void backup();
	//恢复
	public abstract void load(String selectName);
	//备份文件夹地址
	public String upfilepath() {
		return "/static/dbback";
	}
	//刪除
	public boolean delete(String fileName) {
		// System.out.println(fileName);
		String backPath = JFinal.me().getServletContext().getRealPath("/") + "/static/dbback/" + fileName;
		File file = new File(backPath);
		return file.delete();
	}
	//获取文件列表
	public List<DbBackupFile> findList(String webpath) {
		List<DbBackupFile> dataFiles = new ArrayList<DbBackupFile>();
		String backPath = JFinal.me().getServletContext().getRealPath("/") + "/static/dbback/";
		File file = new File(backPath);
		if (!file.exists())
			return dataFiles;
		File[] file1 = file.listFiles();
		for (int i = 0; i < file1.length; i++) {
			if (file1[i].getName().equals("ramdit.txt"))
				continue;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
			java.util.Date dt = new Date(file1[i].lastModified());
			String sDateTime = sdf.format(dt); // 得到精确到秒的表示：08/31/2006 21:08:00
			DbBackupFile dataFile = new DbBackupFile();
			dataFile.setFileName(file1[i].getName());
			dataFile.setFileDate(sDateTime);
			String filePath = webpath + "/static/dbback/" + file1[i].getName();
			dataFile.setFilePath(filePath);
			DecimalFormat df = new DecimalFormat(".## ");
			dataFile.setFileSize(df.format(file1[i].length() / 1024000f));
			dataFiles.add(dataFile);
		}
		return dataFiles;
	}
}
