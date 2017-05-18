package com.sfxie.utils.pool;

import java.util.concurrent.CountDownLatch;

public abstract class SfxiePatchTask implements Runnable {

	private CountDownLatch countSignal;
	
	private Object result;
	
	
	public SfxiePatchTask(){
	}
	
	@Override
	public void run() {
		try{
			runTask();
		}catch(Exception e){
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}finally{
			if(null!=countSignal)
				countSignal.countDown();
		}

	}
	
	public abstract void runTask();

	public void setCountSignal(CountDownLatch countSignal) {
		this.countSignal = countSignal;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
