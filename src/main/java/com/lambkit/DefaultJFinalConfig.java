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
package com.lambkit;

import com.jfinal.config.*;
import com.jfinal.log.Log;
import com.jfinal.template.Engine;
import com.lambkit.common.base.Consts;
import com.lambkit.core.event.EventKit;
import com.lambkit.db.mgr.MgrdbManager;
import com.lambkit.module.DevelopModule;
import com.lambkit.module.LambkitModule;
import com.lambkit.module.upms.server.UpmsModule;

public class DefaultJFinalConfig extends JFinalConfig {

    static final Log log = Log.getLog(DefaultJFinalConfig.class);
    
    @Override
    public void configConstant(Constants constants) {
    	//TimeUtils.startTime("start lambkit configConstant");
    	Lambkit.me().addModule(new UpmsModule());
    	LambkitModule module = MgrdbManager.me().getLambkitModule();
    	if(module!=null) {
    		Lambkit.me().addModule(module);
    	}
    	if(Lambkit.me().isDevMode()) {
    		Lambkit.me().addModule(new DevelopModule());
		}
    	/**
		 * 发送初始化通知
		 */
		EventKit.sendEvent(Consts.EVENT_INIT, null);
        Lambkit.me().getModule().configConstant(constants);
        //TimeUtils.endTime("start lambkit configConstant");
    }


    @Override
    public void configRoute(Routes routes) {
    	//TimeUtils.startTime("start lambkit configRoute");
    	Lambkit.me().getModule().configRoute(routes);
        //TimeUtils.endTime("start lambkit configRoute");
    }

    @Override
    public void configEngine(Engine engine) {
    	//TimeUtils.startTime("start lambkit configEngine");
    	Lambkit.me().getModule().configEngine(engine);
        //TimeUtils.endTime("start lambkit configEngine");
    }


    @Override
    public void configPlugin(Plugins plugins) {
    	//TimeUtils.startTime("start lambkit configPlugin");
        Lambkit.me().getModule().configPlugin(plugins);
        //TimeUtils.endTime("start lambkit configPlugin");
    }


    @Override
    public void configInterceptor(Interceptors interceptors) {
    	//TimeUtils.startTime("start lambkit configInterceptor");
    	Lambkit.me().getModule().configInterceptor(interceptors);
        //TimeUtils.endTime("start lambkit configInterceptor");
    }

    @Override
    public void configHandler(Handlers handlers) {
    	//TimeUtils.startTime("start lambkit configHandler");
        Lambkit.me().getModule().configHandler(handlers);
        //TimeUtils.endTime("start lambkit configHandler");
    }

    @Override
    public void afterJFinalStart() {
    	//TimeUtils.startTime("start lambkit afterJFinalStart");
        super.afterJFinalStart();
        Lambkit.me().getModule().afterJFinalStart();
        //TimeUtils.endTime("start lambkit afterJFinalStart");
    }

    @Override
    public void beforeJFinalStop() {
    	//TimeUtils.startTime("start lambkit beforeJFinalStop");
        Lambkit.me().getModule().beforeJFinalStop();
        //TimeUtils.endTime("start lambkit beforeJFinalStop");
    }
    
}
