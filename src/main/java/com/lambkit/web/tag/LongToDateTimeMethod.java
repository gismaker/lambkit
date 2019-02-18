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
package com.lambkit.web.tag;

import java.sql.Timestamp;
import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * Long型时间转Timestamp型时间
 * 
 * @author 孤竹行
 *
 */
public class LongToDateTimeMethod implements TemplateMethodModel {

	@Override
	public Object exec(List params) throws TemplateModelException {
		// TODO Auto-generated method stub
		if (params==null || params.size() < 1) {
			throw new TemplateModelException("Wrong arguments");
		}
		return new Timestamp(Long.valueOf(params.get(0).toString()));
	}

}
