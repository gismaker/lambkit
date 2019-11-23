package com.lambkit.core.api.route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ApiRender {
	
	public void setView(String view);
	public void Render(ApiResult result, HttpServletRequest request, HttpServletResponse response);

}
