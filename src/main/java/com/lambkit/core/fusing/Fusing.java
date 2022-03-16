package com.lambkit.core.fusing;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class Fusing implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		if (QueuePluginThread.lq.offer(0)) {
			inv.invoke();
		} else {
			inv.getController().renderJson(FusingPlugin.renderJson);
		}
	}

}
