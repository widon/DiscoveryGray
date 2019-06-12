package com.nepxion.discovery.gray.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



/**
 * @author Michael (yidongnan@gmail.com)
 * @since 2016/11/8
 */
//@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class GrpcServerApplicationG1 {

    public static void main(String[] args) {
    	System.setProperty("spring.profiles.active", "g1");
        SpringApplication.run(GrpcServerApplicationG1.class, args);
//        new SpringApplicationBuilder(DiscoveryGrayServiceA1.class).run(args);
    }
    
    
   


}
