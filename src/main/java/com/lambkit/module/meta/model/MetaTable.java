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

import java.math.BigInteger;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.db.DbManager;
import com.lambkit.db.mgr.ITable;
import com.lambkit.db.sql.column.Column;
import com.lambkit.db.sql.column.Columns;
import com.lambkit.db.sql.column.Example;
import com.lambkit.db.mgr.MgrdbConfig;
import com.lambkit.module.meta.MetaMgrManager;

import com.lambkit.module.meta.model.base.BaseMetaTable;
import com.lambkit.module.meta.model.sql.MetaTableCriteria;
import com.lambkit.module.meta.service.MetaTableService;
import com.lambkit.module.meta.service.impl.MetaTableServiceImpl;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2019-01-07
 * @version 1.0
 * @since 1.0
 */
public class MetaTable extends BaseMetaTable<MetaTable> implements ITable {

	private static final long serialVersionUID = 1L;
	
	public static MetaTableService service() {
		return ServiceKit.inject(MetaTableService.class, MetaTableServiceImpl.class);
	}
	
	public static MetaTableCriteria sql() {
		return new MetaTableCriteria();
	}
	
	public static MetaTableCriteria sql(Column column) {
		MetaTableCriteria that = new MetaTableCriteria();
		that.add(column);
        return that;
    }

	public MetaTable() {
		MgrdbConfig config = MetaMgrManager.me().getConfig();
		String dbconfig = config.getDbconfig();
		if(StrKit.notBlank(dbconfig)) {
			this.use(dbconfig);
		}
	}
	
	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return getKeyname();
	}
	
	@Override
	public void setPrimaryKey(String primaryKey) {
		// TODO Auto-generated method stub
		setKeyname(primaryKey);
	}

	@Override
	public int getTypeId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getAttr(String key) {
		// TODO Auto-generated method stub
		return get(key);
	}

	@Override
	public String getStr(String key) {
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
	

	public Record getTableDataById(String ids, String sep) {
		String pkey = getKeyname();
		if(StrKit.isBlank(pkey)) return null;
		String[] pkeys = pkey.split(",");
		String[] pids = ids.split(sep);
		if(pkeys.length == pids.length) {
			Example example = Example.create(getName());
			Columns columns = example.createColumns();
			for (int i = 0; i < pids.length; i++) {
				MetaField field = MetaField.service().getPrimaryKeyField(getId(), pkeys[i]);
				if(field==null) return null;
				if("java.lang.Integer".equals(field.getDatatype())) {
					columns.eq(pkeys[i], Integer.valueOf(pids[i]));
				} else if("java.lang.Long".equals(field.getDatatype())) {
					columns.eq(pkeys[i], Long.valueOf(pids[i]));
				} else if("java.math.BigInteger".equals(field.getDatatype())) {
					columns.eq(pkeys[i], BigInteger.valueOf(Long.valueOf(pids[i])));
				} else {
					columns.eq(pkeys[i], pids[i]);
				}
			}
			SqlPara sqlPara = dialect().forFindByExample(example, null);
			MgrdbConfig config = MetaMgrManager.me().getConfig();
			String dbconfig = config.getDbconfig();
			if(StrKit.notBlank(dbconfig)) {
				return Db.use(dbconfig).findFirst(sqlPara);
			} else {
				return Db.findFirst(sqlPara);
			}
		} else {
			return null;
		}
	}
	
	public Record getTableDataById(String ids, String sep, String dbconfig) {
		String pkey = getKeyname();
		if(StrKit.isBlank(pkey)) return null;
		String[] pkeys = pkey.split(",");
		String[] pids = ids.split(sep);
		if(pkeys.length == pids.length) {
			Example example = Example.create(getName());
			Columns columns = example.createColumns();
			for (int i = 0; i < pids.length; i++) {
				MetaField field = MetaField.service().getPrimaryKeyField(getId(), pkeys[i]);
				if(field==null) return null;
				if("java.lang.Integer".equals(field.getDatatype())) {
					columns.eq(pkeys[i], Integer.valueOf(pids[i]));
				} else if("java.lang.Long".equals(field.getDatatype())) {
					columns.eq(pkeys[i], Long.valueOf(pids[i]));
				} else if("java.math.BigInteger".equals(field.getDatatype())) {
					columns.eq(pkeys[i], BigInteger.valueOf(Long.valueOf(pids[i])));
				} else {
					columns.eq(pkeys[i], pids[i]);
				}
			}
			SqlPara sqlPara = DbManager.me().getDialect(dbconfig).forFindByExample(example, null);
			if(StrKit.notBlank(dbconfig)) {
				return Db.use(dbconfig).findFirst(sqlPara);
			} else {
				return Db.findFirst(sqlPara);
			}
		} else {
			return null;
		}
	}
	
	public Page<Record> getTableDataByPaginate(String ids, String sep) {
		if(StrKit.isBlank(ids)) return null;
		String[] pids = ids.split(sep);
		Integer size = 0;
		Integer page = 1;
		MgrdbConfig config = MetaMgrManager.me().getConfig();
		String dbconfig = config.getDbconfig();
		if(pids.length==1) {
			if("N".equalsIgnoreCase(pids[0])) {
				size = config.getLimit();
			} else {
				size = Integer.valueOf(pids[0]);
			}
		} else if(pids.length == 2) {
			size = Integer.valueOf(pids[0]);
			page = Integer.valueOf(pids[1]);
		}
		size = size==null ? 0 : size;
		page = page==null ? 1 : page;
		Example example = Example.create(getName());
		SqlPara sqlPara = dialect().forFindByExample(example, null);
		if(StrKit.notBlank(dbconfig)) {
			return Db.use(dbconfig).paginate(page, size, sqlPara);
		} else {
			return Db.paginate(page, size, sqlPara);
		}
	}
}
