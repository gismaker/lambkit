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
import freemarker.template.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.util.Map;

/**
 * <p>Equivalent to {@link org.apache.shiro.web.tags.SecureTag}</p>
 */
public abstract class SecureTag implements TemplateDirectiveModel {
  public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
    verifyParameters(params);
    render(env, params, body);
  }

  public abstract void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException;

  protected String getParam(Map params, String name) {
    Object value = params.get(name);

    if (value instanceof SimpleScalar) {
      return ((SimpleScalar) value).getAsString();
    }

    return null;
  }

  protected Subject getSubject() {
    return SecurityUtils.getSubject();
  }

  protected void verifyParameters(Map params) throws TemplateModelException {
  }

  protected void renderBody(Environment env, TemplateDirectiveBody body) throws IOException, TemplateException {
    if (body != null) {
      body.render(env.getOut());
    }
  }
}
