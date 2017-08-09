package com.sfxie.services.center.local;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.sfxie.component.ui.tags.report.ReportServerConfiguration;
import com.sfxie.component.ui.tags.report.netty.server.ReportWebSocketServer;
import com.sfxie.core.framework.boot.BaseApplicationConfig;
import com.sfxie.core.framework.mvc.handle.PagerConfigurer;
import com.sfxie.extension.datasource.mycat.DsMycatConfiguration;
import com.sfxie.services.center.config.MybatisCenterConfiguration;

/**
 * 
 * @author sfxie
 * @since 2017-04-15
 * 启动时请注释掉pom.xml文件的spring-cloud-starter-bus-amqp模块依赖<br>
 * 启动时请注释掉bootstrap.properties文件的配置项<br>
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = { 
		"com.sfxie.core.framework",
		"com.sfxie.core.framework.core",
		"com.sfxie.core.framework.mvc.controller" ,
		"com.sfxie.services.center",
		"com.sfxie.web.tag",
		"com.sfxie.web.ui"
		})
@EnableAutoConfiguration(exclude={
//		HibernateJpaAutoConfiguration.class,
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		XADataSourceAutoConfiguration.class/*,
		EurekaClientAutoConfiguration.class,
		EurekaClientConfigServerAutoConfiguration.class,
		ConfigClientAutoConfiguration.class,
		ConfigServiceBootstrapConfiguration.class,
		DiscoveryClientConfigServiceBootstrapConfiguration.class,
		CloudAutoConfiguration.class,
		HystrixAutoConfiguration.class,
		BusAutoConfiguration.class,
		RabbitBootstrapConfiguration.class,
		RabbitServiceAutoConfiguration.class,
		RabbitMessageChannelBinderConfiguration.class,
		ChannelBindingAutoConfiguration.class,
		RabbitBootstrapConfiguration.class,
		RabbitMessageChannelBinderConfiguration.class,
		RabbitAutoConfiguration.class,
		MessageSourceAutoConfiguration.class,
		AuditAutoConfiguration.class,
		JmsAutoConfiguration.class,
		JndiConnectionFactoryAutoConfiguration.class,
		EndpointAutoConfiguration.class,
		EndpointWebMvcAutoConfiguration.class*/
})
public class CenterServiceApplication extends BaseApplicationConfig {
	@Override
	public void run(String... arg0) throws Exception {
		new ReportWebSocketServer().start();
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
		startServer(new Object[] { 
				CenterServiceApplication.class,
				PagerConfigurer.class,
				ReportServerConfiguration.class,
				DsMycatConfiguration.class,
				MybatisCenterConfiguration.class,
				DataSourceTransactionManagerAutoConfiguration.class }, args);
	}

}
