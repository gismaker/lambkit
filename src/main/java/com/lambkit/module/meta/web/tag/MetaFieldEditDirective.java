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

import com.lambkit.module.meta.model.MetaFieldEdit;
import com.lambkit.module.meta.service.MetaFieldEditService;
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
 * meta_field_edit标签<br>
 * 参数：{id:主键}
 * 返回值：{entity:meta_field_edit信息}
 * @author lambkit
 */
public class MetaFieldEditDirective extends LambkitTemplateModel {

	@Override
	public void onRender(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		String id = get(params, "id");
		String fldid = get(params, "fldid");
		String tmid = get(params, "tmid");
		String checktype = get(params, "checktype");
		String edittype = get(params, "edittype");
		String editid = get(params, "editid");
		String editminlen = get(params, "editminlen");
		String editmaxlen = get(params, "editmaxlen");
		String editorder = get(params, "editorder");
		int pagenum = getInt(params, "pagenum", 0);
		int pagesize = getInt(params, "pagesize", 0);
		String wheresql = get(params, "sql", null);
		String sql = " from meta_field_edit where "; 
		if(wheresql == null) {
			sql += " 1=1 ";
			if(StringUtils.hasText(id)) sql += " and id=" + id;//int unsigned
			if(StringUtils.hasText(fldid)) sql += " and fldid=" + fldid;//int unsigned
			if(StringUtils.hasText(tmid)) sql += " and tmid=" + tmid;//int unsigned
			if(StringUtils.hasText(checktype)) sql += " and checktype=" + checktype;//int unsigned
			if(StringUtils.hasText(edittype)) sql += " and edittype=" + edittype;//int unsigned
			if(StringUtils.hasText(editid)) sql += " and editid=" + editid;//int unsigned
			if(StringUtils.hasText(editminlen)) sql += " and editminlen=" + editminlen;//int unsigned
			if(StringUtils.hasText(editmaxlen)) sql += " and editmaxlen=" + editmaxlen;//int unsigned
			if(StringUtils.hasText(editorder)) sql += " and editorder=" + editorder;//int unsigned
		} else {
			sql += wheresql;
		}
		
		String orderby = get(params, "orderby", null);
		
		if(StrKit.notBlank(orderby)) {
			sql += " order by " + orderby;
		}
		
		MetaFieldEditService service = MetaFieldEdit.service();
		
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
