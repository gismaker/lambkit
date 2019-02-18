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
package com.lambkit.db.mgr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfinal.plugin.activerecord.DbKit;

public class ConnectionFactory {
	/**
	 * 取得数据库连接
	 * 
	 * @return
	 */
	public final static Connection getConnection() {
		try {
			return DbKit.getConfig().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public final static Connection getConnection(String groupname) {
		try {
			return DbKit.getConfig(groupname).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 取得数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection(IConnectionParam param) {
		if(param == null) return null;
		Connection conn = null;
		try {
			Class.forName(param.getDriver());
			conn = DriverManager.getConnection(param.getUrl(), param.getUsername(), param.getPassword());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 取得数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection(String driver, String url, String user, String pass) {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 * @param stam
	 * @param rs
	 */
	public final static void close(ResultSet rs, PreparedStatement stam, Connection conn) {
		try {
			if (conn != null) {
				DbKit.getConfig().close(conn);
			}
			if (stam != null) {
				stam.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param stam
	 * @param rs
	 */
	public final static void close(ResultSet rs, Statement st) {
		if (rs != null) {try {rs.close();} catch (SQLException e) {}}
		if (st != null) {try {st.close();} catch (SQLException e) {}}
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 * @param stam
	 * @param rs
	 */
	public final static void close(Connection conn) {
		if (conn != null) {
			DbKit.getConfig().close(conn);
		}
	}

	public static void main(String start[]) {
		System.out.println(ConnectionFactory.getConnection());
	}
}
