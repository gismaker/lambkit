package com.lambkit.core.api.route;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.log.Log;

public class ApiRenderText implements ApiRender {
	static Log log = Log.getLog(ApiRenderText.class);

	protected String contentType = "text/plain;charset=utf-8";
	
	public ApiRenderText(String contentType) {
		this.contentType = contentType;
	}
	
	public ApiRenderText() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void Render(ApiResult result, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String text;
			if(result.code==200) {
				text = result.getData().toString();
			} else {
				text = result.getMessage() + ", 状态码：" + result.getCode();
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType(contentType);
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			if (text != null) {
				response.getWriter().write(text);
			}
		} catch (IOException e) {
			log.error("服务中心响应异常", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setView(String view) {
		// TODO Auto-generated method stub
	}
}
