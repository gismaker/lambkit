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
package com.lambkit.module.upms.server.tag;

import java.io.IOException;
import java.util.Map;

import com.lambkit.module.upms.rpc.model.UpmsTag;
import com.lambkit.module.upms.rpc.api.UpmsTagService;
import com.lambkit.common.util.StringUtils;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.web.tag.base.BaseDirectiveModel;
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
 * @date 2018-12-26
 * @version 1.0
 * @since 1.0
 */
/**
 * upms_tag标签<br>
 * 参数：{id:主键}
 * 返回值：{entity:upms_tag信息}
 * @author lambkit
 */
public class UpmsTagDirective extends BaseDirectiveModel {

	@Override
	public void executeMe(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		String id = get(params, "id");
		String userId = get(params, "user_id");
		String name = get(params, "name");
		String description = get(params, "description");
		String orders = get(params, "orders");
		String ctime = get(params, "ctime");
		int pagenum = getInt(params, "pagenum", 0);
		int pagesize = getInt(params, "pagesize", 0);
		String wheresql = get(params, "sql", null);
		String sql = " from upms_tag where "; 
		if(wheresql == null) {
			sql += " 1=1 ";
			if(StringUtils.hasText(id)) sql += " and id=" + id;//int unsigned
			if(StringUtils.hasText(userId)) sql += " and user_id=" + userId;//int unsigned
			if(StringUtils.hasText(name)) sql += " and name like '%" + name + "%'";//varchar
			if(StringUtils.hasText(description)) sql += " and description like '%" + description + "%'";//varchar
			if(StringUtils.hasText(orders)) sql += " and orders=" + orders;//bigint unsigned
			if(StringUtils.hasText(ctime)) sql += " and ctime=" + ctime;//bigint unsigned
		} else {
			sql += wheresql;
		}
		
		String orderby = get(params, "orderby", null);
		
		if(StrKit.notBlank(orderby)) {
			sql += " order by " + orderby;
		}
		
		UpmsTagService service = UpmsTag.service();
		
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
