package com.lambkit.test.db;

import java.util.List;

import com.google.common.collect.Lists;
import com.lambkit.common.util.SecurityUtils;

public class DbPasswordEncode {

	private String password;
	/**
     * 这个salt是数据库配置的名称，如lambkit.db.url，名称是main
     * 如果是lambkit.db。upms.url，名称是upms
     */
	private String salt;
	
	public DbPasswordEncode(String password, String salt) {
		this.password = password;
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public static void main(String[] args) {
		List<DbPasswordEncode> pswdList = Lists.newArrayList();
		pswdList.add(new DbPasswordEncode("123456", "main"));
		pswdList.add(new DbPasswordEncode("y3Szd1R%zzO3RtkH", "main"));
		pswdList.add(new DbPasswordEncode("y3Szd1R%zzO3RtkH", "upms"));
		System.out.println("password[salt] | AESEncode | AESDecode");
		for (DbPasswordEncode pswd : pswdList) {
			System.out.print(pswd.getPassword() + "[" + pswd.getSalt() + "] | ");
			/**
             * 这个salt是数据库配置的名称，如lambkit.db.url，名称是main
             * 如果是lambkit.db。upms.url，名称是upms
             */
			String encryptString = SecurityUtils.encodePassword(pswd.getPassword(), pswd.getSalt());
			System.out.print(encryptString + " | ");
			String decryptString = SecurityUtils.decodePassword(encryptString, pswd.getSalt());
			System.out.println(decryptString);
		}
	}

}