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
package com.lambkit.module.wechat.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.ext.interceptor.NotAction;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.LogKit;
import com.jfinal.weixin.sdk.api.*;
import com.lambkit.Lambkit;
import com.lambkit.common.util.RequestUtils;
import com.lambkit.common.util.StringUtils;
import com.lambkit.module.wechat.WechatApis;
import com.lambkit.module.wechat.WechatConfig;
import com.lambkit.module.wechat.interceptor.WechatApiConfigInterceptor;
import com.lambkit.module.wechat.interceptor.WechatUserInterceptor;
import com.lambkit.web.controller.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

@Before({WechatApiConfigInterceptor.class, WechatUserInterceptor.class})
public abstract class WechatController extends BaseController {

    public static final String SESSION_WECHAT_OPEN_ID = "_lambkit_wechat_open_id_";
    public static final String SESSION_WECHAT_ACCESS_TOKEN = "_lambkit_wechat_access_token_";
    public static final String SESSION_WECHAT_SCOPE = "_lambkit_wechat_scope_";
    public static final String SESSION_WECHAT_USER_JSON = "_lambkit_wechat_json_";
    public static final String ATTR_USER_OBJECT = "_lambkit_user_object_";


    public ApiConfig getApiConfig() {
        return ApiConfigKit.getApiConfig();
    }


    @Clear(WechatUserInterceptor.class)
    public void wechatCallback() {

        String gotoUrl = getPara("goto");
        String code = getPara("code");

        //获得不到code？
        if (StringUtils.isBlank(code)) {
            renderText("获取不到正确的code信息");
            return;
        }


        /**
         * 在某些情况下，相同的callback会执行两次，code相同。
         */
        String wechatOpenId = getSessionAttr(SESSION_WECHAT_OPEN_ID);
        String accessToken = getSessionAttr(SESSION_WECHAT_ACCESS_TOKEN);

        if (StringUtils.isNotBlank(wechatOpenId)
                && StringUtils.isNotBlank(accessToken)) {
            doRedirect(gotoUrl, wechatOpenId, accessToken);
            return;
        }


        ApiResult result = WechatApis.getAccessTokenAndOpenId(code);
        if (result == null) {
            renderText("网络错误，获取不到微信信息，请联系管理员");
            return;
        }

        /**
         * 成功获取到 accesstoken 和 openid
         */
        if (result.isSucceed()) {
            wechatOpenId = result.getStr("openid");
            accessToken = result.getStr("access_token");
            setSessionAttr(SESSION_WECHAT_OPEN_ID, wechatOpenId);
            setSessionAttr(SESSION_WECHAT_ACCESS_TOKEN, accessToken);
            setSessionAttr(SESSION_WECHAT_SCOPE, result.getStr("scope"));
        } else {
            wechatOpenId = getSessionAttr(SESSION_WECHAT_OPEN_ID);
            accessToken = getSessionAttr(SESSION_WECHAT_ACCESS_TOKEN);

            if (StringUtils.isBlank(wechatOpenId) || StringUtils.isBlank(accessToken)) {
                renderText("错误：" + result.getErrorMsg());
                return;
            }
        }

        if ("snsapi_base".equalsIgnoreCase(result.getStr("scope"))) {
            redirect(gotoUrl);
            return;
        }

        doRedirect(gotoUrl, wechatOpenId, accessToken);
    }

    private void doRedirect(String gotoUrl, String wechatOpenId, String accessToken) {

        /**
         * 由于 wechatOpenId 或者 accessToken 是可能从session读取的，
         * 从而导致失效等问题
         */
        ApiResult apiResult = WechatApis.getUserInfo(accessToken, wechatOpenId);

        if (!apiResult.isSucceed()) {
            redirect(gotoUrl);
            return;
        }

        setSessionAttr(SESSION_WECHAT_USER_JSON, apiResult.getJson());
        redirect(gotoUrl);
    }

    @Before(NotAction.class)
    public void clearWechatSession() {
        //移除脏数据后，再次进入授权页面
        removeSessionAttr(SESSION_WECHAT_OPEN_ID);
        removeSessionAttr(SESSION_WECHAT_ACCESS_TOKEN);
        removeSessionAttr(SESSION_WECHAT_USER_JSON);
    }


    @Before(NotAction.class)
    public void initJsSdkConfig() {

        WechatConfig config = Lambkit.config(WechatConfig.class);


        // 1.拼接url（当前网页的URL，不包含#及其后面部分）
        String url = getRequest().getRequestURL().toString().split("#")[0];
        String query = getRequest().getQueryString();
        if (StringUtils.isNotBlank(query)) {
            url = url.concat("?").concat(query);
        }
        LogKit.info("signature_url: " + url);

//        JsTicket jsTicket = JsTicketApi.getTicket(JsTicketApi.JsApiType.jsapi);
        JsTicket jsTicket = WechatApis.getTicket(WechatApis.JsApiType.jsapi);

        String _wxJsApiTicket = jsTicket.getTicket();

        String noncestr = StringUtils.uuid();
        String timestamp = (System.currentTimeMillis() / 1000) + "";

        Map<String, String> _wxMap = new TreeMap<String, String>();
        _wxMap.put("noncestr", noncestr);
        _wxMap.put("timestamp", timestamp);
        _wxMap.put("jsapi_ticket", _wxJsApiTicket);
        _wxMap.put("url", url);

        //拼接字符串
        StringBuilder paramsBuilder = new StringBuilder();
        for (Map.Entry<String, String> param : _wxMap.entrySet()) {
            paramsBuilder.append(param.getKey()).append("=").append(param.getValue()).append("&");
        }
        String signString = paramsBuilder.substring(0, paramsBuilder.length() - 1);

        //签名
        String signature = HashKit.sha1(signString);

        setAttr("wechatDebug", config.getDebug());
        setAttr("wechatAppId", getApiConfig().getAppId());
        setAttr("wechatNoncestr", noncestr);
        setAttr("wechatTimestamp", timestamp);
        setAttr("wechatSignature", signature);

    }

    public boolean isAllowVisit() {
        HttpServletRequest req = getRequest();
        if (RequestUtils.isWechatPcBrowser(req)) {
            return false;
        }

        return RequestUtils.isWechatBrowser(req);
    }

    @Before(NotAction.class)
    public void doNotAlloVisitRedirect() {
        /**
         * 一般情况下，此方法是为了调整到其他页面，比如让用户扫描二维码之类的
         * 由子类去实现
         */
        renderText("lambkit wechat error, cannot visit in this browser.");
    }


    public <T> T getCurrentUser() {
        return getAttr(ATTR_USER_OBJECT);
    }


    /**
     * 进行用户查找，找到返回该用户，找不到返回 null
     * 返回的object（用户）通过 getCurrentUser 可以得到。
     *
     * @param openid
     * @return
     */
    public abstract Object doGetUserByOpenId(String openid);

    /**
     * 根据 apiResult 数据来保存或更新用户信息
     * <p>
     * 用户第一次访问的时候
     * <p>
     * <p>
     * <p>
     * apiResult内如如下：
     * {
     * "subscribe": 1,
     * "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M",
     * "nickname": "Band",
     * "sex": 1,
     * "language": "zh_CN",
     * "city": "广州",
     * "province": "广东",
     * "country": "中国",
     * "headimgurl":  "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4
     * eMsv84eavHiaiceqxibJxCfHe/0",
     * "subscribe_time": 1382694957,
     * "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
     * "remark": "",
     * "groupid": 0,
     * "tagid_list":[128,2]
     * }
     *
     * @param apiResult
     * @return
     */

    public abstract Object doSaveOrUpdateUserByApiResult(ApiResult apiResult);
}
