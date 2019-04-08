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
package com.lambkit.module.meta.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;
import com.lambkit.common.service.BaseService;

import com.lambkit.module.meta.model.MetaFileCatalog;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-12-19
 * @version 1.0
 * @since 1.0
 */
public interface MetaFileCatalogService extends BaseService<MetaFileCatalog> {

	//getCatalog(Long pid);
	
	/**
	 * 获取pid目录下的文件夹
	 * @param userId
	 * @param pid
	 * @return
	 */
	List<MetaFileCatalog> getFolders(Long userId, Long pid);
	/**
	 * 获取pid向上级的目录链接
	 * @param userId
	 * @param pid
	 * @return
	 */
	List<MetaFileCatalog> getFolderLink(Long userId, Long pid);
	
	/**
	 * 获取该目录所有文件和文件夹
	 * @param id
	 * @return
	 */
	List<Record> getAll(Long id);
	/**
	 * 移除目录，但不删除
	 * @param id
	 * @return
	 */
	int remove(String ids);
	/**
	 * 删除目录
	 * @param id
	 * @return
	 */
	int delete(String ids);
	
	/**
	 * 移动ids目录列表到pid目录
	 * @param pid
	 * @param ids
	 * @return
	 */
	int move(Long pid, String ids);
	
}
