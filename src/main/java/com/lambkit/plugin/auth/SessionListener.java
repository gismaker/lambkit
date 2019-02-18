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
package com.lambkit.plugin.auth;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.lambkit.plugin.auth.AuthManager;


/**
 * session监听器. <br>
 * 在WEB容器的web.xml中添加本监听器的调用,具体格式如下：(其中的"[","]"分别用" <",">"替换) <br>
 * 
 * <pre>
 * 
 *    [web-app] 
 *    [filter] 
 *    ... 
 *    [/filter] 
 *    [filter-mapping] 
 *    ... 
 *    [/filter-mapping] 
 *    ... 
 *    [listener][listener-class]com.lambkit.auth.SessionListener[/listener-class][/listener] 
 *    ... 
 *    [servlet] 
 *    ... 
 *    [/servlet] 
 *    ... 
 *    [/web-app]
 * 
 * </pre>
 * 
 * 注意在web.xml中配置的位置. <br>
 * 
 * @author stephen
 * @version 1.00
 * @see javax.servlet.http.HttpSessionAttributeListener
 */
public class SessionListener implements HttpSessionAttributeListener {
	/**
	 * 定义监听的session属性名.
	 */
	public final static String LISTENER_NAME = "lambkituser";

	/**
	 * 定义存储客户登录session的集合.
	 */
	private static List sessions = new ArrayList();

	/**
	 * 加入session时的监听方法.
	 * 
	 * @param HttpSessionBindingEvent
	 *            session事件
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent sbe) {
		// TODO Auto-generated method stub
		if (LISTENER_NAME.equals(sbe.getName())) {
			sessions.add(sbe.getValue());
		}
	}

	/**
	 * session失效时的监听方法.
	 * 
	 * @param HttpSessionBindingEvent
	 *            session事件
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent sbe) {
		if (LISTENER_NAME.equals(sbe.getName())) {
			sessions.remove(sbe.getValue());
			AuthManager.me().getCache().removeUser(sbe.getSession().getId());
		}
	}

	/**
	 * session覆盖时的监听方法.
	 * 
	 * @param HttpSessionBindingEvent
	 *            session事件
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent sbe) {
	}

	/**
	 * 返回客户登录session的集合.
	 * 
	 * @return
	 */
	public static List getSessions() {
		return sessions;
	}

}
