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
package com.lambkit.module;

import com.lambkit.common.Consts;
import com.lambkit.core.event.Event;
import com.lambkit.core.event.EventListener;
import com.lambkit.core.event.annotation.Listener;

@Listener(action = Consts.EVENT_INIT)
public class InitListener implements EventListener {

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Starting Lambkit " + Consts.LAMBKIT_VERSION);
	}
}
