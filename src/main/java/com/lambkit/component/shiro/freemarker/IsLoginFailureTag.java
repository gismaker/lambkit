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
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.util.Map;

public class IsLoginFailureTag extends SecureTag {
  private static final Logger log = Logger.getLogger("AuthenticatedTag");

  String getAttr(Map params) {
    return getParam(params, "name");
  }

  @Override
  public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
    Subject subject = getSubject();
    Session session = getSubject().getSession();
    String attr = getAttr(params);
    if (attr != null && subject != null && session != null && session.getAttribute(attr) != null) {
      if (log.isDebugEnabled()) {
        log.debug("Attr is exsit.");
      }

      renderBody(env, body);
    } else {
      if (log.isDebugEnabled()) {
        log.debug("Attr is not exsit.");
      }
    }
  }
}
