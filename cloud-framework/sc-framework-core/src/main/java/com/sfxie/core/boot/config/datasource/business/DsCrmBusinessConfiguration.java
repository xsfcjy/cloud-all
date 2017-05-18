package com.sfxie.core.boot.config.datasource.business;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.sfxie.core.framework.boot.BaseApplicationConfig;

@EnableConfigurationProperties({ JdbcCrmBusinessProperties.class })
public class DsCrmBusinessConfiguration extends BaseApplicationConfig {

	@Resource
	private JdbcCrmBusinessProperties jdbcProperties;

	@Bean
	public ServletRegistrationBean druidServlet() {
		return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
	}

	@Bean(name = "businessDataSource", destroyMethod = "close", initMethod = "init")
	public DataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(jdbcProperties.getDriverClassName());
		druidDataSource.setUrl(jdbcProperties.getUrl());
		druidDataSource.setUsername(jdbcProperties.getUsername());
		druidDataSource.setPassword(jdbcProperties.getPassword());
		try {
			druidDataSource.setFilters("stat, wall");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return druidDataSource;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions",
				"*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("run");
	}
}
