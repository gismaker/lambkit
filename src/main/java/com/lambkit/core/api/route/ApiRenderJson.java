package com.lambkit.core.api.route;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.jfinal.log.Log;
import com.lambkit.common.util.JsonUtils;

public class ApiRenderJson implements ApiRender {
	static Log log = Log.getLog(ApiRenderJson.class);

	@Override
	public void Render(ApiResult result, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			JsonUtils.JSON_MAPPER.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);
			String json = JsonUtils.writeValueAsString(result);
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
