package com.sfxie.component.ui;


import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sfxie.core.framework.core.SpringContext;

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
	
	//引入组织结构所需要的js组件
	@Bean
	public ResourceList resourceList(){
		ResourceList resourceList = new ResourceList();
		
		Map<String, ResourceEntity> map = SpringContext.getBeansOfType(ResourceEntity.class);
		for(Entry<String, ResourceEntity> entity : map.entrySet()){
			resourceList.addResourceEntity(entity.getValue());
		}
		return resourceList;
	}
	    
}
