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

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;


public class FileUtils {

	/**
	 * 获取文件后缀.jpg, .png
	 * @param fileName
	 * @return
	 */
	public static String getSuffix(String fileName) {
		if (fileName != null && fileName.contains(".")) {
			return fileName.substring(fileName.lastIndexOf("."));
		}
		return null;
	}
	
	
	public static String removePrefix(String src, String prefix) {
		if (src != null && src.startsWith(prefix)) {
			return src.substring(prefix.length());
		}
		return src;
	}
	
	
	public static String removeRootPath(String src){
		return removePrefix(src, PathKit.getWebRootPath());
	}

	public static String readString(File file) {
		ByteArrayOutputStream baos = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			for (int len = 0; (len = fis.read(buffer)) > 0;) {
				baos.write(buffer, 0, len);
			}
			return new String(baos.toByteArray(),JFinal.me().getConstants().getEncoding());
		} catch (Exception e) {
		} finally {
			close(fis, baos);
		}
		return null;
	}

	public static void writeString(File file, String string) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file, false);
			fos.write(string.getBytes(JFinal.me().getConstants().getEncoding()));
		} catch (Exception e) {
		} finally {
			close(null, fos);
		}
	}

	private static void close(InputStream is, OutputStream os) {
		if (is != null)
			try {
				is.close();
			} catch (IOException e) {
			}
		if (os != null)
			try {
				os.close();
			} catch (IOException e) {
			}
	}

	public static void unzip(String zipFilePath) throws IOException {
		String targetPath = zipFilePath.substring(0, zipFilePath.lastIndexOf("."));
		unzip(zipFilePath, targetPath);
	}

	public static void unzip(String zipFilePath, String targetPath) throws IOException {
		ZipFile zipFile = new ZipFile(zipFilePath);
		try{
			Enumeration<?> entryEnum = zipFile.entries();
			if (null != entryEnum) {
				while (entryEnum.hasMoreElements()) {
					OutputStream os = null;
					InputStream is = null;
					try {
						ZipEntry zipEntry = (ZipEntry) entryEnum.nextElement();
						if (!zipEntry.isDirectory()) {
							File targetFile = new File(targetPath + File.separator + zipEntry.getName());
							if (!targetFile.getParentFile().exists()) {
								targetFile.getParentFile().mkdirs();
							}
							os = new BufferedOutputStream(new FileOutputStream(targetFile));
							is = zipFile.getInputStream(zipEntry);
							byte[] buffer = new byte[4096];
							int readLen = 0;
							while ((readLen = is.read(buffer, 0, 4096)) > 0) {
								os.write(buffer, 0, readLen);
							}
						}
					} finally {
						if (is != null)
							is.close();
						if (os != null)
							os.close();
					}
				}
			}
		}finally{
			zipFile.close();
		}
	}

	
	public static String readFile(String filename, String charset) {
		String readStr = "";
		File f = new File(filename);
		if (f.isFile() && f.exists()) {
			try {
				InputStreamReader read = new InputStreamReader(new FileInputStream(f), charset);
				BufferedReader reader = new BufferedReader(read);
				String line;
				while ((line = reader.readLine()) != null) {
					readStr = readStr + line + "\r\n";
				}
				reader.close();
				read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return readStr;
	}

	public static boolean writeFile(String fileName, String fileContent, String charset) {
		boolean flag = true;
		try {
			File f = new File(fileName);
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), charset);
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
			write.close();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
}