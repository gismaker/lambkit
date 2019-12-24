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
package com.lambkit.web.directive;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;

import java.util.ArrayList;
import java.util.List;

public abstract class PaginateDirectiveBase extends LambkitDirective {

	public void onRender(Env env, Scope scope, Writer writer) {
		String previousClass = (String) getPara("previousClass", scope, "previous");
		String nextClass = (String) getPara("nextClass", scope, "next");
		String activeClass = (String) getPara("activeClass", scope, "active");
		String disabledClass = (String) getPara("disabledClass", scope, "disabled");
		boolean onlyShowPreviousAndNext = ((Boolean) getPara("onlyShowPreviousAndNext", scope, Boolean.valueOf(false)))
				.booleanValue();

		String previousText = (String) getPara("previousText", scope, "上一页");
		String nextText = (String) getPara("nextText", scope, "下一页");

		Page<?> page = getPage(env, scope, writer);

		int currentPage = page == null ? 1 : page.getPageNumber();
		int totalPage = page == null ? 1 : page.getTotalPage();
		if ((totalPage <= 0) || (currentPage > totalPage)) {
			return;
		}
		int startPage = currentPage - 4;
		if (startPage < 1) {
			startPage = 1;
		}
		int endPage = currentPage + 4;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		if (currentPage <= 8) {
			startPage = 1;
		}
		if (totalPage - currentPage < 8) {
			endPage = totalPage;
		}
		List<PaginateItem> pages = new ArrayList();
		if (currentPage == 1) {
			pages.add(new PaginateItem(previousClass + " " + disabledClass, "javascript:;", previousText));
		} else {
			pages.add(new PaginateItem(previousClass, getUrl(currentPage - 1), previousText));
		}
		if ((currentPage > 8) && (!onlyShowPreviousAndNext)) {
			pages.add(new PaginateItem("", getUrl(1), "1"));
			pages.add(new PaginateItem("", getUrl(2), "2"));
			pages.add(new PaginateItem(disabledClass, "javascript:;", "..."));
		}
		if (!onlyShowPreviousAndNext) {
			for (int i = startPage; i <= endPage; i++) {
				if (currentPage == i) {
					pages.add(new PaginateItem(activeClass, "javascript:;", i));
				} else {
					pages.add(new PaginateItem("", getUrl(i), i));
				}
			}
		}
		if ((totalPage - currentPage >= 8) && (!onlyShowPreviousAndNext)) {
			pages.add(new PaginateItem(disabledClass, "javascript:;", "..."));
			pages.add(new PaginateItem("", getUrl(totalPage - 1), totalPage - 1));
			pages.add(new PaginateItem("", getUrl(totalPage), totalPage));
		}
		if (currentPage == totalPage) {
			pages.add(new PaginateItem(nextClass + " " + disabledClass, "javascript:;", nextText));
		} else {
			pages.add(new PaginateItem(nextClass, getUrl(currentPage + 1), nextText));
		}
		scope.setLocal(getPageItemsName(), pages);

		renderBody(env, scope, writer);
	}

	protected abstract String getUrl(int paramInt);

	protected abstract Page<?> getPage(Env paramEnv, Scope paramScope, Writer paramWriter);

	protected String getPageItemsName() {
		return "pages";
	}

	public boolean hasEnd() {
		return true;
	}

	public static class PaginateItem {
		private String style;
		private String url;
		private String text;

		public PaginateItem(String style, String url, String text) {
			this.style = style;
			this.url = url;
			this.text = text;
		}

		public PaginateItem(String style, String url, int text) {
			this.style = style;
			this.url = url;
			this.text = (text + "");
		}

		public String getStyle() {
			return this.style;
		}

		public void setStyle(String style) {
			this.style = style;
		}

		public String getUrl() {
			return this.url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getText() {
			return this.text;
		}

		public void setText(String text) {
			this.text = text;
		}
	}
}
