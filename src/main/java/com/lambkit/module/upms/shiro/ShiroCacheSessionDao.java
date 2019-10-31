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
package com.lambkit.module.upms.shiro;

import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lambkit.component.shiro.session.ShiroSession;
import com.lambkit.component.shiro.web.SerializableUtil;
import com.lambkit.module.upms.UpmsCache;
import com.lambkit.module.upms.UpmsConstant;
import com.lambkit.module.upms.UpmsManager;

import java.io.Serializable;
import java.util.*;

/**
 * 基于redis的sessionDao，缓存共享session
 */
public class ShiroCacheSessionDao extends CachingSessionDAO {

    private static Logger _log = LoggerFactory.getLogger(ShiroCacheSessionDao.class);
    // 会话key
    private final static String LAMBKIT_SHIRO_SESSION_ID = "lambkit-shiro-session-id";
    // 全局会话key
    private final static String LAMBKIT_SHIRO_SERVER_SESSION_ID = "lambkit-shiro-server-session-id";
    // 全局会话列表key
    private final static String LAMBKIT_SHIRO_SERVER_SESSION_IDS = "lambkit-shiro-server-session-ids";
    // code key
    private final static String LAMBKIT_SHIRO_SERVER_CODE = "lambkit-shiro-server-code";
    // 局部会话key
    private final static String LAMBKIT_SHIRO_CLIENT_SESSION_ID = "lambkit-shiro-client-session-id";
    // 单点同一个code所有局部会话key
    private final static String LAMBKIT_SHIRO_CLIENT_SESSION_IDS = "lambkit-shiro-client-session-ids";
    
    private UpmsCache getCache() {
    	return UpmsManager.me().getCache();
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        getCache().set(LAMBKIT_SHIRO_SESSION_ID + "_" + sessionId, SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
        _log.debug("doCreate >>>>> sessionId={}", sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        String session = getCache().get(LAMBKIT_SHIRO_SESSION_ID + "_" + sessionId);
        _log.debug("doReadSession >>>>> sessionId={}", sessionId);
        return SerializableUtil.deserialize(session);
    }

    @Override
    protected void doUpdate(Session session) {
        // 如果会话过期/停止 没必要再更新了
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return;
        }
        // 更新session的最后一次访问时间
        ShiroSession upmsSession = (ShiroSession) session;
        ShiroSession cacheUpmsSession = (ShiroSession) doReadSession(session.getId());
        if (null != cacheUpmsSession) {
            upmsSession.setStatus(cacheUpmsSession.getStatus());
            upmsSession.setAttribute("FORCE_LOGOUT", cacheUpmsSession.getAttribute("FORCE_LOGOUT"));
        }
        getCache().set(LAMBKIT_SHIRO_SESSION_ID + "_" + session.getId(), SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
        // 更新ZHENG_UPMS_SERVER_SESSION_ID、ZHENG_UPMS_SERVER_CODE过期时间 TODO
        _log.debug("doUpdate >>>>> sessionId={}", session.getId());
    }

    @Override
    protected void doDelete(Session session) {
        String sessionId = session.getId().toString();
        String upmsType = ObjectUtils.toString(session.getAttribute(UpmsConstant.UPMS_TYPE));
        if ("client".equals(upmsType)) {
            // 删除局部会话和同一code注册的局部会话
            String code = getCache().get(LAMBKIT_SHIRO_CLIENT_SESSION_ID + "_" + sessionId);
            getCache().del(LAMBKIT_SHIRO_CLIENT_SESSION_ID + "_" + sessionId);
            getCache().srem(LAMBKIT_SHIRO_CLIENT_SESSION_IDS + "_" + code, sessionId);
        }
        if ("server".equals(upmsType)) {
            // 当前全局会话code
            String code = getCache().get(LAMBKIT_SHIRO_SERVER_SESSION_ID + "_" + sessionId);
            // 清除全局会话
            getCache().del(LAMBKIT_SHIRO_SERVER_SESSION_ID + "_" + sessionId);
            // 清除code校验值
            getCache().del(LAMBKIT_SHIRO_SERVER_CODE + "_" + code);
            // 清除所有局部会话
            Set<String> clientSessionIds = getCache().smembers(LAMBKIT_SHIRO_CLIENT_SESSION_IDS + "_" + code);
            for (String clientSessionId : clientSessionIds) {
            	getCache().del(LAMBKIT_SHIRO_CLIENT_SESSION_ID + "_" + clientSessionId);
            	getCache().srem(LAMBKIT_SHIRO_CLIENT_SESSION_IDS + "_" + code, clientSessionId);
            }
            _log.debug("当前code={}，对应的注册系统个数：{}个", code, getCache().scard(LAMBKIT_SHIRO_CLIENT_SESSION_IDS + "_" + code));
            // 维护会话id列表，提供会话分页管理
            getCache().lrem(LAMBKIT_SHIRO_SERVER_SESSION_IDS, 1, sessionId);
        }
        // 删除session
        getCache().del(LAMBKIT_SHIRO_SESSION_ID + "_" + sessionId);
        _log.debug("doUpdate >>>>> sessionId={}", sessionId);
    }

    /**
     * 获取会话列表
     * @param offset
     * @param limit
     * @return
     */
    public Map getActiveSessions(int offset, int limit) {
        Map sessions = new HashMap();
        // 获取在线会话总数
        long total = getCache().llen(LAMBKIT_SHIRO_SERVER_SESSION_IDS);
        // 获取当前页会话详情
        List<String> ids = getCache().lrange(LAMBKIT_SHIRO_SERVER_SESSION_IDS, offset, (offset + limit - 1));
        List<Session> rows = new ArrayList<>();
        for (String id : ids) {
            String session = getCache().get(LAMBKIT_SHIRO_SESSION_ID + "_" + id);
            // 过滤redis过期session
            if (null == session) {
                getCache().lrem(LAMBKIT_SHIRO_SERVER_SESSION_IDS, 1, id);
                total = total - 1;
                continue;
            }
             rows.add(SerializableUtil.deserialize(session));
        }
        sessions.put("total", total);
        sessions.put("rows", rows);
        return sessions;
    }

    /**
     * 强制退出
     * @param ids
     * @return
     */
    public int forceout(String ids) {
        String[] sessionIds = ids.split(",");
        for (String sessionId : sessionIds) {
            // 会话增加强制退出属性标识，当此会话访问系统时，判断有该标识，则退出登录
            String session = getCache().get(LAMBKIT_SHIRO_SESSION_ID + "_" + sessionId);
            ShiroSession upmsSession = (ShiroSession) SerializableUtil.deserialize(session);
            upmsSession.setStatus(ShiroSession.OnlineStatus.force_logout);
            upmsSession.setAttribute("FORCE_LOGOUT", "FORCE_LOGOUT");
            getCache().set(LAMBKIT_SHIRO_SESSION_ID + "_" + sessionId, SerializableUtil.serialize(upmsSession), (int) upmsSession.getTimeout() / 1000);
        }
        return sessionIds.length;
    }

    /**
     * 更改在线状态
     *
     * @param sessionId
     * @param onlineStatus
     */
    public void updateStatus(Serializable sessionId, ShiroSession.OnlineStatus onlineStatus) {
        ShiroSession session = (ShiroSession) doReadSession(sessionId);
        if (null == session) {
            return;
        }
        session.setStatus(onlineStatus);
        getCache().set(LAMBKIT_SHIRO_SESSION_ID + "_" + session.getId(), SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
    }

}
