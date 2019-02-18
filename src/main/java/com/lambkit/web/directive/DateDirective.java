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


import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;
import com.lambkit.common.util.DateTimeUtils;
import com.lambkit.web.directive.annotation.JFinalDirective;
import com.lambkit.web.directive.base.DirectiveBase;

@JFinalDirective("long2date")
public class DateDirective extends DirectiveBase {
	@Override
	public void exec(Env env, Scope scope, Writer writer) {
		// TODO Auto-generated method stub
		Long longtime = getParam(0, scope);
		write(writer, DateTimeUtils.longToString(longtime));
	}
}
