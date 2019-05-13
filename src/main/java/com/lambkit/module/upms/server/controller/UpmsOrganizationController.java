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
import com.lambkit.web.controller.BaseController;
import com.lambkit.component.swagger.annotation.Api;
import com.lambkit.component.swagger.annotation.ApiOperation;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.UpmsResult;
import com.lambkit.module.upms.UpmsResultConstant;
import com.lambkit.module.upms.rpc.model.UpmsOrganization;
import com.lambkit.module.upms.rpc.model.sql.UpmsOrganizationCriteria;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * 组织controller
 */
@Api(tag = "/manage/organization", description = "组织管理")
//@RequestMapping("/manage/organization")
public class UpmsOrganizationController extends BaseController {

    //private static Logger _log = LoggerFactory.getLogger(UpmsOrganizationController.class);

    @ApiOperation(url = "/manage/organization", tag = "manage/organization", httpMethod = "get", description = "组织首页")
    @RequiresPermissions("upms:organization:read")
    public void index() {
        renderTemplate("index.html");
    }

    @ApiOperation(url = "/manage/organization/list", tag = "manage/organization", httpMethod = "get", description = "组织列表")
    @RequiresPermissions("upms:organization:read")
    public void list() {
    	String sort = getPara("sort");
    	String order = getPara("order");
    	String search = getPara("search", "");
    	int limit = getParaToInt("limit", 10);
    	int offset = getParaToInt("offset", 0);
        Example upmsOrganizationExample = Example.create(UpmsOrganization.service().getTableName());
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsOrganizationExample.setOrderBy(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
        	upmsOrganizationExample.addColumns(UpmsOrganizationCriteria.create()
                    .andNameLike("%" + search + "%"));
        }
        Page<UpmsOrganization> rows = UpmsOrganization.service().paginate(upmsOrganizationExample, offset, limit);
        setAttr("rows", rows.getList());
        setAttr("total", rows.getTotalRow());
        renderJson();
    }

    @ApiOperation(url = "/manage/organization/create", tag = "manage/organization", httpMethod = "get", description = "新增组织")
    @RequiresPermissions("upms:organization:create")
    public void create() {
    	if (getRequest().getMethod().equals("GET")) {
            renderTemplate("create.html");
            return;
    	} else {
    		UpmsOrganization upmsOrganization = getModel(UpmsOrganization.class, "model");
    		renderJson(create(upmsOrganization));
    	}
    }

    @ApiOperation(url = "/manage/organization/create", tag = "manage/organization", httpMethod = "post", description = "新增组织")
    private Object create(UpmsOrganization upmsOrganization) {
    	/*
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsOrganization.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        */
        long time = System.currentTimeMillis();
        upmsOrganization.setCtime(time);
        int count = upmsOrganization.save() ? 1 : -1;
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(url = "/manage/organization/delete", tag = "manage/organization", httpMethod = "get", description = "删除组织")
    @RequiresPermissions("upms:organization:delete")
    public void delete() {
    	String ids = getPara();
        int count = UpmsOrganization.service().deleteByPrimaryKeys(ids);
        renderJson(new UpmsResult(UpmsResultConstant.SUCCESS, count));
    }

    @ApiOperation(url = "/manage/organization/update", tag = "manage/organization", httpMethod = "get", description = "修改组织")
    @RequiresPermissions("upms:organization:update")
    public void update() {
    	Long id = getParaToLong(0);
    	if (getRequest().getMethod().equals("GET")) {
    		UpmsOrganization organization = UpmsOrganization.service().findByPrimaryKey(id);
            setAttr("organization", organization);
            renderTemplate("update.html");
            return;
    	} else {
    		UpmsOrganization upmsOrganization = getModel(UpmsOrganization.class, "model");
    		renderJson(update(id, upmsOrganization));
    	}
    }

    @ApiOperation(url = "/manage/organization/update", tag = "manage/organization", httpMethod = "post", description = "修改组织")
    private Object update(Long id, UpmsOrganization upmsOrganization) {
    	/*
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsOrganization.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        */
        upmsOrganization.setOrganizationId(id);
        int count = upmsOrganization.update() ? 1 : -1;
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}
