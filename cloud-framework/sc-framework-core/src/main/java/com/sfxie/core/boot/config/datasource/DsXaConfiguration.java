package com.sfxie.core.boot.config.datasource;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.sfxie.core.framework.boot.BaseApplicationConfig;

@EnableConfigurationProperties({ JdbcProperties.class })
public class DsXaConfiguration extends BaseApplicationConfig {

	@Resource
	private JdbcProperties jdbcProperties;

	@Bean
	public ServletRegistrationBean druidServlet() {
		return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
	}

	@Bean(name = "businessDataSource", destroyMethod = "close", initMethod = "init")
	public DataSource druidDataSource() {
		DruidXADataSource druidDataSource = new DruidXADataSource();
		druidDataSource.setDriverClassName(jdbcProperties.getDriverClassName());
		druidDataSource.setUrl(jdbcProperties.getUrl());
		druidDataSource.setUsername(jdbcProperties.getUsername());
		druidDataSource.setPassword(jdbcProperties.getPassword());
		try {
			druidDataSource.setFilters("stat, wall");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(druidDataSource);
		xaDataSource.setUniqueResourceName("businessDataSource");
		return xaDataSource;
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
		logger.info("DsXaCrmBusinessConfiguration init");
	}
}
