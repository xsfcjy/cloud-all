package com.sfxie.extension.datasource.multydatasoure.dynamic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

import com.sfxie.extension.datasource.multydatasoure.zookeeper.ZookeeperDatasourceEntity;

/**
 * 多数据源路径服务发现类
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午1:52:25 2016年4月15日
 * @example		
 *
 */
public class MycatDiscovery {
	
	private static  String     PATH ;
	
	private static  String serviceName ;
	
	private static ServiceDiscovery<ZookeeperDatasourceEntity>               serviceDiscovery = null;
	
	public static void initDiscovery(CuratorFramework client,IZookeeperWatchChangeAction action,ZKDatasourceConfig datasourceConfig) throws Exception{
		PATH = datasourceConfig.getMultiDatasourcZkPath();
		serviceName = datasourceConfig.getMultiDatasourcZkServiceName();
        try
        {
//            client.start();
            JsonInstanceSerializer<ZookeeperDatasourceEntity> serializer = new JsonInstanceSerializer<ZookeeperDatasourceEntity>(ZookeeperDatasourceEntity.class);
            serviceDiscovery = ServiceDiscoveryBuilder.builder(ZookeeperDatasourceEntity.class).client(client).basePath(PATH).serializer(serializer).build();
            serviceDiscovery.start();
            watchChange(client,action);
        }
        finally
        {
           /* for ( ServiceProvider<ZookeeperDatasourceEntity> cache : providers.values() )
            {
                CloseableUtils.closeQuietly(cache);
            }

            CloseableUtils.closeQuietly(serviceDiscovery);
            CloseableUtils.closeQuietly(client);*/
        }
	}
	
	public static List<ZookeeperDatasourceEntity> listInstances() throws Exception{
        // This shows how to query all the instances in service discovery
		List<ZookeeperDatasourceEntity>  list = new ArrayList<ZookeeperDatasourceEntity>();
        try
        {
            Collection<String>  serviceNames = serviceDiscovery.queryForNames();
//            System.out.println(serviceNames.size() + " type(s)");
            for ( String sName : serviceNames )
            {
            	if(sName.equals(serviceName)){
            		Collection<ServiceInstance<ZookeeperDatasourceEntity>> instances = serviceDiscovery.queryForInstances(serviceName);
//            		System.out.println(serviceName);
            		for ( ServiceInstance<ZookeeperDatasourceEntity> instance : instances )
            		{
            			list.add(instance.getPayload());
//            			System.out.println(instance.getPayload().getUrl());
            		}
            	}
            }
        }catch(Exception e){e.printStackTrace();}
        finally
        {
//            CloseableUtils.closeQuietly(serviceDiscovery);
        }
        return list;
    }
	public static void watchChange(CuratorFramework client,final IZookeeperWatchChangeAction action){
		/**
         * 监听子节点的变化情况
         */
		@SuppressWarnings("resource")
		final PathChildrenCache childrenCache = new PathChildrenCache(client,PATH+"/"+serviceName, true);
		childrenCache.getListenable().addListener(
				new PathChildrenCacheListener() {

					@Override
					public void childEvent(CuratorFramework client,
							PathChildrenCacheEvent event) throws Exception {
						List<ZookeeperDatasourceEntity> list1 = listInstances();
						action.execute(event.getData().getPath(),event.getType(), list1);
					}
				});
        try {
            childrenCache.start(StartMode.POST_INITIALIZED_EVENT);
        } catch (Exception e) {
        	e.printStackTrace();
//            LoggerUtil.error("Start NodeCache error for path:"+PATH+"/"+serviceName+", error info: "+e.getMessage());
        }
	}
}
