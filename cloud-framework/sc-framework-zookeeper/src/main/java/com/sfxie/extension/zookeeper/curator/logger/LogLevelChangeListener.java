package com.sfxie.extension.zookeeper.curator.logger;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sfxie.extension.logger.LoggerUtil;
import com.sfxie.extension.zookeeper.curator.listener.IZookeeperListener;

/**
 * 利用zookeeper动态改变log4j的根元素(root)的级别
 * 
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午4:20:46 2016年4月7日
 * @example	
 	在zookeeper中创建并且设置znode和data
 	create /logger/level/change dubug
	set /logger/level/change dubug
 *
 */
public class LogLevelChangeListener implements IZookeeperListener {

    //获取logback实例
    private String path;

    //Logback日志级别ZNode
    public LogLevelChangeListener(String path) {
        this.path = path;
    }

    @Override
    public void executor(CuratorFramework client) {

        //使用Curator的NodeCache来做ZNode的监听，不用我们自己实现重复监听
        final NodeCache cache = new NodeCache(client, path);
        cache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {

                byte[] data = cache.getCurrentData().getData();

                //设置日志级别
                if (data != null) {
                    String level = new String(data);
                    try{
                    	  
                    	Logger logger = LogManager.getRootLogger();
	                    Level newLevel = Level.toLevel(level.toUpperCase());
	                    logger.setLevel(newLevel);
	                    LoggerUtil.system(this.getClass(), "Setting "+logger.getName()+" new level to :" + newLevel.toString());
	                    /*for(Enumeration<Logger> e=LogManager.getCurrentLoggers();e.hasMoreElements();){
                    		Level newLevel = Level.toLevel(level.toUpperCase());
                    		Logger logger = e.nextElement();
                    		logger.setLevel(newLevel);
                    		System.out.println();
                    	}*/
                    }catch(Exception e){
                    	e.printStackTrace();
                    }
                }
            }
        });
        try {
            cache.start(true);
        } catch (Exception e) {
            LoggerUtil.error("Start NodeCache error for path:"+path+", error info: "+e.getMessage());
        }
    }
} 
