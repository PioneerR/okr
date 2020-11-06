package com.xc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.xc.mapper")
public class RelationApplication2 {

    public static void main(String[] args) {
        SpringApplication.run(RelationApplication2.class,args);
    }
}