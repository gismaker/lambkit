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

import org.apache.commons.lang.StringUtils;

import com.beust.jcommander.internal.Lists;
import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.common.service.BaseModelServiceImpl;
import com.lambkit.db.sql.column.Column;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.meta.service.MetaFileCatalogService;
import com.lambkit.module.meta.model.MetaFileCatalog;
import com.lambkit.module.meta.model.MetaFileCatalogMapping;
import com.lambkit.module.meta.model.sql.MetaFileCatalogCriteria;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-12-19
 * @version 1.0
 * @since 1.0
 */
public class MetaFileCatalogServiceImpl extends BaseModelServiceImpl<MetaFileCatalog> implements MetaFileCatalogService {
	
	protected MetaFileCatalog DAO = null;
	
	public MetaFileCatalog dao() {
		if(DAO==null) {
			DAO = Enhancer.enhance(MetaFileCatalog.class.getName(), MetaFileCatalog.class);
		}
		return DAO;
	}
	
	@Override
	public List<MetaFileCatalog> getFolders(Long userId, Long pid) {
		userId = userId==null ? 0 : userId;
		Example example = Example.create(getTableName(), MetaFileCatalogCriteria.create().andUserIdEqualTo(userId).andPidEqualTo(pid));
		List<MetaFileCatalog> folders = find(example);
		return folders;
	}

	@Override
	public List<Record> getAll(Long pid) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT id,title,filesize,'' as path,'' as suffix,'folder' as t FROM meta_file_catalog WHERE pid=? ");
		sql.append(" UNION ");
		sql.append(" SELECT id,title,filesize,path,suffix,'file' as t ");
		sql.append(" FROM meta_file_catalog_mapping m LEFT JOIN meta_file f on m.file_id=f.id where m.catalog_id=? ");
		return Db.find(sql.toString(), pid, pid);
	}

	@Override
	public List<MetaFileCatalog> getFolderLink(Long userId, Long id) {
		// TODO Auto-generated method stub
		List<MetaFileCatalog> flds = null;
		MetaFileCatalog catalog = findById(id);
		if(catalog!=null && catalog.getUserId()==userId) {
			flds = Lists.newArrayList();
			if(catalog.getPid()!=null || catalog.getPid()>0) {
				flds = getFolderParent(flds, catalog.getPid());
			}
			flds.add(catalog);
		}
		return flds;
	}
	
	/**
	 * 遍历父类
	 * @param flds
	 * @param id
	 * @return
	 */
	private List<MetaFileCatalog> getFolderParent(List<MetaFileCatalog> flds, Long id) {
		MetaFileCatalog catalog = findById(id);
		if(catalog!=null) {
			if(catalog.getPid()!=null || catalog.getPid()>0) {
				flds = getFolderParent(flds, catalog.getPid());
			}
			flds.add(catalog);
		}
		return flds;
	}
	
	/**
	 * 遍历子类
	 * @param flds
	 * @param pid
	 * @return
	 */
	private List<MetaFileCatalog> getFolderChildren(List<MetaFileCatalog> flds, Long pid) {
		Column column = Column.create("pid", pid);
		List<MetaFileCatalog> catalogs = dao().findListByColumn(column);
		if(catalogs!=null) {
			for (MetaFileCatalog ctlog : catalogs) {
				flds = getFolderChildren(flds, ctlog.getId());
			}
			flds.addAll(catalogs);
		}
		return flds;
	}

	@Override
	public int remove(String ids) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ").append(getTableName()).append(" SET pid=-1");
		sql.append(" WHERE id in (").append(ids).append(")");
		return Db.update(sql.toString());
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(ids)) {
			return 0;
		}
		String[] idArray = ids.split(",");
		int count = 0;
		for (String idStr : idArray) {
			if (StringUtils.isBlank(idStr)) {
				continue;
			}
			Long id = Long.parseLong(idStr);
			int result = deleteChildren(id);
			count += result;
		}
		return count;
	}
	
	private int deleteChildren(Long pid) {
		int n = 0;
		Column column = Column.create("pid", pid);
		List<MetaFileCatalog> catalogs = dao().findListByColumn(column);
		if(catalogs!=null) {
			for (MetaFileCatalog ctlog : catalogs) {
				Long catelogId = ctlog.getId();
				/*删除file
				StringBuilder nsql = new StringBuilder();
				nsql.append("DELETE FROM meta_file WHERE id in (");
				nsql.append("select file_id from meta_file_catalog_mapping where catalog_id in (");
				nsql.append(ids).append("))");
				Db.delete(nsql.toString());
				*/
				n += deleteChildren(catelogId);
				boolean flag = ctlog.delete();
				n += flag ? 1 : 0;
				if(flag) {
					MetaFileCatalogMapping.service().deleteByCatalog(catelogId);
				}
			}
		}
		return n;
	}

	@Override
	public int move(Long pid, String ids) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ").append(getTableName()).append(" SET pid=").append(pid);
		sql.append(" WHERE id in (").append(ids).append(")");
		int n = Db.update(sql.toString());
		return n;
	}
}
