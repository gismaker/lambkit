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
package com.lambkit.core.session;

import com.lambkit.core.session.impl.EhcacheSessionImpl;
import com.lambkit.core.session.impl.NoneSessionImpl;
import com.lambkit.core.session.impl.RedisSessionImpl;

public class SessionManager {

	private static SessionManager me = new SessionManager();

	private SessionManager() {
	}

	private Session session;
	private boolean bdefault = true;

	public static SessionManager me() {
		return me;
	}

	public Session getSession() {
		if (session == null) {
			session = buildSession();
		}
		return session;
	}

	private Session buildSession() {
		SessionConfig config = new SessionConfig();
		switch (config.getType()) {
		case SessionConfig.TYPE_EHCACHE:
			bdefault = false;
			return new EhcacheSessionImpl();
		case SessionConfig.TYPE_REDIS:
			bdefault = false;
			return new RedisSessionImpl();
		default:
			return new NoneSessionImpl();
		}
	}
}
