package com.sfxie.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {

	/**
	 * 将List列表转化成Map
	 * @TODO	
	 * @param list
	 * @param key
	 * @return	
	 *
	 */
	public static <T> Map<Object,T> list2Map(List<T> list,String key){
		if(null==list)
			return null;
		Map<Object,T> map = new HashMap<Object,T>();
		for(T t : list){
			map.put(ReflectUtils.getFieldValue(key, t), t );
		}
		return map;
	}
}
