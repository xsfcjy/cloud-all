package com.sfxie.core.framework.mvc.handle;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 分页组件配置类，只要启用此配置类，即启动mybatis的分页功能<br>
 * 具体的分页方法，请参考com.github.pagehelper开源组件
 * @since 2017-05-01
 * @author xiesf
 *
 */
@Configuration
public class PagerConfigurer extends WebMvcConfigurerAdapter {
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new PageHandlerInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new JsonPHandlerInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);

    }
    
    /**
     * 鉴权Filter
     * @return
     */
    @Bean
    public FilterRegistrationBean authenticateFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        PagerFilter filter = new PagerFilter();
        bean.addUrlPatterns("/*");
        bean.setFilter(filter);
        bean.setOrder(1);
        return bean;
    }
}
