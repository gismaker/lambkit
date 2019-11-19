package com.lambkit.test.registry;

import com.lambkit.LambkitApplication;
import com.lambkit.LambkitApplicationContext;
import com.lambkit.core.registry.zookeeper.ZkServiceDiscovery;
import com.lambkit.core.registry.zookeeper.ZkServiceRegistry;

public class ZookeeperApplication extends LambkitApplicationContext {
	private static final String SERVICE_NAME = "fish.com";
	 
    private static final String SERVER_ADDRESS = "localhost:2181";
    
    @Override
    public void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
    	ZkServiceRegistry registry = new ZkServiceRegistry();
        registry.init();
        registry.registry(SERVICE_NAME,SERVER_ADDRESS);
        
        ZkServiceDiscovery discovery = new ZkServiceDiscovery();
        discovery.init();
        discovery.discover(SERVICE_NAME);
    }
    
    public static void main(String[] args) {
    	LambkitApplication.run(ZookeeperApplication.class, args);
    }
}
