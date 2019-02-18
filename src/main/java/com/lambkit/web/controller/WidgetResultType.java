package com.lambkit.web.controller;

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
