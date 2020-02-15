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
package com.lambkit.module.meta.model;

import java.util.List;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.sql.column.Column;
import com.lambkit.db.mgr.MgrdbConfig;
import com.lambkit.module.meta.MetaMgrManager;

import com.lambkit.module.meta.model.base.BaseMetaField;
import com.lambkit.module.meta.model.sql.MetaFieldCriteria;
import com.lambkit.module.meta.service.MetaFieldService;
import com.lambkit.module.meta.service.impl.MetaFieldServiceImpl;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2019-01-07
 * @version 1.0
 * @since 1.0
 */
public class MetaField extends BaseMetaField<MetaField> implements IField {

	private static final long serialVersionUID = 1L;
	
	public static MetaFieldService service() {
		return ServiceKit.inject(MetaFieldService.class, MetaFieldServiceImpl.class);
	}
	
	public static MetaFieldCriteria sql() {
		return new MetaFieldCriteria();
	}
	
	public static MetaFieldCriteria sql(Column column) {
		MetaFieldCriteria that = new MetaFieldCriteria();
		that.add(column);
        return that;
    }

	public MetaField() {
		MgrdbConfig config = MetaMgrManager.me().getConfig();
		String dbconfig = config.getDbconfig();
		if(StrKit.notBlank(dbconfig)) {
			this.use(dbconfig);
		}
	}
	
	@Override
	public String getAttrName() {
		// TODO Auto-generated method stub
		String name = getName();
		return StrKit.toCamelCase(name);
	}
	
	@Override
	public String getDefault() {
		// TODO Auto-generated method stub
		return getFlddefault();
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
	
	public MetaField joinEditModel(Long tmid) {
		if(getId()!=null) {
			MetaFieldEdit m = MetaFieldEdit.service().findByFieldAndTheme(getId(), tmid);
			this.put("MetaFieldEdit", m);
		}
		return this;
	}
	
	public MetaField joinListModel(Long tmid) {
		if(getId()!=null) {
			MetaFieldList m = MetaFieldList.service().findByFieldAndTheme(getId(), tmid);
			this.put("MetaFieldList", m);
		}
		return this;
	}
	
	public MetaField joinMapModel() {
		if(getId()!=null) {
			MetaFieldMap m = MetaFieldMap.service().findFistByFieldId(getId());
			this.put("MetaFieldMap", m);
		}
		return this;
	}
	public MetaField joinRelationModel() {
		if(getId()!=null) {
			MetaFieldRelation m = MetaFieldRelation.service().findFistByFieldId(getId());
			this.put("MetaFieldJoin", m);
		}
		return this;
	}
	
	public MetaField joinOlapModel() {
		joinDidessionModel();
		joinMeasureModel();
		return this;
	}
	
	public MetaField joinDidessionModel() {
		if(getId()!=null) {
			MetaFieldDimession m = MetaFieldDimession.service().findFistByFieldId(getId());
			this.put("MetaFieldDimession", m);
		}
		return this;
	}
	
	public MetaField joinMeasureModel() {
		if(getId()!=null) {
			List<MetaFieldMeasure> m = MetaFieldMeasure.service().findByFieldId(getId());
			this.put("MetaFieldMeasure", m);
		}
		return this;
	}
	/*
	public MetaFieldEdit getFieldEditModel(Long tmid) {
		if(getId()!=null) {
			MetaFieldEditService service = ServiceKit.inject(MetaFieldEditService.class);
			return service.findByFieldAndTheme(getId(), tmid);
		}
		return null;
	}
	
	public MetaFieldList getFieldListModel(Long tmid) {
		if(getId()!=null) {
			MetaFieldListService service = ServiceKit.inject(MetaFieldListService.class);
			return service.findByFieldAndTheme(getId(), tmid);
		}
		return null;
	}
	
	public MetaFieldMap getFieldMapModel() {
		if(getId()!=null) {
			MetaFieldMapService service = ServiceKit.inject(MetaFieldMapService.class);
			return service.findFistByFieldId(getId());
		}
		return null;
	}
	public MetaFieldJoin getFieldJoinModel() {
		if(getId()!=null) {
			MetaFieldJoinService service = ServiceKit.inject(MetaFieldJoinService.class);
			return service.findFistByFieldId(getId());
		}
		return null;
	}
	
	public MetaFieldOlap getFieldOlapModel() {
		if(getId()!=null) {
			MetaFieldOlapService service = ServiceKit.inject(MetaFieldOlapService.class);
			return service.findFistByFieldId(getId());
		}
		return null;
	}
	
	public MetaField joinFieldEditModel(Long fldid, Long tmid) {
		if(fldid==null) return null;
		if(tmid==null) tmid=1L;
		MetaField field = findById(fldid);
		if(field!=null) {
			MetaFieldEditService service = ServiceKit.inject(MetaFieldEditService.class);
			MetaFieldEdit mfe = service.findByFieldAndTheme(fldid, tmid);
			field.put("MetaFieldEdit", mfe);
		}
		return field;
	}
	
	public MetaField joinFieldListModel(Long fldid, Long tmid) {
		if(fldid==null) return null;
		if(tmid==null) tmid=1L;
		MetaField field = findById(fldid);
		if(field!=null) {
			MetaFieldListService service = ServiceKit.inject(MetaFieldListService.class);
			MetaFieldList m = service.findByFieldAndTheme(fldid, tmid);
			field.put("MetaFieldList", m);
		}
		return field;
	}
	
	public MetaField joinFieldMapModel(Long fldid) {
		if(fldid==null) return null;
		MetaField field = findById(fldid);
		if(field!=null) {
			MetaFieldMapService service = ServiceKit.inject(MetaFieldMapService.class);
			MetaFieldMap m = service.findFistByFieldId(fldid);
			field.put("MetaFieldMap", m);
		}
		return field;
	}
	public MetaField joinFieldJoinModel(Long fldid) {
		if(fldid==null) return null;
		MetaField field = findById(fldid);
		if(field!=null) {
			MetaFieldJoinService service = ServiceKit.inject(MetaFieldJoinService.class);
			MetaFieldJoin m = service.findFistByFieldId(fldid);
			field.put("MetaFieldJoin", m);
		}
		return field;
	}
	
	public MetaField joinFieldOlapModel(Long fldid) {
		if(fldid==null) return null;
		MetaField field = findById(fldid);
		if(field!=null) {
			MetaFieldOlapService service = ServiceKit.inject(MetaFieldOlapService.class);
			MetaFieldOlap m = service.findFistByFieldId(fldid);
			field.put("MetaFieldOlap", m);
		}
		return field;
	}
	*/
}
