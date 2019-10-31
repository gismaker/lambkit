package com.lambkit.module.upms;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.jfinal.plugin.redis.Redis;
import com.lambkit.Lambkit;

public class UpmsCache {
	
	private String cacheName = "upmsCache";
	private boolean serverRedis = false;
	
	public boolean useJedis() {
		String upmsType = Lambkit.config(UpmsConfig.class).getType();
		return "server".equals(upmsType) && serverRedis ? true : false;
	}
	
	public boolean isServerRedis() {
		return serverRedis;
	}

	public void setServerRedis(boolean serverRedis) {
		this.serverRedis = serverRedis;
	}
	
	/**
	 * // 判断是否已登录，如果已登录，则回跳，防止重复登录
	 * @param sessionId
	 * @return
	 */
	public String getSession(String sessionId) {
		return get(UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_ID + "_" + sessionId);
	}
	
	public void saveSession(String sessionId, int timeOut) {
		if (useJedis()) {
			saveSessionOnRedis(sessionId, timeOut);
		} else {
			// 全局会话sessionId列表，供会话管理
			Lambkit.getCache().lpush(cacheName, UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_IDS, sessionId.toString());
	        // 默认验证帐号密码正确，创建code
	        String code = UUID.randomUUID().toString();
	        // 全局会话的code
	        Lambkit.getCache().put(cacheName, UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_ID + "_" + sessionId, code, timeOut);//(int) timeout / 1000
	        // code校验值
	        Lambkit.getCache().put(cacheName, UpmsConstant.LAMBKIT_UPMS_SERVER_CODE + "_" + code, code, timeOut);//(int) timeout / 1000
		}
	}
	
	public String getClientSession(String sessionId) {
		return get(UpmsConstant.LAMBKIT_UPMS_CLIENT_SESSION_ID + "_" + sessionId);
	}
	
	/**
	 * 更新code有效期
	 * @param sessionId
	 * @param cacheClientSession
	 * @param timeOut
	 */
	public void refreshClientSession(String sessionId, String cacheClientSession, int timeOut) {
        if (useJedis()) {
        	refreshClientSessionOnRedis(sessionId, cacheClientSession, timeOut);
		} else {
			Lambkit.getCache().put(cacheName, UpmsConstant.LAMBKIT_UPMS_CLIENT_SESSION_ID + "_" + sessionId, cacheClientSession, timeOut);
			Lambkit.getCache().expire(cacheName, UpmsConstant.LAMBKIT_UPMS_CLIENT_SESSION_IDS + "_" + cacheClientSession, timeOut);
		}
	}
	
	/**
	 * code校验正确，创建局部会话；
	 * 保存code对应的局部会话sessionId，方便退出操作
	 * @param sessionId
	 * @param cacheClientSession
	 * @param timeOut
	 */
	public void saveClientSession(String sessionId, String cacheClientSession, int timeOut) {
		if (useJedis()) {
			saveClientSessionOnRedis(sessionId, cacheClientSession, timeOut);
		} else {
			// code校验正确，创建局部会话
			Lambkit.getCache().put(UpmsConstant.LAMBKIT_UPMS_CLIENT_SESSION_ID + "_" + sessionId, cacheClientSession, timeOut);
			// 保存code对应的局部会话sessionId，方便退出操作
			Lambkit.getCache().sadd(UpmsConstant.LAMBKIT_UPMS_CLIENT_SESSION_IDS + "_" + cacheClientSession, sessionId, timeOut);
		}
	}
	
	public Long getClientNumber(String code) {
		return scard(UpmsConstant.LAMBKIT_UPMS_CLIENT_SESSION_IDS + "_" + code);
	}
	
	public String getCode(String code) {
		// 判断是否已登录，如果已登录，则回跳，防止重复登录
		return get(UpmsConstant.LAMBKIT_UPMS_SERVER_CODE + "_" + code);
	}
	
	public <T> T get(Object key) {
		if (useJedis()) {
			return getOnRedis(key);
		} else {
	        return Lambkit.getCache().get(cacheName, key);
		}
	}
	
	public void set(String key, String value, int seconds) {
		if (useJedis()) {
			setOnRedis(key, value, seconds);
		} else {
	        Lambkit.getCache().put(cacheName, key, value, seconds);
		}
	}
	
	public void del(Object key) {
		if (useJedis()) {
			delOnRedis(key);
		} else {
			Lambkit.getCache().remove(cacheName, key);
		}
	}

	
	public void lpush(Object key, Object value) {
		if (useJedis()) {
			lpushOnRedis(key, value);
		} else {
	        Lambkit.getCache().lpush(cacheName, key, value);
		}
	}
	
	public long llen(Object key) {
		if (useJedis()) {
			return llenOnRedis(key);
		} else {
			return Lambkit.getCache().llen(cacheName, key);
		}
	}

	public List lrange(Object key, int start, int end) {
		if (useJedis()) {
			return lrangeOnRedis(key, start, end);
		} else {
			return Lambkit.getCache().lrange(cacheName, key, start, end);
		}
	}
	
	public void srem(Object key, Object... members) {
		if (useJedis()) {
			sremOnRedis(key, members);
		} else {
			Lambkit.getCache().srem(cacheName, key, members);
		}	
	}

	public void lrem(Object key, int count, Object value) {
		if (useJedis()) {
			lremOnRedis(key, count, value);
		} else {
			Lambkit.getCache().lrem(cacheName, key, count, value);
		}
	}

	public Set smembers(Object key) {
		if (useJedis()) {
			return smembersOnRedis(key);
		} else {
			return Lambkit.getCache().smembers(cacheName, key);
		}
	}

	public Long scard(Object key) {
		if (useJedis()) {
			return scardOnRedis(key);
		} else {
			return Lambkit.getCache().scard(cacheName, key);
		}
	}
	
	///////////////////////////////////
	
	public void saveSessionOnRedis(String sessionId, int timeOut) {
		// 全局会话sessionId列表，供会话管理
		lpushOnRedis(UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_IDS, sessionId.toString());
        // 默认验证帐号密码正确，创建code
        String code = UUID.randomUUID().toString();
        // 全局会话的code
        setOnRedis(UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_ID + "_" + sessionId, code, timeOut);
        // code校验值
        setOnRedis(UpmsConstant.LAMBKIT_UPMS_SERVER_CODE + "_" + code, code, timeOut);//(int) timeout / 1000
	}
	
	/**
	 * 更新code有效期
	 * @param sessionId
	 * @param cacheClientSession
	 * @param timeOut
	 */
	public void refreshClientSessionOnRedis(String sessionId, String cacheClientSession, int timeOut) {
        Redis.use().setex(UpmsConstant.LAMBKIT_UPMS_CLIENT_SESSION_ID + "_" + sessionId, timeOut, cacheClientSession);
        Redis.use().expire(UpmsConstant.LAMBKIT_UPMS_CLIENT_SESSION_IDS + "_" + cacheClientSession, timeOut);
	}
	
	/**
	 * code校验正确，创建局部会话；
	 * 保存code对应的局部会话sessionId，方便退出操作
	 * @param sessionId
	 * @param cacheClientSession
	 * @param timeOut
	 */
	public void saveClientSessionOnRedis(String sessionId, String cacheClientSession, int timeOut) {
		// code校验正确，创建局部会话
		Redis.use().setex(UpmsConstant.LAMBKIT_UPMS_CLIENT_SESSION_ID + "_" + sessionId, timeOut, cacheClientSession);
        // 保存code对应的局部会话sessionId，方便退出操作
        Redis.use().sadd(UpmsConstant.LAMBKIT_UPMS_CLIENT_SESSION_IDS + "_" + cacheClientSession, sessionId, timeOut);
	}
	
	public <T> T getOnRedis(Object key) {
		return Redis.use().get(key);
	}
	
	public void setOnRedis(Object key, Object value, int seconds) {
		Redis.use().setex(key, seconds, value);
	}
	
	public void lpushOnRedis(Object key, Object value) {
        Redis.use().lpush(key, value);
	}

	public void delOnRedis(Object key) {
		// TODO Auto-generated method stub
		Redis.use().del(key);
	}

	public void sremOnRedis(Object key, Object... members) {
		// TODO Auto-generated method stub
		Redis.use().srem(key, members);
	}

	public void lremOnRedis(Object key, int count, Object value) {
		// TODO Auto-generated method stub
		Redis.use().lrem(key, count, value);
	}

	public Set smembersOnRedis(Object key) {
		// TODO Auto-generated method stub
		return Redis.use().smembers(key);
	}

	public Long scardOnRedis(Object key) {
		// TODO Auto-generated method stub
		return Redis.use().scard(key);
	}

	public long llenOnRedis(Object key) {
		// TODO Auto-generated method stub
		return Redis.use().llen(key);
	}

	public List lrangeOnRedis(Object key, int start, int end) {
		// TODO Auto-generated method stub
		return Redis.use().lrange(key, start, end);
	}
	
}
