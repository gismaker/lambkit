package com.lambkit.test.db;

import com.lambkit.common.util.SecurityUtils;

public class DbPasswordEncode {

	public static void main(String[] args) {
		String[] keys = {
                "", "123456", "sdgkii3&#fs"
        };
        System.out.println("key | AESEncode | AESDecode");
        for (String key : keys) {
            System.out.print(key + " | ");
            /**
             * 这个main是数据库配置的名称，如lambkit.db.url，名称是main
             * 如果是lambkit.db。upms.url，名称是upms
             */
            String encryptString = SecurityUtils.encodePassword(key, "main");
            System.out.print(encryptString + " | ");
            String decryptString = SecurityUtils.decodePassword(encryptString, "main");
            System.out.println(decryptString);
        }
	}
}
