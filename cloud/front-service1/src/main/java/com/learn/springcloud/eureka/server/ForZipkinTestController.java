package com.learn.springcloud.eureka.server;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/27
 * Time: 下午5:48
 * CopyRight: taobao
 * Descrption:
 */
@RestController
@RefreshScope
public class ForZipkinTestController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "zipkinAddCallback")
    @RequestMapping(value = "/zipkinAdd/{a}/{b}" ,method = RequestMethod.GET)
    public Integer zipkinAdd(@PathVariable Integer a, @PathVariable Integer b) {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("a", ""+a);
        vars.put("b", ""+b);
        logger.info("request test for zipkin" );
        Integer r = restTemplate.getForObject("http://api-service:7071/add/{a}/{b}", Integer.class,vars);
        return  r;
    }
    
    public Integer zipkinAddCallback( Integer a,  Integer b) {
    	return -1;
    }
    
}
