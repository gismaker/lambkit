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
package com.lambkit.component.shiro.freemarker;

import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;


/**
 * JSP tag that renders the tag body if the current user <em>is not</em> known to the system, either because they
 * haven't logged in yet, or because they have no 'RememberMe' identity.
 * <p/>
 * <p>The logically opposite tag of this one is the {@link cn.dreampie.common.plugin.shiro.freemarker.UserTag}.  Please read that class's JavaDoc as it explains
 * more about the differences between Authenticated/Unauthenticated and User/Guest semantic differences.
 * <p/>
 * <p>Equivalent to {@link org.apache.shiro.web.tags.GuestTag}</p>
 *
 * @since 0.9
 */
public class GuestTag extends SecureTag {
  private static final Logger log = Logger.getLogger("AuthenticatedTag");

  @Override
  public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
    if (getSubject() == null || getSubject().getPrincipal() == null) {
      if (log.isDebugEnabled()) {
        log.debug("Subject does not exist or does not have a known identity (aka 'principal').  " +
            "Tag body will be evaluated.");
      }

      renderBody(env, body);
    } else {
      if (log.isDebugEnabled()) {
        log.debug("Subject exists or has a known identity (aka 'principal').  " +
            "Tag body will not be evaluated.");
      }
    }
  }
//  String getName(Map params) {
//    return getParam(params, "name");
//  }
//
//  @Override
//  public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
//    boolean show = false;
//    //判断是否使用访客权限
//
//    show = (getSubject() == null || getSubject().getPrincipal() == null || !getSubject().isAuthenticated());
//
//    if (show) {
//      renderBody(env, body);
//    }
//  }
//
//  protected abstract boolean showTagBody(String roleName);
}
