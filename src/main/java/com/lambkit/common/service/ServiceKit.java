package com.lambkit.common.service;

import com.lambkit.common.util.ClassNewer;

public class ServiceKit {

	public static <T> T inject(Class<T> interfaceClass) {
    	Service service = ServiceManager.me().getServices().get(interfaceClass.getName());
    	if(service!=null) {
    		return service.inject();
    	}
    	return null;
    }
    
    public static <T> T inject(Class<T> interfaceClass, Class<? extends T> defaultClass) {
    	Service service = ServiceManager.me().getServices().get(interfaceClass.getName());
    	if(service!=null) {
    		return service.inject();
    	} else {
    		return ClassNewer.singleton(defaultClass);
    	}
    }
}
