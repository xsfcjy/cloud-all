package com.sfxie.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 对象辅助类
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	上午10:56:13 2015年9月23日
 * @example		
 *
 */
public class ObjectUtil {

	/**
	 * 如果参数obj为空,则clazz.newInstance()返回,否则返回参数obj
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	上午9:02:16 2015年9月18日
	 * @param obj
	 * @param clazz
	 * @return	
	 *
	 */
	public static <T> Object nullRetureNewObject(T obj,Class<T> clazz){
		if(null==obj ){
			try {
				return clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
	
	public static boolean isNotEmpty(Object obj){
		if(null==obj)
			return false;
		if(obj instanceof String){
			return !obj.equals("");
		}
		return true;
	}
	/**
	 *  判断key是否为空
	 * @param obj
	 * 		被判断的对象
	 * @param keyMessage
	 * 		被判断的key和提示信息map
	 * @return
	 */
	public static String judgeNull(Object obj,Map<String,String> keyMessage){
		for(String key : keyMessage.keySet()){
			try {
				if(null==BeanUtils.getProperty(obj, key)){
					return keyMessage.get(key);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
}
