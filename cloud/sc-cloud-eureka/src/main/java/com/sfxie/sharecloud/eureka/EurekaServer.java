package com.sfxie.sharecloud.eureka;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *  此模块代表一个注册管理器,即eureka的服务器,服务启动时会自动注册自己到eureka服务器,每一个服务都有一个名字,
 *  这个名字会被注册到eureka服务器.使用服务的一方只需要使用该名字加上方法名就可以调到服务.
 *  访问:http://localhost:8761/ 可以看到注册中心后台
 */
@EnableEurekaServer
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaServer {

    public static void main(String[] args) throws UnknownHostException {
    	InetAddress netAddress = getInetAddress();  
        System.out.println("host ip:" + getHostIp(netAddress));  
        System.out.println("host name:" + getHostName(netAddress));  
        new SpringApplicationBuilder(EurekaServer.class).web(true).run(args);
    }

    
    public static InetAddress getInetAddress() throws UnknownHostException{
    	return InetAddress.getLocalHost(); 
    }
    
    public static String getHostIp(InetAddress netAddress){  
        if(null == netAddress){  
            return null;  
        }  
        String ip = netAddress.getHostAddress(); //get the ip address  
        return ip;  
    }  
  
    public static String getHostName(InetAddress netAddress){  
        if(null == netAddress){  
            return null;  
        }  
        String name = netAddress.getHostName(); //get the host address  
        return name;  
    }  
}
