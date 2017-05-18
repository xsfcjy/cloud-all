package com.sfxie.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/**
 * 集合工具类
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	上午10:32:16 2015-8-4
 * @example		
 *
 */
public class CollectionUtil {
	/**
	 * 判断是否为空
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午2:32:17 2015年9月17日
	 * @param c
	 * @return	
	 *
	 */
	public static boolean isEmpty(Collection<?> c){
		if(null==c || c.size()==0){
			return true;
		}
		return false;
	}
	public static boolean isNotEmpty(Collection<?> c){
		if(null!=c && c.size()>0){
			return true;
		}
		return false;
	}
	/**
	 * 如果参数list为空,则新建List返回,否则返回参数list
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	上午9:02:16 2015年9月18日
	 * @param list
	 * @return	
	 *
	 */
	public static <T> List<T> nullRetureNewList(List<T> list){
		if(null==list ){
			return new ArrayList<T>();
		}
		return list;
	}
	/**
	 * 循环Set列表
	 * @TODO	
	 * @author 	xieshengfeng
	 * @since 	上午10:32:25 2015-8-4
	 * @param set
	 * @param loopSetAction	
	 *
	 */
	public static void loopSet(Set<?> set,LoopSetAction loopSetAction){
		Iterator<?> it = set.iterator();  
		while (it.hasNext()) {  
		  Object obj = it.next();  
		  loopSetAction.action(obj,it);
		} 
	}
	/**
	 * 循环Set动作处理接口
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	上午10:33:50 2015-8-4
	 * @example		
	 *
	 */
	public interface LoopSetAction{
		 public void action(Object element,Iterator<?> setIterator);
	}
	
}
