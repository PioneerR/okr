package com.xc;

import com.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
//@EnableEurekaClient
@RibbonClient(name = "RELATION-PRO", configuration = RibbonConfig.class)
public class ConsumerAppRibbon {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerAppRibbon.class,args);
    }
}