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

import com.jfinal.plugin.activerecord.Page;
import com.lambkit.web.controller.LambkitController;
import com.lambkit.component.swagger.annotation.Api;
import com.lambkit.component.swagger.annotation.ApiOperation;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.UpmsResult;
import com.lambkit.module.upms.UpmsResultConstant;
import com.lambkit.module.upms.rpc.model.UpmsLog;
import com.lambkit.module.upms.rpc.model.sql.UpmsLogCriteria;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * 日志controller
 */
@Api(tag = "/upms/log", description = "日志管理")
//@RequestMapping("/upms/log")
public class UpmsLogController extends LambkitController {

    //private static Logger _log = LoggerFactory.getLogger(UpmsLogController.class);

    @ApiOperation(tag = "日志首页")
    @RequiresPermissions("upms:log:read")
    public void index() {
    	renderTemplate("index.html");
    }

    @ApiOperation(url = "/upms/log", tag = "manage/log", httpMethod = "get", description = "日志列表")
    @RequiresPermissions("upms:log:read")
    public void list() {
    	String sort = getPara("sort");
    	String order = getPara("order");
    	String search = getPara("search", "");
    	int limit = getParaToInt("limit", 10);
    	int offset = getParaToInt("offset", 0);
        Example upmsLogExample = Example.create(UpmsLog.service().getTableName());
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsLogExample.setOrderBy(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
        	upmsLogExample.addColumns(UpmsLogCriteria.create()
                    .andDescriptionLike("%" + search + "%"));
        }
        Page<UpmsLog> rows = UpmsLog.service().paginate(upmsLogExample, offset, limit);
        setAttr("rows", rows.getList());
        setAttr("total", rows.getTotalRow());
        renderJson();
    }

    @ApiOperation(url = "/upms/delete", tag = "manage/log", httpMethod = "get", description = "删除日志")
    @RequiresPermissions("upms:log:delete")
    public void delete() {
    	String ids = getPara();
        int count = UpmsLog.service().deleteByPrimaryKeys(ids);
        renderJson(new UpmsResult(UpmsResultConstant.SUCCESS, count));
    }

}