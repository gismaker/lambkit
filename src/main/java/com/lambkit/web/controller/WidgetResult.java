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
 */package com.lambkit.web.controller;

import com.jfinal.kit.Kv;

public class WidgetResult {

	private WidgetResultType resultType;
	/*
	private RenderType renderType;
	private int errorCode;
	private String url;
	private String view;
	*/
	private Kv data = null;
	
	public WidgetResult(WidgetResultType resultType) {
		this.resultType = resultType;
	}
	
	public static WidgetResult create(WidgetResultType resultType) {
		return new WidgetResult(resultType);
	}
	
	public static WidgetResult over() {
		return new WidgetResult(WidgetResultType.OVER);
	}
	
	public static WidgetResult view() {
		return new WidgetResult(WidgetResultType.VIEW);
	}
	
	public static WidgetResult redirect() {
		return new WidgetResult(WidgetResultType.REDIRECT);
	}
	
	public static WidgetResult error() {
		return new WidgetResult(WidgetResultType.ERROR);
	}
	
	public WidgetResultType getResultType() {
		return resultType;
	}
	public void setResultType(WidgetResultType resultType) {
		this.resultType = resultType;
	}
	public RenderType getRenderType() {
		return (RenderType) getData().get("renderType");
	}
	public WidgetResult setRenderType(RenderType renderType) {
		getData().set("renderType", renderType);
		return this;
	}

	public int getErrorCode() {
		return (int) getData().get("errorCode");
	}

	public WidgetResult setErrorCode(int errorCode) {
		getData().set("errorCode", errorCode);
		return this;
	}

	public String getUrl() {
		return (String) getData().get("url");
	}

	public WidgetResult setUrl(String url) {
		getData().set("url", url);
		return this;
	}

	public String getView() {
		return (String) getData().get("view");
	}

	public WidgetResult setView(String view) {
		getData().set("view", view);
		return this;
	}

	public Kv getData() {
		if(data==null) {
			data = Kv.create();
		}
		return data;
	}

	public void setData(Kv data) {
		this.data = data;
	}
	
	
}
