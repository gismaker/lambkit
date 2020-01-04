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
package com.lambkit.web.render;

import java.io.PrintWriter;

import com.jfinal.render.Render;
import com.jfinal.render.RenderException;

public class XmlFileRender extends Render {
	
	protected String view;
	
	private static final String contentType = "text/xml; charset=" + getEncoding();

	public XmlFileRender(String view) {
		this.view = view;
	}

	@Override
	public void render() {
		response.setContentType(contentType);
        
		PrintWriter writer = null;
        try {
			writer = response.getWriter();
	        writer.write(view);
	        writer.flush();
		} catch (Exception e) {
			throw new RenderException(e);
		}
		finally {
			if (writer != null)
				writer.close();
		}
	}

}
