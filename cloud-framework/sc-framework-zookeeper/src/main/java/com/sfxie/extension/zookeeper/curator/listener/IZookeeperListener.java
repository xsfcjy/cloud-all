package com.sfxie.extension.zookeeper.curator.listener;

import org.apache.curator.framework.CuratorFramework;

public interface IZookeeperListener {
    void executor(CuratorFramework client);
} 