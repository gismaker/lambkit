package test.util;

import com.lambkit.common.util.StringUtils;

public class StringUtilsTest {
	public static void main(String[] args) {
		/*
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
		*/
		
		String sqlExceptSelect = " from ttt where 1=1 and t=?";
		sqlExceptSelect = sqlExceptSelect.substring(sqlExceptSelect.indexOf("where"));
		System.out.println(sqlExceptSelect);
	}
}
