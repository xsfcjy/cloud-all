package com.sfxie.sharecloud.apigateway.authentication.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.sfxie.sharecloud.apigateway.authentication.properties.msg.CodeErrorAuth;
import com.sfxie.sharecloud.apigateway.authentication.properties.msg.MsgErrorAuth;

@Configuration
@EnableConfigurationProperties({ MsgErrorAuth.class ,CodeErrorAuth.class })
public class PropertiesConfiguration {
}
