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
package com.lambkit.plugin.mail.mockhttp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MockHttpServletRequest implements InvocationHandler {

	@SuppressWarnings("rawtypes")
	private Map dataMap =  new HashMap();
	
    @SuppressWarnings("unchecked")
	public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if ("getAttributeNames".equals(method.getName())) {
            return Collections.enumeration(dataMap.keySet());
        }
        else if ("setAttribute".equals(method.getName())) {
            return dataMap.put(objects[0],objects[1]);
        }
        else if ("getAttribute".equals(method.getName())) {
            return dataMap.get(objects[0]);
        }
        return null;
    }
}