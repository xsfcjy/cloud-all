package com.sfxie.extension.zookeeper.curator.test;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import locking.ExampleClientThatLocks;
import locking.FakeLimitedResource;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

public class TestCuratorLock {
	
	public static String PATH = "/examples/locks/goods/1";
	
	 private static final int        QTY = 10;
	    private static final int        REPETITIONS = QTY * 1000000;


	    public static void main(String[] args) throws Exception
	    {
	        // all of the useful sample code is in ExampleClientThatLocks.java

	        // FakeLimitedResource simulates some external resource that can only be access by one process at a time
	        final FakeLimitedResource   resource = new FakeLimitedResource();

	        ExecutorService             service = Executors.newFixedThreadPool(QTY);
	        final TestingServer         server = new TestingServer();
	        try
	        {
	            for ( int i = 0; i < QTY; ++i )
	            {
	                final int       index = i;
	                Callable<Void>  task = new Callable<Void>()
	                {
	                    @Override
	                    public Void call() throws Exception
	                    {
	                        CuratorFramework        client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
	                        try
	                        {
	                            client.start();

	                            ExampleClientThatLocks      example = new ExampleClientThatLocks(client, PATH, resource, "Client " + index);
	                            for ( int j = 0; j < REPETITIONS; ++j )
	                            {
	                                example.doWork(10, TimeUnit.SECONDS);
	                            }
	                        }
	                        catch ( InterruptedException e )
	                        {
	                            Thread.currentThread().interrupt();
	                        }
	                        catch ( Exception e )
	                        {
	                            e.printStackTrace();
	                            // log or do something
	                        }
	                        finally
	                        {
	                            CloseableUtils.closeQuietly(client);
	                        }
	                        return null;
	                    }
	                };
	                service.submit(task);
	            }

	            service.shutdown();
	            service.awaitTermination(10, TimeUnit.MINUTES);
	        }
	        finally
	        {
	            CloseableUtils.closeQuietly(server);
	        }
	    }
	
	/*public static void main(String[] args) {
		String servers = "192.168.23.4:2181";
	    CuratorFramework curator = CuratorFrameworkFactory.builder().retryPolicy(new ExponentialBackoffRetry(10000, 3)).connectString(servers).build();
	    curator.start();
	    final InterProcessMutex lock = new InterProcessMutex(curator, PATH);

	    Executor pool = Executors.newFixedThreadPool(10);
	    for (int i = 0; i < 10; i ++) {
	        pool.execute(new Runnable() {
	            public void run() {
	                try {
	                    lock.acquire();
	                    System.out.println(Thread.currentThread().getName());
	                    TimeUnit.SECONDS.sleep(5);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }finally{
	                    try {
	                        lock.release();
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        });
	    }
	}*/

	/*public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		CountDownLatch latch = new CountDownLatch(5);

		String zookeeperConnectionString = "192.168.23.4:2181";
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
		client.start();
		System.out.println("客户端启动。。。。");
		ExecutorService exec = Executors.newCachedThreadPool();

		for (int i = 0; i < 5; i++) {
			exec.submit(new MyLock("client" + i, client, latch));
		}

		exec.shutdown();
		latch.await();
		System.out.println("所有任务执行完毕");

		client.close();

		System.out.println("客户端关闭。。。。");

	}

	static class MyLock implements Runnable {

		private String name;

		private CuratorFramework client;

		private CountDownLatch latch;

		public MyLock(String name, CuratorFramework client, CountDownLatch latch) {
			this.name = name;
			this.client = client;
			this.latch = latch;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			InterProcessMutex lock = new InterProcessMutex(client,PATH);
			try {
				if (lock.acquire(120, TimeUnit.SECONDS)) {
					try {
						// do some work inside of the critical section here
						System.out.println("----------" + this.name
								+ "获得资源----------");
						System.out.println("----------" + this.name
								+ "正在处理资源----------");
						Thread.sleep(10 * 1000);
						System.out.println("----------" + this.name
								+ "资源使用完毕----------");
						latch.countDown();
					} finally {
						lock.release();
						System.out.println("----------" + this.name
								+ "释放----------");
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}*/

}
