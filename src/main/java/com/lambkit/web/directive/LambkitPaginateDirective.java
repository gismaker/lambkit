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

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;
import com.lambkit.web.LambkitControllerContext;

import javax.servlet.http.HttpServletRequest;

public abstract class LambkitPaginateDirective extends PaginateDirectiveBase {
	
	protected String getUrl(int pageNumber)
	  {
	    HttpServletRequest request = LambkitControllerContext.get().getRequest();
	    String queryString = request.getQueryString();
	    
	    String url = request.getRequestURI();
	    if (StrKit.notBlank(queryString)) {
	      url = url.concat("?").concat(queryString);
	    }
	    String pageString = "page=";
	    int index = url.indexOf(pageString);
	    if (index != -1)
	    {
	      StringBuilder sb = new StringBuilder();
	      sb.append(url, 0, index).append(pageString).append(pageNumber);
	      int idx = url.indexOf("&", index);
	      if (idx != -1) {
	        sb.append(url.substring(idx));
	      }
	      url = sb.toString();
	    }
	    else if (url.contains("?"))
	    {
	      url = url.concat(String.format("&page=%s", new Object[] { Integer.valueOf(pageNumber) }));
	    }
	    else
	    {
	      url = url.concat(String.format("?page=%s", new Object[] { Integer.valueOf(pageNumber) }));
	    }
	    return url;
	  }
	  
	  protected Page<?> getPage(Env env, Scope scope, Writer writer)
	  {
	    return (Page)LambkitControllerContext.get().getAttr(getPageAttrName());
	  }
	  
	  protected String getPageAttrName()
	  {
	    return "page";
	  }
}
