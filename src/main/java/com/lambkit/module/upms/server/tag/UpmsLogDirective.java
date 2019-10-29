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

import com.lambkit.module.upms.rpc.model.UpmsLog;
import com.lambkit.web.tag.LambkitTemplateModel;
import com.lambkit.module.upms.rpc.api.UpmsLogService;
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
 * @date 2018-12-26
 * @version 1.0
 * @since 1.0
 */
/**
 * upms_log标签<br>
 * 参数：{id:主键}
 * 返回值：{entity:upms_log信息}
 * @author lambkit
 */
public class UpmsLogDirective extends LambkitTemplateModel {

	@Override
	public void onRender(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		String logId = get(params, "log_id");
		String description = get(params, "description");
		String username = get(params, "username");
		String startTime = get(params, "start_time");
		String spendTime = get(params, "spend_time");
		String basePath = get(params, "base_path");
		String uri = get(params, "uri");
		String url = get(params, "url");
		String method = get(params, "method");
		String parameter = get(params, "parameter");
		String userAgent = get(params, "user_agent");
		String ip = get(params, "ip");
		String result = get(params, "result");
		String permissions = get(params, "permissions");
		int pagenum = getInt(params, "pagenum", 0);
		int pagesize = getInt(params, "pagesize", 0);
		String wheresql = get(params, "sql", null);
		String sql = " from upms_log where "; 
		if(wheresql == null) {
			sql += " 1=1 ";
			if(StringUtils.hasText(logId)) sql += " and log_id=" + logId;//int
			if(StringUtils.hasText(description)) sql += " and description like '%" + description + "%'";//varchar
			if(StringUtils.hasText(username)) sql += " and username like '%" + username + "%'";//varchar
			if(StringUtils.hasText(startTime)) sql += " and start_time=" + startTime;//bigint
			if(StringUtils.hasText(spendTime)) sql += " and spend_time=" + spendTime;//int
			if(StringUtils.hasText(basePath)) sql += " and base_path like '%" + basePath + "%'";//varchar
			if(StringUtils.hasText(uri)) sql += " and uri like '%" + uri + "%'";//varchar
			if(StringUtils.hasText(url)) sql += " and url like '%" + url + "%'";//varchar
			if(StringUtils.hasText(method)) sql += " and method like '%" + method + "%'";//varchar
			if(StringUtils.hasText(parameter)) sql += " and parameter like '%" + parameter + "%'";//varchar
			if(StringUtils.hasText(userAgent)) sql += " and user_agent like '%" + userAgent + "%'";//varchar
			if(StringUtils.hasText(ip)) sql += " and ip like '%" + ip + "%'";//varchar
			if(StringUtils.hasText(result)) sql += " and result like '%" + result + "%'";//varchar
			if(StringUtils.hasText(permissions)) sql += " and permissions like '%" + permissions + "%'";//varchar
		} else {
			sql += wheresql;
		}
		
		String orderby = get(params, "orderby", null);
		
		if(StrKit.notBlank(orderby)) {
			sql += " order by " + orderby;
		}
		
		UpmsLogService service = UpmsLog.service();
		
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
