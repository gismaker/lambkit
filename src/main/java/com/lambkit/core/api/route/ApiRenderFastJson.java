package com.lambkit.core.api.route;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.json.FastJson;
import com.jfinal.log.Log;

public class ApiRenderFastJson implements ApiRender {
	static Log log = Log.getLog(ApiRenderFastJson.class);

	@Override
	public void Render(ApiResult result, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String json = FastJson.getJson().toJson(result);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html/json;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			if (json != null) {
				response.getWriter().write(json);
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
