package com.sfxie.extension.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class CuratorFrameworkZk implements FactoryBean<CuratorFramework>, InitializingBean{

	private String zkConnectionString;
	
	private CuratorFramework zkClient;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		//1000 是重试间隔时间基数，3 是重试次数
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        zkClient = createWithOptions(zkConnectionString, retryPolicy, 20000, 10000);
        zkClient.start();
	}

	@Override
	public CuratorFramework getObject() throws Exception {
		// TODO Auto-generated method stub
		return zkClient;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return CuratorFramework.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getZkConnectionString() {
		return zkConnectionString;
	}

	public void setZkConnectionString(String zkConnectionString) {
		this.zkConnectionString = zkConnectionString;
	}
	
	/**
     * 通过自定义参数创建
     */
    public CuratorFramework  createWithOptions(String connectionString, RetryPolicy retryPolicy, int connectionTimeoutMs, int sessionTimeoutMs)
    {
        return CuratorFrameworkFactory.builder()
                .connectString(connectionString)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                .build();
    }   

}
