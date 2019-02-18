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
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

import java.io.IOException;
import java.util.Map;

/**
 * <p>Equivalent to {@link org.apache.shiro.web.tags.PermissionTag}</p>
 */
public abstract class PermissionTag extends SecureTag {
  String getName(Map params) {
    return getParam(params, "name");
  }

  @Override
  protected void verifyParameters(Map params) throws TemplateModelException {
    String permission = getName(params);

    if (permission == null || permission.length() == 0) {
      throw new TemplateModelException("The 'name' tag attribute must be set.");
    }
  }

  @Override
  public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
    String p = getName(params);

    boolean show = showTagBody(p);
    if (show) {
      renderBody(env, body);
    }
  }

  protected boolean isPermitted(String p) {
    return getSubject() != null && getSubject().isPermitted(p);
  }

  protected abstract boolean showTagBody(String p);
}
