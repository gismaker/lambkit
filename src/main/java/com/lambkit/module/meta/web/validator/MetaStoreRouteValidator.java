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
package com.lambkit.module.meta.web.validator;

import com.jfinal.core.Controller;
import com.lambkit.web.validator.LambkitValidator;

import com.lambkit.module.meta.model.MetaStoreRoute;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2019-01-07
 * @version 1.0
 * @since 1.0
 */
public class MetaStoreRouteValidator extends LambkitValidator {

	@Override
	protected String getTableName(Controller c) {
		return MetaStoreRoute.service().getTableName();
	}
	
	@Override
	protected String getPrefix() {
		return "model";//StrKit.firstCharToLowerCase(MetaStoreRoute.class.getSimpleName());
	}
}
