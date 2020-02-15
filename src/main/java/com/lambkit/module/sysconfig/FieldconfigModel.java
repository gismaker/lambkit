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

import java.util.List;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.lambkit.db.meta.ColumnMeta;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.mgr.IFieldDao;
import com.lambkit.db.mgr.ITable;
import com.lambkit.db.mgr.MgrConstants;

/**
 * @author yangyong 
 * @website: www.epgw.com.cn
 * @email: epgw@foxmail.com
 * @date 2015-08-13
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class FieldconfigModel extends Model<FieldconfigModel> implements IField, IFieldDao {

	public static final FieldconfigModel dao = new FieldconfigModel();
	
	public static final String TABLE = "sys_fieldconfig";
	public static final String FLDID = "fldid";
	public static final String FLDTBID = "fldtbid";
	public static final String FLDNAME = "fldname";
	public static final String FLDCNN = "fldcnn";
	public static final String DATATYPE = "datatype";
	public static final String FLDDEFAULT = "flddefault";
	
	@Override
	public IField findById(Object fldid, int type) {
		// TODO Auto-generated method stub
		return findById(fldid);
	}

	@Override
	public List findByTbid(Object tbid) {
		// TODO Auto-generated method stub
		return findByTbid(tbid, MgrConstants.NONE);
	}
	
	public List<FieldconfigModel> findByTbid(Object tbid, int type) {
		return find("select * from " + TABLE + " where " + FLDTBID + "=? order by fldorder, fldid", tbid);
	}
	
	@Override
	public List findByTbid(Object tbid, String orderby) {
		// TODO Auto-generated method stub
		return findByTbid(tbid, MgrConstants.NONE, orderby);
	}
	
	public List<FieldconfigModel> findByTbid(Object tbid, int type, String orderby) {
		if(StrKit.isBlank(orderby)) orderby = "fldorder, fldid";
		return find("select * from " + TABLE + " where " + FLDTBID + "=? order by " + orderby, tbid);
	}
	
	public int deleteByTbid(Object tbid) {
		return Db.deleteById(TABLE, FLDTBID, tbid) ? 0 : -1;
	}
	
	@Override
	public IField findFirstByTbName(String tbname, String fld, Object value) {
		// TODO Auto-generated method stub
		return findFirstByTbName(tbname, MgrConstants.NONE, fld, value);
	}
	
	public FieldconfigModel findFirstByTbName(String tbname, int type, String fld, Object value) {
		String sql = "select * from " + TABLE + " where " 
				+ TABLE + ".fldtbid=(SELECT tbid from " 
				+ TableconfigModel.TABLE + " where tbname=?" + ") and " 
				+ TABLE + "."+ fld +"=?";
		return FieldconfigModel.dao.findFirst(sql, tbname, value);
	}
	
	public FieldconfigModel findFirstByTbid(Object tbid, String fld, Object value) {
		return findFirstByTbid(tbid, MgrConstants.NONE, fld, value);
	}
	public FieldconfigModel findFirstByTbid(Object tbid, int type, String fld, Object value) {
		String sql = "select * from " + TABLE + " where fldtbid=? and "+ fld +"=?";
		return FieldconfigModel.dao.findFirst(sql, tbid, value);
	} 
	
	public FieldconfigModel findGeomField(Object tbid, int type) {
		String sql = "select * from " + TABLE + " where fldtbid=? and fldname like '%geom%' and datatype='Type'";
		return FieldconfigModel.dao.findFirst(sql, tbid);
	} 
	
	/*
	public String getFieldSQL(Object id) {
		FieldconfigModel model = findById(id);
		if(model==null) return null;
		return model.getFieldSQL();
	}
	*/
	
	public FieldconfigModel getAddOrEditModel(Object id, int type) {
		FieldconfigModel model =  findById(id);
		if(model==null) model =  new FieldconfigModel();
		model.set("checktype",0);
		model.set("edittype",1);
		model.set("fldmetaid",0);
		model.set("isselect","Y");
		model.set("isview","Y");
		model.set("maxlenview",0);
		model.set("viewfldorder",0);
		model.set("isedit","N");
		model.set("editorder",0);
		model.set("editminlen",0);
		model.set("editmaxlen",0);
		model.set("ismustfld","Y");
		model.set("fldorder",0);
		model.set("fldrole",1);
		model.set("meta","");
		model.set("ext01","N");
		return model;
	}
	
	@Override
	public String getPrimaryKey(Object tbid) {
		String sql = "select * from " + TABLE + " where fldtbid=? and iskey='Y'";
		FieldconfigModel m = FieldconfigModel.dao.findFirst(sql, tbid);
		if(m!=null) return m.getFldname();
		return null;
	}
	
	//////////////////////////////////////////
	// get / set
	//////////////////////////////////////////
	
	public java.lang.Long getFldid() {
		return this.get("fldid");
	}
	
	public void setFldid(java.lang.Long fldid) {
		this.set("fldid", fldid);
	}
	
	public java.lang.Long getFldtbid() {
		return this.get("fldtbid");
	}
	
	public void setFldtbid(Object object) {
		this.set("fldtbid", object);
	}
	
	public java.lang.String getFldname() {
		return this.get("fldname");
	}
	
	public void setFldname(java.lang.String fldname) {
		this.set("fldname", fldname);
	}
	
	public java.lang.String getFldcnn() {
		return this.get("fldcnn");
	}
	
	public void setFldcnn(java.lang.String fldcnn) {
		this.set("fldcnn", fldcnn);
	}
	
	public java.lang.String getDatatype() {
		return this.get("datatype");
	}
	
	public void setDatatype(java.lang.String datatype) {
		this.set("datatype", datatype);
	}
	
	public java.lang.Integer getMaxlenview() {
		return this.get("maxlenview");
	}
	
	public void setMaxlenview(java.lang.Integer maxlenview) {
		this.set("maxlenview", maxlenview);
	}
	
	public java.lang.Integer getViewfldorder() {
		return this.get("viewfldorder");
	}
	
	public void setViewfldorder(java.lang.Integer viewfldorder) {
		this.set("viewfldorder", viewfldorder);
	}
	
	public java.lang.String getIskey() {
		return this.get("iskey");
	}
	
	public void setIskey(java.lang.String iskey) {
		this.set("iskey", iskey);
	}
	
	public java.lang.String getIsunsigned() {
		return this.get("isunsigned");
	}
	
	public void setIsunsigned(java.lang.String isunsigned) {
		this.set("isunsigned", isunsigned);
	}
	
	public java.lang.String getIsnullable() {
		return this.get("isnullable");
	}
	
	public void setIsnullable(java.lang.String isnullable) {
		this.set("isnullable", isnullable);
	}
	
	public java.lang.String getIsview() {
		return this.get("isview");
	}
	
	public void setIsview(java.lang.String isview) {
		this.set("isview", isview);
	}
	
	public java.lang.String getIsselect() {
		return this.get("isselect");
	}
	
	public void setIsselect(java.lang.String isselect) {
		this.set("isselect", isselect);
	}
	
	public java.lang.String getIsai() {
		return this.get("isai");
	}
	
	public void setIsai(java.lang.String isai) {
		this.set("isai", isai);
	}
	
	public java.lang.String getIsfk() {
		return this.get("isfk");
	}
	
	public void setIsfk(java.lang.String isfk) {
		this.set("isfk", isfk);
	}
	
	public java.lang.String getFktbname() {
		return this.get("fktbname");
	}
	
	public void setFktbname(java.lang.String fktbname) {
		this.set("fktbname", fktbname);
	}
	
	public java.lang.String getFktbkey() {
		return this.get("fktbkey");
	}
	
	public void setFktbkey(java.lang.String fktbkey) {
		this.set("fktbkey", fktbkey);
	}
	
	public java.lang.String getFldlinkfk() {
		return this.get("fldlinkfk");
	}
	
	public void setFldlinkfk(java.lang.String fldlinkfk) {
		this.set("fldlinkfk", fldlinkfk);
	}
	
	public java.lang.Integer getChecktype() {
		return this.get("checktype");
	}
	
	public void setChecktype(java.lang.Integer checktype) {
		this.set("checktype", checktype);
	}
	
	public java.lang.Integer getEdittype() {
		return this.get("edittype");
	}
	
	public void setEdittype(java.lang.Integer edittype) {
		this.set("edittype", edittype);
	}
	
	public java.lang.String getFlddefault() {
		return this.get("flddefault");
	}
	
	public void setFlddefault(java.lang.String flddefault) {
		this.set("flddefault", flddefault);
	}
	
	public java.lang.String getDescript() {
		return this.get("descript");
	}
	
	public void setDescript(java.lang.String descript) {
		this.set("descript", descript);
	}
	
	public java.lang.Integer getFldmetaid() {
		return this.get("fldmetaid");
	}
	
	public void setFldmetaid(java.lang.Integer fldmetaid) {
		this.set("fldmetaid", fldmetaid);
	}
	
	public java.lang.String getIsedit() {
		return this.get("isedit");
	}
	
	public void setIsedit(java.lang.String isedit) {
		this.set("isedit", isedit);
	}
	
	public java.lang.Integer getEditorder() {
		return this.get("editorder");
	}
	
	public void setEditorder(java.lang.Integer editorder) {
		this.set("editorder", editorder);
	}
	
	public java.lang.Integer getEditminlen() {
		return this.get("editminlen");
	}
	
	public void setEditminlen(java.lang.Integer editminlen) {
		this.set("editminlen", editminlen);
	}
	
	public java.lang.Integer getEditmaxlen() {
		return this.get("editmaxlen");
	}
	
	public void setEditmaxlen(java.lang.Integer editmaxlen) {
		this.set("editmaxlen", editmaxlen);
	}
	
	public java.lang.String getIsmustfld() {
		return this.get("ismustfld");
	}
	
	public void setIsmustfld(java.lang.String ismustfld) {
		this.set("ismustfld", ismustfld);
	}
	
	public java.lang.Integer getFldorder() {
		return this.get("fldorder");
	}
	
	public void setFldorder(java.lang.Integer fldorder) {
		this.set("fldorder", fldorder);
	}
	
	public java.lang.Integer getFldrole() {
		return this.get("fldrole");
	}
	
	public void setFldrole(java.lang.Integer fldrole) {
		this.set("fldrole", fldrole);
	}

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.get(FLDID);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.get(FLDNAME);
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return this.get(FLDCNN);
	}
	
	@Override
	public String getDefault() {
		// TODO Auto-generated method stub
		return this.get(FLDDEFAULT);
	}

	@Override
	public Object getAttr(String key) {
		// TODO Auto-generated method stub
		return this.get(key);
	}

	@Override
	public void setAttr(String key, Object value) {
		// TODO Auto-generated method stub
		this.set(key, value);
	}
	
	@Override
	public void putAttr(String key, Object value) {
		// TODO Auto-generated method stub
		this.put(key, value);
	}
	
	@Override
	public Map<String, Object> getAttrs() {
		// TODO Auto-generated method stub
		return _getAttrs();
	}
	
	public IField columnToField(ColumnMeta column, ITable tbc) {
		if(column!=null) {
			String tbname = tbc.getName();
			String name = column.getName();
			if(StrKit.notBlank(tbname) && StrKit.notBlank(tbname)) {
				FieldconfigModel model = FieldconfigModel.dao.findFirstByTbName(tbname, MgrConstants.NONE, FLDNAME, name);
				if(model!=null) return model;
			}
		}
		FieldconfigModel model = FieldconfigModel.dao.getAddOrEditModel(-1, MgrConstants.NONE);
		if(column.isPrimaryKey()) {
			model.setIskey("Y");
		} else {
			model.setIskey("N");
		}
		model.setFldtbid(tbc.getId());
		model.setFldname(column.getName());
		model.setFldcnn(column.getTitle());
		String datatype = column.getType().toLowerCase();
		if(datatype.contains("char") ||
				datatype.contains("number") ||
				datatype.contains("numeric") ||
				datatype.contains("decimal")){
			if(column.getPrecision() > 0) {
				datatype += "(" + column.getPrecision();
				if(column.getScale() > 0) datatype += "," + column.getScale();
				datatype += ")";
			}
		}
		model.setDatatype(datatype);
		model.setIsunsigned("N");
		if(column.isAutoInctement()) model.setIsai("Y");
		else model.setIsai("N");
		if(column.getIsNullableType() == 1) model.setIsnullable("Y");
		else model.setIsnullable("N");
		model.setDescript(column.getRemarks());
		model.setIsfk("N");
		model.save();
		return model;
	}

	@Override
	public String getAttrName() {
		// TODO Auto-generated method stub
		String name = getName();
		return StrKit.toCamelCase(name);
	}
}