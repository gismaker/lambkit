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
package com.lambkit.db.mgr.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.NotAction;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.Lambkit;
import com.lambkit.db.mgr.Chart;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.mgr.MgrConstants;
import com.lambkit.db.mgr.MgrTable;
import com.lambkit.db.mgr.MgrdbManager;
import com.lambkit.db.mgr.MgrdbService;
import com.lambkit.db.mgr.MgrdbValidator;
import com.lambkit.db.mgr.Pivot;
import com.lambkit.db.sql.condition.ConditionBuilder;
import com.lambkit.db.sql.condition.SqlBuilder;
import com.lambkit.common.AjaxResult;
import com.lambkit.common.util.DateTimeUtils;
import com.lambkit.common.util.StringUtils;
import com.lambkit.web.WebConfig;
import com.lambkit.web.controller.LambkitController;

/**
 * @author yangyong
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2016-01-22
 * @version 1.0
 * @since 1.0
 */
public class MgrdbApiController extends LambkitController {

	private final static Log _log = Log.getLog(MgrdbApiController.class);

	/**
	 * 获取一条记录
	 */
	//@RequiresPermissions("mgr:api:read")
	public void index() {
		MgrTable tbc = getBase(MgrConstants.VIEW);
		if (tbc == null) {
			renderJson(new AjaxResult(0, "fail", "table is not find."));
			return;
		}
		Record rcd = null;
		IField fld = MgrdbManager.me().getService().getFieldDao().findFirstByTbid(tbc.getModel().getId(),
				MgrConstants.NONE, "iskey", "Y");
		if (fld != null) {
			String model_id = getPara(1, getPara("id"));
			if (StrKit.notBlank(model_id)) {
				if (fld.getDatatype().startsWith("varchar")) {
					rcd = Db.findById(tbc.getName(), fld.getName(), model_id);
				} else if (fld.getDatatype().startsWith("int")) {
					rcd = Db.findById(tbc.getName(), fld.getName(), Integer.valueOf(model_id));
				}
				setAttr("model", rcd);
			}
		}
		if (rcd == null) {
			renderJson(new AjaxResult(0, "fail", "data is not find."));
		} else {
			renderJson(new AjaxResult(1, "success", rcd));
		}
	}

	/**
	 * 表格列表页
	 */
	//@RequiresPermissions("mgr:api:list")
	public void list() {
		MgrTable tbc = getBase(MgrConstants.VIEW);
		if (tbc == null) {
			renderJson(new AjaxResult(0, "fail", "table is not find."));
			return;
		}
		SqlBuilder sb = new SqlBuilder();
		String select = sb.appendSelect(tbc).build();
		String sql = sb.clear().appendFrom(tbc).build();
		Integer pNumber = getParaToInt(2, 1);
		Integer pSize = getParaToInt(1, 15);
		Page<Record> page = Db.paginate(pNumber, pSize, select, sql);
		renderJson(new AjaxResult(1, "success", page));
	}

	/**
	 * 表格列表页
	 */
	//@RequiresPermissions("mgr:api:list")
	public void page() {
		MgrTable tbc = getBase(MgrConstants.VIEW);
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
	 * 自动补全
	 */
	//@RequiresPermissions("mgr:api:read")
	public void autoc() {
		MgrTable tbc = getBase(MgrConstants.NONE);
		if (tbc == null) {
			renderJson(new AjaxResult(0, "fail", "table is not find."));
			return;
		}
		String fldname = getPara(1, getPara("fldname"));
		String keyword = getPara(2, getPara("keyword"));
		if (StringUtils.hasText(fldname)) {
			ConditionBuilder cb = getConditionsSQL(tbc).build("");
			String sql = " from \"" + tbc.getName() + "\" where 1=1 " + cb.getSql();
			if (StringUtils.hasText(keyword)) {
				sql += " and \"" + fldname + "\" like '" + keyword + "%'";
			} else {
				sql += " and \"" + fldname + "\" is not null and \"" + fldname + "\" <> '' group by \"" + fldname
						+ "\" ORDER BY \"" + fldname + "\" DESC";
			}
			Page<Record> m = Db.paginate(1, 10, "select \"" + fldname + "\" as value ", sql);
			renderJson(new AjaxResult(1, "success", m.getList()));
		} else {
			renderJson(new AjaxResult(0, "fail", "column not exist."));
		}
	}

	/**
	 * 导出Excel
	 */
	//@RequiresPermissions("mgr:api:export")
	public void excel() {
		WebConfig web = Lambkit.config(WebConfig.class);
		if (!web.isExcel()) {
			renderNull();
			return;
		}
		MgrTable tbc = getBase(MgrConstants.NONE);
		if (tbc == null) {
			renderError(404);
			return;
		}
		// String sql = getSelectSQLOfView(tbc) + getSearchSQL(tbc);
		ConditionBuilder cb = getConditionsSQL(tbc).build("");
		SqlBuilder sb = new SqlBuilder();
		String sql = sb.appendSelect(tbc).appendFrom(tbc).appendConditions(cb).appendOrderBy(getPara("orderby"))
				.build();
		// System.out.println("sql:" +sql);
		// --------------------------------------------
		getResponse().setContentType("application/octet-stream; charset=GBK");
		String fileName = tbc.getModel().getTitle();
		OutputStream os = null;
		try {
			getResponse().setHeader("Content-Disposition",
					"attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls");// 指定下载的文件名
			getResponse().setContentType("application/vnd.ms-excel");
			os = getResponse().getOutputStream();
			MgrdbManager.me().getService().exportExcel(tbc, os, sql, cb.getSqlParas());
			// 解决getOutputStream() has already been called for this response问题
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		renderNull();
	}

	/**
	 * 保存数据
	 * 
	 * 注：对于日期类型让转换函数自动转换 日期 逻辑： 数据库中保存 long类型，界面显示日期类型 保存：界面日期->转换函数->数据库long
	 * 查询：数据库long->转换函数->界面日期 at = json 或 redirect(url)中的url
	 */
	@Before(MgrdbValidator.class)
	//@RequiresPermissions("mgr:api:save")
	public void save() {
		MgrTable tbc = getBase(MgrConstants.MAP_EDIT);
		if (tbc == null) {
			renderJson(new AjaxResult(0, "fail", "table is not find."));
			return;
		}
		System.out.println("tablename:" + tbc.getName());
		String idname = tbc.getModel().getPrimaryKey();
		Record m = new Record();
		List<? extends IField> flds = tbc.getFieldList();
		setRecord(m, flds, true);
		System.out.println("id_name:" + idname);
		m.remove(idname);
		System.out.println("Record:" + m);
		boolean flag = false;
		try {
			flag = Db.save(tbc.getName(), idname, m);
			if (flag) {
				MgrdbService tcs = MgrdbManager.me().getService();
				IField geomfld = tcs.getFieldDao().findGeomField(tbc.getModel().getId(), MgrConstants.MAP);
				if (geomfld != null && StringUtils.hasText(getPara("model.lon"))
						&& StringUtils.hasText(getPara("model.lat"))) {
					/***
					 * 更新位置信息
					 */
					double lon = Double.valueOf(getPara("model.lon", "0"));
					double lat = Double.valueOf(getPara("model.lat", "0"));
					String ssql = "UPDATE " + tbc.getName() + " SET geom = st_geomfromtext('POINT(" + lon + " " + lat
							+ ")') where gid= " + m.get(tbc.getModel().getPrimaryKey());
					Db.update(ssql);
				}

				if (m.getColumns().containsKey("dataid") && m.get("dataid") == null) {
					m.set("dataid", m.get(tbc.getModel().getPrimaryKey()));
					Db.update(tbc.getName(), tbc.getModel().getPrimaryKey(), m);
				}
			}
		} catch (Exception ex) {
			_log.error(tbc.getModel().getName()+ "保存数据异常", ex);
		}
		if (flag) {
			renderJson(new AjaxResult(0, "fail", "data is not find."));
		} else {
			renderJson(new AjaxResult(1, "success", null));
		}
	}

	@NotAction
	private void setRecord(Record m, List<? extends IField> flds, boolean bsave) {
		for (int i = 0; i < flds.size(); i++) {
			IField fld = flds.get(i);
			if (StrKit.notBlank(getPara("model." + fld.getName()))) {
				// 需要进行类型转换
				if (fld.getDatatype().startsWith("float") || fld.getDatatype().startsWith("double")
						|| fld.getDatatype().startsWith("number") || fld.getDatatype().startsWith("numeric")) {
					m.set(fld.getName(), Double.parseDouble(getPara("model." + fld.getName())));
				} else if (fld.getDatatype().startsWith("int8") || fld.getDatatype().startsWith("int(11)")) {
					m.set(fld.getName(), getParaToLong("model." + fld.getName()));
				} else if (fld.getDatatype().startsWith("long") || fld.getDatatype().equals("int unsigned")) {
					m.set(fld.getName(), getParaToLong("model." + fld.getName()));
				} else if (fld.getDatatype().startsWith("int")) {
					m.set(fld.getName(), getParaToInt("model." + fld.getName()));
				} else if (fld.getDatatype().startsWith("datetime")) {
					String dt = getPara("model." + fld.getName());
					if (dt != null && !"".equals(dt.trim())) {
						Date d = DateTimeUtils.stringToDate(dt.trim());
						m.set(fld.getName(), DateTimeUtils.dateToTimestamp(d));
					}
				} else if (fld.getDatatype().startsWith("date")) {
					// 需要将java.util.date 转换成Timestamp
					Date d = getParaToDate("model." + fld.getName());
					m.set(fld.getName(), DateTimeUtils.dateToTimestamp(d));
				} else {
					m.set(fld.getName(), getPara("model." + fld.getName()));
				}
			} else {
				Long time = DateTimeUtils.getCurrentTimeLong();
				if (bsave) {
					if (fld.getName().equals("create_time") || "create_time".equals(fld.getStr("ext05"))) {
						m.set(fld.getName(), time);
					}
					if (fld.getName().equals("check_status") || "status".equals(fld.getStr("ext05"))) {
						if (hasUser() && getUser().getType() == 0) {
							m.set(fld.getName(), "1");
						} else {
							m.set(fld.getName(), "0");
						}
					}
					if (fld.getName().equals("create_user") || "create_user".equals(fld.getStr("ext05"))) {
						if (hasUser()) {
							m.set(fld.getName(), getUser().getId());
						}
					}
				} else {
					if (fld.getName().equals("reply_time") || "reply_time".equals(fld.getStr("ext05"))) {
						m.set(fld.getName(), time);
					}
					if (fld.getName().equals("reply_user") || "reply_user".equals(fld.getStr("ext05"))) {
						if (hasUser()) {
							m.set(fld.getName(), getUser().getId());
						}
					}
				}
				if (fld.getName().equals("modify_time") || "modify_time".equals(fld.getStr("ext05"))) {
					m.set(fld.getName(), time);
				}
				if (hasUser() && fld.getName().equals("user_uuid")) {
					m.set("user_uuid", getUser().getUuid());
				}
			}
			if (!m.getColumns().containsKey("dataid") && fld.getName().equals("dataid")) {
				m.set("dataid", null);
			}
		}
	}

	/**
	 * 保存数据 at = json 或 redirect(url)中的url
	 */
	@Before(MgrdbValidator.class)
	//@RequiresPermissions("mgr:api:update")
	public void update() {
		MgrTable tbc = getBase(MgrConstants.MAP_EDIT);
		if (tbc == null) {
			renderJson(new AjaxResult(0, "fail", "table is not find."));
			return;
		}
		Record m = new Record();
		List<? extends IField> flds = tbc.getFieldList();
		setRecord(m, flds, false);
		if (m.getColumns().containsKey("dataid") && !StringUtils.hasText(getPara("model.dataid"))) {
			m.set("dataid", m.get(tbc.getModel().getPrimaryKey()));
		}
		boolean flag = Db.update(tbc.getName(), tbc.getModel().getPrimaryKey(), m);
		if (flag) {
			MgrdbService tcs = MgrdbManager.me().getService();
			IField geomfld = tcs.getFieldDao().findGeomField(tbc.getModel().getId(), MgrConstants.MAP);
			if (geomfld != null && StringUtils.hasText(getPara("model.lon"))
					&& StringUtils.hasText(getPara("model.lat"))) {
				/***
				 * 更新位置信息
				 */
				double lon = Double.valueOf(getPara("model.lon", "0"));
				double lat = Double.valueOf(getPara("model.lat", "0"));
				String ssql = "UPDATE " + tbc.getName() + " SET " + geomfld.getName() + " = st_geomfromtext('POINT("
						+ lon + " " + lat + ")') where gid= " + m.get(tbc.getModel().getPrimaryKey());
				System.out.println(ssql);
				Db.update(ssql);
			}
		}
		if (flag) {
			renderJson(new AjaxResult(0, "fail", "data is not find."));
		} else {
			renderJson(new AjaxResult(1, "success", null));
		}
	}

	//@RequiresPermissions("mgr:api:delete")
	public void delete() {
		MgrTable tbc = getBase(MgrConstants.NONE);
		if (tbc == null) {
			renderJson(new AjaxResult(0, "fail", "table is not find."));
			return;
		}
		int model_id = getParaToInt("id");
		boolean del = Db.deleteById(tbc.getName(), tbc.getModel().getPrimaryKey(), model_id);
		if (del) {
			renderJson(new AjaxResult(0, "fail", "data is not find."));
		} else {
			renderJson(new AjaxResult(1, "success", null));
		}
	}

	//@RequiresPermissions("mgr:api:delete")
	public void delete_pl() {
		MgrTable tbc = getBase(MgrConstants.NONE);
		if (tbc == null) {
			renderJson(new AjaxResult(0, "fail", "table is not find."));
			return;
		}
		String[] ids = getParaValues("id");
		boolean del = false;
		for (String model_id : ids) {
			del = Db.deleteById(tbc.getName(), tbc.getModel().getPrimaryKey(), Integer.parseInt(model_id));
		}
		if (del) {
			renderJson(new AjaxResult(0, "fail", "data is not find."));
		} else {
			renderJson(new AjaxResult(1, "success", null));
		}
	}

	/**
	 * 图表二维分析
	 */
	//@RequiresPermissions("mgr:api:chart")
	public void chart() {
		MgrTable tbc = getBase(MgrConstants.OLAP);
		if (tbc == null) {
			renderJson(new AjaxResult(0, "fail", "table is not find."));
			return;
		}
		String cls = getPara("cls");
		if (!StringUtils.hasText(cls)) {
			renderJson(new AjaxResult(0, "fail", "维度不存在，请选择维度！"));
			renderJson();
		}
		Chart chart = new Chart(true);
		chart.setClassify(cls);
		String serias = getPara("serias");
		if (!StringUtils.hasText(serias)) {
			renderJson(new AjaxResult(0, "fail", "度量不存在，请选择度量！"));
			return;
		}
		String yuns = getPara("yuns", "SUM");
		chart.setOperation(yuns);
		chart.setClassifyName(tbc.getName(), cls);
		chart.setSerias(tbc, serias, "");
		ConditionBuilder cb = getConditionsSQL(tbc).build("");
		SqlBuilder sb = new SqlBuilder();
		String sql = sb.append("SELECT ").append(cls).append(",").append(chart.getSeriasSQL()).appendFrom(tbc)
				.appendConditions(cb).append(" GROUP BY ").append(cls).appendOrderBy(cls).build();
		chart.setDataSQL(sql);
		chart.setSqlParas(cb.getSqlParas());
		List<Record> list = Db.find(chart.getDataSQL(), chart.getSqlParas());
		renderJson(new AjaxResult(1, "success", list));
	}

	/**
	 * 透视表分析
	 */
	//@RequiresPermissions("mgr:api:chart")
	public void pivot() {
		MgrTable tbc = getBase(MgrConstants.OLAP);
		if (tbc == null) {
			renderJson(new AjaxResult(0, "fail", "table is not find."));
			return;
		}
		String row = getPara("row");
		String column = getPara("column");
		String measure = getPara("measure");
		if (!StringUtils.hasText(row)) {
			renderJson(new AjaxResult(0, "fail", "维度不存在，请选择维度！"));
			renderJson();
		}
		if (!StringUtils.hasText(measure)) {
			renderJson(new AjaxResult(0, "fail", "度量不存在，请选择度量！"));
			return;
		}
		Pivot pivot = new Pivot(tbc, row, column, measure, "");
		// set where sql
		ConditionBuilder cb = getConditionsSQL(tbc).build("");
		SqlBuilder sb = new SqlBuilder();
		String wsql = sb.clear().appendConditions(cb).build();
		pivot.setSql(tbc, wsql, cb.getSqlParas());

		renderJson(new AjaxResult(1, "success", pivot.getTable()));
	}
}