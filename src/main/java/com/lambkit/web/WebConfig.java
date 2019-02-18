/**
 * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.web;

import com.lambkit.core.config.annotation.PropertieConfig;

@PropertieConfig(prefix = "lambkit.web")
public class WebConfig {

	private String portal = "/portal";
	private String templatePath = "/templates/center";
	private String adminPage = "/admin";
	private String adminPath = "/WEB-INF/admin";
	private int navcid = 1;
	
	private String homePage = "/center";
	private String homePath = "/WEB-INF/app";
	private String managePage = "/manager";
	private String managePath = "/WEB-INF/mgr";
	
	private String wapPage = "/wap";
	private String wapPath = "/wap";
	private String wechatPage = "/wechat";
	private String wechatPath = "/wechat";
	
	private boolean jsonp = false;
	private boolean excel = false;
	
	public String getPortal() {
		return portal;
	}
	public void setPortal(String portal) {
		this.portal = portal;
	}
	public String getTemplatePath() {
		return templatePath;
	}
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
	public String getAdminPage() {
		return adminPage;
	}
	public void setAdminPage(String adminPage) {
		this.adminPage = adminPage;
	}
	public String getAdminPath() {
		return adminPath;
	}
	public void setAdminPath(String adminPath) {
		this.adminPath = adminPath;
	}
	public int getNavcid() {
		return navcid;
	}
	public void setNavcid(int navcid) {
		this.navcid = navcid;
	}
	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	public String getHomePath() {
		return homePath;
	}
	public void setHomePath(String homePath) {
		this.homePath = homePath;
	}
	public String getManagePage() {
		return managePage;
	}
	public void setManagePage(String managePage) {
		this.managePage = managePage;
	}
	public String getManagePath() {
		return managePath;
	}
	public void setManagePath(String managePath) {
		this.managePath = managePath;
	}
	public String getWapPage() {
		return wapPage;
	}
	public void setWapPage(String wapPage) {
		this.wapPage = wapPage;
	}
	public String getWapPath() {
		return wapPath;
	}
	public void setWapPath(String wapPath) {
		this.wapPath = wapPath;
	}
	public String getWechatPage() {
		return wechatPage;
	}
	public void setWechatPage(String wechatPage) {
		this.wechatPage = wechatPage;
	}
	public String getWechatPath() {
		return wechatPath;
	}
	public void setWechatPath(String wechatPath) {
		this.wechatPath = wechatPath;
	}
	public boolean isJsonp() {
		return jsonp;
	}
	public void setJsonp(boolean jsonp) {
		this.jsonp = jsonp;
	}

	public boolean isExcel() {
		return excel;
	}
	public void setExcel(boolean excel) {
		this.excel = excel;
	}
}
