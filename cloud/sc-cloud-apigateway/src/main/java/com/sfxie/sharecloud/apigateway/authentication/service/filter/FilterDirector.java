package com.sfxie.sharecloud.apigateway.authentication.service.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterDirector {
	
	private ThreadLocal<Integer> next = new ThreadLocal<Integer>();
	
	private static List<AbstractAuthFilter> filters = new ArrayList<AbstractAuthFilter>();
	
	/**
	 * 清除当前线程的计数
	 */
	private void clear(){
		next.remove();
	}
	
	private int getIndex(){
		if(null==next.get()){
			next.set(0);
		}else{
			 next.set(next.get()+1);
		}
		return  next.get();
	}
	
}
