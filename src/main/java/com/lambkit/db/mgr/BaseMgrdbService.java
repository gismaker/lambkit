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

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.common.util.DateTimeUtils;
import com.lambkit.common.util.JXLExcelUtils;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.db.dialect.LambkitDialect;
import com.lambkit.db.dialect.LambkitPostgreSqlDialect;
import com.lambkit.db.meta.MetaKit;
import com.lambkit.db.meta.TableMeta;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.mgr.IFieldDao;
import com.lambkit.db.mgr.ITable;
import com.lambkit.db.mgr.ITableDao;
import com.lambkit.db.mgr.MgrdbConfig;
import com.lambkit.db.mgr.MgrTable;
import com.lambkit.db.mgr.MgrdbService;
import com.lambkit.db.sql.condition.ConditionBuilder;
import com.lambkit.db.sql.condition.SqlBuilder;
import com.lambkit.web.controller.LambkitController;

public abstract class BaseMgrdbService implements MgrdbService {

	public abstract ITableDao getTableDao();

	public abstract IFieldDao getFieldDao();

	public MgrTable createTable(Object tbid, int type) {
		return createTable(tbid, type, null);
	}

	public MgrTable createTable(String tableName, int type) {
		return createTable(tableName, type, null);
	}

	public MgrTable createTable(Object tbid, int type, String orderby) {
		ITable tb = getTableDao().findById(tbid);
		return createTable(tb, type, orderby);
	}

	public MgrTable createTable(String tableName, int type, String orderby) {
		ITable tb = getTableDao().findByName(tableName);
		return createTable(tb, type, orderby);
	}

	public MgrTable createTable(ITable tb, int type, String orderby) {
		if(tb==null) return null;
		MgrTable mtb = new MgrTable();
		if(type==MgrConstants.MAP || type==MgrConstants.MAP_EDIT) {
			tb.configMap();
			type = type==MgrConstants.MAP_EDIT ? MgrConstants.EDIT : type;
		}
		mtb.setModel(tb);
		mtb.setName(tb.getName());
		Object tbid = tb.getId();
		if (tbid != null) {
			mtb.setFieldList(getFieldDao().findByTbid(tbid, type, orderby));
			if(StrKit.isBlank(tb.getPkey())) {
				tb.setPkey(getFieldDao().getPkey(tbid));
				tb.update();
			}
		}
		MgrdbConfig config = ConfigManager.me().get(MgrdbConfig.class);
		String prefix = config.getTableRemovePrefixes();
		mtb.setMeta(MetaKit.createTable(null, tb.getName(), prefix));
		return mtb;
	}
	
	public MgrTable createTableWithoutMeta(String tableName, int type, String orderby) {
		ITable tb = getTableDao().findByName(tableName);
		if (tb != null) {
			MgrTable mtb = new MgrTable();
			if(type==MgrConstants.MAP || type==MgrConstants.MAP_EDIT) {
				tb.configMap();
				type = type==MgrConstants.MAP_EDIT ? MgrConstants.EDIT : type;
			}
			mtb.setModel(tb);
			mtb.setName(tb.getName());
			Object tbid = tb.getId();
			if (tbid != null) {
				mtb.setFieldList(getFieldDao().findByTbid(tbid, type, orderby));
				tb.setPkey(getFieldDao().getPkey(tbid));
			}
			return mtb;
		}
		return null;
	}
	
	public MgrTable createTableWithoutModel(String tableName, int type) {
		MgrTable mtb = new MgrTable();
		MgrdbConfig config = ConfigManager.me().get(MgrdbConfig.class);
		String prefix = config.getTableRemovePrefixes();
		mtb.setMeta(MetaKit.createTable(null, tableName, prefix));
		mtb.setName(tableName);
		return mtb;
	}

	public void recordLongToDate(Record rod,MgrTable tbc)
	{
		List<? extends IField> flds = tbc.getFieldList();
		for(int i=0; i<flds.size(); i++) {
			IField fld = flds.get(i);  
			if(fld.getDatatype().indexOf("time") > -1) { 
				Object obj = rod.get(fld.getName()); 
				if(obj != null && obj.toString().length() > 0)
				    rod.set(fld.getName(), DateTimeUtils.longToString((Long)obj));
				else
					rod.set(fld.getName(), DateTimeUtils.dateToString(new Date())); 
			} 
		}
	}
	
	/**
	 * 将对象的日期,date数据类型转化成Long 
	 *  
	 * @param rod
	 * @param tbc
	 * @author saga
	 */
	public void recordDateToLong(Record rod, MgrTable tbc)
	{ 
		List<? extends IField> flds = tbc.getFieldList();
		try{
			for(int i=0; i<flds.size(); i++) {
				IField fld = flds.get(i);  
				/****
				 * 包含date 类型的要转换 long
				 */
				if(fld.getDatatype().indexOf("time") > -1) { 
					Object obj = rod.get(fld.getName()); 
					if(obj != null && obj.toString().length() > 0)
					    rod.set(fld.getName(), ((Date)obj).getTime());
					else
						rod.set(fld.getName(), new Date().getTime());
						
				} 
			} 
		}catch(Exception ex)
		{
			System.out.println("日期转换错误："+ex.getMessage());
		}
	}
	
	/**
	 * 字段列表
	 * @return
	 */
	public List<String> getColumnNames(MgrTable tbc)
	{
		List<String> list = new ArrayList<String>();
		List<? extends IField> flds = tbc.getFieldList();
		for(int i=0; i<flds.size();i++) {
			list.add(flds.get(i).getName());
		}
		return list;
	}
	/**
	 * 字段
	 * @return
	 */
	public String getSelectNames(MgrTable tbc, String alis)
	{
		String fldnames = "";
		List<? extends IField> flds = tbc.getFieldList();
		for(int i=0; i<flds.size();i++) {
			fldnames += "," + alis + settingNameOfDialect(flds.get(i).getName(), tbc.getDialect());
		}
		return fldnames.substring(1);
	}

	public String getSelectNamesOfView(MgrTable tbc, String alis)
	{
		StringBuilder sb = new StringBuilder();
		List<? extends IField> flds = tbc.getFieldList();
		for(int i=0; i<flds.size();i++) {
			if(flds.get(i).getIskey().equals("Y") || flds.get(i).getIsview().equals("Y")) {
				if(flds.get(i)==null || StrKit.isBlank(flds.get(i).getName())) {
					continue;
				}
				sb.append(",").append(alis).append(settingNameOfDialect(flds.get(i).getName(), tbc.getDialect()));
			}
		}
		String fldnames = sb.toString();
		if(fldnames.length() > 1) fldnames = fldnames.substring(1);
		if(fldnames.trim().length() < 1) fldnames = alis+"*";
		return fldnames;
	}
	
	public String getSelectNamesOfViewInJoin(MgrTable tbc, String alis)
	{
		String ren = StrKit.notBlank(alis) ? alis.replace(".", "_") : null;
		StringBuilder sb = new StringBuilder();
		List<? extends IField> flds = tbc.getFieldList();
		for(int i=0; i<flds.size();i++) {
			if(flds.get(i).getIskey().equals("N") && flds.get(i).getIsview().equals("Y")) {
				if(flds.get(i)==null || StrKit.isBlank(flds.get(i).getName())) {
					continue;
				}
				sb.append(",").append(alis).append(settingNameOfDialect(flds.get(i).getName(), tbc.getDialect()));
				if(StrKit.notBlank(ren)) {
					String rename = settingNameOfDialect(ren + flds.get(i).getName(), tbc.getDialect());
					sb.append(" as ").append(rename);
				}
			}
		}
		String fldnames = sb.toString();
		if(fldnames.length() > 1) fldnames = fldnames.substring(1);
		if(fldnames.trim().length() < 1) fldnames = alis+"*";
		return fldnames;
	}
	
	protected String settingNameOfDialect(String name, LambkitDialect dialect) {
		if(StrKit.isBlank(name)) return name;
		if(dialect!=null) {
			if(dialect instanceof LambkitPostgreSqlDialect) {
				return "\"" + name + "\"";
			}
		}
		return name;
	}
	
	public String getSelectNamesOfView(MgrTable tbc, String fld, Object val)
	{
		StringBuilder sb = new StringBuilder();
		List<? extends IField> flds = tbc.getFieldList();
		for(int i=0; i<flds.size();i++) {
			if(flds.get(i).getIskey().equals("Y") || flds.get(i).getAttr(fld).equals(val)) {
				sb.append(",");
				sb.append(settingNameOfDialect(flds.get(i).getName(), tbc.getDialect()));
			}
		}
		String fldnames = sb.toString();
		if(fldnames.length() > 1) fldnames = fldnames.substring(1);
		if(fldnames.trim().length() < 1) fldnames = "*";
		return fldnames;
	}
	
	/**
	 * 获取字段类型
	 * @param field
	 * @return
	 */
	public String getColumnType(Object tbid, String field) {
		IField fld = getFieldDao().findFirstByTbid(tbid, MgrConstants.NONE, "fldname", field);
		if(fld==null) return "";
		return fld.getDatatype();
	}
	
	public SqlBuilder getSelectSQLOfView(SqlBuilder sb, MgrTable tbc, String alis) {
		sb.append("select ");
		sb.append(getSelectNamesOfView(tbc, alis));
		return sb;
	}
	
	public SqlBuilder getSelectNamesOfView(SqlBuilder sb, MgrTable tbc, String fld, Object val) {
		sb.append("select ");
		sb.append(getSelectNamesOfView(tbc, fld, val));
		return sb;
	}
	
	public ConditionBuilder getConditionsSQL(LambkitController c, MgrTable tbc) {
		ConditionBuilder builder = new ConditionBuilder();
		//ConditonsParam sqlParam = new ConditonsParam(new Conditions());
		List<? extends IField> flds = tbc.getFieldList();
		for(int i=0; i<flds.size(); i++) {
			IField fld = flds.get(i);
			if(fld.getIsselect().toUpperCase().equals("Y"))
			{
				String tname = settingNameOfDialect(fld.getName(), tbc.getDialect());
				builder.append(tname, c.getParaTrans(fld.getName()), fld.getDatatype());
			}
		}
		//ConditonsParam sqlParam = builder.get(prefix);
        //log.info("getConditionsSQL sql:" + sqlParam.getSql());
		return builder;
	}
	
	/**
	 * 输出Excel
	 */
	public void exportExcel(MgrTable tbc, OutputStream os, String sql) {
		List<Record> mlist = Db.find(sql);
		toExcel(tbc, os, mlist, "*");
	}
	
	/**
	 * 输出Excel
	 */
	public void exportExcel(MgrTable tbc, OutputStream os, String sql, Object[] paras) {
		List<Record> mlist = Db.find(sql, paras);
		toExcel(tbc, os, mlist, "*");
	}
	
	/**
	 * 输出Excel
	 */
	public void toExcel(MgrTable tbc, OutputStream os, List<Record> mlist, String selectSQL) {
		List<? extends IField> flds = tbc.getFieldList();
		JXLExcelUtils exl = new JXLExcelUtils();
		int c=0, r=0;
		selectSQL = selectSQL.replace(" ", "");
		if(selectSQL.equals("*")) {
			String fldnames = "";
			for(int i=0; i<flds.size();i++) {
				fldnames += "," + settingNameOfDialect(flds.get(i).getName(), tbc.getDialect());
			}
			selectSQL = fldnames.substring(1);
		}
		exl.createWorkBook(os).createSheet(tbc.getModel().getStr("tbcnn"), 0);
		//boolean ball = false;
		//if(selectSQL.equals("*")) ball = true;
		//else selectSQL += ",";
		selectSQL += ",";
		for(IField fld : flds) {
			if(fld.getStr("ext01").equals("N")) continue;
			if(selectSQL.contains(fld.getName() + ",")) {
				exl.addCell(c, r, fld.getTitle());
				c++;
			}
		}
		c=0;
		r++;
		for(Record m : mlist) {
			for(IField fld : flds) {
				if(fld.getStr("ext01").equals("N")) continue;
				if(selectSQL.contains(fld.getName() + ",")) {
					addCell(exl, m, fld, c, r, fld.getName());
					c++;
				}
			}
			c=0;
			r++;
		}
		exl.export();
		//return tbcnn;
	}
	
	private void addCell(JXLExcelUtils exl, Record m, IField fld, int c, int r, String name) {
		if(m.get(name)==null) exl.addCell(c, r,"");
		else if(fld.getDatatype().startsWith("int")) exl.addCell(c, r, m.getInt(name));
		else if(fld.getDatatype().startsWith("long")) exl.addCell(c, r, m.getLong(name));
		else if(fld.getDatatype().startsWith("float")) exl.addCell(c, r, m.getFloat(name));
		else if(fld.getDatatype().startsWith("double")) exl.addCell(c, r, m.getDouble(name));
		else if(fld.getDatatype().startsWith("varchar")) exl.addCell(c, r, m.getStr(name));
		else if(fld.getDatatype().startsWith("timestamp")) exl.addCell(c, r, m.getDate(name));
		else exl.addCell(c, r, m.get(name).toString());
	}
	
	public void tablesToMgrdb(Map<String, Object> options) {
		MgrdbService service = MgrdbManager.me().getService();
		Map<String, TableMeta> tableMetas = MetaKit.getTableMetas(options);
		for (Entry<String, TableMeta> entry : tableMetas.entrySet()) {
			service.tableToMgrdb(entry.getValue());
        }
	}
}
