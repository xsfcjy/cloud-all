package com.sfxie.extension.datasource.multydatasoure.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.sfxie.extension.datasource.multydatasoure.zookeeper.ZookeeperDatasourceEntity;
import com.sfxie.extension.logger.LoggerUtil;
import com.sfxie.extension.logger.level.mail.MailLogger;


/**
 * 动态数据源类
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午1:53:00 2016年4月15日
 * @example		
 *
 */
public class ZKDynamicDataSource extends AbstractRoutingDataSource {  
	
	private IZookeerDataSourceInitor zookeerDataSourceInitor;
	
	private boolean isMail = false;
	
	private static Random random = new Random();
	
	private Map<Object, Object> targetDataSourcesTemp;
	

    private static List<String> targetDataSourcesList;
    
    private CuratorFramework zkClient;
    
    private boolean zookeeperAble = false;
    
    private ZKDatasourceConfig datasourceConfig;
	
	/*private ZookeeperFactoryBean zookeeperFactoryBean;
	
	public ZookeeperFactoryBean getZookeeperFactoryBean() {
		return zookeeperFactoryBean;
	}

	public void setZookeeperFactoryBean(ZookeeperFactoryBean zookeeperFactoryBean) {
		this.zookeeperFactoryBean = zookeeperFactoryBean;
	}*/
    @Override  
    protected Object determineCurrentLookupKey() {
    	List<String> targetSqlSessionFactorysList = getTargetDataSourcesList();
    	int length = targetSqlSessionFactorysList.size();
    	if(length > 0){
    		String lookupKey = targetSqlSessionFactorysList.get(random.nextInt(length));
    		System.out.println(lookupKey);
    		return lookupKey;
    	}
		return null;  
    }  
    
    @Override
	public void afterPropertiesSet() {
		// TODO Auto-generated method stub
		try {
			super.afterPropertiesSet();
			if(zookeeperAble){
				zookeeperInit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void zookeeperInit() throws Exception{
		
    	MycatDiscovery.initDiscovery(getZkClient(), new IZookeeperWatchChangeAction(){
			@Override
			public void execute(String path,Type eventType, List<ZookeeperDatasourceEntity> list1) throws Exception {
				List<ZookeeperDatasourceEntity> list = MycatDiscovery.listInstances();
				switch (eventType) {
					case CHILD_ADDED:
						addSqlSessionFactory(list);
						break;
					case CHILD_REMOVED:
						removeSqlSessionFactory(list);
						break;
					case CHILD_UPDATED:
						break;
					default:
						break;
				}
			}
    		
    	},datasourceConfig);
    }
	private void addSqlSessionFactory(List<ZookeeperDatasourceEntity> list){
//		DruidDataSource
		Map<Object, Object> targetDataSources = getTargetDataSourcesTemp();
		for(ZookeeperDatasourceEntity zkEntity : list){
			if(null==targetDataSources.get(zkEntity.getUniqueResourceName())){
				DataSource ds = zookeerDataSourceInitor.generateDataSource(zkEntity);
				targetDataSources.put(zkEntity.getUniqueResourceName(), ds);
				getTargetDataSourcesList().add(zkEntity.getUniqueResourceName());
				if(isMail){
					MailLogger.mail(ZKDynamicDataSource.class, zkEntity.getUniqueResourceName()+ "数据源已被启用.");
				}
				LoggerUtil.system(ZKDynamicDataSource.class,zkEntity.getUniqueResourceName()+ "数据源已被启用.");
			}
		}
		if(targetDataSources.size()>0){
			this.setTargetDataSources(targetDataSources);
			super.afterPropertiesSet();
		}
	}
	
	
	public static List<String> getTargetDataSourcesList() {
		if(null==targetDataSourcesList){
			targetDataSourcesList = new ArrayList<String>();
		}
		return targetDataSourcesList;
	}

	public static void setTargetDataSourcesList(List<String> targetDataSourcesList) {
		ZKDynamicDataSource.targetDataSourcesList = targetDataSourcesList;
	}

	public Map<Object, Object> getTargetDataSourcesTemp() {
		if(null==targetDataSourcesTemp){
			targetDataSourcesTemp = new HashMap<Object, Object>();
		}
		return targetDataSourcesTemp;
	}

	public void setTargetDataSourcesTemp(Map<Object, Object> targetDataSourcesTemp) {
		this.targetDataSourcesTemp = targetDataSourcesTemp;
	}
	private void removeSqlSessionFactory(List<ZookeeperDatasourceEntity> list){
		List<String> dieMycatList = new ArrayList<String>();
		for(Object key : targetDataSourcesTemp.keySet()){
			boolean dieflag = true;
			for(ZookeeperDatasourceEntity zkEntity : list){
				if(key.equals(zkEntity.getUniqueResourceName())){
					dieflag = false;
				}
			}
			if(dieflag){
				dieMycatList.add(key.toString());
			}
		}
//		synchronized (targetDataSourcesTemp) {
		for(String dieMycat : dieMycatList){
			if(null!=targetDataSourcesTemp.get(dieMycat)){
				targetDataSourcesTemp.remove(dieMycat);
				targetDataSourcesList.remove(dieMycat);
				if(isMail){
					MailLogger.mailError(ZKDynamicDataSource.class, dieMycat+ "数据源已被移除.");
				}
				LoggerUtil.system(ZKDynamicDataSource.class,dieMycat+ "数据源已被移除.");
			}
		}
//		}
	}
	public void setZkClient(CuratorFramework zkClient) {
		this.zkClient = zkClient;
	}
	private CuratorFramework getZkClient(){
		return zkClient;
	}

	/**
	 * 默认数据源生成类为{@link com.sfxie.extension.datasource.multydatasoure.dynamic.DruidDataSourceInitor}
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午1:48:52 2016年4月15日
	 * @example
	 * @return	
	 *
	 */
	public IZookeerDataSourceInitor getZookeerDataSourceInitor() {
		if(null==zookeerDataSourceInitor){
			zookeerDataSourceInitor = new DruidDataSourceInitor();
		}
		return zookeerDataSourceInitor;
	}

	public void setZookeerDataSourceInitor(
			IZookeerDataSourceInitor zookeerDataSourceInitor) {
		this.zookeerDataSourceInitor = zookeerDataSourceInitor;
	}

	public void setMail(boolean isMail) {
		this.isMail = isMail;
	}

	public void setZookeeperAble(boolean zookeeperAble) {
		this.zookeeperAble = zookeeperAble;
	}
}  