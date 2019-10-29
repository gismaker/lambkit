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

import com.lambkit.module.meta.model.MetaField;
import com.lambkit.module.meta.service.MetaFieldService;
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
 * meta_field标签<br>
 * 参数：{id:主键}
 * 返回值：{entity:meta_field信息}
 * @author lambkit
 */
public class MetaFieldDirective extends LambkitTemplateModel {

	@Override
	public void onRender(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		String id = get(params, "id");
		String tbid = get(params, "tbid");
		String name = get(params, "name");
		String title = get(params, "title");
		String datatype = get(params, "datatype");
		String classtype = get(params, "classtype");
		String iskey = get(params, "iskey");
		String isunsigned = get(params, "isunsigned");
		String isnullable = get(params, "isnullable");
		String isai = get(params, "isai");
		String flddefault = get(params, "flddefault");
		String descript = get(params, "descript");
		String isfk = get(params, "isfk");
		String fktbid = get(params, "fktbid");
		String isview = get(params, "isview");
		String isselect = get(params, "isselect");
		String isedit = get(params, "isedit");
		String ismustfld = get(params, "ismustfld");
		String ismap = get(params, "ismap");
		String olap = get(params, "olap");
		String orders = get(params, "orders");
		String permission = get(params, "permission");
		int pagenum = getInt(params, "pagenum", 0);
		int pagesize = getInt(params, "pagesize", 0);
		String wheresql = get(params, "sql", null);
		String sql = " from meta_field where "; 
		if(wheresql == null) {
			sql += " 1=1 ";
			if(StringUtils.hasText(id)) sql += " and id=" + id;//int unsigned
			if(StringUtils.hasText(tbid)) sql += " and tbid=" + tbid;//int unsigned
			if(StringUtils.hasText(name)) sql += " and name like '%" + name + "%'";//varchar
			if(StringUtils.hasText(title)) sql += " and title like '%" + title + "%'";//varchar
			if(StringUtils.hasText(datatype)) sql += " and datatype like '%" + datatype + "%'";//varchar
			if(StringUtils.hasText(classtype)) sql += " and classtype like '%" + classtype + "%'";//varchar
			if(StringUtils.hasText(iskey)) sql += " and iskey like '%" + iskey + "%'";//varchar
			if(StringUtils.hasText(isunsigned)) sql += " and isunsigned like '%" + isunsigned + "%'";//varchar
			if(StringUtils.hasText(isnullable)) sql += " and isnullable like '%" + isnullable + "%'";//varchar
			if(StringUtils.hasText(isai)) sql += " and isai like '%" + isai + "%'";//varchar
			if(StringUtils.hasText(flddefault)) sql += " and flddefault like '%" + flddefault + "%'";//varchar
			if(StringUtils.hasText(descript)) sql += " and descript like '%" + descript + "%'";//varchar
			if(StringUtils.hasText(isfk)) sql += " and isfk like '%" + isfk + "%'";//varchar
			if(StringUtils.hasText(fktbid)) sql += " and fktbid=" + fktbid;//int unsigned
			if(StringUtils.hasText(isview)) sql += " and isview like '%" + isview + "%'";//varchar
			if(StringUtils.hasText(isselect)) sql += " and isselect like '%" + isselect + "%'";//varchar
			if(StringUtils.hasText(isedit)) sql += " and isedit like '%" + isedit + "%'";//varchar
			if(StringUtils.hasText(ismustfld)) sql += " and ismustfld like '%" + ismustfld + "%'";//varchar
			if(StringUtils.hasText(ismap)) sql += " and ismap like '%" + ismap + "%'";//varchar
			if(StringUtils.hasText(olap)) sql += " and olap like '%" + olap + "%'";//varchar
			if(StringUtils.hasText(orders)) sql += " and orders=" + orders;//int unsigned
			if(StringUtils.hasText(permission)) sql += " and permission like '%" + permission + "%'";//varchar
		} else {
			sql += wheresql;
		}
		
		String orderby = get(params, "orderby", null);
		
		if(StrKit.notBlank(orderby)) {
			sql += " order by " + orderby;
		}
		
		MetaFieldService service = MetaField.service();
		
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
