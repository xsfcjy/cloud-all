package com.sfxie.context;


import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;




/**
 * uxun security框架上下文环境保存器.<br/>
 * @author XIESHENGFENG 
 * @since 2013-06-21
 */
public class SpringContextHolder  implements ServletContextAware,ApplicationContextAware,InitializingBean{
 
	private ApplicationContext webApplicationContext;
	
	private static SpringContextHolder instance;
	
	
	private SpringContextHolder() {
		instance = this;
	}
	/** 保存所有extjs4的i18n信息 */
//	private List<EntityI18NProperties> entityI18NProperties = new ArrayList<EntityI18NProperties>();
	/** 保存servletContext */
	private ServletContext servletContext;
	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// 仅服务于web环境.
		this.webApplicationContext = applicationContext;
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
	 * @param clsName
	 * @return
	 */
	public static Object getBeanByName(String clsName){
		return instance.webApplicationContext.getBean(clsName);
	}
	/**
	 * 获取bean
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getBeansOfType(Class cls){
		return instance.webApplicationContext.getBeansOfType(cls);
	}
	/**
	 * 获取bean
	 * @param cls
	 * @return
	 */
	public static Object getBeanByClass(Class<?> cls){
		return instance.webApplicationContext.getBean(cls);
	}
	
	public static ApplicationContext getWebApplicationContext() {
		return instance.webApplicationContext;
	}
	
	/**
	 * 系统启动时执行
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
	}
	@Override
	public void setServletContext(ServletContext sc) {
		this.servletContext = sc;
	}
	
	public static ServletContext getServletContext(){
		return instance.servletContext;
	}
}
