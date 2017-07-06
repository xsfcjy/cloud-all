package com.sfxie.web.boot.i18n;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class I18nConfiguration extends WebMvcConfigurerAdapter {
	
	/*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new LocaleChangeInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);

    }*/
//	@Bean
//	public CookieLocaleResolver cookieLocaleResolver(){
//		return new CookieLocaleResolver();
//	}
//	@Bean
//	public SessionLocaleResolver sessionLocaleResolver(){
//		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
//		sessionLocaleResolver.setDefaultLocale(new Locale("zh_CN"));
//		return sessionLocaleResolver;
//	}

//	@Bean
//	public ResourceBundleMessageSource resourceBundleMessageSource(){
//		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
//		resourceBundleMessageSource.addBasenames("/messages");
//		return resourceBundleMessageSource;
//	}
//	
    
}
