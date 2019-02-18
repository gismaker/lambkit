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
package com.lambkit.web.tag.base;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public abstract class BaseDirectiveModel implements TemplateDirectiveModel {

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		executeMe(env, params, loopVars, body);
	}
	
	public abstract void executeMe(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException;

	protected String get(Map params, String key) {
		return get(params, key, "");
	}

	protected String get(Map params, String key, String defaultvalue) {
		if (params.containsKey(key) && params.get(key) != null) {
			return params.get(key).toString();
        }
		return defaultvalue;
	}
	
	protected int getInt(Map params, String key, int defaultvalue) {
		if (params.containsKey(key) && params.get(key) != null) {
			String str = params.get(key).toString();
			if("".equals(str)) return defaultvalue;
			else return Integer.valueOf(str.toString());
        }
		return defaultvalue;
	}
	
	protected Long getLong(Map params, String key, Long defaultvalue) {
		if (params.containsKey(key) && params.get(key) != null) {
			String str = params.get(key).toString();
			if("".equals(str)) return defaultvalue;
			else return Long.valueOf(str.toString());
        }
		return defaultvalue;
	}
}
