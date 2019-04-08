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

import com.lambkit.module.meta.model.MetaFile;
import com.lambkit.module.meta.service.MetaFileService;
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
 * @date 2019-01-07
 * @version 1.0
 * @since 1.0
 */
/**
 * meta_file标签<br>
 * 参数：{id:主键}
 * 返回值：{entity:meta_file信息}
 * @author lambkit
 */
public class MetaFileDirective extends BaseDirectiveModel {

	@Override
	public void executeMe(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		String id = get(params, "id");
		String sid = get(params, "sid");
		String userId = get(params, "user_id");
		String title = get(params, "title");
		String description = get(params, "description");
		String path = get(params, "path");
		String mimeType = get(params, "mime_type");
		String suffix = get(params, "suffix");
		String filesize = get(params, "filesize");
		String type = get(params, "type");
		String flag = get(params, "flag");
		String orders = get(params, "orders");
		String status = get(params, "status");
		String created = get(params, "created");
		String modified = get(params, "modified");
		String permission = get(params, "permission");
		int pagenum = getInt(params, "pagenum", 0);
		int pagesize = getInt(params, "pagesize", 0);
		String wheresql = get(params, "sql", null);
		String sql = " from meta_file where "; 
		if(wheresql == null) {
			sql += " 1=1 ";
			if(StringUtils.hasText(id)) sql += " and id=" + id;//int unsigned
			if(StringUtils.hasText(sid)) sql += " and sid=" + sid;//int
			if(StringUtils.hasText(userId)) sql += " and user_id=" + userId;//int unsigned
			if(StringUtils.hasText(title)) sql += " and title like '%" + title + "%'";//varchar
			if(StringUtils.hasText(description)) sql += " and description like '%" + description + "%'";//varchar
			if(StringUtils.hasText(path)) sql += " and path like '%" + path + "%'";//varchar
			if(StringUtils.hasText(mimeType)) sql += " and mime_type like '%" + mimeType + "%'";//varchar
			if(StringUtils.hasText(suffix)) sql += " and suffix like '%" + suffix + "%'";//varchar
			if(StringUtils.hasText(filesize)) sql += " and filesize=" + filesize;//int unsigned
			if(StringUtils.hasText(type)) sql += " and type like '%" + type + "%'";//varchar
			if(StringUtils.hasText(flag)) sql += " and flag like '%" + flag + "%'";//varchar
			if(StringUtils.hasText(orders)) sql += " and orders=" + orders;//int unsigned
			if(StringUtils.hasText(status)) sql += " and status=" + status;//tinyint
			if(StringUtils.hasText(created)) sql += " and created like '%" + created + "%'";//datetime
			if(StringUtils.hasText(modified)) sql += " and modified like '%" + modified + "%'";//datetime
			if(StringUtils.hasText(permission)) sql += " and permission like '%" + permission + "%'";//varchar
		} else {
			sql += wheresql;
		}
		
		String orderby = get(params, "orderby", null);
		
		if(StrKit.notBlank(orderby)) {
			sql += " order by " + orderby;
		}
		
		MetaFileService service = MetaFile.service();
		
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
