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
package com.lambkit.module.upms.server.controller.manage;

import com.alibaba.fastjson.JSONArray;
import com.jfinal.plugin.activerecord.Page;
import com.lambkit.web.controller.BaseController;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.common.UpmsResult;
import com.lambkit.module.upms.common.UpmsResultConstant;
import com.lambkit.module.upms.rpc.model.UpmsRole;
import com.lambkit.module.upms.rpc.model.UpmsRolePermission;
import com.lambkit.module.upms.rpc.model.sql.UpmsRoleCriteria;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.servlet.http.HttpServletRequest;

/**
 * 角色controller
 */
//@Api(value = "角色管理", description = "角色管理")
//@RequestMapping("/manage/role")
public class UpmsRoleController extends BaseController {

    //private static Logger _log = LoggerFactory.getLogger(UpmsRoleController.class);

    ////@ApiOperation(value = "角色首页")
    @RequiresPermissions("upms:role:read")
    public void index() {
    	renderTemplate("index.html");
    }

    ////@ApiOperation(value = "角色权限")
    @RequiresPermissions("upms:role:permission")
    public void permission() {
    	Long id = getParaToLong(0);
    	if (getRequest().getMethod().equals("GET")) {
    		UpmsRole role = UpmsRole.service().findByPrimaryKey(id);
	        setAttr("role", role);
	        renderTemplate("permission.html");
	        return;
    	} else {
    		renderJson(permission(id,getRequest()));
    	}
    }

    ////@ApiOperation(value = "角色权限")
    @RequiresPermissions("upms:role:permission")
    private Object permission(Long id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        int result = UpmsRolePermission.service().rolePermission(datas, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, result);
    }

    ////@ApiOperation(value = "角色列表")
    @RequiresPermissions("upms:role:read")
    public void list() {
	String sort = getPara("sort");
	String order = getPara("order");
	String search = getPara("search", "");
	int limit = getParaToInt("limit", 10);
	int offset = getParaToInt("offset", 0);
        Example upmsRoleExample = Example.create(UpmsRole.service().getTableName());
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsRoleExample.setOrderBy(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            upmsRoleExample.addColumns(UpmsRoleCriteria.create().andTitleLike("%" + search + "%"));
        }
        Page<UpmsRole> rows = UpmsRole.service().paginate(upmsRoleExample, offset, limit);
        setAttr("rows", rows.getList());
        setAttr("total", rows.getTotalRow());
        renderJson();
    }

    ////@ApiOperation(value = "新增角色")
    @RequiresPermissions("upms:role:create")
    public void create() {
    	if (getRequest().getMethod().equals("GET")) {
    		renderTemplate("create.html");
    		return;
    	} else {
    		UpmsRole upmsRole = getModel(UpmsRole.class, "model");
    		renderJson(create(upmsRole));
    	}
    }

    ////@ApiOperation(value = "新增角色")
    @RequiresPermissions("upms:role:create")
    private Object create(UpmsRole upmsRole) {
    	/*
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsRole.getName(), new LengthValidator(1, 20, "名称"))
                .on(upmsRole.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        */
        long time = System.currentTimeMillis();
        upmsRole.setCtime(time);
        upmsRole.setOrders(time);
        int count = upmsRole.save() ? 1 : -1;
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    ////@ApiOperation(value = "删除角色")
    @RequiresPermissions("upms:role:delete")
    public void delete() {
    	String ids = getPara();
        int count = UpmsRole.service().deleteByPrimaryKeys(ids);
        renderJson(new UpmsResult(UpmsResultConstant.SUCCESS, count));
    }

    ////@ApiOperation(value = "修改角色")
    @RequiresPermissions("upms:role:update")
    public void update() {
    	Long id = getParaToLong(0);
    	if (getRequest().getMethod().equals("GET")) {
    		UpmsRole role = UpmsRole.service().findByPrimaryKey(id);
            setAttr("role", role);
            renderTemplate("update.html");
            return;
    	} else {
    		UpmsRole upmsRole = getModel(UpmsRole.class, "model");
    		renderJson(update(id, upmsRole));
    	}
    }

    ////@ApiOperation(value = "修改角色")
    @RequiresPermissions("upms:role:update")
    private Object update(Long id, UpmsRole upmsRole) {
    	//Long id = getParaToLong("id");
    	/*
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsRole.getName(), new LengthValidator(1, 20, "名称"))
                .on(upmsRole.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        */
        upmsRole.setRoleId(id);
        int count = upmsRole.update() ? 1 : -1;
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}
