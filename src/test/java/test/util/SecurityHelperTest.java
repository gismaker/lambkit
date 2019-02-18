package test.util;

import com.lambkit.common.util.SecurityHelper;

public class SecurityHelperTest {
	public static void main(String[] args) {
		String encryptTxt = "";
		String plainTxt = "美国";
		try {
			System.out.println(plainTxt);
			encryptTxt = SecurityHelper.encrypt("lambkit", plainTxt);
			plainTxt = SecurityHelper.decrypt("lambkit", encryptTxt);
			System.out.println(encryptTxt);
			System.out.println(plainTxt);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
