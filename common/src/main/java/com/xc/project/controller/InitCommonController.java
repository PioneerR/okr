package com.xc.project.controller;

import com.xc.project.service.InitDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class InitCommonController {

	@Autowired
	InitDataService service;


	@PostMapping("/init-common-data")
	@PostConstruct
	public void initCommonData(){
		//初始化错误信息
		service.initErrorMessage();
		// TODO 省略 初始化码值表

	}





}
