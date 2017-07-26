package com.sfxie.web.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sfxie.component.ui.ResourceEntity;

/**
 * 前端组件引入配置类
 * @author xiesf
 * @since 2017-07-18
 *
 */
@Configuration
public class ClientResourceConfiguration extends WebMvcConfigurerAdapter {
	
	///jsp/center/organization.jsp页面引入ztree组件
	@Bean
	public ResourceEntity organizationZtreeComponent(){
		return new ResourceEntity()
		.addCssPath("/static/js/ui/zTree_v3/css/zTreeStyle/zTreeStyle.css")
		.addJsPath("/static/js/ui/zTree_v3/js/jquery.ztree.all.min.js")
		.addDescription("使用ztree")
		.addPageUri("/jsp/center/organization/index.jsp");
	}
	
}
