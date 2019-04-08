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

public enum WidgetResultType {
	/**完成所有操作，直接return*/
	OVER,
	/**外部进行render***(view)操作*/
	VIEW,
	/**外部进行redirect(url)操作*/
	REDIRECT,
	/**外部进行renderError(errorCode)操作*/
	ERROR
}
