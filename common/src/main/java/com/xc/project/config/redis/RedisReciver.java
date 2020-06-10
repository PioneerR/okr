package com.xc.project.config.redis;

import com.xc.common.config.redis.RedisKey;
import com.xc.project.consts.CommonErrMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Hashtable;

/**
 * 接收消息并处理的类
 *
 * @author shaper 2020/6/10 22:07
 */
@Component
public class RedisReciver implements MessageListener {

	@Autowired
	StringRedisTemplate template;
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Override
	public void onMessage(Message message, byte[] pattern) {
		RedisSerializer<String> serializer = template.getStringSerializer();
		// 收到消息
		String msg = serializer.deserialize(message.getBody());
		System.out.println("========= 收到消息  =========" + msg);

		// 消息频道
		String channel = serializer.deserialize(message.getChannel());
		// 不同频道对应不同的处理逻辑
		switch (channel){
			case RedisKey.ERROR_MESSAGE:
				Hashtable errorTable = (Hashtable<String, String>)redisTemplate.opsForValue().get(RedisKey.ERROR_MESSAGE);
				CommonErrMsg.errorTable = errorTable;
				break;
			default:
				break;
		}
	}


}
