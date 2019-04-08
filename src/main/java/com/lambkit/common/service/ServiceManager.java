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
 */package com.lambkit.common.service;

import java.util.Map;

import com.google.common.collect.Maps;
import com.lambkit.common.aop.AopKit;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.rpc.RpcConfig;
import com.lambkit.core.rpc.RpcKit;

public class ServiceManager {

	private static ServiceManager manager;

    public static ServiceManager me() {
        if (manager == null) {
            manager = AopKit.singleton(ServiceManager.class);
        }
        return manager;
    }
    
    private Map<String, ServiceObject> services;
    
    public Map<String, ServiceObject> getServices() {
    	if(services==null) {
    		services = Maps.newHashMap();
    	}
    	return services;
    }
    public <T> void put(ServiceObject service) {
    	getServices().put(service.getInterfaceClass().getName(), service);
    }
    
    public ServiceObject get(Class<?> interfaceClass) {
    	return getServices().get(interfaceClass.getName());
    }
    /**
     * 获取一个非null的接口服务对象
     * @param interfaceClass
     * @return
     */
    protected ServiceObject getOrNew(Class<?> interfaceClass) {
    	ServiceObject service = getServices().get(interfaceClass.getName());
    	if(service==null) {
    		service = new ServiceObject(interfaceClass);
    	}
    	return service;
    }
    /**
     * 设置接口服务的实现类
     * @param interfaceClass
     * @param implementClass
     */
    public void setImpl(Class<?> interfaceClass, Class<?> implementClass) {
    	getOrNew(interfaceClass).setImplementClass(implementClass);
    }
    /**
     * 设置接口服务的mock类
     * @param interfaceClass
     * @param mockClass
     */
    public void setMock(Class<?> interfaceClass, Class<?> mockClass) {
    	getOrNew(interfaceClass).setMockClass(mockClass);
    }
    /**
     * server端或者本地自用的接口服务注册
     * @param interfaceClass
     * @param implementClass
     * @param mockClass
     */
    public <T> void mapping(Class<?> interfaceClass, Class<?> implementClass, Class<?> mockClass) {
    	RpcConfig rpcConfig = ConfigManager.me().get(RpcConfig.class);
    	mapping(interfaceClass, implementClass, mockClass, rpcConfig.getDefaultGroup(), rpcConfig.getDefaultVersion(), rpcConfig.getDefaultPort());
    }
    
    /**
     * server端或者本地自用的接口服务注册
     * @param interfaceClass
     * @param implementClass
     * @param mockClass
     * @param group
     * @param version
     * @param port
     * @return
     */
    public <T> boolean mapping(Class<?> interfaceClass, Class<?> implementClass, Class<?> mockClass, String group, String version, int port) {
    	ServiceObject service = new ServiceObject(interfaceClass, implementClass, mockClass, group, version, port);
    	put(service);
    	if(implementClass!=null) {
    		return RpcKit.serviceExport(interfaceClass, service.inject(), group, version, port);
    	}
    	return false;
	}
    
    /**
     * rpc中client端的接口服务注册
     * @param interfaceClass
     * @param mockClass
     */
    public <T> void remote(Class<?> interfaceClass, Class<?> mockClass) {
    	RpcConfig rpcConfig = ConfigManager.me().get(RpcConfig.class);
    	ServiceObject service = new ServiceObject(interfaceClass, null, mockClass, rpcConfig.getDefaultGroup(), rpcConfig.getDefaultVersion(), rpcConfig.getDefaultPort());
    	service.setType(ServiceObject.CLIENT);
    	put(service);
	}
    
    /**
     * rpc中client端的接口服务注册
     * @param interfaceClass
     * @param mockClass
     * @param group
     * @param version
     * @param port
     */
    public <T> void remote(Class<?> interfaceClass, Class<?> mockClass, String group, String version, int port) {
    	ServiceObject service = new ServiceObject(interfaceClass, null, mockClass, group, version, port);
    	service.setType(ServiceObject.CLIENT);
    	put(service);
	}
    
    /**
     * 获取服务接口
     * @param interfaceClass
     * @return
     */
    public <T> T inject(Class<?> interfaceClass) {
    	ServiceObject service = getServices().get(interfaceClass.getName());
    	if(service!=null) {
    		return service.inject();
    	}
    	return null;
    }
    /**
     * 获取服务接口
     * @param interfaceClass
     * @param defaultClass
     * @return
     */
    public <T> T inject(Class<?> interfaceClass, Class<?> defaultClass) {
    	ServiceObject service = getServices().get(interfaceClass.getName());
    	if(service!=null) {
    		return service.inject();
    	} else {
    		return (T) AopKit.singleton(defaultClass);
    	}
    }

    /*
    static Class[] default_excludes = new Class[]{EventListener.class, MqMessageListener.class, Serializable.class};


    public void init() {
        List<Class> classes = ClassScanner.scanClass(true);
        if (ArrayUtils.isNullOrEmpty(classes)) {
            return;
        }

        for (Class clazz : classes) {
            RpcService rpcService = (RpcService) clazz.getAnnotation(RpcService.class);
            if (rpcService == null) continue;

            String group = StringUtils.isBlank(rpcService.group()) ? config.getDefaultGroup() : rpcService.group();
            String version = StringUtils.isBlank(rpcService.version()) ? config.getDefaultVersion() : rpcService.version();
            int port = rpcService.port() <= 0 ? config.getDefaultPort() : rpcService.port();

            Class[] inters = clazz.getInterfaces();
            LambkitAssert.assertFalse(inters == null || inters.length == 0,
                    String.format("class[%s] has no interface, can not use @RpcService", clazz));

            //对某些系统的类 进行排查，例如：Serializable 等
            Class[] excludes = ArrayUtils.concat(default_excludes, rpcService.exclude());
            for (Class inter : inters) {
                boolean exclude = false;
                for (Class ex : excludes) {
                    if (ex == inter) exclude = true;
                }
                if (!exclude) {
                    getrpc().serviceExport(inter, Lambkit.bean(clazz), group, version, port);
                }
            }
        }
    }
*/
}
