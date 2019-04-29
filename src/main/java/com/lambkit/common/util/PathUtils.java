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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathUtils {
	
	/**
     * 删除目录
     * @param dir
     * @throws IOException
     */
	public static void cleanDirectory(String dir) throws IOException {
		if(dir==null || dir.length() < 1)
			return;
		File file = new File(dir);
		cleanDirectory(file);
		System.out.println("清空目录[clean dir]：" + file.getAbsolutePath());
    }
	
	/**
     * 删除目录
     * @param dir
     * @throws IOException
     */
	public static void deleteOutRootDir(String dir) throws IOException {
		if(dir==null || dir.length() < 1)
			return;
		File file = new File(dir);
		deleteDirectory(file);
		System.out.println("删除目录[delete dir]：" + file.getAbsolutePath());
    }
	
	/**
	 * 创建输出目录
	 * @param folderList
	 * @param folderpath
	 */
	public static void createOutRootDir(Map<String, Object> templateModel, Map<String, Object> filepathModel) {
		if(filepathModel==null) return;
		@SuppressWarnings("unchecked")
		List<String> folderList = (List<String>) filepathModel.get("folderList");
		if(folderList==null) return;
		String folderpath = (String) filepathModel.get("folderpath");
		// 开始处理
		for (String dir : folderList) {
			// 处理文件夹地址模板
			String newPath = processDir(templateModel, dir);
			if(newPath==null) continue;
			String newdir = newPath.replace(replacePath(folderpath),
					(String) templateModel.get("outRootDir"));
			File filePath = new File(newdir);
			createFilePath(filePath);
		}
	}
	
	/**
	 * 创建文件地址模型
	 * @param templatePath
	 */
	public static Map<String, Object> createFilepathModel(String templatePath) {
		boolean flag = templatePath.length() > 2 && templatePath.substring(1, 2).equals(":") ? false : true;
		// 模板文件夹位置
		String folderpath = flag ? System.getProperty("user.dir") + templatePath : templatePath;
		folderpath = PathUtils.replacePath(folderpath);
		//System.out.println("模板所在文件夹绝对路径为: " + folderpath);
		// 遍历文件夹template
		List<String> fileList = new ArrayList<String>();
		List<String> folderList = new ArrayList<String>();
		File file = new File(folderpath);
		PathUtils.getDirectory(file, fileList, folderList);
		Map<String, Object> filepathModel = new HashMap<String, Object>();
		filepathModel.put("filelist", fileList);
		filepathModel.put("folderList", folderList);
		filepathModel.put("folderpath", folderpath);
		filepathModel.put("templatePath", templatePath);
		return filepathModel;
	}

	/**
     * 删除文件夹
     *
     * @param directory  directory to delete
     * @throws IOException in case deletion is unsuccessful
     */
    public static void deleteDirectory(File directory) throws IOException {
        if (!directory.exists()) {
            return;
        }

        cleanDirectory(directory);
        if (!directory.delete()) {
            String message =
                "Unable to delete directory " + directory + ".";
            throw new IOException(message);
        }
    }

    /**
     * Cleans a directory without deleting it.
     *
     * @param directory directory to clean
     * @throws IOException in case cleaning is unsuccessful
     */
    public static void cleanDirectory(File directory) throws IOException {
        if (!directory.exists()) {
            String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }

        File[] files = directory.listFiles();
        if (files == null) {  // null if security restricted
            throw new IOException("Failed to list contents of " + directory);
        }

        IOException exception = null;
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            try {
                forceDelete(file);
            } catch (IOException ioe) {
                exception = ioe;
            }
        }

        if (null != exception) {
            throw exception;
        }
    }

    public static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            boolean filePresent = file.exists();
            if (!file.delete()) {
                if (!filePresent){
                    throw new FileNotFoundException("File does not exist: " + file);
                }
                String message =
                    "Unable to delete file: " + file;
                throw new IOException(message);
            }
        }
    }
    
	/**
	 * 创建文件
	 * 
	 * @param file
	 */
	public static void createFilePath(File file) {
		if (!file.exists()) {
			System.out.println("创建[" + file.getAbsolutePath() + "]情况：" + file.mkdirs());
		} else {
			//System.out.println("存在目录：" + file.getAbsolutePath());
		}
	}
	
	public static void createDirectory(String path) {
		File file = new File(path);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			//System.out.println("//不存在");
			file.mkdir();
		} else {
			//System.out.println("//目录存在");
		}
	}
	
	/**
	 * 判断并创建父类目录
	 * @param file
	 */
	public static void checkAndCreateParent(File file) {
		if (!file.getParentFile().isDirectory()) {
			file.getParentFile().mkdirs();
		}
	}

	/**
	 * 递归遍历文件及文件夹
	 * 
	 * @param file
	 * @param filelist
	 * @param folderlist
	 */
	public static void getDirectory(File file, List<String> filelist,
			List<String> folderlist) {
		File flist[] = file.listFiles();
		if (flist == null || flist.length == 0) {
			return;
		}
		for (File f : flist) {
			if (f.isDirectory()) {
				// 这里将列出所有的文件夹
				// 绝对路径f.getAbsolutePath()
				// 文件名f.getName()
				folderlist.add(f.getAbsolutePath());
				getDirectory(f, filelist, folderlist);
			} else {
				// 这里将列出所有的文件
				if(!f.getName().endsWith(".include")) {
					filelist.add(f.getAbsolutePath());
				}
			}
		}
	}
	
	/**
	 * 替换文件地址中的模板
	 * 比如：src/${basepackage_dir}/service 替换为 src/com/app/service
	 * 其中：basepackage_dir=com/app
	 * @param path
	 * @param config
	 * @return
	 */
	public static String processDir(Map<String, Object> templateModel, String path) {
		String result_dir = "";
		// 替换反斜杠"\",使用"\\\\",替换为"/"
		String dir = replacePath(path);
		// 正则表达式,识别${*}字符串
		String regEx2 = "\\$\\{(.[^/]*)\\}"; 
		// 拆分地址
		String[] array = dir.split("/");
		for (int i = 0; i < array.length; i++) {
			//System.out.print(array[i]+"; ");
			if (i != 0)
				result_dir += "/";
			Pattern pattern = Pattern.compile(regEx2);
			Matcher matcher = pattern.matcher(array[i]);
			if (matcher.find()) {
				// 取大括号{}中的字符
				//System.out.print(matcher.group(1)+"; "+i);
				String val = String.valueOf(templateModel.get(matcher.group(1)));
				if(val.equals("null")) {
					return null;
				}
				String res = matcher.replaceAll(val);
				result_dir += val != null ? res : matcher.group(1);
			} else {
				result_dir += array[i];
			}
			// System.out.println();
		}
		return result_dir;
	}

	/**
	 * 替换路径中的"\"为"/" 比如："d:\web\apps"替换为"d:/web/apps"
	 * 
	 * @param path
	 * @return
	 */
	public static String replacePath(String path) {
		return path.replaceAll("\\\\", "/");
	}

	/*
	 * Java文件操作 获取文件扩展名
	 * 
	 * Created on: 2011-8-2 Author: blueeagle
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/*
	 * Java文件操作 获取不带扩展名的文件名
	 * 
	 * Created on: 2011-8-2 Author: blueeagle
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}
}
