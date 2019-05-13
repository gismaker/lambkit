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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

/**
 * HDFS操作类
 * 
 * @author yangyong
 */
public class Hdfs {
	private Configuration conf;
	private FileSystem fs;

	/**
	 * @Title: HDFSOperation
	 * @Description 初始化配置
	 * @author cpthack
	 * @see 初始化配置
	 * @return 对参数的说明
	 * @param 对方法中某参数的说明
	 * @example 方法使用例子
	 */
	public Hdfs() throws IOException {
		conf = new Configuration();
		fs = FileSystem.get(conf);
	}

	/**
	 * @Title: upLoad
	 * @Description 上传文件
	 * @author cpthack
	 * @see 上传文件
	 * @return 对参数的说明
	 * @param in：文件输入流；hdfsPath：保存在云端的文件路径
	 * @example 方法使用例子
	 */
	public boolean upLoad(InputStream in, String hdfsPath) {
		Path p = new Path(hdfsPath);
		try {
			if (fs.exists(p)) {
				System.out.println("文件已经存在");
				return false;
			}
			// 获得hadoop系统的连接
			FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
			// out对应的是Hadoop文件系统中的目录
			OutputStream out = fs.create(new Path(hdfsPath));
			IOUtils.copyBytes(in, out, 4096, true);// 4096是4k字节
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * @Title: upLoad
	 * @Description 下载文件
	 * @author cpthack
	 * @see 下载文件
	 * @return 对参数的说明
	 * @param localPath：文件保存在本地的路径；hdfsPath：文件存在云端的路径
	 * @example 方法使用例子
	 */
	public boolean downLoad(String hdfsPath, String localPath) {
		Path path = new Path(hdfsPath);
		try {
			if (!fs.exists(path)) {
				System.out.println("云端文件不存在");
				return false;
			}
			FileSystem hdfs = FileSystem.get(conf);
			Path dstPath = new Path(localPath);
			hdfs.copyToLocalFile(true, path, dstPath);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean downFromCloud(String hdfsPath, String srcFileName) {
		// 实例化一个文件系统
		FileSystem fs;
		try {
			fs = FileSystem.get(URI.create(hdfsPath), conf);
			// 读出流
			FSDataInputStream HDFS_IN = fs.open(new Path(hdfsPath));
			// 写入流
			OutputStream OutToLOCAL = new FileOutputStream(srcFileName);
			// 将InputStrteam 中的内容通过IOUtils的copyBytes方法复制到OutToLOCAL中
			IOUtils.copyBytes(HDFS_IN, OutToLOCAL, 1024, true);

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Title: deletePath
	 * @Description 删除文件
	 * @author cpthack
	 * @see 删除文件
	 * @return 对参数的说明
	 * @param hdfsPath：文件存在云端的路径
	 * @example 方法使用例子
	 */
	public boolean deletePath(String hdfsPath) {
		try {
			fs.delete(new Path(hdfsPath), true);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @Title: getFileList
	 * @Description 获取某个目录下所有文件
	 * @author cpthack
	 * @see 获取某个目录下所有文件
	 * @return 对参数的说明
	 * @param hdfsPath：存在云端的文件夹
	 * @example 方法使用例子
	 */
	public ArrayList<FileBean> getFileList(String hdfsPath) {
		Path path = new Path(hdfsPath);
		ArrayList<FileBean> fileList = new ArrayList<FileBean>();
		FileStatus[] status;
		try {
			status = fs.listStatus(path);
			for (FileStatus fs : status) {
				fileList.add(new FileBean(fs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileList;

	}

	// 创建文件夹
	public boolean mkdir(String dir) {
		FileSystem fs;
		try {
			fs = FileSystem.get(conf);
			fs.mkdirs(new Path(dir));
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/* 删除文件夹 */
	@SuppressWarnings("deprecation")
	public boolean deleteDir(String dir) {
		FileSystem fs;
		try {
			fs = FileSystem.get(conf);
			fs.delete(new Path(dir));
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
