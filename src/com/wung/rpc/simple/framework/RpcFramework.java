/*
 * Copyright (C), 2011-2020.
 */
package com.wung.rpc.simple.framework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现一个简单rpc框架。
 * 例子来自：<a>https://www.iteye.com/blog/javatar-1123915</a>
 *
 * @author wung 2020-02-25.
 */
public class RpcFramework {
	
	
	/**
	 * 引用服务。
	 *
	 * @param interfaceClass 服务提供者接口类型
	 * @param host 服务提供者主机
	 * @param port 服务提供者端口
	 * @param <T>
	 * @return 返回服务提供者的代理类
	 */
	@SuppressWarnings("unchecked")
	public static <T> T refer(Class<T> interfaceClass, String host, int port) {
		// 参数校验
		validateWhenRefer(interfaceClass, host, port);
		
		System.out.println("get remote service: " + interfaceClass.getName() + " at " + host + ":" + port);
		
		// 利用java 动态代理动态生成代理服务
		return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(),
				new Class[]{interfaceClass},
				(proxy, method, args) -> {
					Socket client = new Socket(host, port);
					try {
						ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
						try {
							outputStream.writeUTF(method.getName());
							outputStream.writeObject(method.getParameterTypes());
							outputStream.writeObject(args);
							
							ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
							try {
								final Object result = inputStream.readObject();
								if (result instanceof Throwable) {
									throw (Throwable) result;
								}
								return result;
							} finally {
								inputStream.close();
							}
						} finally {
							outputStream.close();
						}
					} finally {
						client.close();
					}
				});
	}
	
	static <T> void validateWhenRefer(Class<T> interfaceClass, String host, int port) {
		if (interfaceClass == null) {
			throw new IllegalArgumentException("interfaceClass is null!");
		}
		if (!interfaceClass.isInterface()) {
			throw new IllegalArgumentException("interfaceClass not be interface!");
		}
		if (host == null || host.length() == 0) {
			throw new IllegalArgumentException("host is null!");
		}
		if (port < 0 || port > 65535) {
			throw new IllegalArgumentException("port must between 1 and 65535!");
		}
	}
	
	
	/**
	 * 暴露服务
	 *
	 * @param service 要暴露的服务
	 * @param port 端口
	 */
	public static void export(final Object service, int port) throws Exception {
		validateWhenExport(service, port);
		
		System.out.println("export service: " + service.getClass().getName() + " on port :" + port);
		
		// 循环等待客户端调用
		ServerSocket server = new ServerSocket(port);
		while (true) {
			try {
				final Socket socket = server.accept();
				// 多线程去跑调用
				new Thread(() -> {
					try {
						try {
							ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
							try {
								String methodName = inputStream.readUTF();
								Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
								Object[] args = (Object[]) inputStream.readObject();
								
								ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
								try {
									Method method = service.getClass().getMethod(methodName, parameterTypes);
									Object result = method.invoke(service, args);
									outputStream.writeObject(result);
								} catch (Throwable e) {
									e.printStackTrace();
									outputStream.writeObject(e);
								} finally {
									outputStream.close();
								}
							} finally {
								inputStream.close();
							}
						} finally {
							socket.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	static void validateWhenExport(final Object service, int port) {
		if (service == null) {
			throw new IllegalArgumentException("service is null!");
		}
		if (port < 0 || port > 65535) {
			throw new IllegalArgumentException("port must between 1 and 65535!");
		}
	}
}
