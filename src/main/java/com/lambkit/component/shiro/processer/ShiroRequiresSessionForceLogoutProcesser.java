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
package com.lambkit.component.shiro.processer;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public class ShiroRequiresSessionForceLogoutProcesser implements IShiroAuthorizeProcesser {

	@Override
	public AuthorizeResult authorize() {
		// TODO Auto-generated method stub
		Session session = SecurityUtils.getSubject().getSession(false);
        if(session == null) {
            return AuthorizeResult.ok();
        }
        boolean forceout = session.getAttribute("FORCE_LOGOUT") == null;
        if(forceout) {
        	return AuthorizeResult.ok();
        } else {
        	return AuthorizeResult.fail(AuthorizeResult.ERROR_CODE_SESSION_FORCE_LOGOUT);
        }
	}
}
