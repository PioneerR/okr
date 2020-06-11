package com.xc.gateway.controller;

import com.xc.gateway.feignclient.CommonFeignClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@Validated
@Api(value = "登录接口", tags = {"登录接口"})
public class LoginController {

	@Autowired
	CommonFeignClient client;

	@PostMapping("/login")
	@PostConstruct
	public void login(){
		// TODO 省略 登录

		// 初始化公共数据 - 码值、错误信息等
		client.initCommonData();
	}





}
