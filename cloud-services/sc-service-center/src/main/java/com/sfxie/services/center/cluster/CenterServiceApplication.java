package com.sfxie.services.center.cluster;

import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.sfxie.core.framework.boot.BaseApplicationConfig;
import com.sfxie.core.framework.mvc.handle.PagerConfigurer;
import com.sfxie.extension.datasource.mycat.DsMycatConfiguration;
import com.sfxie.services.center.config.MybatisCenterConfiguration;

/**
 * 
 * @author sfxie
 * @since 2017-04-15
 * 启动时请打开pom.xml文件的spring-cloud-starter-bus-amqp模块依赖<br>
 * 启动时请打开bootstrap.properties文件的配置项<br>
 *
 */
@EnableDiscoveryClient
@EnableTransactionManagement
@ComponentScan(basePackages = { 
		"com.sfxie.core.framework",
		"com.sfxie.core.framework.core",
		"com.sfxie.core.framework.mvc.controller" ,
		"com.sfxie.services.center"
		})
@RefreshScope
public class CenterServiceApplication extends BaseApplicationConfig {
	@Override
	public void run(String... arg0) throws Exception {
	}


	/**
	 * 启动服务
	 * 
	 * @param configs
	 * @param args
	 */
	protected static void startServer(Object[] configs, String[] args) {
		applicationContext = new SpringApplicationBuilder(configs).web(true).run(args);
	}

	public static void main(String[] args) {
		changeIp();
		startServer(new Object[] { 
				CenterServiceApplication.class,
				PagerConfigurer.class,
				DsMycatConfiguration.class,
				MybatisCenterConfiguration.class,
				DataSourceTransactionManagerAutoConfiguration.class }, args);
	}

}
