package com.learn.springcloud.eureka.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 *   @EnableEurekaServer:  此注解表明该服务为一个eureka服务,可以联合多个服务作为集群,对外提供服务注册及发现功能
 */
//@EnableDiscoveryClient
//@SpringBootApplication
//@EnableHystrix
//@EnableHystrixDashboard
//@EnableCircuitBreaker

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
public class FrontServiceServer1 {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FrontServiceServer1.class).web(true).run(args);
    }
    @Bean
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
