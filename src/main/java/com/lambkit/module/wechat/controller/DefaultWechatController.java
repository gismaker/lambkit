package com.lambkit.module.wechat.controller;

import com.jfinal.kit.Kv;
import com.jfinal.weixin.sdk.api.ApiResult;

public class DefaultWechatController extends WechatController {

	public void index() {
		initJsSdkConfig();
		renderJson();
	}
	
	@Override
	public Object doGetUserByOpenId(String openid) {
		// TODO Auto-generated method stub
		return getSessionAttr(openid);
	}

	@Override
	public Object doSaveOrUpdateUserByApiResult(ApiResult apiResult) {
		// TODO Auto-generated method stub
		Kv user = Kv.by("nickname", apiResult.getStr("nickname"));
		return setSessionAttr(apiResult.getStr("openid"), user);
	}

}
