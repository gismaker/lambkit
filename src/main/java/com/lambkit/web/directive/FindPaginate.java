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

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;
import com.lambkit.web.directive.annotation.JFinalDirective;
import com.lambkit.web.directive.base.DirectiveBase;

/**
 * #findPage("select * from table",1,10) #for(x:model.getList())
 * <li>#(x.id)</li> #end #end
 * 
 * @author 孤竹行
 */
@JFinalDirective("findPage")
public class FindPaginate extends DirectiveBase {

	@Override
	public void exec(Env env, Scope scope, Writer writer) {
		// TODO Auto-generated method stub
		String key = "model";
		String sql = getParam(0, scope);
		String[] sqls = sql.split("from");
		int pageNum = (Integer) exprList.getExpr(1).eval(scope);
		int pageSize = (Integer) exprList.getExpr(2).eval(scope);
		if (exprList.length() > 3) {
			Object[] paras = new Object[exprList.length() - 3];
			for (int i = 3; i < exprList.length(); i++) {
				paras[i - 3] = getParam(i, scope);
			}
			Page<Record> page = Db.paginate(pageNum, pageSize, sqls[0], " from " + sqls[1], paras);
			scope.set(key, page);
		} else {
			Page<Record> page = Db.paginate(pageNum, pageSize, sqls[0], " from " + sqls[1]);
			scope.set(key, page);
		}
		stat.exec(env, scope, writer);// 执行自定义标签中包围的 html
	}

	@Override
	public boolean hasEnd() {
		return true;
	}
}
