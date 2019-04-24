package com.lambkit.plugin.nim;

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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.StrKit;

public class NimKit {

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
	 * 创建网易云通信ID
	 * accid	String	是	网易云通信ID，最大长度32字符，必须保证一个APP内唯一（只允许字母、数字、半角下划线_、@、半角点以及半角-组成，不区分大小写，会统一小写处理，请注意以此接口返回结果中的accid为准）。
	 * name		String	否	网易云通信ID昵称，最大长度64字符，用来PUSH推送时显示的昵称
	 * props	String	否	json属性，第三方可选填，最大长度1024字符
	 * icon		String	否	网易云通信ID头像URL，第三方可选填，最大长度1024
	 * token	String	否	网易云通信ID可以指定登录token值，最大长度128字符，并更新，如果未指定，会自动生成token，并在创建成功后返回
	 * sign		String	否	用户签名，最大长度256字符
	 * email	String	否	用户email，最大长度64字符
	 * birth	String	否	用户生日，最大长度16字符
	 * mobile	String	否	用户mobile，最大长度32字符，非中国大陆手机号码需要填写国家代码(如美国：+1-xxxxxxxxxx)或地区代码(如香港：+852-xxxxxxxx)
	 * gender	int		否	用户性别，0表示未知，1表示男，2女表示女，其它会报参数错误
	 * ex		String	否	用户名片扩展字段，最大长度1024字符，用户可自行扩展，建议封装成JSON字符串
	 * @param config
	 * @return
	 */
	public static String createUser(NimConfig config, NimUser user) {
		String url = "https://api.netease.im/nimserver/user/create.action";
		// 设置请求的参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", user.getAccid()));
		if(StrKit.notBlank(user.getName())) {
			nvps.add(new BasicNameValuePair("name", user.getName()));
		}
		if(StrKit.notBlank(user.getIcon())) {
			nvps.add(new BasicNameValuePair("icon", user.getIcon()));
		}
		if(StrKit.notBlank(user.getProps())) {
			nvps.add(new BasicNameValuePair("props", user.getProps()));
		}
		if(StrKit.notBlank(user.getToken())) {
			nvps.add(new BasicNameValuePair("token", user.getToken()));
		}
		if(StrKit.notBlank(user.getSign())) {
			nvps.add(new BasicNameValuePair("sign", user.getSign()));
		}
		if(StrKit.notBlank(user.getEmail())) {
			nvps.add(new BasicNameValuePair("email", user.getEmail()));
		}
		if(StrKit.notBlank(user.getBirth())) {
			nvps.add(new BasicNameValuePair("birth", user.getBirth()));
		}
		if(StrKit.notBlank(user.getMobile())) {
			nvps.add(new BasicNameValuePair("mobile", user.getMobile()));
		}
		if(StrKit.notBlank(user.getGender())) {
			nvps.add(new BasicNameValuePair("gender", user.getGender()));
		}
		if(StrKit.notBlank(user.getEx())) {
			nvps.add(new BasicNameValuePair("ex", user.getEx()));
		}
		try {
			// 执行请求
			HttpResponse response = connect(config, url, nvps);
			JSONObject responseBody = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
			if(responseBody.getInteger("code")==200) {
				JSONObject info = responseBody.getJSONObject("info");
				return info.getString("token");
			}
			LogKit.warn("NimKit[createUser] nimserver code is " + responseBody.getInteger("code"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		/*
		 * "Content-Type": "application/json; charset=utf-8" { "code":200,
		 * "info":{"token":"xx","accid":"xx","name":"xx"} }
		 */
	}

	/**
	 * 网易云通信ID更新
	 * accid String 是 网易云通信ID，最大长度32字符，必须保证一个APP内唯一
	 * props String 否  json属性，第三方可选填，最大长度1024字符
	 * token String 否 网易云通信ID可以指定登录token值，最大长度128字符
	 * 
	 * @param config
	 * @param user
	 * @return
	 */
	public static int updateUser(NimConfig config, NimUser user) {
		String url = "https://api.netease.im/nimserver/user/update.action";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", user.getAccid()));
		if(StrKit.notBlank(user.getProps())) {
			nvps.add(new BasicNameValuePair("props", user.getProps()));
		}
		if(StrKit.notBlank(user.getToken())) {
			nvps.add(new BasicNameValuePair("token", user.getToken()));
		}
		try {
			HttpResponse response = connect(config, url, nvps);
			JSONObject responseBody = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
			return responseBody.getInteger("code");
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		/*
		 * "Content-Type": "application/json; charset=utf-8" { "code":200 }
		 */
	}

	/**
	 * 更新并获取新token
	 * @param config
	 * @param accid
	 * @return
	 */
	public static String refreshToken(NimConfig config, String accid) {
		String url = "https://api.netease.im/nimserver/user/refreshToken.action";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", accid));
		try {
			HttpResponse response = connect(config, url, nvps);
			JSONObject responseBody = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
			if(responseBody.getInteger("code")==200) {
				JSONObject info = responseBody.getJSONObject("info");
				return info.getString("token");
			}
			LogKit.warn("NimKit[refreshToken] nimserver code is " + responseBody.getInteger("code"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		/*
		 * "Content-Type": "application/json; charset=utf-8" { "code":200,
		 * "info":{"token":"xx","accid":"xx","name":"xx"} }
		 */
	}
	
	/**
	 * 更新用户名片
	 * 参数同createUser
	 * accid	String	是	网易云通信ID，最大长度32字符，必须保证一个APP内唯一（只允许字母、数字、半角下划线_、@、半角点以及半角-组成，不区分大小写，会统一小写处理，请注意以此接口返回结果中的accid为准）。
	 * name		String	否	网易云通信ID昵称，最大长度64字符，用来PUSH推送时显示的昵称
	 * icon		String	否	网易云通信ID头像URL，第三方可选填，最大长度1024
	 * sign		String	否	用户签名，最大长度256字符
	 * email	String	否	用户email，最大长度64字符
	 * birth	String	否	用户生日，最大长度16字符
	 * mobile	String	否	用户mobile，最大长度32字符，非中国大陆手机号码需要填写国家代码(如美国：+1-xxxxxxxxxx)或地区代码(如香港：+852-xxxxxxxx)
	 * gender	int		否	用户性别，0表示未知，1表示男，2女表示女，其它会报参数错误
	 * ex		String	否	用户名片扩展字段，最大长度1024字符，用户可自行扩展，建议封装成JSON字符串
	 * @param config
	 * @param user
	 * @return
	 */
	public static int updateUinfo(NimConfig config, NimUser user) {
		String url = "https://api.netease.im/nimserver/user/updateUinfo.action";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", user.getAccid()));
		if(StrKit.notBlank(user.getName())) {
			nvps.add(new BasicNameValuePair("name", user.getName()));
		}
		if(StrKit.notBlank(user.getIcon())) {
			nvps.add(new BasicNameValuePair("icon", user.getIcon()));
		}
		if(StrKit.notBlank(user.getSign())) {
			nvps.add(new BasicNameValuePair("sign", user.getSign()));
		}
		if(StrKit.notBlank(user.getEmail())) {
			nvps.add(new BasicNameValuePair("email", user.getEmail()));
		}
		if(StrKit.notBlank(user.getBirth())) {
			nvps.add(new BasicNameValuePair("birth", user.getBirth()));
		}
		if(StrKit.notBlank(user.getMobile())) {
			nvps.add(new BasicNameValuePair("mobile", user.getMobile()));
		}
		if(StrKit.notBlank(user.getGender())) {
			nvps.add(new BasicNameValuePair("gender", user.getGender()));
		}
		if(StrKit.notBlank(user.getEx())) {
			nvps.add(new BasicNameValuePair("ex", user.getEx()));
		}
		try {
			HttpResponse response = connect(config, url, nvps);
			JSONObject responseBody = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
			return responseBody.getInteger("code");
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 获取用户名片
	 * @param config
	 * @param accids
	 * @return
	 */
	public static List<NimUser> getUinfos(NimConfig config, String accids) {
		String url = "https://api.netease.im/nimserver/user/getUinfos.action";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", accids));
		List<NimUser> users = Lists.newArrayList();
		try {
			HttpResponse response = connect(config, url, nvps);
			JSONObject responseBody = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
			if(responseBody.getInteger("code")==200) {
				JSONArray infos = responseBody.getJSONArray("uinfos");
				for (int i = 0; i < infos.size(); i++) {
					users.add(infos.getObject(i, NimUser.class));
				}
			} else {
				LogKit.warn("NimKit[getUinfos] nimserver code is " + responseBody.getInteger("code"));	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}
	/**
	 * 设置桌面端在线时，移动端是否需要推送
	 * @param config
	 * @param accid
	 * @param donnopOpen 桌面端在线时，移动端是否不推送：true:移动端不需要推送，false:移动端需要推送
	 * @return
	 */
	public static int setDonnop(NimConfig config, String accid, boolean donnopOpen) {
		String url = "https://api.netease.im/nimserver/user/setDonnop.action";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", accid));
		if(donnopOpen) {
			nvps.add(new BasicNameValuePair("donnopOpen", "true"));
		} else {
			nvps.add(new BasicNameValuePair("donnopOpen", "false"));
		}
		try {
			HttpResponse response = connect(config, url, nvps);
			JSONObject responseBody = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
			return responseBody.getInteger("code");
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 加好友
	 * @param config
	 * @param accid
	 * @param faccid
	 * @param type
	 * @return
	 */
	public static int friendAdd(NimConfig config, String accid, String faccid, int type) {
		String url = "https://api.netease.im/nimserver/friend/add.action";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", accid));
		nvps.add(new BasicNameValuePair("faccid", faccid));
		nvps.add(new BasicNameValuePair("type", String.valueOf(type)));
		try {
			HttpResponse response = connect(config, url, nvps);
			JSONObject responseBody = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
			return responseBody.getInteger("code");
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 删除好友
	 * @param config
	 * @param accid
	 * @param faccid
	 * @return
	 */
	public static int friendDelete(NimConfig config, String accid, String faccid) {
		String url = "https://api.netease.im/nimserver/friend/delete.action";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", accid));
		nvps.add(new BasicNameValuePair("faccid", faccid));
		try {
			HttpResponse response = connect(config, url, nvps);
			JSONObject responseBody = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
			return responseBody.getInteger("code");
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 发送普通消息
	 * 接口描述
	 * 给用户或者高级群发送普通消息，包括文本，图片，语音，视频和地理位置，具体消息参考下面描述
	 * @param config
	 * @param from 发送者accid
	 * @param opt 0：点对点个人消息，1：群消息（高级群），其他返回414
	 * @param to ope==0是表示accid即用户id，ope==1表示tid即群id
	 * @param type 0 表示文本消息,1 表示图片，2 表示语音，3 表示视频，4 表示地理位置信息，6 表示文件，
	 * @param msg 最大长度5000字符，为一个JSON串
	 * @return
	 */
	public static JSONObject sendMsg(NimConfig config, String from, int opt, String to, int type, String msg) {
		String url = "https://api.netease.im/nimserver/msg/sendMsg.action";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("from", from));
		nvps.add(new BasicNameValuePair("opt", String.valueOf(opt)));
		nvps.add(new BasicNameValuePair("to", to));
		nvps.add(new BasicNameValuePair("type", String.valueOf(type)));
		nvps.add(new BasicNameValuePair("body", msg));
		try {
			HttpResponse response = connect(config, url, nvps);
			JSONObject responseBody = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
			if(responseBody.getInteger("code")==200) {
				return responseBody.getJSONObject("data");
			}
			LogKit.warn("NimKit[sendMsg] nimserver code is " + responseBody.getInteger("code"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 批量发送点对点普通消息
	 * 接口描述
	 * 1.给用户发送点对点普通消息，包括文本，图片，语音，视频，地理位置和自定义消息。
	 * 2.最大限500人，只能针对个人,如果批量提供的帐号中有未注册的帐号，会提示并返回给用户。
	 * 3.此接口受频率控制，一个应用一分钟最多调用120次，超过会返回416状态码，并且被屏蔽一段时间；
	 * @param config
	 * @param from 发送者accid
	 * @param to ope==0是表示accid即用户id，ope==1表示tid即群id
	 * @param type 0 表示文本消息,1 表示图片，2 表示语音，3 表示视频，4 表示地理位置信息，6 表示文件，
	 * @param msg 最大长度5000字符，为一个JSON串
	 * @return
	 */
	public static JSONObject sendBatchMsg(NimConfig config, String from, String to, int type, String msg) {
		String url = "https://api.netease.im/nimserver/msg/sendBatchMsg.action";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("fromAccid", from));
		nvps.add(new BasicNameValuePair("toAccids", to));
		nvps.add(new BasicNameValuePair("type", String.valueOf(type)));
		nvps.add(new BasicNameValuePair("body", msg));
		try {
			HttpResponse response = connect(config, url, nvps);
			JSONObject responseBody = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
			if(responseBody.getInteger("code")==200) {
				return responseBody;
			}
			LogKit.warn("NimKit[sendBatchMsg] nimserver code is " + responseBody.getInteger("code"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 发送自定义系统通知
	 * 接口描述
	 * 1.自定义系统通知区别于普通消息，方便开发者进行业务逻辑的通知；
	 * 2.目前支持两种类型：点对点类型和群类型（仅限高级群），根据msgType有所区别。
	 * 
	 * 应用场景：如某个用户给另一个用户发送好友请求信息等，具体attach为请求消息体，第三方可以自行扩展，建议是json格式
	 * @param config
	 * @param from 发送者accid
	 * @param opt 0：点对点个人消息，1：群消息（高级群），其他返回414
	 * @param to ope==0是表示accid即用户id，ope==1表示tid即群id
	 * @param msg 最大长度5000字符，为一个JSON串
	 * @return
	 */
	public static int sendAttachMsg(NimConfig config, String from, int opt, String to, String msg) {
		String url = "https://api.netease.im/nimserver/msg/sendAttachMsg.action";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("from", from));
		nvps.add(new BasicNameValuePair("msgtype", String.valueOf(opt)));
		nvps.add(new BasicNameValuePair("to", to));
		nvps.add(new BasicNameValuePair("attach", msg));
		try {
			HttpResponse response = connect(config, url, nvps);
			JSONObject responseBody = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
			return responseBody.getInteger("code");
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 批量发送点对点自定义系统通知
	 * 接口描述
	 * 1.系统通知区别于普通消息，应用接收到直接交给上层处理，客户端可不做展示；
	 * 2.目前支持类型：点对点类型；
	 * 3.最大限500人，只能针对个人,如果批量提供的帐号中有未注册的帐号，会提示并返回给用户；
	 * 4.此接口受频率控制，一个应用一分钟最多调用120次，超过会返回416状态码，并且被屏蔽一段时间；
	 * 
	 * 应用场景：如某个用户给另一个用户发送好友请求信息等，具体attach为请求消息体，第三方可以自行扩展，建议是json格式
	 * @param config
	 * @param from 发送者accid
	 * @param to ope==0是表示accid即用户id，ope==1表示tid即群id
	 * @param msg 最大长度5000字符，为一个JSON串
	 * @return
	 */
	public static JSONObject sendBatchAttachMsg(NimConfig config, String from, String to, String msg) {
		String url = "https://api.netease.im/nimserver/msg/sendBatchAttachMsg.action";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("fromAccid", from));
		nvps.add(new BasicNameValuePair("toAccids", to));
		nvps.add(new BasicNameValuePair("attach", msg));
		try {
			HttpResponse response = connect(config, url, nvps);
			JSONObject responseBody = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
			if(responseBody.getInteger("code")==200) {
				return responseBody;
			}
			LogKit.warn("NimKit[sendBatchAttachMsg] nimserver code is " + responseBody.getInteger("code"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
