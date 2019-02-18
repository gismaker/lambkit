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
package com.lambkit.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5和sha-1加密类
 */
public class EncryptUtils {
	
	private static String DB_PREFIX = "lk_";
	
	public final static void setDbPrefix(String dbprefix) {
		DB_PREFIX = dbprefix;
	}
	
	/**
	 * md5 + 前缀加密
	 * @param intxt
	 * @return
	 */
	public final static String md5WithPrefix(String intxt) {
		return md5WithPrefix(intxt, DB_PREFIX);
	}
	
	/**
	 * md5 + 前缀加密
	 * @param intxt : 输入
	 * @param prefix : 前缀
	 * @return
	 */
	public final static String md5WithPrefix(String intxt, String prefix) {
		prefix = md5(prefix);
		intxt =  md5(intxt);
		intxt = prefix.substring(0, 12) + intxt;
		intxt += prefix.substring(prefix.length()-4,prefix.length());
		return intxt;
	}
	
	/**
	 * sha  + 前缀加密
	 * @param intxt
	 * @return
	 */
	public final static String shaWithPrefix(String intxt) {
		return shaWithPrefix(intxt, DB_PREFIX);
	}
	/**
	 * sha  + 前缀加密
	 * @param intxt : 输入
	 * @param prefix : 前缀
	 * @return
	 */
	public final static String shaWithPrefix(String intxt,  String prefix) {
		prefix = sha(prefix);
		intxt =  sha(intxt);
		intxt = prefix.substring(0, 12) + intxt;
		intxt += prefix.substring(prefix.length()-4,prefix.length());
		return intxt;
	}
	
	/**
	 * 转换MD5加密
	 * @param plainText
	 * @param isbit16
	 * @return
	 */
	public static String md5(String plainText, boolean isbit16) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			//System.out.println("result: " + buf.toString());// 32位的加密
			//System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
			result = isbit16 ? buf.toString().substring(8, 24) : buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * MD5加密
	 * @param content
	 * @return 字母大写
	 */
	public static String MD5(String content) {
        //用于加密的字符
        char md5String[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = content.getBytes();

            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }

            //返回经过加密后的字符串
            return new String(str);

        } catch (Exception e) {
            return null;
        }
    }

	/**
	 *  md5加密
	 * @param inputText
	 * @return 字母小写
	 */
	public static String md5(String inputText) {
		return encrypt(inputText, "md5");
	}
	
	/**
	 *  sha加密
	 * @param inputText
	 * @return
	 */
	public static String sha(String inputText) {
		return encrypt(inputText, "sha-1");
	}

	/**
	 * md5或者sha-1加密
	 * 
	 * @param inputText
	 *            要加密的内容
	 * @param algorithmName
	 *            加密算法名称：md5或者sha-1，不区分大小写
	 * @return
	 */
	private static String encrypt(String inputText, String algorithmName) {
		if (inputText == null || "".equals(inputText.trim())) {
			throw new IllegalArgumentException("请输入要加密的内容");
		}
		if (algorithmName == null || "".equals(algorithmName.trim())) {
			algorithmName = "md5";
		}
		String encryptText = null;
		try {
			MessageDigest m = MessageDigest.getInstance(algorithmName);
			m.update(inputText.getBytes("UTF8"));
			byte s[] = m.digest();
			// m.digest(inputText.getBytes("UTF8"));
			return hex(s);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encryptText;
	}

	/**
	 * 返回十六进制字符串
	 * @param arr
	 * @return
	 */
	private static String hex(byte[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
					3));
		}
		return sb.toString();
	}
}
