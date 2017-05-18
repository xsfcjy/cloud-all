package com.sfxie.core.framework.core;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware , InitializingBean {

	private static SpringContext instance;
	
	public static SpringContext getInstance(){
		if(null==instance)
			instance = new SpringContext();
		return instance;
	}

	private ApplicationContext webApplicationContext;

	@Override
	public void setApplicationContext(ApplicationContext webApplicationContext)
			throws BeansException {
		this.webApplicationContext = webApplicationContext;
	}

	public SpringContext() {
		instance = this;
	}

	/**
	 * 根据给出的beanId来获取在Spring当中配置的bean
	 * 
	 * @param beanId
	 *            给出的beanId
	 * @return 返回找到的bean对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSpringBean(String beanId) {
		return (T) getWebApplicationContext().getBean(beanId);
	}

	/**
	 * 获取bean
	 * 
	 * @param clsName
	 * @return
	 */
	public static Object getBeanByName(String clsName) {
		return instance.webApplicationContext.getBean(clsName);
	}

	/**
	 * 获取bean
	 * 
	 * @param cls
	 * @return
	 */
	public static <T> Map<String, T> getBeansOfType(Class<T> cls) {
		return instance.webApplicationContext.getBeansOfType(cls);
	}

	/**
	 * 获取bean
	 * 
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBeanByClass(Class<?> cls) {
		return (T) instance.webApplicationContext.getBean(cls);
	}

	public static ApplicationContext getWebApplicationContext() {
		return instance.webApplicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(111);
	}
}
