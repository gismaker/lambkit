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

import java.io.IOException;
import java.math.BigInteger;

import com.jfinal.template.Directive;
import com.jfinal.template.Env;
import com.jfinal.template.TemplateException;
import com.jfinal.template.expr.ast.ExprList;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;

/**
 * Jfinal 指令的基类
 */
public abstract class LambkitDirective extends Directive {

	public void setExprList(ExprList exprList)
    {
      super.setExprList(exprList);
    }
    
    public void exec(Env env, Scope scope, Writer writer)
    {
      scope = new Scope(scope);
      scope.getCtrl().setLocalAssignment();
      this.exprList.eval(scope);
      onRender(env, scope, writer);
    }
    
    public abstract void onRender(Env env, Scope scope, Writer writer);
    
    public void renderBody(Env env, Scope scope, Writer writer)
    {
      this.stat.exec(env, scope, writer);
    }
    
    public void renderText(Writer writer, String text)
    {
      try
      {
        writer.write(text);
      }
      catch (IOException e)
      {
        throw new TemplateException(e.getMessage(), this.location, e);
      }
    }
    
    public <T> T getPara(String key, Scope scope)
    {
      return (T)getPara(key, scope, null);
    }
    
    public <T> T getPara(String key, Scope scope, T defaultValue)
    {
      Object data = scope.getLocal(key);
      return (T) (data == null ? defaultValue : data);
    }
    
    public <T> T getPara(int index, Scope scope)
    {
      return (T)getPara(index, scope, null);
    }
    
    public <T> T getPara(int index, Scope scope, T defaultValue)
    {
      if ((index < 0) || (index >= this.exprList.length())) {
        return defaultValue;
      }
      Object data = this.exprList.getExpr(index).eval(scope);
      return (T) (data == null ? defaultValue : data);
    }
    
    public Integer getParaToInt(String key, Scope scope)
    {
      Object object = getPara(key, scope, null);
      if ((object == null) || ((object instanceof Integer))) {
        return (Integer)object;
      }
      return Integer.valueOf(object.toString());
    }
    
    public Integer getParaToInt(String key, Scope scope, Integer defaultValue)
    {
      Integer v = getParaToInt(key, scope);
      return v == null ? defaultValue : v;
    }
    
    public Integer getParaToInt(int index, Scope scope)
    {
      Object object = getPara(index, scope, null);
      if ((object == null) || ((object instanceof Integer))) {
        return (Integer)object;
      }
      return Integer.valueOf(object.toString());
    }
    
    public Integer getParaToInt(int index, Scope scope, Integer defaultValue)
    {
      Integer v = getParaToInt(index, scope);
      return v == null ? defaultValue : v;
    }
    
    public Long getParaToLong(String key, Scope scope)
    {
      Object object = getPara(key, scope, null);
      if ((object == null) || ((object instanceof Long))) {
        return (Long)object;
      }
      return Long.valueOf(object.toString());
    }
    
    public Long getParaToLong(String key, Scope scope, Long defaultValue)
    {
      Long v = getParaToLong(key, scope);
      return v == null ? defaultValue : v;
    }
    
    public Long getParaToLong(int index, Scope scope)
    {
      Object object = getPara(index, scope, null);
      if ((object == null) || ((object instanceof Long))) {
        return (Long)object;
      }
      return Long.valueOf(object.toString());
    }
    
    public Long getParaToLong(int index, Scope scope, Long defaultValue)
    {
      Long v = getParaToLong(index, scope);
      return v == null ? defaultValue : v;
    }
    
    public Boolean getParaToBool(String key, Scope scope)
    {
      Object object = getPara(key, scope, null);
      if ((object == null) || ((object instanceof Boolean))) {
        return (Boolean)object;
      }
      return Boolean.valueOf(object.toString());
    }
    
    public Boolean getParaToBool(String key, Scope scope, Boolean defaultValue)
    {
      Boolean v = getParaToBool(key, scope);
      return v == null ? defaultValue : v;
    }
    
    public Boolean getParaToBool(int index, Scope scope)
    {
      Object object = getPara(index, scope, null);
      if ((object == null) || ((object instanceof Boolean))) {
        return (Boolean)object;
      }
      return Boolean.valueOf(object.toString());
    }
    
    public Boolean getParaToBool(int index, Scope scope, Boolean defaultValue)
    {
      Boolean v = getParaToBool(index, scope);
      return v == null ? defaultValue : v;
    }
    
    public BigInteger getParaToBigInteger(String key, Scope scope)
    {
      Object object = getPara(key, scope, null);
      if ((object == null) || ((object instanceof BigInteger))) {
        return (BigInteger)object;
      }
      return new BigInteger(object.toString());
    }
    
    public BigInteger getParaToBigInteger(String key, Scope scope, BigInteger defaultValue)
    {
      BigInteger v = getParaToBigInteger(key, scope);
      return v == null ? defaultValue : v;
    }
    
    public BigInteger getParaToBigInteger(int index, Scope scope)
    {
      Object object = getPara(index, scope, null);
      if ((object == null) || ((object instanceof BigInteger))) {
        return (BigInteger)object;
      }
      return new BigInteger(object.toString());
    }
    
    public BigInteger getParaToBigInteger(int index, Scope scope, BigInteger defaultValue)
    {
      BigInteger v = getParaToBigInteger(index, scope);
      return v == null ? defaultValue : v;
    }
}
