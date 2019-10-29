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

import com.jfinal.plugin.activerecord.Db;
import com.lambkit.common.service.LambkitModelServiceImpl;
import com.lambkit.core.aop.AopKit;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.meta.service.MetaFileCatalogMappingService;
import com.lambkit.module.meta.model.MetaFile;
import com.lambkit.module.meta.model.MetaFileCatalog;
import com.lambkit.module.meta.model.MetaFileCatalogMapping;
import com.lambkit.module.meta.model.sql.MetaFileCatalogMappingCriteria;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-12-19
 * @version 1.0
 * @since 1.0
 */
public class MetaFileCatalogMappingServiceImpl extends LambkitModelServiceImpl<MetaFileCatalogMapping> implements MetaFileCatalogMappingService {
	
	protected MetaFileCatalogMapping DAO = null;
	
	public MetaFileCatalogMapping dao() {
		if(DAO==null) {
			DAO = AopKit.singleton(MetaFileCatalogMapping.class);
		}
		return DAO;
	}

	@Override
	public boolean save(MetaFile file, MetaFileCatalog catelog) {
		// TODO Auto-generated method stub
		if(file==null || catelog==null) return false;
		MetaFileCatalogMapping fcm = new MetaFileCatalogMapping();
		fcm.setCatalogId(catelog.getId());
		fcm.setFileId(file.getId());
		return fcm.save();
	}

	@Override
	public boolean save(Long fileId, Long catelogId) {
		// TODO Auto-generated method stub
		if(fileId==null || catelogId==null) return false;
		MetaFileCatalogMapping fcm = new MetaFileCatalogMapping();
		fcm.setCatalogId(catelogId);
		fcm.setFileId(fileId);
		return fcm.save();
	}

	@Override
	public int deleteByCatalog(Long catelogId) {
		// TODO Auto-generated method stub
		Example example = Example.create(getTableName(), MetaFileCatalogMappingCriteria.create().andCatalogIdEqualTo(catelogId));
		return delete(example);
	}

	@Override
	public int deleteByFile(Long fileId) {
		// TODO Auto-generated method stub
		Example example = Example.create(getTableName(), MetaFileCatalogMappingCriteria.create().andFileIdEqualTo(fileId));
		return delete(example);
	}

	@Override
	public MetaFileCatalogMapping findById(Long fileId, Long catelogId) {
		// TODO Auto-generated method stub
		return dao().findByIds(catelogId, fileId);
		/*
		MetaFileCatalogMappingExample example = new MetaFileCatalogMappingExample();
		example.createCriteria().andCatalogIdEqualTo(catelogId).andFileIdEqualTo(fileId);
		return findFirst(example.toQueryParas());
		*/
	}

	@Override
	public int moveFile(Long cataLogId, Long targetId, String ids) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ").append(getTableName()).append(" SET catalog_id=").append(targetId);
		sql.append(" WHERE file_id in (").append(ids).append(") and catalog_id=").append(cataLogId);
		return Db.update(sql.toString());
	}

	@Override
	public int removeFile(Long cataLogId, String ids) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ").append(getTableName()).append(" WHERE file_id in (");
		sql.append(ids).append(")").append(" adn catalog_id=").append(cataLogId);
		return Db.delete(sql.toString());
	}

	@Override
	public int removeFile(String ids) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ").append(getTableName()).append(" WHERE file_id in (");
		sql.append(ids).append(")");
		return Db.delete(sql.toString());
	}
}
