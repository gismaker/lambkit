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

import java.util.Map;

import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;

/**
 * 认证处理器 执行后的认证结果。
 */
public class AuthorizeResult extends Kv {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4192813857068328914L;

	/**
     * 未进行身份认证
     */
    public static final int ERROR_CODE_UNAUTHENTICATED = 1;

    /**
     * 没有权限访问
     */
    public static final int ERROR_CODE_UNAUTHORIZATION = 2;
    
    /**
     * 强制退出会话
     */
    public static final int ERROR_CODE_SESSION_FORCE_LOGOUT = 3;

    public static AuthorizeResult by(Object key, Object value) {
		return new AuthorizeResult().set(key, value);
	}
	
	public static AuthorizeResult create() {
		return new AuthorizeResult();
	}
	
	public AuthorizeResult set(Object key, Object value) {
		super.set(key, value);
		return this;
	}
	
	public AuthorizeResult setIfNotBlank(Object key, String value) {
		if (StrKit.notBlank(value)) {
			set(key, value);
		}
		return this;
	}
	
	public AuthorizeResult setIfNotNull(Object key, Object value) {
		if (value != null) {
			set(key, value);
		}
		return this;
	}
	
	public AuthorizeResult set(Map map) {
		super.set(map);
		return this;
	}
	
	public AuthorizeResult set(Kv kv) {
		super.set(kv);
		return this;
	}
	
	public AuthorizeResult delete(Object key) {
		super.delete(key);
		return this;
	}

    public static AuthorizeResult ok() {
        return by("authorizeResult", true);
    }


    public static AuthorizeResult fail(int errorCode) {
        return by("authorizeResult", false).set("errorCode", errorCode);
    }

    public int getErrorCode() {
        return (int) get("errorCode");
    }


	public boolean isFail() {
		return isFalse("authorizeResult");
	}


	public boolean isOk() {
		return isTrue("authorizeResult");
	}
}
