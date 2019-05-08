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

import com.lambkit.common.service.BaseModelServiceImpl;
import com.lambkit.core.aop.AopKit;
import com.lambkit.db.sql.column.Column;
import com.lambkit.module.meta.service.MetaFieldMeasureService;
import com.lambkit.module.meta.model.MetaFieldMeasure;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2019-01-07
 * @version 1.0
 * @since 1.0
 */
public class MetaFieldMeasureServiceImpl extends BaseModelServiceImpl<MetaFieldMeasure> implements MetaFieldMeasureService {
	
	private MetaFieldMeasure DAO = null;
	
	public MetaFieldMeasure dao() {
		if(DAO==null) {
			DAO = AopKit.singleton(MetaFieldMeasure.class);
		}
		return DAO;
	}

	@Override
	public List<MetaFieldMeasure> findByFieldId(Long fldid) {
		// TODO Auto-generated method stub
		Column column = Column.create("fldid", fldid);
		return dao().findListByColumn(column);
	}
}
