package com.lambkit.web.controller;

import com.lambkit.web.WebConfig;
import com.lambkit.web.WebConfigManager;

public class WebConfigController extends LambkitController {

	private WebConfig webConfig;
	
	protected String getTemplatePath() {
		String path = getWebConfig().getPath()  + "/" + getWebConfig().getTemplate();
		System.out.println("template path: " + path);
		return path;
	}
	
	protected String getUrl() {
		return getWebConfig().getUrl();
	}
	
	private String getViewPath(String view) {
		view = view.startsWith("/") ? view : "/" + view;
		return getTemplatePath() + view;
	}
	
	public WebConfig getWebConfig() {
		if(webConfig==null) {
			webConfig = WebConfigManager.me().getDefaultWebConfig();
		}
		return webConfig;
	}

	public void setWebConfig(WebConfig webConfig) {
		this.webConfig = webConfig;
	}

	@Override
	public void render(String view) {
		super.render(getViewPath(view));
	}
	
	@Override
	public void render(String view, int status) {
		super.render(getViewPath(view), status);
	}
	
	@Override
	public void renderTemplate(String view) {
		System.out.println(getViewPath(view));
		super.renderTemplate(getViewPath(view));
	}
	
	@Override
	public void renderFreeMarker(String view) {
		super.renderFreeMarker(getViewPath(view));
	}
	
	@Override
	public void renderVelocity(String view) {
		super.renderVelocity(getViewPath(view));
	}
	
	@Override
	public void renderJsp(String view) {
		super.renderJsp(getViewPath(view));
	}
	
	@Override
	public void renderXml(String view) {
		// TODO Auto-generated method stub
		super.renderXml(getViewPath(view));
	}
}
