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
package com.lambkit.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;

/**
 * 附件工具
 */
public class AttachmentUtils {

	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	public static String moveFile(File file, String fileName) {
		if (!file.exists()) {
			return null;
		}

		String webRoot = PathKit.getWebRootPath();

		StringBuilder newFileName = new StringBuilder(webRoot).append(File.separator).append("attachment")
				.append(File.separator).append(dateFormat.format(new Date())).append(File.separator).append(fileName);

		File newfile = new File(newFileName.toString());

		if (!newfile.getParentFile().exists()) {
			newfile.getParentFile().mkdirs();
		}

		file.renameTo(newfile);

		return FileUtils.removePrefix(newfile.getAbsolutePath(), webRoot);
	}
	
	public static String moveMetaFile(File file, String path, String fileName) {
		if (!file.exists()) {
			return null;
		}

		String webRoot = PathKit.getWebRootPath();

		StringBuilder newFileName = new StringBuilder(webRoot).append(File.separator).append("attachment")
				.append(File.separator).append(path)
				.append(File.separator).append(dateFormat.format(new Date()))
				.append(File.separator).append(fileName);

		File newfile = new File(newFileName.toString());

		if (!newfile.getParentFile().exists()) {
			newfile.getParentFile().mkdirs();
		}

		file.renameTo(newfile);

		return FileUtils.removePrefix(newfile.getAbsolutePath(), webRoot);
	}
	
	/**
	 * @param uploadFile
	 * @return new file relative path
	 */
	public static String moveFile(UploadFile uploadFile) {
		if (uploadFile == null)
			return null;

		File file = uploadFile.getFile();
		if (!file.exists()) {
			return null;
		}

		String webRoot = PathKit.getWebRootPath();

		String uuid = UUID.randomUUID().toString().replace("-", "");

		StringBuilder newFileName = new StringBuilder(webRoot).append(File.separator).append("attachment")
				.append(File.separator).append(dateFormat.format(new Date())).append(File.separator).append(uuid)
				.append(FileUtils.getSuffix(file.getName()));

		File newfile = new File(newFileName.toString());

		if (!newfile.getParentFile().exists()) {
			newfile.getParentFile().mkdirs();
		}

		file.renameTo(newfile);

		return FileUtils.removePrefix(newfile.getAbsolutePath(), webRoot);
	}

	static List<String> imageSuffix = new ArrayList<String>();

	static {
		imageSuffix.add(".jpg");
		imageSuffix.add(".jpeg");
		imageSuffix.add(".png");
		imageSuffix.add(".bmp");
		imageSuffix.add(".gif");
	}

	public static boolean isImage(String path) {
		String sufffix = FileUtils.getSuffix(path);
		if (StringUtils.isNotBlank(sufffix))
			return imageSuffix.contains(sufffix.toLowerCase());
		return false;
	}
}
