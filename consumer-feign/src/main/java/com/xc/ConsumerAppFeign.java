package com.xc;

import com.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "RELATION-PRO", configuration = RibbonConfig.class)
@EnableFeignClients("com.xc.service")
public class ConsumerAppFeign {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerAppFeign.class,args);
    }
}