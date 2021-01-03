package com.xc.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xc.pojo.Users;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@RequestMapping("/get/{name}")
	@HystrixCommand	//就算不做服务降级的处理，但是如果想要能被Turbine监控，这个注解还是需要使用
	public Object get(@PathVariable("name") String name) {
		Users users = new Users();
		users.setName(name);
		users.setAge(18);
		users.setSex("F");
		return users;
	}
}