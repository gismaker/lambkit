package com.lambkit.core.api.route;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.lambkit.common.LambkitConsts;
import com.lambkit.common.service.ServiceObject;

public class ApiActionReporter {

	private static final String title = "\nLambkit-" + LambkitConsts.VERSION + " api action report -------- ";
	private static boolean reportAfterInvocation = true;
	private static int maxOutputLengthOfParaValue = 512;
	private static Writer writer = new SystemOutWriter();
	
	private static final ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	
	public static void setReportAfterInvocation(boolean reportAfterInvocation) {
		ApiActionReporter.reportAfterInvocation = reportAfterInvocation;
	}
	
	public static void setMaxOutputLengthOfParaValue(int maxOutputLengthOfParaValue) {
		if (maxOutputLengthOfParaValue < 16) {
			throw new IllegalArgumentException("maxOutputLengthOfParaValue must more than 16");
		}
		ApiActionReporter.maxOutputLengthOfParaValue = maxOutputLengthOfParaValue;
	}
	
	public static void setWriter(Writer writer) {
		if (writer == null) {
			throw new IllegalArgumentException("writer can not be null");
		}
		ApiActionReporter.writer = writer;
	}
	
	public static boolean isReportAfterInvocation(HttpServletRequest request) {
		if (reportAfterInvocation) {
			return true;
		} else {
			String contentType = request.getContentType();
			if (contentType != null && contentType.toLowerCase().indexOf("multipart") != -1) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * Report the action
	 */
	public static final void report(String target, HttpServletRequest request, ApiAction action) {
		StringBuilder sb = new StringBuilder(title).append(sdf.get().format(new Date())).append(" --------------------------\n");
		sb.append("Url         : ").append(request.getMethod()).append(" ").append(target).append("\n");
		ServiceObject so = action.getServiceObject();
		sb.append("Service     : ").append(action.getTargetName()).append(".(").append(so.getImplementClass().getSimpleName()).append(".java:1)");
		sb.append("\nMethod      : ").append(action.getMethodName()).append("\n");
		
		/*
		String urlParas = request.getParameter(ApiRoute.PARAMS);
		if (urlParas != null) {
			sb.append("UrlPara     : ").append(urlParas).append("\n");
		}
		*/
		
		ApiInterceptor[] inters = action.getInterceptors();
		if (inters.length > 0) {
			sb.append("ApiInterceptor : ");
			for (int i=0; i<inters.length; i++) {
				if (i > 0)
					sb.append("\n              ");
				ApiInterceptor inter = inters[i];
				Class<? extends ApiInterceptor> ic = inter.getClass();
				sb.append(ic.getName()).append(".(").append(ic.getSimpleName()).append(".java:1)");
			}
			sb.append("\n");
		}
		
		// print all parameters
		Enumeration<String> e = request.getParameterNames();
		if (e.hasMoreElements()) {
			sb.append("Parameter   : ");
			while (e.hasMoreElements()) {
				String name = e.nextElement();
				String[] values = request.getParameterValues(name);
				if (values.length == 1) {
					sb.append(name).append("=");
					if (values[0] != null && values[0].length() > maxOutputLengthOfParaValue) {
						sb.append(values[0].substring(0, maxOutputLengthOfParaValue)).append("...");
					} else {
						sb.append(values[0]);
					}
				}
				else {
					sb.append(name).append("[]={");
					for (int i=0; i<values.length; i++) {
						if (i > 0)
							sb.append(",");
						sb.append(values[i]);
					}
					sb.append("}");
				}
				sb.append("  ");
			}
			sb.append("\n");
		}
		sb.append("--------------------------------------------------------------------------------\n");
		
		try {
			writer.write(sb.toString());
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	private static class SystemOutWriter extends Writer {
		public void write(String str) throws IOException {
			System.out.print(str);
		}
		public void write(char[] cbuf, int off, int len) throws IOException {}
		public void flush() throws IOException {}
		public void close() throws IOException {}
	}
}
