package com.lambkit.test.rpc.zbus;

import com.lambkit.test.rpc.InterfaceExample;

import io.zbus.rpc.Request;
import io.zbus.rpc.RpcCallback;
import io.zbus.rpc.RpcInvoker;
import io.zbus.rpc.bootstrap.mq.ClientBootstrap;

public class RpcClient {

	public static void main(String[] args) throws Exception {   
		ClientBootstrap b = new ClientBootstrap(); 
		b.serviceAddress("localhost:15555")
		 .serviceName("LambkitRpc");
		 //.serviceToken("myrpc_service"); 
		
		RpcInvoker rpc = b.invoker();
		
		//Way 1) Raw request
		Request req = new Request();
		req.setModule("InterfaceExample");
		req.setMethod("plus");
		req.setParams(new Object[]{1,2});
		
		Object res = rpc.invokeSync(req);
		System.out.println("raw: " + res);
		
		//asynchronous call
		rpc.invokeAsync(Integer.class, req, new RpcCallback<Integer>() { 
			@Override
			public void onSuccess(Integer result) {  
				System.out.println("async raw: " + result);
			}
			
			@Override
			public void onError(Exception error) {
				System.err.println(error);
			}
		});
		
		
		//Way 2) More abbreviated
		int result = rpc.invokeSync(Integer.class, "plus", 1, 2);
		System.out.println("typed: " + result); 
		
		//Way 3) Dynamic proxy class, the client side only need Interface
		InterfaceExample api = rpc.createProxy(InterfaceExample.class);
		result = api.plus(1, 2); 
		System.out.println("proxy class: " + result);
		
		
		b.close(); 
	}

}
