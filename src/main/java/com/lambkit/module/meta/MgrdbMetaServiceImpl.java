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
package com.lambkit.module.meta;

import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.db.meta.ColumnMeta;
import com.lambkit.db.meta.TableMeta;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.mgr.IFieldDao;
import com.lambkit.db.mgr.ITable;
import com.lambkit.db.mgr.ITableDao;
import com.lambkit.db.mgr.MgrConstants;
import com.lambkit.db.mgr.BaseMgrdbService;
import com.lambkit.module.meta.model.MetaField;
import com.lambkit.module.meta.service.MetaFieldService;
import com.lambkit.module.meta.service.MetaTableService;

public class MgrdbMetaServiceImpl extends BaseMgrdbService {

	public ITableDao getTableDao() {
		return ServiceKit.inject(MetaTableService.class);
	}

	public IFieldDao getFieldDao() {
		return ServiceKit.inject(MetaFieldService.class);
	}
	
	public void tableToMgrdb(TableMeta tbc) {
		ITable model = getTableDao().getAddOrEditModel(tbc.getName());
		if(model.getAttr("sid")==null) {
			model.setAttr("sid", 1);
		}
		model.setAttr("name", tbc.getName());
		if(StrKit.isBlank(model.getTitle())) {
			if(StrKit.notBlank(tbc.getTitle())) {
				model.setAttr("title", tbc.getTitle());
			} else {
				model.setAttr("title", tbc.getName());
			}
		}
		model.setPrimaryKey(tbc.getPrimaryKey());
		if(model.getId() !=null ) model.update();
		else model.save();
		List<? extends IField> fields = getFieldDao().findByTbid(model.getId());
		removeOldColumn(fields, tbc.getColumnMetas());
		for(ColumnMeta col : tbc.getColumnMetas()) {
			columnToMgrdb(col, tbc.getName(), model.getId(), tbc.getPrimaryKey());
		}
	}
	
	/**
	 * 去掉已被删除的字段
	 * @param fields
	 * @param columnMetas
	 */
	public void removeOldColumn(List<? extends IField> fields, List<ColumnMeta> columnMetas) {
		if(fields==null || fields.size()<1) return;
		Kv fldmap = Kv.create();
		for (ColumnMeta columnMeta : columnMetas) {
			fldmap.set(columnMeta.getName(), "meta");
		}
		for (IField field : fields) {
			if(fldmap.get(field.getName())==null) {
				getFieldDao().deleteByTbid(field.getId());
			}
		}
		fldmap.clear();
		fldmap = null;
	}
	
	public void columnToMgrdb(ColumnMeta column, String tbname, Object tbid, String pkname) {
		IField model = getFieldDao().findFirstByTbName(tbname, MgrConstants.NONE, "fldname", column.getName());
		if(model!=null) {
			MetaField fld = (MetaField) model;
			fld.setDatatype(column.getType());
			fld.setClasstype(column.getJavaType());
			if(StrKit.isBlank(fld.getDescript())) fld.setDescript(column.getRemarks());
			if(StrKit.isBlank(fld.getFlddefault())) fld.setFlddefault(column.getDefaultValue());
			if(StrKit.isBlank(fld.getIskey())) fld.setIskey(column.isPrimaryKey() ? "Y" : "N");
			if(StrKit.isBlank(fld.getIsunsigned())) fld.setIsunsigned(column.isSigned() ? "N" : "Y");
			if(StrKit.isBlank(fld.getIsai())) fld.setIsai(column.isAutoInctement() ? "Y" : "N");
			if(StrKit.isBlank(fld.getIsnullable())) fld.setIsnullable(column.getIsNullableType()==1 ? "Y" : "N");

			if(StrKit.isBlank(fld.getIsedit())) fld.setIsedit(column.isPrimaryKey() ? "N" : "Y");
			if(StrKit.isBlank(fld.getIsview())) fld.setIsview(column.isPrimaryKey() ? "N" : "Y");		
			if(StrKit.isBlank(fld.getIsselect())) fld.setIsselect(column.isPrimaryKey() ? "N" : "Y");
			if(StrKit.isBlank(fld.getIsmustfld())) fld.setIsmustfld(column.isPrimaryKey() ? "N" : "Y");
			if(StrKit.isBlank(fld.getIsmap())) fld.setIsmap("N");
			fld.update();
		} else {
			IField field = getFieldDao().getAddOrEditModel(null, MgrConstants.NONE);
			MetaField fld = (MetaField) field;
			fld.setName(column.getName());
			fld.setTbid(Long.valueOf(tbid.toString()));
			fld.setTitle(column.getTitle());
			fld.setDatatype(column.getType());
			fld.setClasstype(column.getJavaType());
			fld.setDescript(column.getRemarks());
			fld.setFlddefault(column.getDefaultValue());
			fld.setIskey(column.isPrimaryKey() ? "Y" : "N");
			fld.setIsunsigned(column.isSigned() ? "N" : "Y");
			fld.setIsai(column.isAutoInctement() ? "Y" : "N");
			fld.setIsnullable(column.getIsNullableType()==1 ? "Y" : "N");
			fld.setIsedit(column.isPrimaryKey() ? "N" : "Y");
			fld.setIsview(column.isPrimaryKey() ? "N" : "Y");		
			fld.setIsselect(column.isPrimaryKey() ? "N" : "Y");
			fld.setIsmustfld(column.isPrimaryKey() ? "N" : "Y");
			fld.setIsmap("N");
			fld.save();
		}
	}
	
	/*
	public void tableToMgrdb(MgrTable tbc) {
		Object id = null;
		if(tbc.getModel()==null) {
			ITable model = getTableDao().getAddOrEditModel(null);
			model.setAttr("name", tbc.getName());
			if(StrKit.notBlank(tbc.getMeta().getTitle())) model.setAttr("title", tbc.getMeta().getTitle());
			else model.setAttr("title", tbc.getName());
			model.setPrimaryKey(tbc.getMeta().getPrimaryKey());
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
		if (col != null && col instanceof MetaField) {
			if (col.getId() != null) {
				col.update();
			} else {
				IField model = getFieldDao().findFirstByTbid(tbid, MgrConstants.NONE, "name", col.getName());
				if (model != null) {
					MetaField fld = (MetaField) model;
					MetaField res = (MetaField) col;
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
	*/
}
