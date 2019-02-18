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
import com.jfinal.template.expr.ast.Expr;
import com.jfinal.template.expr.ast.ExprList;
import com.jfinal.template.stat.Scope;
import com.lambkit.common.util.ArrayUtils;
import com.lambkit.web.directive.annotation.JFinalDirective;

import com.jfinal.template.io.Writer;


/**
 * 拥有任何一个角色
 * #shiroHasAnyRoles(roleName1,roleName2)
 * body
 * #end
 */
@JFinalDirective("shiroHasAnyRoles")
public class ShiroHasAnyRolesDirective extends ShiroDirectiveBase {
    private Expr[] exprs;


    public void setExprList(ExprList exprList) {
        exprs = exprList.getExprArray();
    }


    public void exec(Env env, Scope scope, Writer writer) {
        if (getSubject() != null && ArrayUtils.isNotEmpty(exprs)) {
            for (Expr expr : exprs) {
                if (getSubject().hasRole(expr.toString())) {
                    stat.exec(env, scope, writer);
                    break;
                }
            }
        }
    }

    public boolean hasEnd() {
        return true;
    }


}