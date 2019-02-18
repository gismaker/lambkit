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

import com.jfinal.core.JFinal;

public class MySQLBackup extends DbBackup {

	public MySQLBackup() {
		config();
	}
	
	@Override
	public void backup() {
		// TODO Auto-generated method stub
		// 得到配置文件
		try {
			Runtime rt = Runtime.getRuntime();
			String backPath = JFinal.me().getServletContext().getRealPath("/")
					+ "/static/dbback/" + System.currentTimeMillis() + ".sql";
			String mysql = "mysqldump -u" + getUsername() + " -p"
					+ getPassword() + " --default-character-set=utf8 -h"
					+ getHost() + " -P" + getPORT() + " " + getDbname() + " >"
					+ "\"" + backPath + "\"";
			//System.out.println("backup : " + mysql);
			Process proc = rt.exec("cmd.exe /c " + mysql);// 设置导出编码为utf8。这里必须是utf8
			int tag = proc.waitFor();// 等待进程终止
			//System.out.println("load: "+tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(String selectName) {
		// TODO Auto-generated method stub
		// System.out.println(sqlPath);
		if (selectName.substring(selectName.lastIndexOf(".") + 1).equals("sql")) {
			String backPath = JFinal.me().getServletContext().getRealPath("/")
					+ "/static/dbback/" + selectName;
			// 得到配置文件
			try {
				Runtime rt = Runtime.getRuntime();
				String createDb = "mysqladmin -u" + getUsername() + " -p"
						+ getPassword() + " create " + getDbname();
				String mysql = "mysql -u" + getUsername() + " -p"
						+ getPassword() + " " + getDbname() + " < \""
						+ backPath + "\"";
				System.out.println("load : " + mysql);
				rt.exec("cmd.exe /c " + createDb);
				Process proc = rt.exec("cmd.exe /c " + mysql);
				int tag = proc.waitFor();// 等待进程终止
				System.out.println("load: "+tag);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
