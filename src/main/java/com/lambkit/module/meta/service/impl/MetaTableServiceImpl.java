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

import java.sql.Timestamp;
import java.util.List;

import com.jfinal.aop.Enhancer;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.lambkit.common.service.BaseModelServiceImpl;
import com.lambkit.db.mgr.ITable;
import com.lambkit.db.sql.column.Column;
import com.lambkit.module.meta.service.MetaTableService;
import com.lambkit.module.meta.model.MetaTable;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-11-24
 * @version 1.0
 * @since 1.0
 */
public class MetaTableServiceImpl extends BaseModelServiceImpl<MetaTable> implements MetaTableService {
	
	protected MetaTable DAO = null;
	
	public MetaTable dao() {
		if(DAO==null) {
			DAO = Enhancer.enhance(MetaTable.class.getName(), MetaTable.class);
		}
		return DAO;
	}
	
	public String getTableName() {
		return dao().tableName();
	}
	
	public MetaTable findById(Object id) {
		return dao().findById(id);
	}
	
	public boolean deleteById(Object id) {
		return dao().deleteById(id);
	}

	public MetaTable findFirst(String sql) {
		return dao().findFirst(sql);
	}
	
	public List<MetaTable> find(String sql) {
		return dao().find(sql);
	}
	
	public Page<MetaTable> paginate(int pagenum, int pagesize, String selectSQL, String FromWhereSQL) {
		return dao().paginate(pagenum, pagesize, selectSQL, FromWhereSQL);
	}

	@Override
	public MetaTable findByName(String tableName) {
		// TODO Auto-generated method stub
		Column column = Column.create("name", tableName);
		if(dao()==null) System.out.println("dao is null");
		return dao().findFirstByColumn(column);
	}

	@Override
	public int deleteByTbId(Object tbid) {
		// TODO Auto-generated method stub
		return dao().deleteById(tbid) ? 1 : 0;
	}

	@Override
	public ITable getAddOrEditModel(Object tbid) {
		// TODO Auto-generated method stub
		MetaTable model = null;
		if(tbid != null) {
			model = findById(tbid);
		}
		if(model!=null) return model;
		model = new MetaTable();
		Timestamp tims = new Timestamp(System.currentTimeMillis());
		model.set("created", tims);
		model.set("modified", tims);
		model.set("orders",1);
		model.set("status","0");
		return model;
	}

	@Override
	public ITable getAddOrEditModel(String tbname) {
		// TODO Auto-generated method stub
		MetaTable model = null;
		if(StrKit.notBlank(tbname)) {
			model = findByName(tbname);
		}
		if(model!=null) return model;
		model = new MetaTable();
		Timestamp tims = new Timestamp(System.currentTimeMillis());
		model.set("created", tims);
		model.set("modified", tims);
		model.set("orders",1);
		model.set("status","0");
		return model;
	}
}
