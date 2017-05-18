package com.sfxie.sharecloud.apigateway.zuul.util;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class BaseDo implements Serializable {

	protected static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
