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
import com.lambkit.common.service.BaseModelServiceImpl;
import com.lambkit.db.sql.column.Columns;
import com.lambkit.module.meta.service.MetaFieldListService;
import com.lambkit.module.meta.model.MetaFieldList;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-11-24
 * @version 1.0
 * @since 1.0
 */
public class MetaFieldListServiceImpl extends BaseModelServiceImpl<MetaFieldList> implements MetaFieldListService {
	
	protected MetaFieldList DAO = null;
	
	public MetaFieldList dao() {
		if(DAO==null) {
			DAO = Enhancer.enhance(MetaFieldList.class.getName(), MetaFieldList.class);
		}
		return DAO;
	}

	@Override
	public MetaFieldList findByFieldAndTheme(Long fldid, Long tmid) {
		// TODO Auto-generated method stub
		Columns columns = Columns.create("fldid", fldid).eq("tmid", tmid);
		return dao().findFirstByColumns(columns);
	}
}
