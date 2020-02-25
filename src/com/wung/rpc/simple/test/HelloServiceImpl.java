/*
 * Copyright (C), 2011-2020.
 */
package com.wung.rpc.simple.test;

/**
 * @author wung 2020-02-25.
 */
public class HelloServiceImpl implements HelloService {
	
	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}
	
}
