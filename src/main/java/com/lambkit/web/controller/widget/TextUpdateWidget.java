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
package com.lambkit.web.controller.widget;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.lambkit.common.util.FileUtils;
import com.lambkit.web.controller.BaseController;
import com.lambkit.web.controller.Widget;
import com.lambkit.web.controller.WidgetResult;

public class TextUpdateWidget extends Widget {

	@Override
	public WidgetResult handle(BaseController c) {
		// 更新文本文件
		String info = c.getPara("model.info", "");
		String path = c.getPara("model.path", "");
		boolean flag = false;
		if(StrKit.notBlank(path)) {
			String wrpath = PathKit.getWebRootPath();
			flag = FileUtils.writeFile(wrpath + path, info, "GBK");
		}
		c.renderJson(flag);
		return WidgetResult.over();
	}
}
