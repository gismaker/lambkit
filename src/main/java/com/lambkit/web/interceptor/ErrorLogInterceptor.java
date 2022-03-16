package com.lambkit.web.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.Kv;
import com.lambkit.common.util.DateTimeUtils;
import com.lambkit.common.util.StringUtils;

public class ErrorLogInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		 try {
		        inv.invoke();  
		    } catch (Exception e) {
		    	StackTraceElement[] stackTrace = e.getStackTrace();
		    	String jsonString = JsonKit.toJson(inv.getController().getRequest().getParameterMap());
		    	
		    	Kv error = new Kv();
		    	error.set("message_id", "127.0.0.1"+ StringUtils.uuid())//位移标识:服务器ID+UUID
		    	.set("service_id", "127.0.0.1")//服务器ID
		    	.set("class_name", stackTrace[0].getClassName()+stackTrace[0].getFileName())//报错类名+方法名
		    	.set("method_name", stackTrace[0].getMethodName())//方法名
		    	.set("parameter_content", jsonString)//传递参数
		    	.set("errorContent", e.getMessage())//异常内容
		    	.set("create_time",DateTimeUtils.getCurrentTime())//时间
		    	.set("line_number", stackTrace[0].getLineNumber());//报错行号
		    	//这里捕获到的异常，可以丢入MQ之中，因为我的是demo所以就没有用MQ，直接存储到MYSQL了
		    	//待加入
		    }
	}

}
