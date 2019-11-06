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
            String encryptString = SecurityUtils.encodePassword(key, "lambkit");
            System.out.print(encryptString + " | ");
            String decryptString = SecurityUtils.decodePassword(encryptString, "lambkit");
            System.out.println(decryptString);
        }
	}
}
