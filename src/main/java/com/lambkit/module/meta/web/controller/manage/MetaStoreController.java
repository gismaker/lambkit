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
package com.lambkit.module.meta.web.controller.manage;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.common.base.ResultJson;
import com.lambkit.db.mgr.MgrConstants;
import com.lambkit.db.mgr.MgrTable;
import com.lambkit.db.sql.condition.ConditionBuilder;
import com.lambkit.db.sql.condition.SqlBuilder;
import com.lambkit.module.meta.model.MetaStore;
import com.lambkit.module.meta.model.MetaStoreDb;
import com.lambkit.module.meta.model.MetaStoreResource;
import com.lambkit.module.meta.model.MetaStoreRoute;
import com.lambkit.web.controller.BaseController;

/**
 * @author yangyong
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2016-01-22
 * @version 1.0
 * @since 1.0
 */
public class MetaStoreController extends BaseController {
	private final String tableName = "meta_store";
	
	public void index() {
		MgrTable tbc = getTable(tableName, MgrConstants.NONE, false);
		if (tbc == null) {
			renderError(404);
			return;
		}
		Long id = getParaToLong(0);
		if(id!=null) {
			setAttr("model", MetaStore.service().findById(id));
			setAttr("dbs", MetaStoreDb.service().find(MetaStoreDb.sql().andSidEqualTo(id).example()));
			setAttr("resources", MetaStoreResource.service().find(MetaStoreResource.sql().andSidEqualTo(id).example()));
			setAttr("routes", MetaStoreRoute.service().find(MetaStoreRoute.sql().andSidEqualTo(id).example()));
		}
		keepPara();
		renderTemplate("index.html");
	}
	
	public void list() {
		MgrTable tbc = getTable(tableName, MgrConstants.VIEW, false);
		if (tbc == null) {
			renderError(404);
			return;
		}
		setAttr("stores", MetaStore.service().find(MetaStore.sql().example()));
		keepTable(tbc);
		keepPara();
		renderTemplate("list.html");
	}
	
	public void page() {
		MgrTable tbc = getTable(tableName, MgrConstants.VIEW, false);
		if (tbc == null) {
			renderJson(new ResultJson(0, "fail", "table is not find."));
			return;
		}
		ConditionBuilder cb = getConditionsSQL(tbc).build("");
		SqlBuilder sb = new SqlBuilder();
		String select = sb.appendSelect(tbc).build();
		String sql = sb.clear().appendFrom(tbc).appendConditions(cb).build();
		Integer pNumber = getParaToInt(2, getParaToInt("pageNum", 1));
		Integer pSize = getParaToInt(1, getParaToInt("numPerPage", 15));
		Page<Record> page = Db.paginate(pNumber, pSize, select, sql, cb.getSqlParas());
		renderJson(new ResultJson(1, "success", page));
	}
	
	public void add() {
		MgrTable tbc = getTable(tableName, MgrConstants.VIEW, false);
		if (tbc == null) {
			renderError(404);
			return;
		}
		keepPara();
		renderTemplate("edit.html");
	}
	
	public void save() {
		MetaStore app = getModel(MetaStore.class, "model");
		app.save();
		renderJson(new ResultJson(1, "success", null));
	}
	
	public void edit() {
		MgrTable tbc = getTable(tableName, MgrConstants.VIEW, false);
		if (tbc == null) {
			renderError(404);
			return;
		}
		Long id = getParaToLong(0);
		if(id!=null) {
			setAttr("model", MetaStore.service().findById(id));
		}
		keepPara();
		renderTemplate("edit.html");
	}
	
	public void update() {
		MetaStore app = getModel(MetaStore.class, "model");
		app.update();
		renderJson(new ResultJson(1, "success", null));
	}
	
	public void delete() {
		Long id = getParaToLong(0);
		if(id!=null) {
			MetaStore.service().deleteById(id);
		}
		renderJson(new ResultJson(1, "success", null));
	}
	
}