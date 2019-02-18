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

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import com.jfinal.kit.StrKit;

public class CommonUtils {
	
	public static String maxLength(String content, int maxLength) {
        if (StrKit.isBlank(content)) {
            return content;
        }

        if (maxLength <= 0) {
            throw new IllegalArgumentException("maxLength 必须大于 0 ");
        }

        return content.length() <= maxLength ? content :
                content.substring(0, maxLength);

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
        } else if (obj instanceof Short && ((Short) obj).shortValue() == 0) {
            return true;
        } else if (obj instanceof Integer && ((Integer) obj).intValue() == 0) {
            return true;
        } else if (obj instanceof Double && ((Double) obj).doubleValue() == 0) {
            return true;
        } else if (obj instanceof Float && ((Float) obj).floatValue() == 0) {
            return true;
        } else if (obj instanceof Long && ((Long) obj).longValue() == 0) {
            return true;
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
