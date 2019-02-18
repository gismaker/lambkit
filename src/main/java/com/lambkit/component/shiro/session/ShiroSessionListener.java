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

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroSessionListener implements SessionListener {

    private static Logger _log = LoggerFactory.getLogger(ShiroSessionListener.class);

    @Override
    public void onStart(Session session) {
        _log.debug("会话创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        _log.debug("会话停止：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        _log.debug("会话过期：" + session.getId());
    }

}
