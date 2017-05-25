package com.ght.learn.hessian.client;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.ght.learn.hessian.common.SayHello;

public class NormalClient {
	public static void main(String[] args) throws MalformedURLException {
		//Spring Hessian代理Servelet  
        String url = "http://admin.localokforwarder.com:8080/learn/sayHello.do";  
        HessianProxyFactory factory = new HessianProxyFactory();  
        SayHello api = (SayHello) factory.create(SayHello.class, url);  
      
       System.out.println(api.sayHello(""));  
		 /*ApplicationContext contex = 
                 new ClassPathXmlApplicationContext("spring/spring-hessian.xml");
        // 获得客户端的Hessian代理工厂bean  
        SayHello i = (SayHello) contex.getBean("hessianClient");  
        System.out.println(i.sayHello("masongchao"));  */
	}

}
