package com.sfxie.extension.datasource.mycat;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.pool.DruidDataSource;
import com.sfxie.core.boot.config.datasource.DsConfiguration;
import com.sfxie.core.boot.config.datasource.JdbcProperties;


@EnableConfigurationProperties({ JdbcMycatProperties.class })
public class DsMycatConfiguration extends DsConfiguration {

	@Resource
	private JdbcMycatProperties jdbcProperties;

	@Override
	public JdbcProperties getJdbcProperties() {
		return jdbcProperties;
	}
	@Bean(name = "mycatDataSource", destroyMethod = "close", initMethod = "init")
	public DataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		
		JdbcMycatProperties jdbcMycatProperties = (JdbcMycatProperties) getJdbcProperties();
		druidDataSource.setDriverClassName(jdbcMycatProperties.getDriverClassName());
		druidDataSource.setUrl(jdbcMycatProperties.getUrl());
		druidDataSource.setUsername(jdbcMycatProperties.getUsername());
		druidDataSource.setPassword(jdbcMycatProperties.getPassword());
		
		 

		druidDataSource.setTimeBetweenEvictionRunsMillis(jdbcMycatProperties.getTimeBetweenEvictionRunsMillis());
		druidDataSource.setMinEvictableIdleTimeMillis(jdbcMycatProperties.getMinEvictableIdleTimeMillis());
		druidDataSource.setMaxActive(jdbcMycatProperties.getMaxActive());
		druidDataSource.setInitialSize(jdbcMycatProperties.getInitialSize());
		druidDataSource.setTransactionQueryTimeout(jdbcMycatProperties.getTransactionQueryTimeout());
//		druidDataSource.setMaxIdle(jdbcMycatProperties.getMaxIdle());
		druidDataSource.setMinIdle(jdbcMycatProperties.getMinIdle());
		druidDataSource.setQueryTimeout(jdbcMycatProperties.getQueryTimeout());
		try {
			druidDataSource.setFilters("stat, wall");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return druidDataSource;
	}
}
