/*
 * Copyright (C), 2011-2020.
 */
package com.wung.rpc.simple.test;

import com.wung.rpc.simple.framework.RpcFramework;

/**
 * @author wung 2020-02-25.
 */
public class RpcProvider {
	
	public static void main(String[] args) throws Exception {
		RpcFramework.export(new HelloServiceImpl(), 2888);
	}
}
