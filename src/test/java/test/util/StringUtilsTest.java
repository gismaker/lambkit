package test.util;

import com.lambkit.common.util.StringUtils;

public class StringUtilsTest {
	public static void main(String[] args) {
		System.out.println(StringUtils.getRandString32());
		
		String str = "120112  			津南区";
		System.out.println(StringUtils.replaceBlank(str));
		System.out.println(str.replaceAll("\t", ""));
		
		String[] ids = {"1","2","3"};
		String idlist = "";
		for (String stri : ids) {
			if(StringUtils.hasText(stri)) idlist += stri + ",";
		}
		idlist = idlist.substring(0, idlist.length()-1);
		System.out.println(idlist);
		
		if(StringUtils.isNumeric("15047584406")) {
			System.out.println("15047584406是数字");
		}
		
		String tb = "data_table";
		System.out.println(tb.split("_")[0]+"_");
		
		if(StringUtils.isDate("2017-08-01 12:01")) {
			System.out.println("2017-08-01 12:01是日期");
		}
		
	}
}
