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
package com.lambkit.system.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Clear;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.jfinal.upload.UploadFile;
import com.lambkit.common.base.ResultKit;
import com.lambkit.common.util.FileUtils;
import com.lambkit.common.util.PathUtils;
import com.lambkit.web.controller.BaseController;

public class FileController extends BaseController {

	public void index() {
		String path = getPara("path", "/");
		// 文件夹位置
		String folderpath = PathKit.getWebRootPath() + path;
		System.out.println(folderpath);
		folderpath = PathUtils.replacePath(folderpath);
		// 遍历文件夹template
		List<String> fileList = new ArrayList<String>();
		List<String> folderList = new ArrayList<String>();
		File file = new File(folderpath);
		File flist[] = file.listFiles();
		if (flist != null && flist.length > 0) {
			for (File f : flist) {
				if (f.isDirectory()) {
					folderList.add(f.getName());
				} else {
					fileList.add(f.getName());
				}
			}
		}
		setAttr("filelist", fileList);
		setAttr("folderList", folderList);
		setAttr("folderpath", folderpath);
		String tpath = path.endsWith("/") ? path.substring(0, path.length() - 1) : path;
		setAttr("templatePath", tpath);
		setAttr("upath", path.substring(0, path.lastIndexOf("/")));
		renderTemplate("index.html");
	}

	/**
	 * 文件上传
	 */
	@Clear
	public void upload() {
		String filepath = getPara("path", PathKit.getWebRootPath());
		// 获取上传的文件
		// getFile一定要放在第一个参数去获取，否则都获取不到参数
		UploadFile uf = getFile("Filedata", filepath);
		String fileext = PathUtils.getExtensionName(uf.getFileName());
		String filename = System.currentTimeMillis() + "." + fileext;
		String newfilename = uf.getFile().getPath().replace(uf.getFileName(), filename);
		// 重命名
		uf.getFile().renameTo(new File(newfilename));

		// 拼接文件上传的完整路径uf.getFileName();
		String fileurl = getSystemPath() + newfilename.replace(PathKit.getWebRootPath(), "");

		this.setAttr("fileUrl", fileurl);
		this.setAttr("fileName", filename);
		System.out.println("================fileurl:" + fileurl);

		// 以json格式进行渲染
		renderJson();
	}

	/**
	 * 读取文本文件
	 */
	public void read() {
		String wrpath = PathKit.getWebRootPath();
		String path = getPara("path");
		if (path.endsWith("html") || path.endsWith("htm") 
				|| path.endsWith("txt") || path.endsWith("csv")
				|| path.endsWith("css")) {
			setAttr("info", FileUtils.readFile(wrpath + path, "GBK"));
		} else {
			setAttr("info", "该格式不支持编辑！");
		}
		setAttr("path", path);
		renderTemplate("info.html");
	}

	/**
	 * 更新文本文件
	 */
	public void update() {
		String info = getPara("model.info", "");
		String path = getPara("model.path", "");
		boolean flag = false;
		if (StrKit.notBlank(path)) {
			String wrpath = PathKit.getWebRootPath();
			flag = FileUtils.writeFile(wrpath + path, info, "GBK");
		}
		renderJson(flag);
	}

	// ------------------
	// API for json result
	// ------------------

	public void data() {
		String path = getPara("path", "/");
		// 文件夹位置
		String folderpath = PathKit.getWebRootPath() + path;
		System.out.println(folderpath);
		folderpath = PathUtils.replacePath(folderpath);
		// 遍历文件夹template
		List<String> fileList = new ArrayList<String>();
		List<String> folderList = new ArrayList<String>();
		File file = new File(folderpath);
		File flist[] = file.listFiles();
		if (flist != null && flist.length > 0) {
			for (File f : flist) {
				if (f.isDirectory()) {
					folderList.add(f.getName());
				} else {
					fileList.add(f.getName());
				}
			}
		}
		Map<String, Object> filepathModel = new HashMap<String, Object>();
		filepathModel.put("filelist", fileList);
		filepathModel.put("folderList", folderList);
		filepathModel.put("folderpath", folderpath);
		String tpath = path.endsWith("/") ? path.substring(0, path.length() - 1) : path;
		filepathModel.put("templatePath", tpath);
		filepathModel.put("upath", path.substring(0, path.lastIndexOf("/")));
		renderJson(ResultKit.json(getPara(0), 1, "success", filepathModel));
	}
}
