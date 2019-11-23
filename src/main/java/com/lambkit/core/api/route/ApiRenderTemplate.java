package com.lambkit.core.api.route;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.log.Log;
import com.jfinal.template.Engine;

public class ApiRenderTemplate implements ApiRender {
	static Log log = Log.getLog(ApiRenderTemplate.class);

	protected String contentType = "text/html;charset=utf-8";
	protected String view;
	
	public ApiRenderTemplate(String contentType) {
		this.contentType = contentType;
	}
	
	public ApiRenderTemplate() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void Render(ApiResult result, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Map<Object, Object> data = new HashMap<Object, Object>();
		data.put("code", result.getCode());
		data.put("message", result.getMessage());
		data.put("data", result.getData());
		try {
			if(result.code==200) {
			} else {
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType(contentType);
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			Engine.use().getTemplate(view).render(data, response.getOutputStream());
		} catch (IOException e) {
			log.error("服务中心响应异常", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setView(String view) {
		// TODO Auto-generated method stub
		this.view = view;
	}
}
