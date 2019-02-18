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

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import com.lambkit.common.service.BaseModelServiceImpl;
import com.lambkit.module.meta.service.MetaFileService;
import com.lambkit.module.meta.model.MetaFile;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-12-19
 * @version 1.0
 * @since 1.0
 */
public class MetaFileServiceImpl extends BaseModelServiceImpl<MetaFile> implements MetaFileService {
	
	protected MetaFile DAO = null;
	
	public MetaFile dao() {
		if(DAO==null) {
			DAO = Enhancer.enhance(MetaFile.class.getName(), MetaFile.class);
		}
		return DAO;
	}

	@Override
	public int remove(String ids) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ").append(getTableName()).append(" SET status=2");
		sql.append(" WHERE id in (").append(ids).append(")");
		return Db.update(sql.toString());
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ").append(getTableName()).append(" WHERE id in (");
		sql.append(ids).append(")");
		return Db.delete(sql.toString());
	}
}
