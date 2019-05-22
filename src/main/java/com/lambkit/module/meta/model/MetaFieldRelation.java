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

import com.jfinal.kit.StrKit;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.db.sql.column.Column;
import com.lambkit.db.mgr.MgrdbConfig;
import com.lambkit.module.meta.MetaMgrManager;

import com.lambkit.module.meta.model.base.BaseMetaFieldRelation;
import com.lambkit.module.meta.model.sql.MetaFieldRelationCriteria;
import com.lambkit.module.meta.service.MetaFieldRelationService;
import com.lambkit.module.meta.service.impl.MetaFieldRelationServiceImpl;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2019-01-07
 * @version 1.0
 * @since 1.0
 */
public class MetaFieldRelation extends BaseMetaFieldRelation<MetaFieldRelation> {

	private static final long serialVersionUID = 1L;
	
	public static MetaFieldRelationService service() {
		return ServiceKit.inject(MetaFieldRelationService.class, MetaFieldRelationServiceImpl.class);
	}
	
	public static MetaFieldRelationCriteria sql() {
		return new MetaFieldRelationCriteria();
	}
	
	public static MetaFieldRelationCriteria sql(Column column) {
		MetaFieldRelationCriteria that = new MetaFieldRelationCriteria();
		that.add(column);
        return that;
    }

	public MetaFieldRelation() {
		MgrdbConfig config = MetaMgrManager.me().getConfig();
		String dbconfig = config.getDbconfig();
		if(StrKit.notBlank(dbconfig)) {
			this.use(dbconfig);
		}
	}
}
