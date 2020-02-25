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
		for (int i = 0; i < 10; i++) {
			String result = helloService.sayHello("world" + i);
			System.out.println(result);
		}
	}
}
