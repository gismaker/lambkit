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

import com.jfinal.kit.StrKit;
import com.lambkit.db.meta.ColumnMeta;
import com.lambkit.db.meta.TableMeta;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.mgr.IFieldDao;
import com.lambkit.db.mgr.ITable;
import com.lambkit.db.mgr.ITableDao;
import com.lambkit.db.mgr.MgrConstants;
import com.lambkit.db.mgr.MgrTable;
import com.lambkit.db.mgr.BaseMgrdbService;

public class MgrdbSysconfigServiceImpl extends BaseMgrdbService {

	public ITableDao getTableDao() {
		return TableconfigModel.dao;
	}

	public IFieldDao getFieldDao() {
		return FieldconfigModel.dao;
	}
	
	public void tableToMgrdb(TableMeta tbc) {
		ITable model = getTableDao().getAddOrEditModel(tbc.getName());
		if(model==null) System.out.println("model is null");
		model.setAttr("tbname", tbc.getName());
		if(StrKit.isBlank(model.getTitle())) {
			if(StrKit.notBlank(tbc.getTitle())) model.setAttr("tbcnn", tbc.getTitle());
			else model.setAttr("tbcnn", tbc.getName());
		}
		if(model.getId() !=null ) model.update();
		else {
			model.setAttr("isdelete", "N");
			model.setAttr("isedit", "Y");
			model.setAttr("ispages", "Y");
			model.setAttr("isallsel", "Y");
			model.setAttr("isorder", "Y");
			model.setAttr("isdiycol", "N");
			model.setAttr("tborder", 0);
			model.setAttr("tbrole", 1);
			model.setAttr("tbnavtypeid", 0);
			model.save();
		}
		for(ColumnMeta col : tbc.getColumnMetas()) {
			columnToMgrdb(col, tbc.getName(), model.getId(), tbc.getPrimaryKey());
		}
	}
	
	public void columnToMgrdb(ColumnMeta col, String tbname, Object tbid, String pkname) {
		IField model = getFieldDao().findFirstByTbName(tbname, MgrConstants.NONE, "fldname", col.getName());
		if(model!=null) {
			FieldconfigModel fld = (FieldconfigModel) model;
			if(StrKit.isBlank(fld.getDatatype())) fld.setDatatype(col.getType());
			if(StrKit.isBlank(fld.getIskey())) fld.setIskey(col.isPrimaryKey() ? "Y" : "N");
			if(StrKit.isBlank(fld.getIsunsigned())) fld.setIsunsigned(col.isSigned() ? "N" : "Y");
			if(StrKit.isBlank(fld.getIsai())) fld.setIsai(col.isAutoInctement() ? "Y" : "N");
			if(StrKit.isBlank(fld.getIsnullable())) fld.setIsnullable(col.getIsNullableType()==1 ? "Y" : "N");
			fld.update();
		} else {
			IField field = getFieldDao().getAddOrEditModel(null, MgrConstants.NONE);
			FieldconfigModel fld = (FieldconfigModel) field;
			fld.setFldname(col.getName());
			fld.setFldtbid(tbid);
			fld.setFldcnn(col.getTitle());
			fld.setDatatype(col.getType());
			fld.setIskey(col.isPrimaryKey() ? "Y" : "N");
			fld.setIsunsigned(col.isSigned() ? "N" : "Y");
			fld.setIsai(col.isAutoInctement() ? "Y" : "N");
			fld.setIsnullable(col.getIsNullableType()==1 ? "Y" : "N");
			fld.save();
		}
	}
	
	public void tableToMgrdb(MgrTable tbc) {
		Object id = null;
		if(tbc.getModel()==null) {
			ITable model = getTableDao().getAddOrEditModel(null);
			model.setAttr("tbname", tbc.getName());
			if(StrKit.notBlank(tbc.getMeta().getTitle())) model.setAttr("tbcnn", tbc.getMeta().getTitle());
			else model.setAttr("tbcnn", tbc.getName());
			model.save();
			id = model.getId();
		} else {
			tbc.getModel().update();
			id = tbc.getModel().getId();
		}
		for(IField col : tbc.getFieldList()) {
			columnToMgrdb(col, id, tbc.getMeta().getPrimaryKey());
		}
	}
	
	public void columnToMgrdb(IField col, Object tbid, String pkname) {
		if(col != null && col instanceof FieldconfigModel) {
			if(col.getId()!=null) {
				col.update();
			} else {
				IField model = getFieldDao().findFirstByTbid(tbid, MgrConstants.NONE, "fldname", col.getName());
				if(model!=null) {
					FieldconfigModel fld = (FieldconfigModel) model;
					FieldconfigModel res = (FieldconfigModel) col;
					fld.setDatatype(res.getDatatype());
					fld.setIskey(res.getIskey());
					fld.setIsunsigned(res.getIsunsigned());
					fld.setIsai(res.getIsai());
					fld.setIsnullable(res.getIsnullable());
					fld.setIsfk(res.getIsfk());
					fld.update();
				} else {
					col.save();
				}
			}
		}
	}
	
}
