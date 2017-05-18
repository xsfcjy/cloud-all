package com.sfxie.extension.zookeeper.curator.selection;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.EnsurePath;

import com.sfxie.extension.logger.LoggerUtil;

public class LeaderListener3 {
    public static final String C_PATH = "/TestLeader";
    public static final String CHARSET = "UTF-8";
    public static final String APP_NAME = "app3";

    public static void excute(){
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
                        CuratorFramework client = CuratorFrameworkFactory.newClient(LLTest.connectionString, retryPolicy);
                        client.start();

                        //ensure path of /test
                        new EnsurePath(C_PATH).ensure(client.getZookeeperClient());

                        final LeaderSelector leaderSelector = new LeaderSelector(client, C_PATH, new LeaderSelectorListener() {
                            @Override
                            public void takeLeadership(CuratorFramework client) throws Exception {
                                try {
                                    int timeMilliSeconds = 6000;
                                    LoggerUtil.system(this.getClass(),"======" + APP_NAME + " take leader ship, will do some task, will hold time milli seconds=" + timeMilliSeconds);

                                    for(int i = 0; i < 6; i++){
                                        LoggerUtil.system(this.getClass(),"===" + APP_NAME + " sleep " + i);
                                        Thread.sleep(1000);
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void stateChanged(CuratorFramework client, ConnectionState newState) {

                            }
                        });

                        leaderSelector.start();

                        Thread.sleep(Integer.MAX_VALUE);
                        client.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}