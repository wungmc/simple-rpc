/*
 * Copyright (C), 2011-2020.
 */
package com.wung.rpc.simple.test;

/**
 * @author wung 2020-02-25.
 */
public interface HelloService {
	
	/**
	 * 测试简单字符串
	 * @param name
	 * @return
	 */
	String sayHello(String name);
	
	/**
	 * 测试自定义对象。
	 * 注意：对象需要实现序列化接口
	 *
	 * @param user
	 * @return
	 */
	RpcResult sayHello(User user);
	
}
