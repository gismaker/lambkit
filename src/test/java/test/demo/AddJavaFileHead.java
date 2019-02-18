package test.demo;

import java.io.File;

import com.lambkit.common.util.FileUtils;

public class AddJavaFileHead {

	private String banner = "/**\n" 
			+ " * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).\n" + " * <p>\n"
			+ " * Licensed under the Apache License, Version 2.0 (the \"License\");\n"
			+ " * you may not use this file except in compliance with the License.\n"
			+ " * You may obtain a copy of the License at\n" + " * <p>\n"
			+ " * http://www.apache.org/licenses/LICENSE-2.0\n" + " * <p>\n"
			+ " * Unless required by applicable law or agreed to in writing, software\n"
			+ " * distributed under the License is distributed on an \"AS IS\" BASIS,\n"
			+ " * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n"
			+ " * See the License for the specific language governing permissions and\n"
			+ " * limitations under the License.\n" + " */";

	public void getDirectory(File file) {
		File flist[] = file.listFiles();
		if (flist == null || flist.length == 0) {
			return;
		}
		for (File f : flist) {
			if (f.isDirectory()) {
				// 这里将列出所有的文件夹
				// 绝对路径f.getAbsolutePath()
				// 文件名f.getName()
				getDirectory(f);
			} else {
				// 这里将列出所有的文件
				if (f.getName().endsWith(".java")) {
					setBanner(f);
				}
			}
		}
	}
	
	public void setBanner(File file) {
		String content = FileUtils.readString(file);
		boolean isBanner = false;
		if(content.startsWith("/**")) {
			isBanner = true;
			int e1 = content.indexOf("*/");
			int epg = content.indexOf("package");
			if(e1 < epg) {
				content = content.substring(epg-1);
				isBanner = false;
			}
		}

		if(!isBanner) {
			content = banner + content;
		}
		FileUtils.writeString(file, content);
	}

	public static void main(String[] args) {
		AddJavaFileHead fbt = new AddJavaFileHead();
		fbt.getDirectory(new File("D:/lambkit-workspace/workspace/Lambkit-opensource/lambkit/src/main/java"));
		System.out.println("---------over---------");
	}
}
