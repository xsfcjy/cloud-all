package com.learn.springcloud.eureka.server;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.springcloud.eureka.server.config.AllMsg;

/**
 * 
 * @author xiesf
 *
 */
@RestController
public class ConfigController {


    @Value("${error.auth.emptyToken}")
    public String emptyToken ;
	@Resource
	public AllMsg allMsg;

    
    @RequestMapping("/getConfigs")
    String getConfigs() {
        return ( "    emptyTime: "+allMsg.getEmptyTime() + "    emptyToken: "+emptyToken);
    }

}
