package com.lambkit.test.node;

import com.lambkit.core.rpc.RpcKit;
import com.lambkit.distributed.node.ManagerNodeService;

public class TestRpc {

	public static void main(String[] args) {
		ManagerNodeService service = RpcKit.obtain(ManagerNodeService.class, "lambkit", "1.0", "127.0.0.1:8002");
		if(service!=null) {
			System.out.println(service.getToken());
		} else {
			System.out.println("fail");
		}
	}
}
