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

import com.lambkit.module.meta.model.base.BaseMetaStoreResource;
import com.lambkit.module.meta.model.sql.MetaStoreResourceCriteria;
import com.lambkit.module.meta.service.MetaStoreResourceService;
import com.lambkit.module.meta.service.impl.MetaStoreResourceServiceImpl;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2019-01-07
 * @version 1.0
 * @since 1.0
 */
public class MetaStoreResource extends BaseMetaStoreResource<MetaStoreResource> {

	private static final long serialVersionUID = 1L;
	
	public static MetaStoreResourceService service() {
		return ServiceKit.inject(MetaStoreResourceService.class, MetaStoreResourceServiceImpl.class);
	}
	
	public static MetaStoreResourceCriteria sql() {
		return new MetaStoreResourceCriteria();
	}
	
	public static MetaStoreResourceCriteria sql(Column column) {
		MetaStoreResourceCriteria that = new MetaStoreResourceCriteria();
		that.add(column);
        return that;
    }

	public MetaStoreResource() {
		MgrdbConfig config = MetaMgrManager.me().getConfig();
		String dbconfig = config.getDbconfig();
		if(StrKit.notBlank(dbconfig)) {
			this.use(dbconfig);
		}
	}
}
