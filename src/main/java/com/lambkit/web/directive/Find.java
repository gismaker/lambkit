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
package com.lambkit.web.directive;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;
import com.lambkit.web.directive.annotation.JFinalDirective;
import com.lambkit.web.directive.base.DirectiveBase;

/**
 * #find("select * from table where tbid=?", 1) #for(x:model)
 * <li>#(x.id)</li> #end #end
 * 
 * @author 孤竹行
 */
@JFinalDirective("find")
public class Find extends DirectiveBase {

	@Override
	public void exec(Env env, Scope scope, Writer writer) {
		// TODO Auto-generated method stub
		String sql = getParam(0, scope);
		String key = "model";
		if (exprList.length() > 1) {
			Object[] paras = new Object[exprList.length() - 1];
			for (int i = 1; i < exprList.length(); i++) {
				paras[i - 1] = getParam(i, scope);
			}
			List<Record> list = Db.find(sql, paras);
			scope.set(key, list);
		} else {
			List<Record> list = Db.find(sql);
			scope.set(key, list);
		}
		stat.exec(env, scope, writer);// 执行自定义标签中包围的 html
	}

	@Override
	public boolean hasEnd() {
		return true;
	}
}
