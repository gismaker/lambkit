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
package com.lambkit.db.mgr;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.common.util.StringUtils;
import com.lambkit.db.sql.condition.SqlBuilder;


public class Pivot {
	
	private String prefix;
	private String rowSql;
	private String columnSql;
	private String measureSql;
	private String dataSql;
	private String whereSql;
	private Object[] sqlParas;
	
	PivotTable table;
	
	/**
	 * 等价
	 * select sum(case when year='2008' and water_system='长江' then pH else null end) as pH from wq_river_section;
	 * select sum(pH) as pH from wq_river_section where year='2008' and water_system='长江';
	 */
	
	public Pivot(MgrTable tbc, String row, String column, String measure, String prefix) {
		rowSql = "";
		measureSql = "";
		table = new PivotTable();
		setPrefix(prefix);
		setColumn(tbc, column, prefix);
		setRow(tbc, row, prefix);
		setMeasure(tbc, measure, prefix);
		int collen = StrKit.notBlank(column) ? column.split(",").length : 0;
		table.setRowlen(collen+1);
	}
	
	public PivotTable getTable() {
		return table;
	}
	
	public String getMeasureSql() {
		return measureSql;
	}
	public void setMeasureSql(String measureSql) {
		this.measureSql = measureSql;
	}

	public String getDataSql() {
		return dataSql;
	}

	public void setDataSql(String dataSql) {
		this.dataSql = dataSql;
	}
	public Object[] getSqlParas() {
		return sqlParas;
	}

	public void setSqlParas(Object[] sqlParas) {
		this.sqlParas = sqlParas;
	}

	public String getRowSql() {
		return rowSql;
	}

	public void setRowSql(String rowSql) {
		this.rowSql = rowSql;
	}

	public String getColumnSql() {
		return columnSql;
	}

	public void setColumnSql(String columnSql) {
		this.columnSql = columnSql;
	}
	
	public String getWhereSql() {
		return whereSql;
	}

	public void setWhereSql(String whereSql) {
		this.whereSql = whereSql;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	/*
	public String getSelectSql() {
		String selectSql = "";
		if(StringUtils.hasText(rowSql)) selectSql += " " + rowSql;
		if(StringUtils.hasText(measureSql)) selectSql += "," + measureSql;
		selectSql += " ";
		return selectSql;
	}
	*/
	/**
	 * 加入where过滤条件
	 * @param tbc
	 * @param whereSql
	 * @param sqlParas
	 */
	public void setSql(MgrTable tbc, String whereSql, Object[] sqlParas) {
		setWhereSql(whereSql);
		setSqlParas(sqlParas);
		String column = getColumnSql();
		SqlBuilder sb = new SqlBuilder();
		String sql = sb.append("select ").append(column).appendFrom(tbc)
		.append(getWhereSql()).append(" group by ").append(column)
		.append(" order by ").append(column).append(" desc").build();
		// System.out.println("getColumn SQL="+sql);
		List<Record> list = tbc.db().find(sql, getSqlParas());
		List<IField> mns = table.getMeasures();
		List<Record> colHead = new ArrayList<>();
		for (int m = 0; m < list.size(); m++) {
			for(int j=0; j<mns.size(); j++) {
				Record nrd = new Record();
				nrd.setColumns(list.get(m));
				nrd.set(mns.get(j).getName(), mns.get(j).getTitle());
				colHead.add(nrd);
			}
		}
		table.setColHead(colHead);
		//
		setRowCategory(tbc);
		setColCategory(tbc);
		setData(tbc);
		//over
	}
	
	private void setRowCategory(MgrTable tbc) {
		String row = getRowSql();
		SqlBuilder sb = new SqlBuilder();
		String sql = sb.append("select ").append(row).appendFrom(tbc)
		.append(" group by ").append(row)
		.append(" order by ").append(row).append(" desc").build();
		// System.out.println("getColumn SQL="+sql);
		List<Record> list = tbc.db().find(sql);
		table.setRowCategory(list);
	}

	private void setColCategory(MgrTable tbc) {
		String column = getColumnSql();
		if(StrKit.isBlank(column)) return;
		SqlBuilder sb = new SqlBuilder();
		String sql = sb.append("select ").append(column).appendFrom(tbc)
		.append(" group by ").append(column)
		.append(" order by ").append(column).append(" desc").build();
		// System.out.println("getColumn SQL="+sql);
		List<Record> list = tbc.db().find(sql);
		List<IField> mns = table.getMeasures();
		List<Record> cols = new ArrayList<>();
		sb.clear();
		for (int m = 0; m < list.size(); m++) {
			for(int j=0; j<mns.size(); j++) {
				Record nrd = new Record();
				nrd.setColumns(list.get(m));
				IField fld = mns.get(j);
				nrd.set(fld.getName(), fld.getTitle());
				cols.add(nrd);
				sb.append(", ").append(getMeasureSelectSql(nrd, fld, m));
			}
		}
		String selectSql = sb.build();
		measureSql = StrKit.notBlank(selectSql) ? selectSql.substring(1) : measureSql;
		table.setColCategory(cols);
	}
	
	public void setData(MgrTable tbc) {
		String row = getRowSql();
		SqlBuilder sb = new SqlBuilder();
		String sql = sb.append("select ").append(rowSql).append(", ").append(measureSql).appendFrom(tbc)
				.append(getWhereSql()).append(" group by ").append(row)
				.append(" order by ").append(row).append(" desc").build();
		// System.out.println("getColumn SQL="+sql);
		List<Record> list = tbc.db().find(sql, getSqlParas());
		table.setData(list);
	}
	
	private String getMeasureSelectSql(Record col, IField ms, int n) {
		String sql = "";
		//sum(case when year='2008' and water_system='长江' then pH else null end) as pH
		SqlBuilder sb = new SqlBuilder();
		sb.append(ms.getStr("lk0pt1ysf")).append("(case when ");
		List<IField> columns = table.getColumns();
		for(int i=0; i<columns.size(); i++) {
			IField fld = columns.get(i);
			String name = fld.getName();
			if(i>0) sb.append(" and ");
			sb.append(name).append("=");
			String value = col.get(name);
			String measure_type = fld.getDatatype();
			if(measure_type.contains("varchar") || measure_type.contains("date")) {
				sb.append("'").append(value).append("'");
			} else {
				sb.append(value);
			}
		}
		sb.append(" then ").append(ms.getName()).append(" else null end) as ");
		String extname = ms.getName() + "_" + n;
		sb.append(extname);
		sql = sb.build();
		System.out.println("----"+sql);
		return sql;
	}
	
	/**
	 * 设置行
	 * @param tbc
	 * @param row
	 * @param prefix
	 * @return
	 */
	private Pivot setRow(MgrTable tbc, String row, String prefix) {
		if(!StringUtils.hasText(row)) {
			return this;
		}
		String theSql = "";
		String[] rows = row.split(",");
		for (int i = 0; i < rows.length; i++) {
			String ss=  rows[i].trim();
			if(StringUtils.hasText(ss)) {
				theSql += "," + prefix + ss;
				table.addRow(getField(tbc, ss));
			}
		}
		this.setRowSql(theSql.substring(1));
		return this;
	}
	
	/**
	 * 设置列
	 * @param tbc
	 * @param column
	 * @param prefix
	 * @return
	 */
	private Pivot setColumn(MgrTable tbc, String column, String prefix) {
		if(!StringUtils.hasText(column)) {
			return this;
		}
		String theSql = "";
		String[] columns = column.split(",");
		for (int i = 0; i < columns.length; i++) {
			String ss=  columns[i].trim();
			if(StringUtils.hasText(ss)) {
				theSql += "," + prefix + ss;
				table.addColumn(getField(tbc, ss));
			}
		}
		this.setColumnSql(theSql.substring(1));
		return this;
	}
	/**
	 * 设置度量
	 * @param tbc table
	 * @param measure COUNT(value)
	 * @param prefix
	 * @return
	 */
	private Pivot setMeasure(MgrTable tbc, String measure, String prefix) {
		if(!StringUtils.hasText(measure)) {
			return this;
		}
		String theSql = "";
		String[] sers = measure.split(",");
		for (int i = 0; i < sers.length; i++) {
			String ss=  sers[i].trim();
			if(!StringUtils.hasText(ss)) continue;
			int fst = ss.indexOf("(");
			if(fst < 1) continue;
			String yun = ss.substring(0, fst).trim();
			String themeasure = ss.substring(fst + 1, ss.indexOf(")")).trim();
			String ssSql = getOneMeasure(tbc, themeasure, yun, prefix);
			if(StringUtils.hasText(ssSql)) {
				theSql += "," + ssSql;
				IField fld = getField(tbc, themeasure);
				fld.putAttr("lk0pt1ysf", yun);
				table.addMeasure(fld);
			}
		}
		this.setMeasureSql(theSql.substring(1));
		return this;
	}

	/**
	 * 获取单个序列的Sql
	 * @param measure
	 * @param yuns
	 * @param prefix
	 * @return
	 */
	private String getOneMeasure(MgrTable tbc, String measure, String yuns, String prefix) {
		measure = measure.trim();
		String measure_type = MgrdbManager.me().getService().getColumnType(tbc.getModel().getId(), measure);
		if(measure_type=="") {
			return null;
		}
		IField fld = MgrdbManager.me().getService().getFieldDao().findFirstByTbid(tbc.getModel().getId(), MgrConstants.OLAP, "fldname", measure);
		if(fld!=null && fld.getIskey().equals("Y")) {
			yuns = "COUNT";
		} else if(measure_type.endsWith("Integer") ||
			measure_type.endsWith("Long") ||
			measure_type.endsWith("Float") ||
			measure_type.endsWith("Double") || 
			measure_type.startsWith("int") ||
			measure_type.startsWith("long") ||
			measure_type.startsWith("float") ||
			measure_type.startsWith("double") ||
			measure_type.startsWith("number") ||
			measure_type.startsWith("numeric")) {
			if(yuns.equalsIgnoreCase("COUNT")) yuns = "SUM";
		} else {
			yuns = "COUNT";
		}
		return measure = yuns + "(" + prefix + measure + ") as " + measure + "_" + yuns.toLowerCase();
	}
	/*
	private String getFieldName(TableConfig tbc, String fldname) {
		IField fldm = getField(tbc, fldname);
		fldname = fldm==null ? fldname : fldm.getShowName();
		return fldname;
	}
	*/
	private IField getField(MgrTable tbc, String fldname) {
		return MgrdbManager.me().getService().getFieldDao().findFirstByTbName(tbc.getName(), MgrConstants.OLAP, "fldname", fldname);
	}
}
