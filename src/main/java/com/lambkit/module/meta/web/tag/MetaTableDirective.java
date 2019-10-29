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
package com.lambkit.module.meta.web.tag;

import java.io.IOException;
import java.util.Map;

import com.lambkit.module.meta.model.MetaTable;
import com.lambkit.module.meta.service.MetaTableService;
import com.lambkit.web.tag.LambkitTemplateModel;
import com.lambkit.common.util.StringUtils;
import com.lambkit.common.service.ServiceKit;
import com.jfinal.kit.StrKit;
import com.jfinal.render.FreeMarkerRender;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2019-01-07
 * @version 1.0
 * @since 1.0
 */
/**
 * meta_table标签<br>
 * 参数：{id:主键}
 * 返回值：{entity:meta_table信息}
 * @author lambkit
 */
public class MetaTableDirective extends LambkitTemplateModel {

	@Override
	public void onRender(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		String id = get(params, "id");
		String sid = get(params, "sid");
		String userId = get(params, "user_id");
		String name = get(params, "name");
		String title = get(params, "title");
		String keyname = get(params, "keyname");
		String namefld = get(params, "namefld");
		String olapType = get(params, "olap_type");
		String type = get(params, "type");
		String created = get(params, "created");
		String modified = get(params, "modified");
		String status = get(params, "status");
		String orders = get(params, "orders");
		String permission = get(params, "permission");
		int pagenum = getInt(params, "pagenum", 0);
		int pagesize = getInt(params, "pagesize", 0);
		String wheresql = get(params, "sql", null);
		String sql = " from meta_table where "; 
		if(wheresql == null) {
			sql += " 1=1 ";
			if(StringUtils.hasText(id)) sql += " and id=" + id;//int unsigned
			if(StringUtils.hasText(sid)) sql += " and sid=" + sid;//int unsigned
			if(StringUtils.hasText(userId)) sql += " and user_id=" + userId;//int unsigned
			if(StringUtils.hasText(name)) sql += " and name like '%" + name + "%'";//varchar
			if(StringUtils.hasText(title)) sql += " and title like '%" + title + "%'";//varchar
			if(StringUtils.hasText(keyname)) sql += " and keyname like '%" + keyname + "%'";//varchar
			if(StringUtils.hasText(namefld)) sql += " and namefld like '%" + namefld + "%'";//varchar
			if(StringUtils.hasText(olapType)) sql += " and olap_type like '%" + olapType + "%'";//varchar
			if(StringUtils.hasText(type)) sql += " and type like '%" + type + "%'";//varchar
			if(StringUtils.hasText(created)) sql += " and created like '%" + created + "%'";//datetime
			if(StringUtils.hasText(modified)) sql += " and modified like '%" + modified + "%'";//datetime
			if(StringUtils.hasText(status)) sql += " and status=" + status;//int
			if(StringUtils.hasText(orders)) sql += " and orders=" + orders;//int unsigned
			if(StringUtils.hasText(permission)) sql += " and permission like '%" + permission + "%'";//varchar
		} else {
			sql += wheresql;
		}
		
		String orderby = get(params, "orderby", null);
		
		if(StrKit.notBlank(orderby)) {
			sql += " order by " + orderby;
		}
		
		MetaTableService service = MetaTable.service();
		
		String tagEntityKeyname = get(params, "key", "entity");
		if(pagenum==0) {
			env.setVariable(tagEntityKeyname, FreeMarkerRender.getConfiguration().getObjectWrapper().wrap(
					service.dao().findFirst("select *" + sql)));
		} else {
			if(pagesize==0) {
				env.setVariable(tagEntityKeyname, FreeMarkerRender.getConfiguration().getObjectWrapper().wrap(
						service.dao().find("select *" + sql)));
			} else {
				env.setVariable(tagEntityKeyname, FreeMarkerRender.getConfiguration().getObjectWrapper().wrap(
						service.dao().paginate(pagenum, pagesize, "select *", sql)));
			}
		}
        body.render(env.getOut());
	}
}
