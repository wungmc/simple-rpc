/*
 * Copyright (C), 2011-2020.
 */
package com.wung.rpc.simple.test;

import java.io.Serializable;

/**
 * @author wung 2020-02-25.
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1323129881940526951L;
	
	private Integer id;
	private String name;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
