package com.sfxie.extension.datasource.multydatasoure.dynamic;

import javax.sql.DataSource;

import com.sfxie.extension.datasource.multydatasoure.zookeeper.ZookeeperDatasourceEntity;
/**
 * 数据源生成接口
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午1:49:42 2016年4月15日
 * @example		
 *
 */
public interface IZookeerDataSourceInitor {

	/**
	 * 生成具体的数据源:如生成{@link com.alibaba.druid.pool.DruidDataSource}或{@link com.mchange.v2.c3p0.ComboPooledDataSource}
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午1:49:56 2016年4月15日
	 * @example
	 * @param zkEntity
	 * @return	
	 *
	 */
	public DataSource generateDataSource(ZookeeperDatasourceEntity zkEntity);
}
