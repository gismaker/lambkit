package com.lambkit.web.render;

import com.jfinal.render.Render;
import com.jfinal.render.RenderException;
import com.lambkit.common.LambkitConsts;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LambkitRender extends Render {

	private static final String contentType = "text/html; charset=" + getEncoding();

    public LambkitRender(String view) {
        this.view = view;
    }
    
    @Override
    public Render setContext(HttpServletRequest request, HttpServletResponse response, String viewPath) {
    	this.request = request;
		this.response = response;
    	if (view != null && view.length() > 0 && view.charAt(0) != '/') {
			view = viewPath + view;
		}
    	Object templatePathObj = request.getAttribute(LambkitConsts.ATTR_TEMPLATE_PATH);
    	if(templatePathObj!=null) {
    		String templatePath = (String) templatePathObj;
    		view = view.startsWith(templatePath) ? view : templatePath + view;
    	}
    	return this;
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public void render() {
        response.setContentType(getContentType());

        String html = "";

        renderHtml(response, html, contentType);
    }

    public String toString() {
        return view;
    }

    public void renderHtml(HttpServletResponse response, String html, String contentType) {
        response.setContentType(contentType);
        try {
            PrintWriter responseWriter = response.getWriter();
            responseWriter.write(html);
        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

}
