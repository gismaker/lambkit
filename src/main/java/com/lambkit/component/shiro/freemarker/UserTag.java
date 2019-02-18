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

import com.jfinal.render.FreeMarkerRender;

/**
 * Freemarker tag that renders the tag body if the current user known to the system, either from a successful login attempt
 * (not necessarily during the current session) or from 'RememberMe' services.
 * <p/>
 * <p><b>Note:</b> This is <em>less</em> restrictive than the <code>AuthenticatedTag</code> since it only assumes
 * the user is who they say they are, either via a current session login <em>or</em> via Remember Me services, which
 * makes no guarantee the user is who they say they are.  The <code>AuthenticatedTag</code> however
 * guarantees that the current user has logged in <em>during their current session</em>, proving they really are
 * who they say they are.
 * <p/>
 * <p>The logically opposite tag of this one is the {@link org.apache.shiro.web.tags.GuestTag}.
 * <p/>
 * <p>Equivalent to {@link org.apache.shiro.web.tags.UserTag}</p>
 */
public class UserTag extends SecureTag {
  static final Logger log = Logger.getLogger("UserTag");

  @Override
  public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
    if (getSubject() != null && getSubject().getPrincipal() != null) {
      //log.debug("Subject has known identity (aka 'principal'). Tag body will be evaluated.");
    	env.setVariable("user", FreeMarkerRender.getConfiguration().getObjectWrapper().wrap(getSubject().getPrincipal()));
    	renderBody(env, body);
    } else {
      log.debug("Subject does not exist or have a known identity (aka 'principal'). Tag body will not be evaluated.");
      renderBody(env, body);
    }
  }
}
