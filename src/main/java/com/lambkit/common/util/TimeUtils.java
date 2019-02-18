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

public class TimeUtils {

	private static long startMili;
	
	public static void startTime(String title) {
		startTime(title, false);
	}
	
	public static void startTime(String title, boolean bprint) {
		if(bprint) System.out.println("------------"+title+"-开始----------------");
		startMili = System.currentTimeMillis();// 当前时间对应的毫秒数
	}
	
	public static void endTime(String title) {
		endTime(title, false);
	}
	
	public static void endTime(String title, boolean bprint) {
		long endMili=System.currentTimeMillis();
		System.out.println("[" + title + "] the total time is："+(endMili-startMili)+"ms");
		if(bprint) System.out.println("------------"+title+"-结束----------------");
	}
}
