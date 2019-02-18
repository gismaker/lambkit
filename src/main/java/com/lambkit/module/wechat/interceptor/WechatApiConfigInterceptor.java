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
package com.lambkit.module.wechat.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.lambkit.module.wechat.controller.WechatController;

public class WechatApiConfigInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        try {
            WechatController controller = (WechatController) inv.getController();
            ApiConfig config = controller.getApiConfig();

            if (config == null) {
                inv.getController().renderText("error : cannot get apiconfig,please config lambkit.properties");
                return;
            }

            ApiConfigKit.setThreadLocalAppId(config.getAppId());
            inv.invoke();
        } finally {
            ApiConfigKit.removeThreadLocalAppId();
        }
    }

}
