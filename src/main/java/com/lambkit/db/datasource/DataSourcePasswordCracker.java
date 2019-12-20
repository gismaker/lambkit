package com.lambkit.db.datasource;

import com.jfinal.kit.StrKit;
import com.lambkit.common.LambkitPasswordCracker;
import com.lambkit.common.util.SecurityUtils;

public class DataSourcePasswordCracker implements LambkitPasswordCracker {

	@Override
	public String encode(String password, String salt) {
		// TODO Auto-generated method stub
		if(password==null)
			return password;
		if(StrKit.isBlank(salt)) {
			return SecurityUtils.encodePassword(password);
		} else {
			return SecurityUtils.encodePassword(password, salt);
		}
	}

	@Override
	public String decode(String password, String salt) {
		// TODO Auto-generated method stub
		if(StrKit.isBlank(password) || password.length() < 24)
			return password;
		if(StrKit.isBlank(salt)) {
			return SecurityUtils.decodePassword(password);
		} else {
			return SecurityUtils.decodePassword(password, salt);
		}
	}
}
