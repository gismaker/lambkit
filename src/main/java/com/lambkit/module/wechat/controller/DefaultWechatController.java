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
 */package com.lambkit.module.wechat.controller;

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
