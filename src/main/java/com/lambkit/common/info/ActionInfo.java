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
package com.lambkit.common.info;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.Action;
import com.jfinal.core.Controller;

public class ActionInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4735374353527592777L;

	private final Class<? extends Controller> controllerClass;
	private final String controllerKey;
	private final String actionKey;
	private final Method method;
	private final String methodName;
	private final InterceptorInfo[] interceptors;
	private final String viewPath;
	
	//最近一次调用时间
	private long accessTime;
	//最近一次耗时
	private long lastTime = 0;
	//最大耗时
	private long maxTime = -1L;
	//最小耗时
	private long minTime = -1L;
	//调用次数
	private long count = 0;
	//平均耗时
	private long avgTime = 0;

	public ActionInfo(Action action) {
		// TODO Auto-generated constructor stub
		this.controllerKey = action.getControllerKey();
		this.actionKey = action.getActionKey();
		this.controllerClass = action.getControllerClass();
		this.method = action.getMethod();
		this.methodName = action.getMethodName();
		this.interceptors = new InterceptorInfo[action.getInterceptors().length];
		for(int i=0; i<interceptors.length; i++) {
			this.interceptors[i] = new InterceptorInfo(action.getInterceptors()[i].getClass());
		}
		this.viewPath = action.getViewPath();
	}
	
	public ActionInfo(String controllerKey, String actionKey, Class<? extends Controller> controllerClass, Method method, String methodName, Interceptor[] interceptors, String viewPath) {
		this.controllerKey = controllerKey;
		this.actionKey = actionKey;
		this.controllerClass = controllerClass;
		this.method = method;
		this.methodName = methodName;
		this.interceptors = new InterceptorInfo[interceptors.length];
		for(int i=0; i<interceptors.length; i++) {
			this.interceptors[i] = new InterceptorInfo(interceptors[i].getClass());
		}
		this.viewPath = viewPath;
	}
	
	public void access(long startTime, long endTime) {
		this.accessTime = startTime;
		this.lastTime = endTime - startTime;
		this.count++;
		this.avgTime = (this.avgTime*this.count + this.lastTime)/this.count;
		this.maxTime = this.lastTime > this.maxTime ? this.lastTime : this.maxTime;
		this.minTime = this.minTime > this.lastTime ? this.lastTime : this.minTime;
	}
	
	public Class<? extends Controller> getControllerClass() {
		return controllerClass;
	}
	
	public String getControllerKey() {
		return controllerKey;
	}
	
	public String getActionKey() {
		return actionKey;
	}
	
	public Method getMethod() {
		return method;
	}
	
	public InterceptorInfo[] getInterceptors() {
		return interceptors;
	}
	
	public String getViewPath() {
		return viewPath;
	}
	
	public String getMethodName() {
		return methodName;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}

	public long getMinTime() {
		return minTime;
	}

	public void setMinTime(long minTime) {
		this.minTime = minTime;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getAvgTime() {
		return avgTime;
	}

	public void setAvgTime(long avgTime) {
		this.avgTime = avgTime;
	}

	public long getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(long accessTime) {
		this.accessTime = accessTime;
	}
}
