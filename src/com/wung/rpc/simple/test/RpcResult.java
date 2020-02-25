/*
 * Copyright (C), 2011-2020.
 */
package com.wung.rpc.simple.test;

import java.io.Serializable;

/**
 * @author wung 2020-02-25.
 */
public class RpcResult implements Serializable {
	private static final long serialVersionUID = -6104746053674970512L;
	
	private Integer code;
	private String msg;
	private Object data;
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "RpcResult{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				", data=" + data +
				'}';
	}
}
