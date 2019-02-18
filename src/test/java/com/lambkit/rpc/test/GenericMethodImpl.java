package com.lambkit.rpc.test;

import io.zbus.rpc.Remote;

@Remote
public class GenericMethodImpl implements GenericMethod {

	@Override
	public <T> void test(T t) { 
		System.out.println(t);
	}

}
