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
package com.lambkit.core.event;

public class EventKit {

	public static void register(Class<? extends EventListener> listenerClass) {
		EventManager.me().registerListener(listenerClass);
	}
	
	public static void unRegister(Class<? extends EventListener> listenerClass) {
		EventManager.me().unRegisterListener(listenerClass);
	}

	public static void sendEvent(Event message) {
		EventManager.me().pulish(message);
	}

	public static void sendEvent(String action, Object data) {
		EventManager.me().pulish(new Event(action, data));
	}

	public static void sendEvent(String action) {
		EventManager.me().pulish(new Event(action, null));
	}

}
