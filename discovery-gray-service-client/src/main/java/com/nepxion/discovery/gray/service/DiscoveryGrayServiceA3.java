package com.nepxion.discovery.gray.service;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.nepxion.discovery.gray.service.strategy.DiscoveryGrayEnabledStrategy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class DiscoveryGrayServiceA3 {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "a3");

        new SpringApplicationBuilder(DiscoveryGrayServiceA3.class).run(args);
    }

    @Bean
    public DiscoveryGrayEnabledStrategy serviceEnabledStrategy() {
        return new DiscoveryGrayEnabledStrategy();
    }
}