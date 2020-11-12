package com.xc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.xc.mapper")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker	//熔断机制
public class RelationApplicationHystrixConfig {

    public static void main(String[] args) {
        SpringApplication.run(RelationApplicationHystrixConfig.class,args);
    }
}