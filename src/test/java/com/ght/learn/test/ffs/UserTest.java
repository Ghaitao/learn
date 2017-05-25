package com.ght.learn.test.ffs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ght.learn.ffs.entity.sys.User;
import com.ght.learn.ffs.service.sys.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-db.xml")
public class UserTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void addUser(){
		/*User user=new User();
		user.setUserCode("admin");
		user.setUserName("高海涛");*/
		User user=userService.getUserById((long) 1);
		System.out.println("ss"+user.getUserNameEn());
	}

	
	
	
}
