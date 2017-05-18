package com.learn.springcloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/24
 * Time: ����3:22
 * CopyRight: taobao
 * Descrption:
 * ������ͨ��:http://localhost:8888/test/default����
 *
 * http://localhost:8888/fool/daily
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ScConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScConfigApplication.class, args);
    }
    

}
