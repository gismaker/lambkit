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
package com.lambkit.component.shiro.session;

import org.apache.shiro.session.mgt.SimpleSession;

/**
 * 重写session
 */
public class ShiroSession extends SimpleSession {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2497148064811243643L;

	public static enum OnlineStatus {
        on_line("在线"), off_line("离线"), force_logout("强制退出");
        private final String info;

        private OnlineStatus(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

    // 用户浏览器类型
    private String userAgent;

    // 在线状态
    private OnlineStatus status = OnlineStatus.off_line;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public OnlineStatus getStatus() {
        return status;
    }

    public void setStatus(OnlineStatus status) {
        this.status = status;
    }

}
