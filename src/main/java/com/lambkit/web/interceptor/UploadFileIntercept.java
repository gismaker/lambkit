package com.lambkit.web.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class UploadFileIntercept implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller c = inv.getController();

		String str = ".jsp|.asp|.php";// 允许的后缀名
		for (int i = 0; i < c.getFiles().size(); i++) {
			String filename = c.getFiles().get(i).getFileName();
			if (str.contains(filename.substring(filename.lastIndexOf(".")))) {
				deletefiles(c);// 删除所有上传文件
				c.setAttr("message", "warning");
				c.renderJson();
				return;
			}
		}

		inv.invoke();

		deletefiles(c);// 删除所有上传文件
	}

	/* 执行完后删除上传文件 */
	public void deletefiles(Controller controller) {
		/* 执行完后删除上传文件 */
		for (int i = 0; i < controller.getFiles().size(); i++) {
			System.out.println("删除文件");
			controller.getFiles().get(i).getFile().delete();// 删除所有上传文件
		}
	}

}
