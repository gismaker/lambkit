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

import java.io.File;

import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;
import com.lambkit.common.util.PathUtils;
import com.lambkit.web.controller.LambkitController;
import com.lambkit.web.controller.Widget;
import com.lambkit.web.controller.WidgetResult;

public class FileUploadWidget extends Widget {

	@Override
	public WidgetResult handle(LambkitController c) {
		String filepath = c.getPara("path", PathKit.getWebRootPath());
		// 获取上传的文件
		//getFile一定要放在第一个参数去获取，否则都获取不到参数
		UploadFile uf = c.getFile("Filedata", filepath);
		String fileext = PathUtils.getExtensionName(uf.getFileName());
		String filename = System.currentTimeMillis() + "." + fileext;
		String newfilename = uf.getFile().getPath().replace(uf.getFileName(), filename);
		//重命名
		uf.getFile().renameTo(new File(newfilename));

		// 拼接文件上传的完整路径uf.getFileName();
		String fileurl = c.getSystemPath() + newfilename.replace(PathKit.getWebRootPath(), "");
		
		c.setAttr("fileUrl", fileurl);
		c.setAttr("fileName", filename);
		System.out.println("================fileurl:" + fileurl);
		
		// 以json格式进行渲染
		c.renderJson();
		return WidgetResult.over();
	}
}
