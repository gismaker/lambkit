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
package com.lambkit.module.sysconfig;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.lambkit.db.mgr.ITable;
import com.lambkit.db.mgr.ITableDao;
import com.lambkit.db.mgr.util.MgrDb;

/**
 * @author yangyong
 * @website: www.epgw.com.cn
 * @email: epgw@foxmail.com
 * @date 2015-08-13
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class TableconfigModel extends Model<TableconfigModel> implements ITable, ITableDao {

	public static final TableconfigModel dao = new TableconfigModel();

	public static final String TABLE = "sys_tableconfig";
	
	//必要字段
	public static final String TBID = "tbid";
	public static final String TBNAME = "tbname";
	public static final String TBCNN = "tbcnn";
	public static final String TBCREATETIME = "tbcreatetime";
	public static final String TBMODIFYTIME = "tbmodifytime";
	public static final String TBNAVTYPEID = "tbnavtypeid";
	public static final String ISCREATE = "iscreate";
	
	//非必要字段
	public static final String ISDELETE = "isdelete";
	public static final String ISEDIT = "isedit";
	public static final String ISPAGES = "ispages";
	public static final String ISALLSEL = "isallsel";
	public static final String ISORDER = "isorder";
	public static final String ISDIYCOL = "isdiycol";
	public static final String DIYCOLNAME = "diycolname";
	public static final String TBORDER = "tborder";
	public static final String TBROLE = "tbrole";
	public static final String DELNAME = "delname";
	public static final String META = "meta";
	public static final String EXT01 = "ext01";
	public static final String EXT02 = "ext02";
	public static final String EXT03 = "ext03";
	public static final String EXT04 = "ext04";
	public static final String EXT05 = "ext05";
	
	private String primaryKey;

	public TableconfigModel findByName(String name) {
		return findFirst("select * from sys_tableconfig where tbname=?", name);
	}

	public int deleteByTbId(Object tbid) {
		return deleteById(tbid) ? 0 : -1;
	}
	
	public List<TableconfigModel> findByWhere(String where) {
		if(StrKit.notBlank(where)) {
			return find("select * from sys_tableconfig " + where);
		} else {
			return findAll();
		}
	}
	
	public List<TableconfigModel> findAll() {
		return find("select * from sys_tableconfig ");
	}
	
	// ----------------------------------------
	// DB Manager Function
	// ----------------------------------------
	public void createTable() {
		List<TableconfigModel> ntbs = find("select * from sys_tableconfig where iscreate='N'");
		for (TableconfigModel model : ntbs) {
			createTable(model);
		}
	}

	public boolean createTable(TableconfigModel model) {
		if (model == null)
			return false;
		if (model.get(ISCREATE).equals("Y"))
			return false;
		boolean flag = MgrDb.use().createTableByMgrdb(model.get(TBID),
				model.getStr(TBNAME));
		if (flag) {
			model.set(ISCREATE, "Y").update();
		}
		return flag;
	}

	public boolean dropTable(Long tbid) {
		boolean res = false;
		if (tbid < 1)
			return res;
		TableconfigModel tb = findById(tbid);
		if (tb.getLong(TBNAVTYPEID) < 3) {
			// 系统内部表格不允许删除
			return res;
		}
		res = MgrDb.use().dropTableByMgrdb(tbid, TABLE);
		return res;
	}

	public TableconfigModel getModel(String tbname) {
		return findFirst("select * from sys_tableconfig where tbname=?", tbname);
	}

	// ----------------------------------------
	// DB Manager Function
	// ----------------------------------------

	/**
	 * 输出Excel
	 * 
	 * @param os
	 * @param selectSQL
	 * @param fromSQL
	 * @param paras
	 
	public void exportExcel(OutputStream os, String selectSQL, String fromSQL,
			Object... paras) {
		List<TableconfigModel> mlist = find("select " + selectSQL + " "
				+ fromSQL, paras);
		toExcel(os, mlist, selectSQL);
	}
*/
	/**
	 * 输出Excel
	 * 
	 * @param os
	 * @param selectSQL
	 * @param fromSQL
	 
	public void exportExcel(OutputStream os, String selectSQL, String fromSQL) {
		List<TableconfigModel> mlist = find("select " + selectSQL + " "
				+ fromSQL);
		toExcel(os, mlist, selectSQL);
	}
*/
	/**
	 * 输出Excel
	
	public void toExcel(OutputStream os, List<TableconfigModel> mlist,
			String selectSQL) {
		TableconfigModel tbconfig = TableconfigModel.dao.getModel(TABLE);
		JXLExcelUtils exl = new JXLExcelUtils();
		int c = 0, r = 0;
		selectSQL = selectSQL.replace(" ", "");
		if (selectSQL.equals("*"))
			selectSQL = getSelectNames();
		String[] sltSQLs = selectSQL.split(",");
		if (tbconfig == null) {
			exl.createWorkBook(os).createSheet(TABLE, 0);
			for (String name : sltSQLs) {
				exl.addCell(c, r, name);
				c++;
			}
			c = 0;
			r++;
		} else {
			exl.createWorkBook(os).createSheet(tbconfig.getStr(TBCNN), 0);
			List<FieldconfigModel> fldconfig = FieldconfigModel.dao
					.findByTbid(tbconfig.getLong(TBID));
			// boolean ball = false;
			// if(selectSQL.equals("*")) ball = true;
			// else selectSQL += ",";
			selectSQL += ",";
			for (FieldconfigModel fld : fldconfig) {
				if (selectSQL.contains(fld.get(FieldconfigModel.FLDNAME) + ",")) {
					exl.addCell(c, r, fld.getStr(FieldconfigModel.FLDCNN));
				}
				c++;
			}
			c = 0;
			r++;
		}
		for (TableconfigModel m : mlist) {
			for (String name : sltSQLs) {
				if (getColumnType(name).endsWith("Int"))
					exl.addCell(c, r, m.getInt(name));
				else if (getColumnType(name).endsWith("Long"))
					exl.addCell(c, r, m.getLong(name));
				else if (getColumnType(name).endsWith("Float"))
					exl.addCell(c, r, m.getFloat(name));
				else if (getColumnType(name).endsWith("Double"))
					exl.addCell(c, r, m.getDouble(name));
				else if (getColumnType(name).endsWith("String"))
					exl.addCell(c, r, m.getStr(name));
				else if (getColumnType(name).endsWith("Timestamp"))
					exl.addCell(c, r, m.getTimestamp(name));
				else
					exl.addCell(c, r, m.get(name).toString());
				c++;
			}
			c = 0;
			r++;
		}
		exl.export();
		// return tbcnn;
	}
 */
	/**
	 * 字段
	 * 
	 * @return
	 
	public String getSelectNames() {
		String fldnames = "";
		fldnames += ",tbid";
		fldnames += ",tbname";
		fldnames += ",tbcnn";
		fldnames += ",isdelete";
		fldnames += ",isedit";
		fldnames += ",ispages";
		fldnames += ",isallsel";
		fldnames += ",isorder";
		fldnames += ",isdiycol";
		fldnames += ",diycolname";
		fldnames += ",tborder";
		fldnames += ",tbrole";
		fldnames += ",tbcreatetime";
		fldnames += ",tbmodifytime";
		fldnames += ",tbnavtypeid";
		fldnames += ",delname";
		fldnames += ",iscreate";
		fldnames += ",meta";
		fldnames += ",ext01";
		fldnames += ",ext02";
		fldnames += ",ext03";
		fldnames += ",ext04";
		fldnames += ",ext05";
		return fldnames.substring(1);
	}
*/
	/**
	 * 获取字段类型
	 * 
	 * @param field
	 * @return
	 
	public String getColumnType(String field) {
		if (field.equals("tbid"))
			return "java.lang.Long";
		if (field.equals("tbname"))
			return "java.lang.String";
		if (field.equals("tbcnn"))
			return "java.lang.String";
		if (field.equals("isdelete"))
			return "java.lang.String";
		if (field.equals("isedit"))
			return "java.lang.String";
		if (field.equals("ispages"))
			return "java.lang.String";
		if (field.equals("isallsel"))
			return "java.lang.String";
		if (field.equals("isorder"))
			return "java.lang.String";
		if (field.equals("isdiycol"))
			return "java.lang.String";
		if (field.equals("diycolname"))
			return "java.lang.String";
		if (field.equals("tborder"))
			return "java.lang.Integer";
		if (field.equals("tbrole"))
			return "java.lang.Integer";
		if (field.equals("tbcreatetime"))
			return "java.sql.Timestamp";
		if (field.equals("tbmodifytime"))
			return "java.sql.Timestamp";
		if (field.equals("tbnavtypeid"))
			return "java.lang.Long";
		if (field.equals("delname"))
			return "java.lang.String";
		if (field.equals("iscreate"))
			return "java.lang.String";
		if (field.equals("meta"))
			return "java.lang.String";
		if (field.equals("ext01"))
			return "java.lang.String";
		if (field.equals("ext02"))
			return "java.lang.String";
		if (field.equals("ext03"))
			return "java.lang.String";
		if (field.equals("ext04"))
			return "java.lang.String";
		if (field.equals("ext05"))
			return "java.lang.String";
		return "";
	}
	*/
	
	@Override
	public ITable getAddOrEditModel(String tbname) {
		// TODO Auto-generated method stub
		TableconfigModel model = null;
		if(StrKit.notBlank(tbname)) {
			model = findByName(tbname);
		}
		if(model == null) {
			model = new TableconfigModel();
			Timestamp tims = new Timestamp(System.currentTimeMillis());
			model.set("tbcreatetime", tims);
			model.set("tbmodifytime", tims);
			model.set("iscreate","N");
		}
		return model;
	}

	public TableconfigModel getAddOrEditModel(Object id) {
		TableconfigModel model = null;
		if(id != null) {
			model = findById(id);
		}
		if(model == null) {
			model = new TableconfigModel();
			/*
			model.set("isdelete","N");
			model.set("isedit","Y");
			model.set("ispages","Y");
			model.set("isallsel","Y");
			model.set("isorder","Y");
			model.set("isdiycol","N");
			model.set("diycolname","");
			model.set("tborder",1);
			model.set("tbrole",1);*/
			Timestamp tims = new Timestamp(System.currentTimeMillis());
			model.set("tbcreatetime", tims);
			model.set("tbmodifytime", tims);
			//model.set("tbnavtypeid","1");
			//model.set("delname","");
			model.set("iscreate","N");
		}
		return model;
	}

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return get(TBID);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return get(TBNAME);
	}
	
	@Override
	public String getPkey() {
		// TODO Auto-generated method stub
		return primaryKey;
	}
	
	@Override
	public void setPkey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return get(TBCNN);
	}

	@Override
	public int getTypeId() {
		// TODO Auto-generated method stub
		return get(TBNAVTYPEID);
	}

	@Override
	public Object getAttr(String key) {
		// TODO Auto-generated method stub
		return get(key);
	}

	@Override
	public void setAttr(String key, Object value) {
		// TODO Auto-generated method stub
		set(key, value);
	}
	
	@Override
	public void putAttr(String key, Object value) {
		// TODO Auto-generated method stub
		put(key, value);
	}
	
	@Override
	public Map<String, Object> getAttrs() {
		// TODO Auto-generated method stub
		return _getAttrs();
	}

	@Override
	public void configMap() {
		// TODO Auto-generated method stub
		
	}

}