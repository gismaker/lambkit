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
package com.lambkit.component.shiro.directives;

import com.jfinal.template.Env;
import com.jfinal.template.stat.Scope;
import com.lambkit.web.directive.annotation.JFinalDirective;

import com.jfinal.template.io.Writer;

/**
 * 游客访问时。
 * 但是，当用户登录成功了就不显示了
 * #shiroGuest()
 * body
 * #end
 */
@JFinalDirective("shiroGuest")
public class ShiroGuestDirective extends ShiroDirectiveBase {
    public void exec(Env env, Scope scope, Writer writer) {
        if (getSubject() == null || getSubject().getPrincipal() == null)
            stat.exec(env, scope, writer);
    }

    public boolean hasEnd() {
        return true;
    }
}
