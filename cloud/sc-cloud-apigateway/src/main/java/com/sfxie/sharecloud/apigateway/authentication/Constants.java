package com.sfxie.sharecloud.apigateway.authentication;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "constants")
@RefreshScope
public class Constants {

	/** 接口访问有效时间(单位：秒) */
	private int accessTimeout;

	public int getAccessTimeout() {
		return accessTimeout;
	}

	public void setAccessTimeout(int accessTimeout) {
		this.accessTimeout = accessTimeout;
	}

}
