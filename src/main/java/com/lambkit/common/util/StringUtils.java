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
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.core.JFinal;
import com.jfinal.log.Log;

public final class StringUtils {

	private static final Log log = Log.getLog(StringUtils.class);

	// ---------------------------------
	// 字符串null、空的判断
	// ---------------------------------
	/**
	 * 判断str是否是实际内容，纯空格组成的str返回false Check whether the given String has actual
	 * text. More specifically, returns <code>true</code> if the string not
	 * <code>null<code>,
	 * its length is greater than 0, and it contains at least one non-whitespace character.
	 * <p><pre>
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * </pre>
	 * 
	 * &#64;param str
	 *            the String to check (may be <code>null</code>)
	 * 
	 * @return <code>true</code> if the String is not <code>null</code>, its
	 *         length is greater than 0, and is does not contain whitespace only
	 * @see java.lang.Character#isWhitespace
	 */
	public static boolean hasText(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断字符串是否有长度 Check that the given String is neither <code>null</code> nor
	 * of length 0. Note: Will return <code>true</code> for a String that purely
	 * consists of whitespace.
	 * <p>
	 * 
	 * <pre>
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * 
	 * @param str
	 *            the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not null and has length
	 * @see #hasText(String)
	 */
	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * 检查字符串是否是空白： <code>null</code> 、空字符串 <code>""</code> 或只有空白字符。
	 * 
	 * <pre>
	 * 
	 *    StringUtil.isBlank(null)      = true 
	 *    StringUtil.isBlank(&quot;&quot;)        = true 
	 *    StringUtil.isBlank(&quot; &quot;)       = true 
	 *    StringUtil.isBlank(&quot;bob&quot;)     = false 
	 *    StringUtil.isBlank(&quot;  bob  &quot;) = false
	 * 
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果为空白, 则返回 <code>true</code>
	 */
	public static boolean isBlank(String str) {
		int length;
		if ((str == null) || ((length = str.length()) == 0)) {
			return true;
		}
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isBlank(StringBuffer strbuf) {
		int length;
		if ((strbuf == null) || ((length = strbuf.length()) == 0)) {
			return true;
		}
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(strbuf.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查字符串是否不是空白： <code>null</code> 、空字符串 <code>""</code> 或只有空白字符。
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果不是空白, 则返回 <code>true</code>
	 * 
	 * @see com.nonfamous.commom.util.StringUtils#isBlank(String)(String)
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static boolean areNotEmpty(String... strings) {
		if (strings == null || strings.length == 0)
			return false;

		for (String string : strings) {
			if (string == null || "".equals(string)) {
				return false;
			}
		}
		return true;
	}

	public static boolean notBlank(String str) {
		return !isBlank(str);
	}

	public static boolean notBlank(StringBuffer strbuf) {
		return !isBlank(strbuf);
	}

	/**
	 * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null)
			return true;

		if (obj instanceof CharSequence)
			return ((CharSequence) obj).length() == 0;

		if (obj instanceof Collection)
			return ((Collection) obj).isEmpty();

		if (obj instanceof Map)
			return ((Map) obj).isEmpty();

		if (obj instanceof Object[]) {
			Object[] object = (Object[]) obj;
			if (object.length == 0) {
				return true;
			}
			boolean empty = true;
			for (int i = 0; i < object.length; i++) {
				if (!isNullOrEmpty(object[i])) {
					empty = false;
					break;
				}
			}
			return empty;
		}
		return false;
	}

	// ---------------------------------
	// 字符串操作
	// ---------------------------------

	/**
	 * 去除"_"符号，并分割开的各字符首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String fromUnderline(String str) {
		if (str == null || (str.length()) == 0) {
			return str;
		}
		String res = "";
		String[] strs = str.split("_");
		for (String s : strs) {
			res += getUppercaseChar(s);
		}
		return res;
	}

	/**
	 * 把第一个字母变为大写<br>
	 * 如：<br>
	 * <code>str = "userDao";</code><br>
	 * <code>return "UserDao";</code>
	 * 
	 * @param str
	 * @return
	 */
	public static String getUppercaseChar(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuffer(strLen).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1))
				.toString();
		// return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 把第一个字母变为小写<br>
	 * 如：<br>
	 * <code>str = "UserDao";</code><br>
	 * <code>return "userDao";</code>
	 * 
	 * @param str
	 * @return
	 */
	public static String getLowercaseChar(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuffer(strLen).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1))
				.toString();
		// return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	/**
	 * 获取路径的最后面字符串<br>
	 * 如：<br>
	 * <code>str = "com.app.base.bean.User"</code><br>
	 * <code> return "User";<code>
	 * 
	 * @param str
	 * @return
	 */
	public static String getLastChar(String str) {
		if ((str != null) && (str.length() > 0)) {
			int dot = str.lastIndexOf('.');
			if ((dot > -1) && (dot < (str.length() - 1))) {
				return str.substring(dot + 1);
			}
		}
		return str;
	}

	/**
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {
		StringBuffer unicode = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			// 取出每一个字符
			char c = string.charAt(i);
			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}
		return unicode.toString();
	}

	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {
		StringBuffer string = new StringBuffer();
		String[] hex = unicode.split("\\\\u");
		for (int i = 1; i < hex.length; i++) {
			// 转换出每一个代码点
			int data = Integer.parseInt(hex[i], 16);
			// 追加成string
			string.append((char) data);
		}
		return string.toString();
	}

	/**
	 * @Desc:将阿拉伯数字转换为大写字中文汉字
	 * @param a
	 * @return
	 */
	public static String translateNumToChinese(int a) {
		String[] units = { "", "十", "百", "千", "万", "十", "百", "千", "亿" };
		String[] nums = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
		String result = "";
		if (a < 0) {
			result = "负";
			a = Math.abs(a);
		}
		String t = String.valueOf(a);
		for (int i = t.length() - 1; i >= 0; i--) {
			int r = (int) (a / Math.pow(10, i));
			if (r % 10 != 0) {
				String s = String.valueOf(r);
				String l = s.substring(s.length() - 1, s.length());
				result += nums[Integer.parseInt(l) - 1];
				result += (units[i]);
			} else {
				if (!result.endsWith("零")) {
					result += "零";
				}
			}
		}
		return result;
	}

	/**
	 * @Desc:将对象转换为字符串，如果为空则返回null
	 * @param str
	 * @return
	 */
	public static String convertStr(Object str) {
		if (str == null || str.toString().equals(""))
			return null;
		return str.toString().trim();
	}

	public static String toStr(Object obj) {
		if (obj == null || obj.toString().equals("") || "null".equals(obj))
			return "";
		return obj.toString().trim();
	}

	public static String toHtmlStr(Object obj) {
		if (obj == null || obj.toString().equals("") || "null".equals(obj))
			return "&nbsp;";
		return obj.toString().trim();
	}

	/**
	 * @Desc:将对象转换为Integer数据，如果为空则返回null
	 * @param str
	 * @return
	 */
	public static Integer convertInt(Object str) {
		if (str == null || "".equals(str.toString()))
			return null;
		return Integer.valueOf(str.toString());
	}

	/**
	 * @Desc:将对象转换为Long型数据，如果为空则返回null
	 * @param str
	 * @return
	 */
	public static Long convertLong(Object str) {
		if (str == null || "".equals(str.toString()))
			return null;
		return Long.valueOf(str.toString());
	}

	/**
	 * @Desc:将对象转换为Double型数据，如果为空则返回null
	 * @param obj
	 * @return
	 */
	public static Double convertDouble(Object obj) {
		if (obj == null || "".equals(obj.toString()))
			return null;
		return Double.valueOf(obj.toString());
	}

	/**
	 * @Desc:将对象转换为BigDecimal型数据，如果为空则返回null
	 * @param obj
	 * @return
	 */
	public static BigDecimal convertBigDecimal(Object obj) {
		if (obj == null || "".equals(obj.toString()))
			return StringUtils.convertBigDecimal("0");
		return BigDecimal.valueOf(Double.valueOf(obj.toString()));
	}

	/**
	 * @Desc:将
	 * @param src
	 * @param separator
	 * @param quot
	 * @param defaultValue
	 * @return
	 */
	public static String join(Object[] src, String separator, String quot, String defaultValue) {
		StringBuffer sb = new StringBuffer();
		if (src == null || src.length == 0) {
			return defaultValue;
		} else {
			for (int i = 0; i < src.length; i++) {
				if (sb.length() > 0) {
					sb.append(separator);
				}
				if (quot != null) {
					sb.append(quot);
				}
				sb.append(src[i]);
				if (quot != null) {
					sb.append(quot);
				}
			}
			return sb.toString();
		}
	}

	public static String join(List<String> list, String separator, String quot, String defaultValue) {
		StringBuffer sb = new StringBuffer();
		if (list == null || list.size() == 0) {
			return defaultValue;
		} else {
			for (String value : list) {
				if (sb.length() > 0 && separator != null) {
					sb.append(separator);
				}
				if (quot != null) {
					sb.append(quot);
				}
				sb.append(value);
				if (quot != null) {
					sb.append(quot);
				}
			}
			return sb.toString();
		}
	}

	/**
	 * @Desc:将数字转换成制定格式的字符串，如： @format("￥#,##0.00元",
	 *                         66778899),format("yyyy-MM-dd",new Date())
	 * @param pattern
	 * @param value
	 * @return
	 */
	public static String format(String pattern, Object value) {
		if (value == null) {
			return "0.00";
		} else if (value instanceof Number) {
			return new DecimalFormat(pattern).format(value);
		} else if (value instanceof Date) {
			return new SimpleDateFormat(pattern).format(value);
		} else if (value instanceof Calendar) {
			return new SimpleDateFormat(pattern).format(((Calendar) value).getTime());
		} else {
			return value.toString();
		}
	}

	/**
	 * @Desc:截取字符串，如果字符串长度小于要求的最大长度直接返回，否则截取相应的长度
	 * @Desc:一个汉字和和字符都按一个长度来计算
	 * @param str
	 * @param maxLength
	 * @return
	 */
	public static String subMaxStr(String str, int maxLength) {
		String result = null;
		if (str != null) {
			if (str.length() <= maxLength) {
				result = str;
			} else {
				result = str.substring(0, maxLength);
			}
		}
		return result;
	}

	/**
	 * @Desc:截取字符串，如果字符串长度小于要求的最大长度直接返回，否则截取相应的长度,剩余的又......代替，或者由flag里面的表示
	 * @param str
	 * @param maxLength
	 * @param flag
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String subMaxStrToEtc(String str, int maxLength, String flag) {
		String result = null;
		if (str != null) {
			if (strlen(str) <= maxLength) {
				result = str;
			} else {
				result = StringUtils.subString(str, maxLength) + flag;
			}
		} else {
			str = "";
		}
		return result;
	}

	/**
	 * @Desc:去除字符串中的回车符
	 * @param str
	 * @return
	 */
	public static String removeNewline(String str) {
		String result = null;
		if (str != null) {
			result = str.replaceAll("\\r\\n", "");
			result = result.replaceAll(new String(new char[] { 10 }), "");
		}
		return result;
	}

	/**
	 * @Desc:将字符串数组转换为数值字符串
	 * @param values
	 * @return
	 */
	public static Integer[] convertStringArray2IntegerArray(String[] values) {
		List<Integer> list = new ArrayList<Integer>();
		if (values != null) {
			for (String value : values) {
				list.add(Integer.valueOf(value));
			}
			return list.toArray(new Integer[] {});
		} else {
			return null;
		}
	}

	/**
	 * @Desc:将数字转换为汉文字符
	 * @param strNum
	 * @return
	 */
	public static String number2Cn(String strNum) {
		String result = null;
		if (strNum != null && strNum.length() > 0) {
			result = strNum;
			result = result.replaceAll("0", "零");
			result = result.replaceAll("1", "一");
			result = result.replaceAll("2", "二");
			result = result.replaceAll("3", "三");
			result = result.replaceAll("4", "四");
			result = result.replaceAll("5", "五");
			result = result.replaceAll("6", "六");
			result = result.replaceAll("7", "七");
			result = result.replaceAll("8", "八");
			result = result.replaceAll("9", "九");
		}
		return result;
	}

	/**
	 * @Desc:将List转换为split分隔的字符串，默认以","进行分隔
	 * @param list
	 * @param split
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	public static String listToString(List list, String split) {
		if (list == null || list.size() == 0) {
			return "";
		} else {
			String str = "";
			for (int i = 0; i < list.size(); i++) {
				if (split == null || split.length() < 1) {
					str = str + list.get(i) + ",";
				} else {
					str = str + list.get(i) + split;
				}
			}
			return str;
		}
	}

	/**
	 * @Desc:将字符串str转换成以split为分隔符的List数组
	 * @param str
	 * @param split
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List strToList(String str, String split) {
		if (str == null || str.equals("")) {
			return new ArrayList();
		} else {
			StringTokenizer st = new StringTokenizer(str, split);
			List re = new ArrayList();
			while (st.hasMoreElements()) {
				String s = (String) st.nextElement();
				if (s == null || s.equals("")) {
					continue;
				} else {
					re.add(s);
				}
			}

			return re;
		}

	}

	public static String replaceSQL(String sql) {
		sql = sql.replaceAll("'", "''");
		sql = sql.replaceAll("/", "//");
		return sql;
	}

	/**
	 * @Desc SQL语句效验，防止SQL语句字符注入
	 * @param str
	 * @return
	 */
	public static boolean validateSQL(String str) {
		str = str.toLowerCase();// 统一转为小写
		String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|"
				+ "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|"
				+ "table|from|grant|use|group_concat|column_name|"
				+ "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|"
				+ "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";// 过滤掉的sql关键字，可以手动添加
		String[] badStrs = badStr.split("\\|");
		for (int i = 0; i < badStrs.length; i++) {
			if (str.indexOf(badStrs[i]) >= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @Desc SQL语句效验，防止SQL语句字符注入
	 * @param sSql
	 * @return 0 - 没有注入, 1 - 有注入
	 */
	public static int filterSQL(String sSql) {
		int srcLen, decLen = 0;
		sSql = sSql.toLowerCase().trim();
		srcLen = sSql.length();
		sSql = sSql.replace("exec", "");
		sSql = sSql.replace("delete", "");
		sSql = sSql.replace("master", "");
		sSql = sSql.replace("truncate", "");
		sSql = sSql.replace("declare", "");
		sSql = sSql.replace("create", "");
		sSql = sSql.replace("xp_", "no");
		decLen = sSql.length();
		if (srcLen == decLen)
			return 0;
		else
			return 1;
	}

	/**
	 * @Desc 防止sql注入
	 * @param sql
	 * @return
	 */
	public static String transactSQLInjection(String sql) {
		return sql.replaceAll(".*([';]+|(--)+).*", " ");
	}

	/**
	 * @Desc 过滤SQL语句，防止SQL语句字符注入
	 * @param inStr
	 * @return
	 */
	public static String doneSQL(String inStr) {
		StringBuffer sb = new StringBuffer("");
		char[] chStr = inStr.toCharArray();
		for (int j = 0; j < chStr.length; j++) {
			if (chStr[j] == '_' || chStr[j] == '%' || chStr[j] == '\\') {
				sb.append("\\");
			}
			sb.append(chStr[j]);
		}
		return sb.toString();
	}

	public static int strlen(String str) {
		if (str == null || str.length() <= 0) {
			return 0;
		}
		int len = 0;
		char c;
		for (int i = str.length() - 1; i >= 0; i--) {
			c = str.charAt(i);
			if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				// 字母, 数字
				len++;
			} else {
				if (Character.isLetter(c)) { // 中文
					len += 2;
				} else { // 符号或控制字符
					len++;
				}
			}
		}
		return len;
	}

	/**
	 * @Desc:截取字符串，中文两个、英文一个
	 * @param str
	 * @param length
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String subString(String str, int length) {
		String str_rtn = "";
		if (length > 0) {
			// 将字符串按照"gbk"编码形式解码为字节序列，并保存在数组中
			// 当汉字采用GBK编码时占两个字节，而采用UTF-8编码时占3个字节，并且都为负整数

			try {
				byte[] bt = str.getBytes("gbk");
				if (length <= bt.length) {
					// 判断在要截取长度的数组中有多少个负数
					int count = 0;
					for (int i = 0; i < length; i++) {
						if (bt[i] < 0)
							count++;
					}
					if (count % 2 == 0) {
						// 如果刚好被2整除，说明截取不会出现乱码
						str_rtn = new String(bt, 0, length, "gbk");
					} else {
						// 不能整除则会出现乱码，则需要把最后一个字节去掉
						str_rtn = new String(bt, 0, --length, "gbk");
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str_rtn;
	}

	/**
	 * @Desc:序列化生成100010001000格式数据
	 * @param isAdd是否需要重新追加长度为len以1000开头的编码
	 * @param oldcode传入的编码
	 * @param len长度
	 * @return
	 */
	public static String getStrSeqCode(boolean isAdd, String oldcode, int len) {
		String str_seq = "";
		// 如果
		if (isAdd == true) {
			if (oldcode == null || oldcode.length() < 1) {
				str_seq = "1";
				for (int i = 0; i < len - 1; i++) {
					str_seq += "0";
				}
			} else {
				str_seq = oldcode + "1";
				for (int i = 0; i < len - 1; i++) {
					str_seq += "0";
				}
			}
		} else {
			if (oldcode != null && oldcode.length() > 0) {
				if (oldcode.length() < len) {
					System.out.println("getStrSeqCode()方法中oldcode长度不能小于给定的len长度！");
				} else if (oldcode.length() == len) {
					str_seq = (Long.parseLong(oldcode) + 1) + "";
				} else if (oldcode.length() > len) {
					String str_left = oldcode.substring(0, oldcode.length() - len);
					String str_right = oldcode.substring(oldcode.length() - len, oldcode.length());
					str_right = String.valueOf(Long.parseLong(str_right) + 1);
					if (str_right.length() < len) {
						String str_temp = "";
						for (int i = 0; i < len - str_right.length(); i++) {
							str_temp += "0";
						}
						str_right = str_temp + str_right;
					}
					str_seq = str_left + str_right;
				}
			} else {
				System.out.println("getStrSeqCode()方法中oldcode为空！");
			}
		}
		return str_seq;
	}

	public static String decode(String ss, String enc) {
		try {
			return java.net.URLDecoder.decode(ss, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getFirstCat(String s) {
		String[] sb = s.split(" ");
		StringBuffer ss = new StringBuffer("");
		for (int i = 0; i < sb.length; i++) {
			sb[i] = sb[i].substring(0, 1).toUpperCase() + sb[i].substring(1);
		}
		for (int i = 0; i < sb.length; i++) {
			ss.append(sb[i]);
			ss.append(" ");
		}
		return ss.toString();
	}

	public static String getClassName(String s) {
		s = s.replace("_", " ");
		s = getFirstCat(s.toLowerCase());
		s = s.replace(" ", "");
		return s;
	}
	
	/**
	 * @Desc:获取32位UUID编码
	 * @return
	 */
	public static String getRandString32() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "").replace("{", "").replace("}", "");
	}

	/**
	 * @Desc:获取32位中前16位UUID编码
	 * @return
	 */
	public static String getRandString16() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "").replace("{", "").replace("}", "").substring(0, 16);
	}

	public static String getUuid32() {
		String uuid = java.util.UUID.randomUUID().toString();
		return uuid.replace("-", "").toLowerCase();
	}

	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExt(String filename) {
		int index = filename.lastIndexOf(".");
		String ext = filename.substring(index).toLowerCase();
		return ext;
	}

	public static String getRemoteLoginUserIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取随机数字
	 * 
	 * @return
	 */
	public static String getRandomDec() {
		Random ran = new Random();
		DecimalFormat df = new DecimalFormat(".00");
		double j = 0;
		do {
			j = ran.nextInt(50) / (100.0);
		} while (j < 0.2);
		System.out.println(df.format(j));
		return df.format(j);
	}

	/**
	 * 计算地球上任意两点(经纬度)距离
	 * 
	 * @param long1
	 *            第一点经度
	 * @param lat1
	 *            第一点纬度
	 * @param long2
	 *            第二点经度
	 * @param lat2
	 *            第二点纬度
	 * @return 返回距离 单位：米
	 */
	public static String Distance(double long1, double lat1, double long2, double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		DecimalFormat df = new DecimalFormat("######0.00");
		return df.format(d);
	}

	/**
	 * 获取缩略图名称
	 * 
	 * @param filename
	 * @return
	 */
	public static String getThumbnail(String filename) {
		String newfile = "";
		if (StringUtils.toStr(filename).length() > 0) {
			newfile = filename.substring(0, filename.indexOf(".")) + "-thumbnail"
					+ filename.substring(filename.indexOf("."));
		}
		return newfile;
	}

	/**
	 * 去除字符串中的空格、回车、换行符、制表符 注：\n 回车(\u000a) \t 水平制表符(\u0009) \s 空格(\u0008) \r
	 * 换行(\u000d)
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static boolean match(String string, String regex) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}

	/**
	 * 判断是否为整数
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 匹配是否为数字
	 * 
	 * @param str
	 *            可能为中文，也可能是-19162431.1254，不使用BigDecimal的话，变成-1.91624311254E7
	 * @return
	 * @author https://blog.csdn.net/u013066244/article/details/53197756
	 */
	public static boolean isNumeric(String str) {
		// 该正则表达式可以匹配所有的数字 包括负数
		Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
		String bigStr;
		try {
			bigStr = new BigDecimal(str).toString();
		} catch (Exception e) {
			return false;// 异常 说明包含非数字。
		}

		Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isEmail(String email) {
		return Pattern.matches("\\w+@(\\w+.)+[a-z]{2,3}", email);
	}

	public static boolean isMobileNumber(String phoneNumber) {
		return Pattern.matches("^(1[3,4,5,7,8])\\d{9}$", phoneNumber);
	}

	/**
	 * 判断字符串是否为日期格式
	 * @param strDate
	 * @return
	 */
	public static boolean isDate(String strDate) {
		Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468]048)|([13579]26))[\\-\\/\\s]?((((0?[13578])|(102))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(301)))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(102))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(301)))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9]))?)))?$");
        Matcher m = pattern.matcher(strDate);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * html转义
	 * 
	 * @param text
	 * @return
	 */
	public static String escapeHtml(String text) {
		if (isBlank(text))
			return text;

		return text.replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#x27;")
				.replace("/", "&#x2F;");
	}

	/**
	 * 生成流水号
	 *
	 * @param uuid
	 *            谋订单的主键ID
	 * @return
	 */
	public static String generateSerialNumber(String uuid) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + Math.abs(uuid.hashCode());
	}

	/**
	 * 去除特殊字符
	 * 
	 * @param string
	 * @return
	 */
	public static String clearSpecialCharacter(String string) {
		if (isBlank(string)) {
			return string;
		}

		/**
		 * P：标点字符； L：字母； M：标记符号（一般不会单独出现）； Z：分隔符（比如空格、换行等）； S：符号（比如数学符号、货币符号等）；
		 * N：数字（比如阿拉伯数字、罗马数字等）； C：其他字符
		 */
		// return string.replaceAll("[\\pP\\pZ\\pM\\pC]", "");
		return string.replaceAll("[\\\\\'\"\\/\f\n\r\t]", "");
	}

	/**
	 * 生成验证码
	 */
	public static String getValidateCode() {

		Random random = new Random();
		return String.valueOf(random.nextInt(9999 - 1000 + 1) + 1000);// 为变量赋随机值1000-9999
	}

	/**
	 * 字符串分割
	 * 
	 * @param src
	 * @param regex
	 * @return
	 */
	public static Set<String> splitToSet(String src, String regex) {
		if (src == null) {
			return null;
		}

		String[] strings = src.split(regex);
		Set<String> set = new HashSet<>();
		for (String table : strings) {
			if (StringUtils.isBlank(table)) {
				continue;
			}
			set.add(table.trim());
		}
		return set;
	}

	/**
	 * url解码
	 * 
	 * @param string
	 * @return
	 */
	public static String urlDecode(String string) {
		try {
			return URLDecoder.decode(string, JFinal.me().getConstants().getEncoding());
		} catch (UnsupportedEncodingException e) {
			log.error("urlDecode is error", e);
		}
		return string;
	}

	/**
	 * url编码
	 * 
	 * @param string
	 * @return
	 */
	public static String urlEncode(String string) {
		try {
			return URLEncoder.encode(string, JFinal.me().getConstants().getEncoding());
		} catch (UnsupportedEncodingException e) {
			log.error("urlEncode is error", e);
		}
		return string;
	}

	/**
	 * 
	 * @param redirect
	 * @return
	 */
	public static String urlRedirect(String redirect) {
		try {
			redirect = new String(redirect.getBytes(JFinal.me().getConstants().getEncoding()), "ISO8859_1");
		} catch (UnsupportedEncodingException e) {
			log.error("urlRedirect is error", e);
		}
		return redirect;
	}

	/**
	 * 是否符合正则匹配
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public static boolean isMatch(String s, String p) {
		int idxs = 0, idxp = 0, idxstar = -1, idxmatch = 0;
		while (idxs < s.length()) {
			// 当两个指针指向完全相同的字符时，或者p中遇到的是?时
			if (idxp < p.length() && (s.charAt(idxs) == p.charAt(idxp) || p.charAt(idxp) == '?')) {
				idxp++;
				idxs++;
				// 如果字符不同也没有?，但在p中遇到是*时，我们记录下*的位置，但不改变s的指针
			} else if (idxp < p.length() && p.charAt(idxp) == '*') {
				idxstar = idxp;
				idxp++;
				// 遇到*后，我们用idxmatch来记录*匹配到的s字符串的位置，和不用*匹配到的s字符串位置相区分
				idxmatch = idxs;
				// 如果字符不同也没有?，p指向的也不是*，但之前已经遇到*的话，我们可以从idxmatch继续匹配任意字符
			} else if (idxstar != -1) {
				// 用上一个*来匹配，那我们p的指针也应该退回至上一个*的后面
				idxp = idxstar + 1;
				// 用*匹配到的位置递增
				idxmatch++;
				// s的指针退回至用*匹配到位置
				idxs = idxmatch;
			} else {
				return false;
			}
		}
		// 因为1个*能匹配无限序列，如果p末尾有多个*，我们都要跳过
		while (idxp < p.length() && p.charAt(idxp) == '*') {
			idxp++;
		}
		// 如果p匹配完了，说明匹配成功
		return idxp == p.length();
	}
	
	private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        str = sb.toString();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }

	/**
     * 驼峰转下划线,效率比上面高
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
