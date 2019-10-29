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

import com.lambkit.common.service.LambkitService;
import com.lambkit.module.meta.model.MetaFile;
import com.lambkit.module.meta.model.MetaFileCatalog;
import com.lambkit.module.meta.model.MetaFileCatalogMapping;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-12-19
 * @version 1.0
 * @since 1.0
 */
public interface MetaFileCatalogMappingService extends LambkitService<MetaFileCatalogMapping> {
	/**
	 * 保存
	 * @param file
	 * @param catelog
	 * @return
	 */
	boolean save(MetaFile file, MetaFileCatalog catelog);
	/**
	 * 保存
	 * @param fileId
	 * @param catelogId
	 * @return
	 */
	boolean save(Long fileId, Long catelogId);
	/**
	 * 删除
	 * @param catelogId
	 * @return
	 */
	int deleteByCatalog(Long catelogId);
	/**
	 * 删除
	 * @param fileId
	 * @return
	 */
	int deleteByFile(Long fileId);
	/**
	 * 获取
	 * @param fileId
	 * @param catelogId
	 * @return
	 */
	MetaFileCatalogMapping findById(Long fileId, Long catelogId);
	
	int moveFile(Long cataLogId, Long targetId, String ids);
	
	int removeFile(Long cataLogId, String ids);
	
	int removeFile(String ids);
	
}
