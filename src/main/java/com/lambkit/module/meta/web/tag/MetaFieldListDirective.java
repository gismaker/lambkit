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

import com.lambkit.module.meta.model.MetaFieldList;
import com.lambkit.module.meta.service.MetaFieldListService;
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
 * meta_field_list标签<br>
 * 参数：{id:主键}
 * 返回值：{entity:meta_field_list信息}
 * @author lambkit
 */
public class MetaFieldListDirective extends BaseDirectiveModel {

	@Override
	public void executeMe(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		String id = get(params, "id");
		String fldid = get(params, "fldid");
		String tmid = get(params, "tmid");
		String viewname = get(params, "viewname");
		String isview = get(params, "isview");
		String isorder = get(params, "isorder");
		String viewmaxlen = get(params, "viewmaxlen");
		String viewtype = get(params, "viewtype");
		String vieworder = get(params, "vieworder");
		String issearch = get(params, "issearch");
		String searchtype = get(params, "searchtype");
		String searchinfo = get(params, "searchinfo");
		int pagenum = getInt(params, "pagenum", 0);
		int pagesize = getInt(params, "pagesize", 0);
		String wheresql = get(params, "sql", null);
		String sql = " from meta_field_list where "; 
		if(wheresql == null) {
			sql += " 1=1 ";
			if(StringUtils.hasText(id)) sql += " and id=" + id;//int unsigned
			if(StringUtils.hasText(fldid)) sql += " and fldid=" + fldid;//int unsigned
			if(StringUtils.hasText(tmid)) sql += " and tmid=" + tmid;//int unsigned
			if(StringUtils.hasText(viewname)) sql += " and viewname like '%" + viewname + "%'";//varchar
			if(StringUtils.hasText(isview)) sql += " and isview like '%" + isview + "%'";//varchar
			if(StringUtils.hasText(isorder)) sql += " and isorder like '%" + isorder + "%'";//varchar
			if(StringUtils.hasText(viewmaxlen)) sql += " and viewmaxlen=" + viewmaxlen;//int unsigned
			if(StringUtils.hasText(viewtype)) sql += " and viewtype like '%" + viewtype + "%'";//varchar
			if(StringUtils.hasText(vieworder)) sql += " and vieworder=" + vieworder;//int unsigned
			if(StringUtils.hasText(issearch)) sql += " and issearch like '%" + issearch + "%'";//varchar
			if(StringUtils.hasText(searchtype)) sql += " and searchtype like '%" + searchtype + "%'";//varchar
			if(StringUtils.hasText(searchinfo)) sql += " and searchinfo like '%" + searchinfo + "%'";//varchar
		} else {
			sql += wheresql;
		}
		
		String orderby = get(params, "orderby", null);
		
		if(StrKit.notBlank(orderby)) {
			sql += " order by " + orderby;
		}
		
		MetaFieldListService service = MetaFieldList.service();
		
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
