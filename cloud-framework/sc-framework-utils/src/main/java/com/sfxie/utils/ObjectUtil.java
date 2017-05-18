package com.sfxie.utils;

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
}
