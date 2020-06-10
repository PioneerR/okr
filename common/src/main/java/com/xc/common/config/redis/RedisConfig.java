package com.xc.common.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {

	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory){
		return new StringRedisTemplate(factory);
	}

	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		serializer.setObjectMapper(mapper);
		template.setValueSerializer(serializer);

		StringRedisSerializer redisSerializer = new StringRedisSerializer();
		// 使用StringRedisSerializer来序列化和反序列化redis的key值
		template.setKeySerializer(redisSerializer);
		template.setValueSerializer(redisSerializer);
		// hash的key也采用String的序列化方式
		template.setHashKeySerializer(redisSerializer);
		// hash的value序列化方式采用jackson
		template.setHashValueSerializer(serializer);
		template.afterPropertiesSet();
		return template;
	}

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory){
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
		// 解决查询缓存转换异常的问题
		serializer.setObjectMapper(mapper);
		// 配置序列化(解决乱码的问题)
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
		//失效时间
		config.entryTtl(Duration.ZERO);
		config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer));
		config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));
		config.disableCachingNullValues();

		RedisCacheManager manager = RedisCacheManager.builder(factory).cacheDefaults(config).build();
		return manager;
	}




}
