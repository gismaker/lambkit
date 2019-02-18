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

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class RequestUtils {

	static String[] mobileAgents = { "iphone", "android", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
			"opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod", "nokia",
			"samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma", "docomo",
			"up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos", "techfaith",
			"palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem", "wellcom", "bunjalloo",
			"maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos", "pantech", "gionee", "portalmmm",
			"jig browser", "hiptop", "benq", "haier", "^lct", "320x320", "240x320", "176x220", "w3c ", "acs-", "alav",
			"alca", "amoi", "audi", "avan", "benq", "bird", "blac", "blaz", "brew", "cell", "cldc", "cmd-", "dang",
			"doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g",
			"lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-", "newt", "noki",
			"oper", "palm", "pana", "pant", "phil", "play", "port", "prox", "qwap", "sage", "sams", "sany", "sch-",
			"sec-", "send", "seri", "sgh-", "shar", "sie-", "siem", "smal", "smar", "sony", "sph-", "symb", "t-mo",
			"teli", "tim-", "tsm-", "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc",
			"winw", "winw", "xda", "xda-", "googlebot-mobile" };

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		return "XMLHttpRequest".equalsIgnoreCase(header);
	}

	public static boolean isMultipartRequest(HttpServletRequest request) {
		String contentType = request.getContentType();
		return contentType != null && contentType.toLowerCase().indexOf("multipart") != -1;
	}

	/**
	 * 是否是手机浏览器
	 * 
	 * @return
	 */
	public static boolean isMoblieBrowser(HttpServletRequest request) {
		String ua = request.getHeader("User-Agent");
		if (ua == null) {
			return false;
		}
		ua = ua.toLowerCase();
		for (String mobileAgent : mobileAgents) {
			if (ua.indexOf(mobileAgent) >= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否是微信浏览器
	 * 
	 * @return
	 */
	public static boolean isWechatBrowser(HttpServletRequest request) {
		String ua = request.getHeader("User-Agent");
		if (ua == null) {
			return false;
		}
		ua = ua.toLowerCase();
		if (ua.indexOf("micromessenger") > 0) {
			return true;
		}
		return false;
	}
	
	/**
     * 是否是PC版的微信浏览器
     *
     * @param request
     * @return
     */
    public static boolean isWechatPcBrowser(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        if (ua == null) {
            return false;
        }
        ua = ua.toLowerCase();
        if (ua.indexOf("windowswechat") > 0) {
            return true;
        }
        return false;
    }

	/**
	 * 是否是IE浏览器
	 * 
	 * @return
	 */
	public static boolean isIEBrowser(HttpServletRequest request) {
		String ua = request.getHeader("User-Agent");
		if (ua == null) {
			return false;
		}

		ua = ua.toLowerCase();
		if (ua.indexOf("msie") > 0) {
			return true;
		}

		if (ua.indexOf("gecko") > 0 && ua.indexOf("rv:11") > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取ip工具类，除了getRemoteAddr，其他ip均可伪造
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("Cdn-Src-Ip");    // 网宿cdn的真实ip
		if (isUnAvailableIp(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");// 蓝讯cdn的真实ip
		}
		if (isUnAvailableIp(ip)) {
			ip = request.getHeader("X-Forwarded-For");// 获取代理ip
		}
		if (isUnAvailableIp(ip)) {
			ip = request.getHeader("Proxy-Client-IP");// 获取代理ip
		}
		if (isUnAvailableIp(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");// 获取代理ip
		}
		if (isUnAvailableIp(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (isUnAvailableIp(ip)) {
			ip = request.getRemoteAddr();// 获取真实ip
		}
        if (ip != null && ip.contains(",")) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
	}
	
	private static boolean isUnAvailableIp(String ip) {
        return StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip);
    }

	public static String getUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}

	public static String getReferer(HttpServletRequest request) {
        return request.getHeader("Referer");
    }
	
	/**
	 * 移除request指定参数
	 * @param request
	 * @param paramName
	 * @return
	 */
	public String removeParam(HttpServletRequest request, String paramName) {
		String queryString = "";
		Enumeration keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			if (key.equals(paramName)) {
				continue;
			}
			if (queryString.equals("")) {
				queryString = key + "=" + request.getParameter(key);
			} else {
				queryString += "&" + key + "=" + request.getParameter(key);
			}
		}
		return queryString;
	}

	/**
	 * 获取请求basePath
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request) {
		StringBuffer basePath = new StringBuffer();
		String scheme = request.getScheme();
		String domain = request.getServerName();
		int port = request.getServerPort();
		basePath.append(scheme);
		basePath.append("://");
		basePath.append(domain);
		if("http".equalsIgnoreCase(scheme) && 80 != port) {
			basePath.append(":").append(String.valueOf(port));
		} else if("https".equalsIgnoreCase(scheme) && port != 443) {
			basePath.append(":").append(String.valueOf(port));
		}
		return basePath.toString();
	}

	/**
	 * 请求中参数转Map<String, String>,for支付宝异步回调,平时建议直接使用request.getParameterMap(),返回Map<String, String[]>
	 * @param request
	 * @return
	 */
	public static Map<String, String> getParameterMap(HttpServletRequest request) {
		Map<String, String> result = new HashMap<>();
		Enumeration parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = (String) parameterNames.nextElement();
			result.put(parameterName, request.getParameter(parameterName));
		}
		return result;
	}
}
