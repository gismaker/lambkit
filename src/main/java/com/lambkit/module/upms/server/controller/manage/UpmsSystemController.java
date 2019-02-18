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

import com.jfinal.plugin.activerecord.Page;
import com.lambkit.web.controller.BaseController;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.common.UpmsResult;
import com.lambkit.module.upms.common.UpmsResultConstant;
import com.lambkit.module.upms.rpc.model.UpmsSystem;
import com.lambkit.module.upms.rpc.sql.UpmsSystemCriteria;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * 系统controller
 */
//@Api(value = "系统管理", description = "系统管理")
//@RequestMapping("/manage/system")
public class UpmsSystemController extends BaseController {

	//private static Logger _log = LoggerFactory.getLogger(UpmsSystemController.class);

	//@ApiOperation(value = "系统首页")
	@RequiresPermissions("upms:system:read")
	public void index() {
		renderTemplate("index.html");
	}

	//@ApiOperation(value = "系统列表")
	@RequiresPermissions("upms:system:read")
	public void list() {
    	String sort = getPara("sort");
    	String order = getPara("order");
    	String search = getPara("search", "");
    	int limit = getParaToInt("limit", 10);
    	int offset = getParaToInt("offset", 0);
		Example upmsSystemExample = Example.create(UpmsSystem.service().getTableName());
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			upmsSystemExample.setOrderBy(sort + " " + order);
		}
		if (StringUtils.isNotBlank(search)) {
			upmsSystemExample.addColumns(UpmsSystemCriteria.create().andTitleLike("%" + search + "%"));
		}
		Page<UpmsSystem> rows = UpmsSystem.service().paginate(upmsSystemExample, offset, limit);
		setAttr("rows", rows.getList());
		setAttr("total", rows.getTotalRow());
		renderJson();
	}

	//@ApiOperation(value = "新增系统")
	@RequiresPermissions("upms:system:create")
	public void create() {
		if (getRequest().getMethod().equals("GET")) {
            renderTemplate("create.html");
            return;
    	} else {
    		UpmsSystem upmsSystem = getModel(UpmsSystem.class, "model");
    		renderJson(create(upmsSystem));
    	}
	}

	//@ApiOperation(value = "新增系统")
	@RequiresPermissions("upms:system:create")
	private Object create(UpmsSystem upmsSystem) {
		/*
		ComplexResult result = FluentValidator.checkAll()
				.on(upmsSystem.getTitle(), new LengthValidator(1, 20, "标题"))
				.on(upmsSystem.getName(), new LengthValidator(1, 20, "名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		*/
		long time = System.currentTimeMillis();
		upmsSystem.setCtime(time);
		upmsSystem.setOrders(time);
		int count = upmsSystem.save() ? 1 : -1;
		return new UpmsResult(UpmsResultConstant.SUCCESS, count);
	}

	//@ApiOperation(value = "删除系统")
	@RequiresPermissions("upms:system:delete")
	public void delete() {
		String ids = getPara();
		int count = UpmsSystem.service().deleteByPrimaryKeys(ids);
		renderJson(new UpmsResult(UpmsResultConstant.SUCCESS, count));
	}

	//@ApiOperation(value = "修改系统")
	@RequiresPermissions("upms:system:update")
	public void update() {
		Long id = getParaToLong(0);
		if (getRequest().getMethod().equals("GET")) {
			UpmsSystem system = UpmsSystem.service().findByPrimaryKey(id);
			setAttr("system", system);
            renderTemplate("update.html");
            return;
    	} else {
    		UpmsSystem upmsSystem = getModel(UpmsSystem.class, "model");
    		renderJson(update(id, upmsSystem));
    	}
	}

	//@ApiOperation(value = "修改系统")
	@RequiresPermissions("upms:system:update")
	private Object update(Long id, UpmsSystem upmsSystem) {
		/*
		ComplexResult result = FluentValidator.checkAll()
				.on(upmsSystem.getTitle(), new LengthValidator(1, 20, "标题"))
				.on(upmsSystem.getName(), new LengthValidator(1, 20, "名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		*/
		upmsSystem.setSystemId(id);
		int count = upmsSystem.update() ? 1 : -1;
		return new UpmsResult(UpmsResultConstant.SUCCESS, count);
	}

}