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

import java.util.Collection;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import com.jfinal.plugin.activerecord.Model;

public class CommonUtils {
	
	public static String generateCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(9999 - 1000 + 1) + 1000);
    }

    public static void quietlyClose(AutoCloseable... autoCloseables) {
        for (AutoCloseable closeable : autoCloseables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    // do nothing
                }
            }
        }
    }
    
    public static String maxLength(String content, int maxLength) {
        if (StringUtils.isBlank(content)) {
            return content;
        }

        if (maxLength <= 0) {
            throw new IllegalArgumentException("maxLength 必须大于 0 ");
        }

        return content.length() <= maxLength ? content :
                content.substring(0, maxLength);

    }


    public static String maxLength(String content, int maxLength, String suffix) {
        if (StringUtils.isBlank(suffix)) {
            return maxLength(content, maxLength);
        }

        if (StringUtils.isBlank(content)) {
            return content;
        }

        if (maxLength <= 0) {
            throw new IllegalArgumentException("maxLength 必须大于 0 ");
        }

        return content.length() <= maxLength ? content :
                content.substring(0, maxLength) + suffix;

    }

    public static String removeSuffix(String url) {

        int indexOf = url.indexOf(".");

        if (indexOf == -1) {
            return url;
        }

        return url.substring(0, indexOf);
    }

    /**
     * 防止 model 存储关于 xss 相关代码
     *
     * @param model
     */
    public static void escapeHtmlForAllAttrs(Model model, String... ignoreAttrs) {
        String[] attrNames = model._getAttrNames();
        for (String attr : attrNames) {

            if (ArrayUtils.contains(ignoreAttrs, attr)) {
                continue;
            }

            Object value = model.get(attr);

            if (value != null && value instanceof String) {
                model.set(attr, StringUtils.escapeHtml(value.toString()));
            }
        }
    }
	
	public static boolean notNullorEmpty(Object obj) {
		return !isNullOrEmpty(obj);
	}

	/**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     * 
     * @param obj
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String && (obj.equals(""))) {
            return true;
//        } else if (obj instanceof Short && ((Short) obj).shortValue() == 0) {
//            return true;
//        } else if (obj instanceof Integer && ((Integer) obj).intValue() == 0) {
//            return true;
//        } else if (obj instanceof Double && ((Double) obj).doubleValue() == 0) {
//            return true;
//        } else if (obj instanceof Float && ((Float) obj).floatValue() == 0) {
//            return true;
//        } else if (obj instanceof Long && ((Long) obj).longValue() == 0) {
//            return true;
        } else if (obj instanceof Boolean && !((Boolean) obj)) {
            return true;
        } else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        } else if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        } else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
            return true;
        }
        return false;
    }
    /*
	public static <T> T[] concat(T[] first, T[] second) {
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}
	*/
}
