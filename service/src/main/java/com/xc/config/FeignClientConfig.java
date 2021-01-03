package com.xc.config;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {
	/**
	 * SpringSecurity
	 *
	 * @author shaper 2020/11/9 16:24
	 */
    @Bean
    public BasicAuthRequestInterceptor getBasicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("admin", "123456");
    }

    /**
     * 指定日志级别
     *
     * @author shaper 2020/11/9 16:25
     */
    @Bean
    public Logger.Level getFeignLoggerLevel() {
        return feign.Logger.Level.FULL ;
    }
}