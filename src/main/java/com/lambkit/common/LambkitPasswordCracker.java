package com.lambkit.common;


public interface LambkitPasswordCracker {

	/**
	 * 加密
	 * @param password
	 * @return
	 */
	String encode(String password, String salt);
	
	/**
	 * 解密
	 * @param password
	 * @return
	 */
	String decode(String password, String salt);
	
}
