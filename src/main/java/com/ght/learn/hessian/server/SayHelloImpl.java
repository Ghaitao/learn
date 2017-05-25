package com.ght.learn.hessian.server;

import com.ght.learn.hessian.common.SayHello;

public class SayHelloImpl implements SayHello{

	@Override
	public String sayHello(String name) {
		
		System.out.println("服务端方法被调用.");
		return "hello,"+name;
	}

}
