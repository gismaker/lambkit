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

import java.io.IOException;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.render.FreeMarkerRender;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * SQL语句查询数据
 * 
 * @author 孤竹行
 *
 */
public class QueryMarker extends LambkitTemplateModel {

	private String SQL = "sql";
	private String PAGE_NUM = "pagenum";
	private String PAGE_SIZE = "pagesize";
	private String SELECT = "select"; 
	private String ExceptSELECT = "sqlExceptSelect";
	
	@Override
	public void onRender(Environment env, Map params,
			TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		String sql = get(params, SQL);
		String select = get(params, SELECT);
		String sqlExceptSelect = get(params, ExceptSELECT);
		int pagenum = getInt(params, PAGE_NUM, 0);
		int pagesize = getInt(params, PAGE_SIZE, 0);
		
		String keyname = get(params, "key", "entity");
		if(StrKit.notBlank(sql)) {
			if(pagenum==0) {
				env.setVariable(keyname, FreeMarkerRender.getConfiguration().getObjectWrapper().wrap(Db.findFirst(sql)));
			} else if(pagesize==0) {
				env.setVariable(keyname, FreeMarkerRender.getConfiguration().getObjectWrapper().wrap(Db.find(sql)));
			}
		} else if(pagenum > 0 && pagesize > 0 && StrKit.notBlank(select) && StrKit.notBlank(sqlExceptSelect)) {
			env.setVariable(keyname, FreeMarkerRender.getConfiguration().getObjectWrapper().wrap(Db.paginate(pagenum, pagesize, select, sqlExceptSelect)));
		}
		body.render(env.getOut());
	}

}
