package com.xc.project.service;

import com.xc.common.config.redis.RedisKey;
import com.xc.common.entity.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Service
public class InitDataService {

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Async
	public void initErrorMessage(){
		List<ErrorMessage> messages = listErrorMsg();
		Hashtable<String, String> errorTable = new Hashtable<>();
		for (ErrorMessage message : messages) {
			String errorCode = String.valueOf(message.getErrorCode());
			errorTable.put(errorCode, message.getErrorMsg());
		}
		redisTemplate.opsForValue().set(RedisKey.ERROR_MESSAGE, errorTable);

		// 向队列发布消息
		stringRedisTemplate.convertAndSend(RedisKey.ERROR_MESSAGE, "更新 errorCode 信息");
	}

	public List<ErrorMessage> listErrorMsg() {
		//TODO 省略步骤

		return new ArrayList<>();
	}
}
