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
import com.lambkit.common.aop.AopKit;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.common.UpmsResult;
import com.lambkit.module.upms.common.UpmsResultConstant;
import com.lambkit.module.upms.rpc.model.*;
import com.lambkit.module.upms.rpc.api.UpmsPermissionService;
import com.lambkit.module.upms.rpc.api.UpmsSystemService;
import com.lambkit.module.upms.rpc.service.impl.UpmsPermissionServiceImpl;
import com.lambkit.module.upms.rpc.service.impl.UpmsSystemServiceImpl;
import com.lambkit.module.upms.rpc.sql.UpmsPermissionCriteria;
import com.lambkit.module.upms.rpc.sql.UpmsSystemCriteria;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * 权限controller
 */
//@Api(value = "权限管理", description = "权限管理")
//@RequestMapping("/manage/permission")
public class UpmsPermissionController extends BaseController {

    //private static Logger _log = LoggerFactory.getLogger(UpmsPermissionController.class);

    private UpmsPermissionService upmsPermissionService = AopKit.newInstance(UpmsPermissionServiceImpl.class);

    private UpmsSystemService upmsSystemService = AopKit.newInstance(UpmsSystemServiceImpl.class);

    ////@ApiOperation(value = "权限首页")
    @RequiresPermissions("upms:permission:read")
    public void index() {
    	renderTemplate("index.html");
    }

    ////@ApiOperation(value = "权限列表")
    @RequiresPermissions("upms:permission:read")
    public void list() {
    	String sort = getPara("sort");
    	String order = getPara("order");
    	String search = getPara("search", "");
    	int limit = getParaToInt("limit", 10);
    	int offset = getParaToInt("offset", 0);
    	int type = getParaToInt("type", 0);
    	int systemId = getParaToInt("systemId", 0);
        Example upmsPermissionExample = Example.create(UpmsPermission.service().getTableName());
        UpmsPermissionCriteria criteria = UpmsPermissionCriteria.create();
        if (0 != type) {
            criteria.andTypeEqualTo(type);
        }
        if (0 != systemId) {
            criteria.andSystemIdEqualTo(new Long(systemId));
        }
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsPermissionExample.setOrderBy(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
        	criteria.andNameLike("%" + search + "%");
        }
        upmsPermissionExample.addColumns(criteria);
        Page<UpmsPermission> rows = upmsPermissionService.paginate(upmsPermissionExample, offset, limit);
        //long total = upmsPermissionService.countByExample(upmsPermissionExample);
        //Map<String, Object> result = new HashMap<>();
        setAttr("rows", rows.getList());
        setAttr("total", rows.getTotalRow());
        renderJson();
    }

    ////@ApiOperation(value = "角色权限列表")
    @RequiresPermissions("upms:permission:read")
    public void role() {
    	Long id = getParaToLong(0);
        renderJson(upmsPermissionService.getTreeByRoleId(id));
    }

    ////@ApiOperation(value = "用户权限列表")
    @RequiresPermissions("upms:permission:read")
    public void user() {
    	Long id = getParaToLong(0);
        JSONArray jsonArray = upmsPermissionService.getTreeByUserId(id, getParaToInt("type", 0));
        if(jsonArray==null) {
        	renderJson("");
        } else {
        	renderJson(jsonArray);
        }
    }

    ////@ApiOperation(value = "新增权限")
    @RequiresPermissions("upms:permission:create")
    public void create() {
    	if (getRequest().getMethod().equals("GET")) {
    		Example upmsSystemExample = UpmsSystemCriteria.create().andStatusEqualTo(1).example();
            List<UpmsSystem> upmsSystems = upmsSystemService.find(upmsSystemExample);
            setAttr("upmsSystems", upmsSystems);
            renderTemplate("create.html");
            return;
    	} else {
    		UpmsPermission upmsPermission = getModel(UpmsPermission.class, "model");
    		renderJson(create(upmsPermission));
    	}
    }

    ////@ApiOperation(value = "新增权限")
    @RequiresPermissions("upms:permission:create")
    private Object create(UpmsPermission upmsPermission) {
    	/*
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsPermission.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        */
        long time = System.currentTimeMillis();
        upmsPermission.setCtime(time);
        upmsPermission.setOrders(time);
        int count = upmsPermission.save() ? 1 : -1;;
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    ////@ApiOperation(value = "删除权限")
    @RequiresPermissions("upms:permission:delete")
    public void delete() {
    	String ids = getPara();
        int count = upmsPermissionService.deleteByPrimaryKeys(ids);
        renderJson(new UpmsResult(UpmsResultConstant.SUCCESS, count));
    }

    ////@ApiOperation(value = "修改权限")
    @RequiresPermissions("upms:permission:update")
    public void update() {
    	Long id = getParaToLong(0);
    	if (getRequest().getMethod().equals("GET")) {
    		Example upmsSystemExample = UpmsSystemCriteria.create().andStatusEqualTo(1).example();
            List<UpmsSystem> upmsSystems = upmsSystemService.find(upmsSystemExample);
            UpmsPermission permission = upmsPermissionService.findByPrimaryKey(id);
            setAttr("permission", permission);
            setAttr("upmsSystems", upmsSystems);
            renderTemplate("update.html");
            return;
    	} else {
    		UpmsPermission upmsPermission = getModel(UpmsPermission.class, "model");
    		renderJson(update(id, upmsPermission));
    	}
    }

    ////@ApiOperation(value = "修改权限")
    @RequiresPermissions("upms:permission:update")
    private Object update(Long id, UpmsPermission upmsPermission) {
    	/*
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsPermission.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        */
        upmsPermission.setPermissionId(id);
        int count = upmsPermission.update() ? 1 : -1;
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}
