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
 */package com.lambkit.plugin.nim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.Kv;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.StrKit;

public class NimMessageKit {

	public static HttpResponse connect(NimConfig config, String url, List<NameValuePair> nvps) throws IOException {
		if (config == null || StrKit.isBlank(url))
			return null;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);

		String appKey = config.getAppKey();
		String appSecret = config.getAppSecret();
		String nonce = "12345";
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);

		// 设置请求的header
		httpPost.addHeader("AppKey", appKey);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		// 设置请求的参数
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
		// 执行请求
		HttpResponse response = httpClient.execute(httpPost);
		return response;
	}
	
	/**
	 * 发送验证码短信,
	 * 向指定的手机号码发送短信验证码。
	 * @param config
	 * @param doctorPhoneNumber
	 * @param codeLen
	 * @return
	 * http 响应:json
	 * 发送成功则返回相关信息。msg字段表示此次发送的sendid；obj字段表示此次发送的验证码。
	 */
	public static Kv sendCode(NimConfig config, String doctorPhoneNumber, String codeLen) {
		String url = "https://api.netease.im/sms/sendcode.action/";
		// 设置请求的的参数，requestBody参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        /*
         * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
         * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
         * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
         */
        nvps.add(new BasicNameValuePair("templateid", config.getMesssagTemplateId()));
        nvps.add(new BasicNameValuePair("mobiles", doctorPhoneNumber));
        nvps.add(new BasicNameValuePair("codeLen", codeLen));
		
        Kv result = new Kv();
        try {
			// 执行请求
			HttpResponse response = connect(config, url, nvps);
			JSONObject responseBody = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
			result.set("code", responseBody.getInteger("code"));
			if(responseBody.getInteger("code")==200) {
				result.set("msg", responseBody.getString("msg"));
				result.set("obj", responseBody.getString("obj"));
			}
			LogKit.warn("NimMessageKit[sendCode] sms code is " + responseBody.getInteger("code"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result; 
	}
	
	/**
	 * 发送模板短信请求,
	 * 向手机号发送内容格式预定义的短信，整个短信的内容由模板和变量组成。
	 * @param config
	 * @param doctorPhoneNumber
	 * @param messageContent
	 * @return
	 * http 响应:json
	 * 成功则在obj中返回此次发送的sendid(long),用于查询发送结果
	 */
	public static String sendMessage(NimConfig config, String doctorPhoneNumber, String messageContent) {
		String url = "https://api.netease.im/sms/sendtemplate.action/";
		// + "?templateid=" + TEMPLATEID + "&mobiles=[\"" + doctorPhoneNumber + "\"]" + "&params=" + "[\"" + messageContent + "\"]";
		// 设置请求的的参数，requestBody参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        /*
         * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
         * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
         * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
         */
        nvps.add(new BasicNameValuePair("templateid", config.getMesssagTemplateId()));
        nvps.add(new BasicNameValuePair("mobiles", doctorPhoneNumber));
        nvps.add(new BasicNameValuePair("params", messageContent));
		
        try {
			// 执行请求
			HttpResponse response = connect(config, url, nvps);
			JSONObject responseBody = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
			if(responseBody.getInteger("code")==200) {
				return responseBody.getString("obj");
			}
			LogKit.warn("NimMessageKit[sendMessage] sms code is " + responseBody.getInteger("code"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	/**
	 * 查询通知类和运营类短信发送状态
	 * @param config
	 * @param sendid
	 * @return
	 * http 响应:json
	 * obj中返回JSONArray,格式如下(其中status取值:0-未发送,1-发送成功,2-发送失败,3-反垃圾)：
	 */
	public static Kv queryMessageStatus(NimConfig config, String sendid) {
		String url = "https://api.netease.im/sms/querystatus.action/";
		// + "?templateid=" + TEMPLATEID + "&mobiles=[\"" + doctorPhoneNumber + "\"]" + "&params=" + "[\"" + messageContent + "\"]";
		// 设置请求的的参数，requestBody参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        /*
         * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
         * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
         * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
         */
        nvps.add(new BasicNameValuePair("sendid", sendid));
		
        Kv result = new Kv();
        try {
			// 执行请求
			HttpResponse response = connect(config, url, nvps);
			JSONObject responseBody = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
			result.set("code", responseBody.getInteger("code"));
			if(responseBody.getInteger("code")==200) {
				JSONObject info = responseBody.getJSONObject("obj");
				result.set("status", info.getInteger("code"));
				result.set("mobile", info.getString("mobile"));
				result.set("updatetime", info.getLong("updatetime"));
			}
			LogKit.warn("NimMessageKit[sendMessage] sms code is " + responseBody.getInteger("code"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result; 
	}
}
