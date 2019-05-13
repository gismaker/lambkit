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
package com.lambkit.component.hadoop;

import org.apache.hadoop.fs.FileStatus;

/**
 * 文件
 * 
 * @author yangyong
 */
public class FileBean {
	private String hdfsPath;
	private long fileSize;
	private long modifyTime;
	private String fileName;
	private String fileDir;
	private boolean isDir;

	public FileBean(FileStatus fileStatus) {
		hdfsPath = fileStatus.getPath().toString();
		fileSize = fileStatus.getLen();
		modifyTime = fileStatus.getModificationTime();
		fileName = fileStatus.getPath().getName();
		fileDir = fileStatus.getPath().getParent().getName();
		isDir = fileStatus.isDirectory();
	}

	public String getHdfsPath() {
		return hdfsPath;
	}

	public long getFileSize() {
		return fileSize;
	}

	public long getModifyTime() {
		return modifyTime;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileDir() {
		return fileDir;
	}

	public boolean isDir() {
		return isDir;
	}

	public String toString() {
		return hdfsPath;
	}
}
