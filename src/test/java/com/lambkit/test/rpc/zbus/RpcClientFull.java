package com.lambkit.test.rpc.zbus;

import com.lambkit.test.rpc.GenericMethod;
import com.lambkit.test.rpc.InterfaceExample;

import io.zbus.rpc.bootstrap.mq.ClientBootstrap;

public class RpcClientFull {

	public static void main(String[] args) throws Exception { 
		//Enable SSL + Token based security 
		
		//ServerAddress serverAddress = new ServerAddress("localhost:15555"); 
		//serverAddress.setToken("myrpc_service");
		//serverAddress.setCertFile("ssl/zbus.crt");  
		//serverAddress.setSslEnabled(true); 
		
		ClientBootstrap b = new ClientBootstrap(); 
		//b.serviceAddress(serverAddress)
		b.serviceAddress("localhost:15555;localhost:15556") //Multiple servers, HA support!
		 .serviceName("MyRpc")
		 .serviceToken("myrpc_service"); 
/*
		InterfaceExample api = b.createProxy(InterfaceExample.class); 
		TestCases.testDynamicProxy(api);  //fully test on all cases of parameter types
		
		GenericMethod m = b.createProxy(GenericMethod.class);  
		TestCases.testGenericMethod(m);
		
		SubServiceInterface1 service1 = b.createProxy(SubServiceInterface1.class);
		TestCases.testInheritGeneric1(service1);
		
		SubServiceInterface2 service2 = b.createProxy(SubServiceInterface2.class); 
		TestCases.testInheritGeneric2(service2);  
		*/
		b.close(); 
	} 
}
