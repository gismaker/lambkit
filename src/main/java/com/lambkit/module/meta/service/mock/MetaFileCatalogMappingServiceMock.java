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
package com.lambkit.module.meta.service.mock;

import com.lambkit.common.service.BaseServiceMock;
import com.lambkit.module.meta.model.MetaFile;
import com.lambkit.module.meta.model.MetaFileCatalog;
import com.lambkit.module.meta.model.MetaFileCatalogMapping;
import com.lambkit.module.meta.service.MetaFileCatalogMappingService;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-12-19
 * @version 1.0
 * @since 1.0
 */
public class MetaFileCatalogMappingServiceMock extends BaseServiceMock<MetaFileCatalogMapping> implements MetaFileCatalogMappingService {

	@Override
	public boolean save(MetaFile file, MetaFileCatalog catelog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(Long fileId, Long catelogId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int deleteByCatalog(Long catelogId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByFile(Long fileId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MetaFileCatalogMapping findById(Long fileId, Long catelogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int moveFile(Long cataLogId, Long targetId, String ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeFile(Long cataLogId, String ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeFile(String ids) {
		// TODO Auto-generated method stub
		return 0;
	}

}