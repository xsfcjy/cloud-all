package com.sfxie.component.ui;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 系统UI资源加载器
 * @author xiesf
 * @since 2017-05-12
 *
 */
@Configuration
public class UIConfiguration {

	
	@Bean
	public ResourceLoader resourceLoader(ResourceList resourceList){
		ResourceLoader resourceLoader = new ResourceLoader();
		resourceLoader.setResourceList(resourceList);
		return resourceLoader;
	}
}
