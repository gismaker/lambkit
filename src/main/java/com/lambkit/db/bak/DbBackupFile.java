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
package com.lambkit.db.bak;

/**
 * 系统备份展示对象
 * 
 * */
public class DbBackupFile {
	private String fileName;//备份文件的名称
	 private String fileDate;//备份文件的日期
	 private String filePath;//备份文件的地址
	 private String fileSize;//备份文件的大小
	 public String getFileSize() {
	  return fileSize;
	 }
	 public void setFileSize(String fileSize) {
	  this.fileSize = fileSize;
	 }
	 public String getFileName() {
	  return fileName;
	 }
	 public void setFileName(String fileName) {
	  this.fileName = fileName;
	 }
	 public String getFileDate() {
	  return fileDate;
	 }
	 public void setFileDate(String fileDate) {
	  this.fileDate = fileDate;
	 }
	 public String getFilePath() {
	  return filePath;
	 }
	 public void setFilePath(String filePath) {
	  this.filePath = filePath;
	 }
}
