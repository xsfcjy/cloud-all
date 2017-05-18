package com.sfxie.utils.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 线程池封装类
 * @author xieshengfeng
 * @since 20150706
 *
 */
public class SfxieThreadPool {
	/**	线程池 	*/
	private static ThreadPoolExecutor threadPool;
	/**	结果集	*/
	private List<Object> result;
	/**	是否等待返回结果	*/
	private boolean waitResult;
	/**	初始化线程数	*/
	private static int initThreadPoolSize = 50;
	/**	最大线程数	*/
	private static int maxThreadPoolSize = initThreadPoolSize * 2;
	/**	最大空闲时间(单位:秒)		*/
	private static int keepAliveTime = 3;
	
	/**
	 * 
	 * @param waitResult
	 * 		是否等待返回结果
	 */
	public SfxieThreadPool(boolean waitResult){
		this.waitResult = waitResult;
	}
	
	static{
		
		threadPool =  new ThreadPoolExecutor(initThreadPoolSize, maxThreadPoolSize, keepAliveTime, TimeUnit.SECONDS, 
												new ArrayBlockingQueue<Runnable>(initThreadPoolSize/2),  
								                new ThreadPoolExecutor.DiscardOldestPolicy());  
	}
	
	/**
	 * 运行任务节点集合
	 * @param taskList
	 * 			任务节点(SfxiePatchTask)集合
	 */
	public void runTask(List<SfxiePatchTask> taskList){
		if(null!=taskList && taskList.size()>0){
			try {
				CountDownLatch countSignal = null;
				if(waitResult)
					countSignal = new CountDownLatch(taskList.size());
				for(SfxiePatchTask task : taskList){
					if(waitResult)
						task.setCountSignal(countSignal);
					threadPool.execute(task);
				}
				if(waitResult)
					countSignal.await();
				for(SfxiePatchTask task : taskList){
					if(null!=task.getResult()){
						if(null==result)
							result = new ArrayList<Object>();
						result.add(task.getResult());
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public List<Object> getResult() {
		return result;
	}
}
