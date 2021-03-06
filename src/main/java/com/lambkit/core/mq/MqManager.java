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
package com.lambkit.core.mq;

import com.jfinal.config.Plugins;
import com.lambkit.Lambkit;
import com.lambkit.core.aop.AopKit;
import com.lambkit.core.mq.activemq.ActiveMQ;
import com.lambkit.core.mq.zubs.ZbusMq;
import com.lambkit.module.LambkitModule;

public class MqManager extends LambkitModule {

    private static MqManager manager;

    public static MqManager me() {
        if (manager == null) {
            manager = AopKit.singleton(MqManager.class);
        }
        return manager;
    }

    private MqPlugin mq;

    public MqPlugin getMq(MqConfig config) {
    	if(config==null) {
    		return getPlugin();
    	} else {
    		return buildMq(config.getType());
    	}
    }
    
    public MqPlugin getPlugin() {
        if (mq == null) {
        	 MqConfig config = Lambkit.config(MqConfig.class);
            mq = buildMq(config.getType());
        }
        return mq;
    }
    
    private MqPlugin buildMq(String type) {
        switch (type) {
            case MqConfig.TYPE_ZBUS:
                return new ZbusMq();
            case MqConfig.TYPE_ACTIVEMQ:
            	return new ActiveMQ();
            default:
                return new ZbusMq();
        }
    }
    
    @Override
    public void configPlugin(Plugins me) {
    	// TODO Auto-generated method stub
    	super.configPlugin(me);
    	me.add(this.getPlugin());
    }
}
