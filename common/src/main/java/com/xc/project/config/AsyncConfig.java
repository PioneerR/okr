package com.xc.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// 核心线程数：是初始化线程数，线程池维护的最少线程数
		executor.setCorePoolSize(2);
		// 线程空闲时间(单位：秒 默认60)，线程空闲时间超过这个时间，线程就被销毁。
		// 当前总线程数超过核心线程数，多出来的线程肯定全销毁
		// 核心线程数内的线程超时是否销毁是根据下面的setAllowCoreThreadTimeOut来确定
		executor.setKeepAliveSeconds(120);
		// 核心线程空闲时间超过设定的时间时，是否回收线程，默认false，这里为true，没有任务时不占用系统线程资源
		executor.setAllowCoreThreadTimeOut(true);
		// 线程池最大线程数，只有缓冲队列满了以后才会创建新线程
		executor.setMaxPoolSize(8);
		// 缓冲队列：队列允许的最大待执行任务数，队列未满时，只使用核心线程处理任务
		// 队列满后，会创建新线程执行任务，但线程数不会超过线程池最大数，所以不宜过大，否则永远只使用核心的几个线程执行
		// 缓冲队列最大数，即等执行的任务数
		executor.setQueueCapacity(200);

		// 给线程名称设置前缀
		executor.setThreadNamePrefix("ConfirmThread-");
		executor.initialize();
		return executor;
	}






}
