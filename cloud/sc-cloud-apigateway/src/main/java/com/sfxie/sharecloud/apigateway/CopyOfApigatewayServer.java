package com.sfxie.sharecloud.apigateway;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.netflix.zuul.context.ContextLifecycleFilter;
import com.netflix.zuul.http.ZuulServlet;
import com.sfxie.core.boot.config.datasource.business.DsCrmBusinessConfiguration;
import com.sfxie.core.boot.config.mybatis.business.MybatisBusinessConfiguration;
import com.sfxie.core.framework.boot.BaseApplicationConfig;
import com.sfxie.notuse.ChangeHostsServerDynIp;
import com.sfxie.sharecloud.apigateway.authentication.properties.PropertiesConfiguration;
import com.sfxie.sharecloud.apigateway.zuul.filter.AccessFilter;
import com.sfxie.sharecloud.apigateway.zuul.filter.ErrorFilter;
import com.sfxie.sharecloud.apigateway.zuul.filter.RoutingFilter;

/**
 * Created by IntelliJ IDEA. User: chengjing Date: 16/7/18 Time: 下午3:17
 * CopyRight: taobao Descrption:
 */

@EnableDiscoveryClient
@EnableZuulProxy
@EnableTransactionManagement
@ComponentScan(basePackages = { 
		"com.sfxie.core.framework",
		"com.sfxie.core.framework.core",
		"com.sfxie.core.framework.mvc.controller" ,
		"com.sfxie.sharecloud.apigateway.authentication"
		})
@EnableAutoConfiguration
@RefreshScope
public class CopyOfApigatewayServer extends BaseApplicationConfig {
	@Override
	public void run(String... arg0) throws Exception {
	}

	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
	
	@Bean
	public RoutingFilter routingFilter() {
		return new RoutingFilter();
	}
	
	@Bean
	public ErrorFilter errorFilter(){
		return new ErrorFilter();
	}
	
	@Bean
	public ServletRegistrationBean zuulServlet() {
		ServletRegistrationBean servlet = new ServletRegistrationBean(
				new ZuulServlet());
		servlet.addUrlMappings("/springboot");
		return servlet;
	}

	@Bean
	public FilterRegistrationBean contextLIfecycleFilter() {
		FilterRegistrationBean filter = new FilterRegistrationBean(
				new ContextLifecycleFilter());
		filter.addUrlPatterns("/*");
		return filter;
	}

	/**
	 * 启动服务
	 * 
	 * @param configs
	 * @param args
	 */
	protected static void startServer(Object[] configs, String[] args) {
//		applicationContext = SpringApplication.run(ApigatewayServer.class, args);
		applicationContext = new SpringApplicationBuilder(configs).web(true).run(args);
		
	}

	public static void main(String[] args) {
		//打包时需要移除
		changeIp();
		startServer(new Object[] { CopyOfApigatewayServer.class, PropertiesConfiguration.class,
				DsCrmBusinessConfiguration.class,
				MybatisBusinessConfiguration.class,
				ChangeHostsServerDynIp.class,
				// DsCrmCenterConfiguration.class,
				// MybatisCenterConfiguration.class,
				DataSourceTransactionManagerAutoConfiguration.class }, args);
	}
	

}
