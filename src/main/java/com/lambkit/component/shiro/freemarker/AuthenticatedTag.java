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
 * JSP tag that renders the tag body only if the current user has executed a <b>successful</b> authentication attempt
 * <em>during their current session</em>.
 * <p/>
 * <p>This is more restrictive than the {@link cn.dreampie.common.plugin.shiro.freemarker.UserTag}, which only
 * ensures the current user is known to the system, either via a current login or from Remember Me services,
 * which only makes the assumption that the current user is who they say they are, and does not guarantee it like
 * this tag does.
 * <p/>
 * <p>The logically opposite tag of this one is the {@link cn.dreampie.common.plugin.shiro.freemarker.NotAuthenticatedTag}
 * <p/>
 * <p>Equivalent to {@link org.apache.shiro.web.tags.AuthenticatedTag}</p>
 *
 * @since 0.2
 */
public class AuthenticatedTag extends SecureTag {
  private static final Logger log = Logger.getLogger("AuthenticatedTag");

  @Override
  public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
    if (getSubject() != null && (getSubject().isAuthenticated() || getSubject().isRemembered())) {
      if (log.isDebugEnabled()) {
        log.debug("Subject exists and is authenticated.  Tag body will be evaluated.");
      }

      renderBody(env, body);
    } else {
      if (log.isDebugEnabled()) {
        log.debug("Subject does not exist or is not authenticated.  Tag body will not be evaluated.");
      }
    }
  }
}