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

import com.alibaba.fastjson.JSONArray;
import com.jfinal.plugin.activerecord.Page;
import com.lambkit.web.controller.LambkitController;
import com.lambkit.common.util.EncryptUtils;
import com.lambkit.component.swagger.annotation.Api;
import com.lambkit.component.swagger.annotation.ApiOperation;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.UpmsResult;
import com.lambkit.module.upms.UpmsResultConstant;
import com.lambkit.module.upms.rpc.model.*;
import com.lambkit.module.upms.rpc.model.sql.UpmsUserCriteria;
import com.lambkit.module.upms.rpc.model.sql.UpmsUserOrganizationCriteria;
import com.lambkit.module.upms.rpc.model.sql.UpmsUserRoleCriteria;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * 用户controller
 */
@Api(tag = "/upms/user", description = "用户管理")
//@RequestMapping("/upms/user")
public class UpmsUserController extends LambkitController {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserController.class);

    @ApiOperation(url = "/upms/user", tag = "/upms/user", httpMethod = "get", description = "用户首页")
    @RequiresPermissions("upms:user:read")
    public void index() {
    	renderTemplate("index.html");
    }

    @ApiOperation(url = "/upms/user/organization", tag = "/upms/user", httpMethod = "get", description = "用户组织")
    @RequiresPermissions("upms:user:organization")
    public void organization() {
    	Long id = getParaToLong(0);
    	if (getRequest().getMethod().equals("GET")) {
    		// 所有组织
            List<UpmsOrganization> upmsOrganizations = UpmsOrganization.service().find(Example.create(UpmsOrganization.service().getTableName()));
            // 用户拥有组织
            Example upmsUserOrganizationExample = UpmsUserOrganizationCriteria.create().andUserIdEqualTo(id).example();
            List<UpmsUserOrganization> upmsUserOrganizations = UpmsUserOrganization.service().find(upmsUserOrganizationExample);
            setAttr("upmsOrganizations", upmsOrganizations);
            setAttr("upmsUserOrganizations", upmsUserOrganizations);
            renderTemplate("organization.html");
            return;
    	} else {
    		renderJson(organization(id, getRequest()));
    	}
    }

    //@ApiOperation(value = "用户组织")
    @RequiresPermissions("upms:user:organization")
    private Object organization(Long id, HttpServletRequest request) {
        String[] organizationIds = request.getParameterValues("organizationId");
        UpmsUserOrganization.service().organization(organizationIds, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, "");
    }

    @ApiOperation(url = "/upms/user/role", tag = "/upms/user", httpMethod = "get", description = "用户角色")
    @RequiresPermissions("upms:user:role")
    public void role() {
    	Long id = getParaToLong(0);
    	if (getRequest().getMethod().equals("GET")) {
    		// 所有角色
            List<UpmsRole> upmsRoles = UpmsRole.service().find(Example.create(UpmsRole.service().getTableName()));
            // 用户拥有角色
            Example upmsUserRoleExample = UpmsUserRoleCriteria.create().andUserIdEqualTo(id).example();
            List<UpmsUserRole> upmsUserRoles = UpmsUserRole.service().find(upmsUserRoleExample);
            setAttr("upmsRoles", upmsRoles);
            setAttr("upmsUserRoles", upmsUserRoles);
	        renderTemplate("role.html");
            return;
    	} else {
    		renderJson(role(id, getRequest()));
    	}
    }

    //@ApiOperation(value = "用户角色")
    @RequiresPermissions("upms:user:role")
    private Object role(Long id, HttpServletRequest request) {
        String[] roleIds = request.getParameterValues("roleId");
        UpmsUserRole.service().role(roleIds, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, "");
    }

    @ApiOperation(url = "/upms/user/permission", tag = "/upms/user", httpMethod = "get", description = "用户权限")
    @RequiresPermissions("upms:user:permission")
    public void permission() {
    	Long id = getParaToLong(0);
    	if (getRequest().getMethod().equals("GET")) {
    		UpmsUser user = UpmsUser.service().findByPrimaryKey(id);
	        setAttr("user", user);
	        renderTemplate("permission.html");
            return;
    	} else {
    		renderJson(permission(id, getRequest()));
    	}
    }

    //@ApiOperation(value = "用户权限")
    @RequiresPermissions("upms:user:permission")
    private Object permission(Long id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        UpmsUserPermission.service().permission(datas, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, datas.size());
    }

    @ApiOperation(url = "/upms/user/list", tag = "/upms/user", httpMethod = "get", description = "用户列表")
    @RequiresPermissions("upms:user:read")
    public void list() {
    	String sort = getPara("sort");
    	String order = getPara("order");
    	String search = getPara("search", "");
    	int limit = getParaToInt("limit", 10);
    	int offset = getParaToInt("offset", 0);
        Example upmsUserExample = Example.create(UpmsUser.service().getTableName());
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsUserExample.setOrderBy(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            upmsUserExample.addColumns(UpmsUserCriteria.create().andRealnameLike("%" + search + "%"));
            upmsUserExample.addColumns(UpmsUserCriteria.create().andUsernameLike("%" + search + "%"));
        }
        Page<UpmsUser> page = UpmsUser.service().paginate(upmsUserExample, offset, limit);
        setAttr("rows", page.getList());
        setAttr("total", page.getTotalRow());
        renderJson();
    }

    @ApiOperation(url = "/upms/user/create", tag = "/upms/user", httpMethod = "get", description = "新增用户")
    @RequiresPermissions("upms:user:create")
    public void create() {
    	if (getRequest().getMethod().equals("GET")) {
            renderTemplate("create.html");
            return;
    	} else {
    		UpmsUser upmsUser = getModel(UpmsUser.class, "model");
    		renderJson(create(upmsUser));
    	}
    }

    //@ApiOperation(value = "新增用户")
    @RequiresPermissions("upms:user:create")
    private Object create(UpmsUser upmsUser) {
    	/*
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsUser.getUsername(), new LengthValidator(1, 20, "帐号"))
                .on(upmsUser.getPassword(), new LengthValidator(5, 32, "密码"))
                .on(upmsUser.getRealname(), new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        */
        long time = System.currentTimeMillis();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        upmsUser.setSalt(salt);
        upmsUser.setPassword(EncryptUtils.MD5(upmsUser.getPassword() + upmsUser.getSalt()));
        upmsUser.setCtime(time);
        upmsUser = UpmsUser.service().createUser(upmsUser);
        if (null == upmsUser) {
            return new UpmsResult(UpmsResultConstant.FAILED, "帐号名已存在！");
        }
        _log.info("新增用户，主键：userId={}", upmsUser.getUserId());
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(url = "/upms/user/delete", tag = "/upms/user", httpMethod = "get", description = "删除用户")
    @RequiresPermissions("upms:user:delete")
    public void delete() {
    	String ids = getPara();
        int count = UpmsUser.service().deleteByPrimaryKeys(ids);
        renderJson(new UpmsResult(UpmsResultConstant.SUCCESS, count));
    }
    
    @ApiOperation(url = "/upms/user/update", tag = "/upms/user", httpMethod = "get", description = "修改用户")
    @RequiresPermissions("upms:user:update")
    public void update() {
    	Long id = getParaToLong(0);
    	if (getRequest().getMethod().equals("GET")) {
    		UpmsUser user = UpmsUser.service().findByPrimaryKey(id);
            setAttr("user", user);
            renderTemplate("update.html");
            return;
    	} else {
    		UpmsUser upmsUser = getModel(UpmsUser.class, "model");
    		renderJson(update(id, upmsUser));
    	}
    }

    //@ApiOperation(value = "修改用户")
    @RequiresPermissions("upms:user:update")
    private Object update(Long id, UpmsUser upmsUser) {
    	/*
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsUser.getUsername(), new LengthValidator(1, 20, "帐号"))
                .on(upmsUser.getRealname(), new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        */
        // 不允许直接改密码
        upmsUser.setPassword(null);
        upmsUser.removeNullValueAttrs();
        upmsUser.setUserId(id);
        int count = upmsUser.update() ? 1 : -1;
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}
