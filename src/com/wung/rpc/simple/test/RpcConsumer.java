/*
 * Copyright (C), 2011-2020.
 */
package com.wung.rpc.simple.test;

import com.wung.rpc.simple.framework.RpcFramework;

/**
 * @author wung 2020-02-25.
 */
public class RpcConsumer {
	
	public static void main(String[] args) {
		HelloService helloService = RpcFramework.refer(HelloService.class, "localhost", 2888);
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			String result = helloService.sayHello("world" + i);
			System.out.println(result);
		}
		System.out.println("cost: " + (System.currentTimeMillis() - beginTime));
		
		User user = new User();
		user.setId(0);
		user.setName("jack");
		
		RpcResult result = helloService.sayHello(user);
		System.out.println(result);
	}
	
}
