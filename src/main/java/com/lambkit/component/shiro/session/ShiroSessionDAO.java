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

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import com.jfinal.kit.LogKit;
import com.lambkit.Lambkit;
import com.lambkit.common.util.ArrayUtils;
import com.lambkit.common.util.DateTimeUtils;

public class ShiroSessionDAO extends AbstractSessionDAO {
	private static String SHIRO_KEY = "shiro";

    /*
    * 永不过期，可以通过ini配置文件注入配置
    */
    private int expire = 0;

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    /**
     * 如DefaultSessionManager在创建完session后会调用该方法；
     * 如保存到关系数据库/文件系统/NoSQL数据库；即可以实现会话的持久化；
     * 主要此处返回的ID.equals(session.getId())；
     *
     * @param session session
     * @return 返回会话ID
     */
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    /**
     * 根据会话ID获取会话
     *
     * @param serializable 会话ID
     * @return Session
     */
    protected Session doReadSession(Serializable serializable) {
        if (null == serializable) {
            LogKit.error("session id is null");
            return null;
        }
        return Lambkit.getCache().get(SHIRO_KEY, serializable);
    }

    /**
     * 更新会话
     * 如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
     *
     * @param session session
     */
    public void update(Session session) {
        saveSession(session);
    }

    /**
     * 删除会话
     * 当会话过期/会话停止（如用户退出时）会调用
     *
     * @param session session
     */
    public void delete(Session session) {
        if (null == session || null == session.getId()) {
            LogKit.error("session or session id is null");
        } else {
        	Lambkit.getCache().remove(SHIRO_KEY, session.getId());
            LogKit.debug("delete success shiro key " + session.getId());
        }
    }

    /**
     * 获取当前所有活跃用户，如果用户量多此方法影响性能
     *
     * @return Collection
     */
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<Session>();
        Map map = Lambkit.getCache().getAll(SHIRO_KEY);
        if (ArrayUtils.isNotEmpty(map)) {
            Set<Map.Entry> entrySet = map.entrySet();
            for (Map.Entry entry : entrySet) {
                sessions.add((Session) entry.getValue());
            }
        }
        return sessions;
    }

    /**
     * 保存 session
     *
     * @param session Session
     */
    private void saveSession(Session session) {
        if (session == null || session.getId() == null) {
            LogKit.error("session or session id is null");
            return;
        }
        Lambkit.getCache().put(SHIRO_KEY, session.getId(), session, this.expire);
    }

}
