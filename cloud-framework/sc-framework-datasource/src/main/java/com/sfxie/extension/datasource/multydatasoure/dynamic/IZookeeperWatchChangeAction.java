package com.sfxie.extension.datasource.multydatasoure.dynamic;

import java.util.List;

import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type;

import com.sfxie.extension.datasource.multydatasoure.zookeeper.ZookeeperDatasourceEntity;

/**
 * 多数据源路径监听接口
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午1:51:44 2016年4月15日
 * @example		
 *
 */
public interface IZookeeperWatchChangeAction {

	public void execute(String path,Type eventType,List<ZookeeperDatasourceEntity> list1) throws Exception;
}
