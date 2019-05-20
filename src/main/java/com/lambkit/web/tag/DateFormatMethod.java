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

import java.util.List;
import java.util.zip.DataFormatException;

import com.lambkit.common.util.DateTimeUtils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 时间格式转换
 * @author 孤竹行
 *
 */
public class DateFormatMethod implements TemplateMethodModelEx {

	@Override
	public Object exec(List params) throws TemplateModelException {
		// TODO Auto-generated method stub
		if (params==null || params.size() < 1) {
			throw new TemplateModelException("Wrong arguments");
		}
		String str_type = params.get(0).toString();
		String str_time = params.get(1).toString();
		String res = str_time;
		try {
			if(str_type.equalsIgnoreCase("l2t")) {
				res = DateTimeUtils.formatDefault(DateTimeUtils.parse(Long.valueOf(str_time)));
			} else if(str_type.equalsIgnoreCase("s2t")) {
					res = DateTimeUtils.formatDefault(DateTimeUtils.parseTime(str_time));
			} else if(str_type.equalsIgnoreCase("s2lt")) {
				res = String.valueOf(DateTimeUtils.tolong(str_time));
			}  
		} catch (DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
