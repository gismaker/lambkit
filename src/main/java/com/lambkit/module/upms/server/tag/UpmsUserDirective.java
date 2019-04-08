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

import com.lambkit.module.upms.rpc.model.UpmsUser;
import com.lambkit.module.upms.rpc.api.UpmsUserService;
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
 * upms_user标签<br>
 * 参数：{id:主键}
 * 返回值：{entity:upms_user信息}
 * @author lambkit
 */
public class UpmsUserDirective extends BaseDirectiveModel {

	@Override
	public void executeMe(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		String userId = get(params, "user_id");
		String username = get(params, "username");
		String password = get(params, "password");
		String salt = get(params, "salt");
		String realname = get(params, "realname");
		String avatar = get(params, "avatar");
		String phone = get(params, "phone");
		String email = get(params, "email");
		String sex = get(params, "sex");
		String locked = get(params, "locked");
		String ctime = get(params, "ctime");
		int pagenum = getInt(params, "pagenum", 0);
		int pagesize = getInt(params, "pagesize", 0);
		String wheresql = get(params, "sql", null);
		String sql = " from upms_user where "; 
		if(wheresql == null) {
			sql += " 1=1 ";
			if(StringUtils.hasText(userId)) sql += " and user_id=" + userId;//int unsigned
			if(StringUtils.hasText(username)) sql += " and username like '%" + username + "%'";//varchar
			if(StringUtils.hasText(password)) sql += " and password like '%" + password + "%'";//varchar
			if(StringUtils.hasText(salt)) sql += " and salt like '%" + salt + "%'";//varchar
			if(StringUtils.hasText(realname)) sql += " and realname like '%" + realname + "%'";//varchar
			if(StringUtils.hasText(avatar)) sql += " and avatar like '%" + avatar + "%'";//varchar
			if(StringUtils.hasText(phone)) sql += " and phone like '%" + phone + "%'";//varchar
			if(StringUtils.hasText(email)) sql += " and email like '%" + email + "%'";//varchar
			if(StringUtils.hasText(sex)) sql += " and sex=" + sex;//tinyint
			if(StringUtils.hasText(locked)) sql += " and locked=" + locked;//tinyint
			if(StringUtils.hasText(ctime)) sql += " and ctime=" + ctime;//bigint
		} else {
			sql += wheresql;
		}
		
		String orderby = get(params, "orderby", null);
		
		if(StrKit.notBlank(orderby)) {
			sql += " order by " + orderby;
		}
		
		UpmsUserService service = UpmsUser.service();
		
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
