package com.sfxie.extension.zookeeper.curator;

import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.sfxie.extension.logger.LoggerUtil;
import com.sfxie.extension.zookeeper.curator.listener.IZookeeperListener;

public class ZookeeperFactoryBean implements  InitializingBean, DisposableBean {

    private CuratorFramework zkClient;

    //设置Zookeeper启动后需要调用的监听或者，或者需要做的初始化工作。
    public void setListeners(List<IZookeeperListener> listeners) {
        this.listeners = listeners;
    }

    private List<IZookeeperListener> listeners;

    public CuratorFramework getObject() {
    	return zkClient;
//        try {
//			return zkClient.getObject();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//        return null;
    }

    @Override
    public void destroy() throws Exception {
    	getObject().close();
    }

    //创建ZK链接
    @Override
    public void afterPropertiesSet(){
//        try {
			registerListeners(zkClient);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    	return zkClient;
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

    //注册需要监听的监听者对像.
    private void registerListeners(CuratorFramework client){
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                LoggerUtil.system(LoggerUtil.class, "CuratorFramework state changed: "+newState);
                if(newState == ConnectionState.CONNECTED || newState == ConnectionState.RECONNECTED){
                    for(IZookeeperListener listener : listeners){
                        listener.executor(client);
                        LoggerUtil.system(LoggerUtil.class,"Listener "+listener.getClass().getName()+" executed!");
                    }
                }
            }
        });

        client.getUnhandledErrorListenable().addListener(new UnhandledErrorListener() {
            @Override
            public void unhandledError(String message, Throwable e) {
            	LoggerUtil.system(LoggerUtil.class,"CuratorFramework unhandledError: "+message+"");
            }
        });
    }

	public void setZkClient(CuratorFramework zkClient) {
		this.zkClient = zkClient;
	}
    
    
} 
