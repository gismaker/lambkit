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

import java.util.Map;

import com.jfinal.kit.StrKit;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.db.mgr.ITable;
import com.lambkit.db.sql.column.Column;
import com.lambkit.module.meta.MetaMgrConfig;
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
		MetaMgrConfig config = MetaMgrManager.me().getConfig();
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
}
