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
package com.lambkit.module.upms.server.controller;

import com.lambkit.web.controller.LambkitController;
import com.lambkit.core.aop.AopKit;
import com.lambkit.module.upms.UpmsResult;
import com.lambkit.module.upms.UpmsResultConstant;
import com.lambkit.module.upms.shiro.ShiroRedisSessionDao;

import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * 会话管理controller
 */
//@Api(value = "会话管理", description = "会话管理")
//@RequestMapping("/upms/session")
public class UpmsSessionController extends LambkitController {

    //private static Logger _log = LoggerFactory.getLogger(UpmsSessionController.class);

    private ShiroRedisSessionDao sessionDAO = AopKit.get(ShiroRedisSessionDao.class);

    ////@ApiOperation(value = "会话首页")
    @RequiresPermissions("upms:session:read")
    public void index() {
        renderTemplate("index.html");
    }

    ////@ApiOperation(value = "会话列表")
    @RequiresPermissions("upms:session:read")
    public void list() {
    	int limit = getParaToInt("limit", 10);
    	int offset = getParaToInt("offset", 0);
        renderJson(sessionDAO.getActiveSessions(offset, limit));
    }

    ////@ApiOperation(value = "强制退出")
    @RequiresPermissions("upms:session:forceout")
    public void forceout() {
    	String ids = getPara();
        int count = sessionDAO.forceout(ids);
        renderJson(new UpmsResult(UpmsResultConstant.SUCCESS, count));
    }

}