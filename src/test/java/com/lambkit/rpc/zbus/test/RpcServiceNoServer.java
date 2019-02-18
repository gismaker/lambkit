package com.lambkit.rpc.zbus.test;

import io.zbus.rpc.bootstrap.mq.ServiceBootstrap;

/**
 * 
 * Requirement: Start zbus server first!
 * 
 * @author Rushmore
 *
 */
public class RpcServiceNoServer {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {  
		
		//Enable SSL + Token based security 
		
		//ServerAddress serverAddress = new ServerAddress("localhost:15555");
		//serverAddress.setCertFile("ssl/zbus.crt");
		//serverAddress.setSslEnabled(true);	 
		//serverAddress.setToken("myrpc_service"); 
		
		ServiceBootstrap b = new ServiceBootstrap();  
		b.serviceName("MyRpc")
		 //.serviceToken("myrpc_service") 
		 .serviceAddress("localhost:15555;localhost:15556") //connect to remote server
		 .autoDiscover(true) //auto load class with Annotation Remote
		 .connectionCount(4)
		 .start();
	}
}
