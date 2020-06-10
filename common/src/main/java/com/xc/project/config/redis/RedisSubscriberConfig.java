package com.xc.project.config.redis;

import com.xc.common.config.redis.RedisKey;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 消息订阅配置类
 *
 * @author shaper 2020/6/10 22:29
 */
@Configuration
@AutoConfigureAfter(RedisReciver.class)
public class RedisSubscriberConfig {

	@Bean
	public MessageListenerAdapter messageListenerAdapter(RedisReciver redisReciver){
		return new MessageListenerAdapter(redisReciver,"onMessage");
	}

	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer(
			RedisConnectionFactory factory, MessageListenerAdapter adapter){
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(factory);
		container.addMessageListener(adapter, new PatternTopic(RedisKey.ERROR_MESSAGE));
		return container;
	}
}
