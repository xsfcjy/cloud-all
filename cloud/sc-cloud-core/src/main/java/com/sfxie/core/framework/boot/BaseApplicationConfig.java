package com.sfxie.core.framework.boot;


import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.ApplicationContext;


/**
 * 应用配置基础抽象类<br>
 * 排除了数据源和数据源事务配置项的启动
 * @description: 
 * @author: xiesf
 * @email xsfcy@126.com
 * @date: 2016年8月3日
 * @copyright: Conpyright (c) 2016 共享云平台有限公司 版权所有
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={
//		HibernateJpaAutoConfiguration.class,
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class
})
public abstract class BaseApplicationConfig extends BaseCommandLineRunner{

	
	public static ApplicationContext applicationContext;
	
//	protected List<Class<?>> defaultConfigs = new ArrayList<Class<?>>();

    protected static Logger logger = Logger.getLogger(BaseApplicationConfig.class);
    
    /**
     * 启动服务
     * @param configs
     * @param args
     */
    protected static void startServer(Object[] configs,String [] args){
    	
    	applicationContext = SpringApplication.run(configs, args);  
    }
    /**
     * 获取工程根目录的父目录
     * @return
     */
    protected static String getParentCatalog(){
		String relativelyPath=System.getProperty("user.dir"); 
		return relativelyPath.substring(0, relativelyPath.lastIndexOf(File.separator));
    }
    
}
