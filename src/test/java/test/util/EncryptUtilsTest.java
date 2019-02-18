package test.util;

import com.lambkit.common.util.EncryptUtils;

public class EncryptUtilsTest {
	public static void main(String[] args) {
		//md5加密测试
		String md5_1 = EncryptUtils.md5("000000");
		String md5_2 = EncryptUtils.md5("abc");
		System.out.println(md5_1 + "\n" + md5_2);
		System.out.println("md5 length: " + md5_1.length());
		//sha加密测试
		String sha_1 = EncryptUtils.sha("000000");
		String sha_2 = EncryptUtils.sha("abc");
		System.out.println(sha_1 + "\n" + sha_2);
		System.out.println("sha length: " + sha_1.length());
		
		System.out.println(EncryptUtils.md5WithPrefix("123456"));
	}
}
