package com.sfxie.core.boot.config.mybatis.center;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.sfxie.core.boot.config.datasource.center.DsXaCrmCenterConfiguration;
import com.sfxie.core.framework.boot.BaseApplicationConfig;

@EnableConfigurationProperties({ MybatisCenterProperties.class })
@AutoConfigureAfter(DsXaCrmCenterConfiguration.class)
@MapperScan(basePackages = "com.sfxie.sharecloud.apigateway.authentication.dao.center.mapper", sqlSessionFactoryRef = "centerSqlSessionFactory")
public class MybatisCenterConfiguration extends BaseApplicationConfig {

	@Resource(name = "centerDataSource")
	private DataSource druidDataSource;

	@Resource
	private MybatisCenterProperties mybatisProperties;

	@Bean(name = "centerSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() {
		try {
			SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
			sessionFactory.setDataSource(druidDataSource);
			sessionFactory.setTypeAliasesPackage(mybatisProperties
					.getTypeAliasesPackage());
			sessionFactory
					.setMapperLocations(new PathMatchingResourcePatternResolver()
							.getResources(mybatisProperties
									.getMapperLocations()));
			sessionFactory.setConfigLocation(new DefaultResourceLoader()
					.getResource(mybatisProperties.getConfigLocation()));

			return sessionFactory.getObject();
		} catch (Exception e) {
			logger.warn("Could not confiure mybatis session factory");
			return null;
		}
	}

	// @Bean
	// @ConditionalOnMissingBean
	// public DataSourceTransactionManager transactionManager() {
	// return new DataSourceTransactionManager(druidDataSource);
	// }

	@Override
	public void run(String... arg0) throws Exception {
		logger.info("MybatisCenterConfiguration init");

	}
}
