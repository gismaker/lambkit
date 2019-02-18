package com.lambkit.module.wechat.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.lambkit.module.wechat.controller.WechatController;

public class WechatVisitInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		WechatController controller = (WechatController) inv.getController();

        /**
         * 是否允许访问，默认情况下只有是微信浏览器允许访问
         */
        if (controller.isAllowVisit()) {
            inv.invoke();
            return;
        }

        controller.doNotAlloVisitRedirect();
	}

}
