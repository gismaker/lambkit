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
package com.lambkit.module.upms.server.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.lambkit.module.upms.rpc.model.UpmsUser;
import com.lambkit.common.util.ClassNewer;
import com.lambkit.module.upms.rpc.api.UpmsApiService;
import com.lambkit.module.upms.rpc.service.impl.UpmsApiServiceImpl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 登录信息拦截器
 */
public class UpmsInterceptor implements Interceptor {

    //private static Logger _log = LoggerFactory.getLogger(UpmsInterceptor.class);
    private static final String LAMBKIT_OSS_ALIYUN_OSS_POLICY = PropKit.get("lambkit.oss.aliyun.oss.policy");

    ////@Autowired
    UpmsApiService upmsApiService = ClassNewer.newInstance(UpmsApiServiceImpl.class);
    
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
    	Controller c = inv.getController();
    	c.setAttr("LAMBKIT_OSS_ALIYUN_OSS_POLICY", LAMBKIT_OSS_ALIYUN_OSS_POLICY);
    	// 过滤ajax
        if (null != c.getHeader("X-Requested-With") && c.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
        	//不处理
        } else {
        	// 登录信息
            Subject subject = SecurityUtils.getSubject();
            String username = (String) subject.getPrincipal();
            if(StrKit.notBlank(username)) {
            	UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
                c.setAttr("upmsUser", upmsUser);
            }
            
        }
        inv.invoke();
	}
}
