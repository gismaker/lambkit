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
package com.lambkit.module.meta.web.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.common.AjaxResult;
import com.lambkit.db.mgr.MgrConstants;
import com.lambkit.db.mgr.MgrTable;
import com.lambkit.db.mgr.MgrdbValidator;
import com.lambkit.db.mgr.controller.MgrdbApiController;
import com.lambkit.db.sql.condition.ConditionBuilder;
import com.lambkit.db.sql.condition.SqlBuilder;

/**
 * @author yangyong
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2016-01-22
 * @version 1.0
 * @since 1.0
 */
public class MetaDataApiController extends MgrdbApiController {
	
	@RequiresPermissions("mgr:metadata:list")
	public void tables() {
		MgrTable tbc = getTable("meta_table", MgrConstants.NONE, true);
		if (tbc == null) {
			renderJson(new AjaxResult(0, "fail", "table is not find."));
			return;
		}
		ConditionBuilder cb = getConditionsSQL(tbc).build("");
		SqlBuilder sb = new SqlBuilder();
		String select = sb.appendSelect(tbc).build();
		String sql = sb.clear().appendFrom(tbc).appendConditions(cb).build();
		Integer pNumber = getParaToInt(2, getParaToInt("pageNum", 1));
		Integer pSize = getParaToInt(1, getParaToInt("numPerPage", 15));
		Page<Record> page = Db.paginate(pNumber, pSize, select, sql, cb.getSqlParas());
		renderJson(new AjaxResult(1, "success", page));
	}
	/**
	 * 获取一条记录
	 */
	@RequiresPermissions("mgr:metadata:read")
	public void index() {
		super.index();
	}

	/**
	 * 表格列表页
	 */
	@RequiresPermissions("mgr:metadata:list")
	public void list() {
		super.list();
	}

	/**
	 * 表格列表页
	 */
	@RequiresPermissions("mgr:metadata:list")
	public void page() {
		super.page();
	}
	
	/**
	 * 自动补全
	 */
	@RequiresPermissions("mgr:metadata:read")
	public void autoc() {
		super.autoc();
	}

	/**
	 * 导出Excel
	 */
	@RequiresPermissions("mgr:metadata:export")
	public void excel() {
		super.excel();
	}

	/**
	 * 保存数据
	 * 
	 * 注：对于日期类型让转换函数自动转换 日期 逻辑： 数据库中保存 long类型，界面显示日期类型 保存：界面日期->转换函数->数据库long
	 * 查询：数据库long->转换函数->界面日期 at = json 或 redirect(url)中的url
	 */
	@Before(MgrdbValidator.class)
	@RequiresPermissions("mgr:metadata:save")
	public void save() {
		super.save();
	}

	/**
	 * 保存数据 at = json 或 redirect(url)中的url
	 */
	@Before(MgrdbValidator.class)
	@RequiresPermissions("mgr:metadata:update")
	public void update() {
		super.update();
	}

	@RequiresPermissions("mgr:metadata:delete")
	public void delete() {
		super.delete();
	}

	@RequiresPermissions("mgr:metadata:delete")
	public void delete_pl() {
		super.delete_pl();
	}

	/**
	 * 图表二维分析
	 */
	@RequiresPermissions("mgr:metadata:chart")
	public void chart() {
		super.chart();
	}

	/**
	 * 透视表分析
	 */
	@RequiresPermissions("mgr:metadata:chart")
	public void pivot() {
		super.pivot();
	}
}