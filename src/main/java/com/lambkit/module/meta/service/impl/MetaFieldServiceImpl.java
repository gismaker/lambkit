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
package com.lambkit.module.meta.service.impl;

import java.util.List;

import com.jfinal.aop.Enhancer;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.lambkit.common.service.BaseModelServiceImpl;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.db.meta.ColumnMeta;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.mgr.ITable;
import com.lambkit.db.mgr.MgrConstants;
import com.lambkit.db.mgr.MgrdbManager;
import com.lambkit.db.sql.column.Column;
import com.lambkit.db.sql.column.Columns;
import com.lambkit.module.meta.service.MetaFieldService;
import com.lambkit.module.meta.service.MetaThemeService;
import com.lambkit.module.meta.model.MetaField;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-11-24
 * @version 1.0
 * @since 1.0
 */
public class MetaFieldServiceImpl extends BaseModelServiceImpl<MetaField> implements MetaFieldService {
	
	protected MetaField DAO = null;
	
	public MetaField dao() {
		if(DAO==null) {
			DAO = Enhancer.enhance(MetaField.class.getName(), MetaField.class);
		}
		return DAO;
	}
	
	public String getTableName() {
		return dao().tableName();
	}
	
	public MetaField findById(Object id) {
		return dao().findById(id);
	}
	
	public boolean deleteById(Object id) {
		return dao().deleteById(id);
	}

	public MetaField findFirst(String sql) {
		return dao().findFirst(sql);
	}
	
	public List<MetaField> find(String sql) {
		return dao().find(sql);
	}
	
	public Page<MetaField> paginate(int pagenum, int pagesize, String selectSQL, String FromWhereSQL) {
		return dao().paginate(pagenum, pagesize, selectSQL, FromWhereSQL);
	}
	
	@Override
	public MetaField findById(Object fldid, int type) {
		// TODO Auto-generated method stub
		MetaField metaField = dao().findById(fldid);
		Long tmid = ServiceKit.inject(MetaThemeService.class).activedThemeId();
		return joinSubModel(metaField, type, tmid);
	}

	@Override
	public List<? extends IField> findByTbid(Object tbid, int type) {
		// TODO Auto-generated method stub
		Column column = Column.create("tbid", tbid).valueType("Long");
		List<MetaField> metaFields = dao().findListByColumn(column);
		Long tmid = ServiceKit.inject(MetaThemeService.class).activedThemeId();
		for (MetaField metaField : metaFields) {
			joinSubModel(metaField, type, tmid);
		}
		return metaFields;
	}

	@Override
	public List<? extends IField> findByTbid(Object tbid, int type, String orderby) {
		// TODO Auto-generated method stub
		Column column = Column.create("tbid", tbid).valueType("Long");
		List<MetaField> metaFields = dao().findListByColumns(Columns.create(column), orderby);
		Long tmid = ServiceKit.inject(MetaThemeService.class).activedThemeId();
		for (MetaField metaField : metaFields) {
			joinSubModel(metaField, type, tmid);
		}
		return metaFields;
	}

	@Override
	public int deleteByTbid(Object fldid) {
		// TODO Auto-generated method stub
		return dao().deleteById(fldid) ? 1 : -1;
	}

	@Override
	public IField findFirstByTbName(String tbname, int type, String fld, Object value) {
		// TODO Auto-generated method stub
		ITable table = MgrdbManager.me().getService().getTableDao().findByName(tbname);
		if(table==null) return null;
		return findFirstByTbid(table.getId(), type, fld, value);
	}

	@Override
	public IField findFirstByTbid(Object tbid, String fld, Object value) {
		return findFirstByTbid(tbid, MgrConstants.NONE, fld, value);
	}
	
	@Override
	public IField findFirstByTbid(Object tbid, int type, String fld, Object value) {
		// TODO Auto-generated method stub
		fld = "fldname".equalsIgnoreCase(fld) ? "name" : fld;
		Columns columns = Columns.create(fld, value).eq("tbid", tbid);
		MetaField metaField = dao().findFirstByColumns(columns);
		Long tmid = ServiceKit.inject(MetaThemeService.class).activedThemeId();
		return joinSubModel(metaField, type, tmid);
	}

	@Override
	public IField findGeomField(Object tbid, int type) {
		// TODO Auto-generated method stub
		Columns columns = Columns.create("ismap", "Y").eq("tbid", tbid);
		MetaField metaField = dao().findFirstByColumns(columns);
		Long tmid = ServiceKit.inject(MetaThemeService.class).activedThemeId();
		return joinSubModel(metaField, type, tmid);
	}

	@Override
	public IField getAddOrEditModel(Object id, int type) {
		// TODO Auto-generated method stub
		MetaField model = null;
		if(id!=null) {
			model = findById(id, type);
		}
		if(model==null) {
			model = new MetaField();
		}
		return model;
	}

	@Override
	public IField columnToField(ColumnMeta column, ITable tbc) {
		// TODO Auto-generated method stub
		IField model = findFirstByTbName(tbc.getName(), MgrConstants.NONE, "fldname", column.getName());
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
		} else {
			model = getAddOrEditModel(null, MgrConstants.NONE);
			MetaField fld = (MetaField) model;
			fld.setName(column.getName());
			fld.setTbid(Long.valueOf(tbc.getId().toString()));
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
		}
		return model;
	}
	
	@Override
	public List<? extends IField> findByTbid(Object tbid) {
		// TODO Auto-generated method stub
		return findByTbid(tbid, MgrConstants.NONE);
	}

	@Override
	public List<? extends IField> findByTbid(Object tbid, String orderby) {
		// TODO Auto-generated method stub
		return findByTbid(tbid, MgrConstants.NONE, orderby);
	}

	@Override
	public IField findFirstByTbName(String tbname, String fld, Object value) {
		// TODO Auto-generated method stub
		return findFirstByTbName(tbname, MgrConstants.NONE, fld, value);
	}

	@Override
	public String getPrimaryKey(Object tbid) {
		// TODO Auto-generated method stub
		Columns columns = Columns.create("iskey", "Y").eq("tbid", tbid);
		List<MetaField> metaFields = dao().findListByColumns(columns);
		StringBuilder sb = new StringBuilder();
		for (MetaField metaField : metaFields) {
			sb.append(",");
			sb.append(metaField.getName());
		}
		return sb.toString().substring(1);
	}

	@Override
	public MetaField joinSubModel(MetaField metaField, int type, Long tmid) {
		// TODO Auto-generated method stub
		if(tmid==null) {
			tmid = ServiceKit.inject(MetaThemeService.class).activedThemeId();
		}
		switch (type) {
		case MgrConstants.EDIT:
			metaField.joinEditModel(tmid);
			break;
		case MgrConstants.VIEW:
			metaField.joinListModel(tmid);
			break;
		case MgrConstants.MAP:
			metaField.joinMapModel();
			break;
		case MgrConstants.OLAP:
			metaField.joinOlapModel();
			break;
		case MgrConstants.JOIN:
			metaField.joinRelationModel();
			break;
		case MgrConstants.MAP_EDIT:
			metaField.joinEditModel(tmid).joinMapModel();
			break;
		}
		return metaField;
	}
}
