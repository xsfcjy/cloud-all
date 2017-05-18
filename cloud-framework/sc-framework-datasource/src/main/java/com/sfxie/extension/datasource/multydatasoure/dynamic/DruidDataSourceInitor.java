package com.sfxie.extension.datasource.multydatasoure.dynamic;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.sfxie.extension.datasource.multydatasoure.zookeeper.ZookeeperDatasourceEntity;

/**
 * {@link com.alibaba.druid.pool.DruidDataSource}数据源生成实现类
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午1:53:49 2016年4月15日
 * @example		
 *
 */
public class DruidDataSourceInitor implements IZookeerDataSourceInitor {

	@Override
	public DataSource generateDataSource(ZookeeperDatasourceEntity zkEntity) {
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName(zkEntity.getDriverClassName());
		ds.setUrl(zkEntity.getUrl());
		ds.setUsername(zkEntity.getUserName());
		ds.setPassword(zkEntity.getPassword());
		ds.setMaxActive(20);
		ds.setInitialSize(1);
		ds.setMaxWait(2000);
		ds.setMinIdle(3);
		ds.setRemoveAbandoned(true);
		ds.setRemoveAbandonedTimeout(180);
		ds.setConnectionProperties("clientEncoding=UTF8");
		ds.setValidationQuery("SELECT 'x'");
		try {
			ds.setFilters("stat,log4j");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

}
