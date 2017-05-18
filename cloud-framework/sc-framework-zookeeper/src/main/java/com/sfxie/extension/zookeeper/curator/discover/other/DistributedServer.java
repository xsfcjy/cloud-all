package com.sfxie.extension.zookeeper.curator.discover.other;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author: elite_jigang@163.com
 */
public class DistributedServer  implements Closeable {

    private final ServiceDiscovery<DistributedService> serviceDiscovery;
    private final ServiceInstance<DistributedService> thisInstance;

    DistributedServer(CuratorFramework client,
                      String path,
                      String serviceName,
                      String address,
                      String info ) throws Exception {
        // in a real application, you'd have a convention of some kind for the URI layout
        UriSpec uriSpec = new UriSpec("{scheme}://zookeeper.com:{port}");

        thisInstance = ServiceInstance.<DistributedService>builder()
                .name(serviceName)
                .payload(new DistributedService(address, info))
                .port((int) (65535 * Math.random())) // in a real application, you'd use a common port
                .uriSpec(uriSpec)
                .build();

        // if you mark your payload class with @JsonRootName the provided JsonInstanceSerializer will work
        JsonInstanceSerializer<DistributedService> serializer = new
                JsonInstanceSerializer<DistributedService>(DistributedService.class);

        serviceDiscovery = ServiceDiscoveryBuilder.builder(DistributedService.class)
                .client(client)
                .basePath(path)
                .serializer(serializer)
                .thisInstance(thisInstance)
                .build();
    }

    public ServiceInstance<DistributedService> getThisInstance() {
        return thisInstance;
    }

    public void start() throws Exception {
        serviceDiscovery.start();
    }


    @Override
    public void close() throws IOException {
        serviceDiscovery.close();
    }
}