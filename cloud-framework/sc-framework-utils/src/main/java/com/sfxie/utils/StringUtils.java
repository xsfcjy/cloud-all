/****************************************************************
 ***        Copyright © EASTCOMPEACE        2012.08.22        ***
 ****************************************************************/

package com.sfxie.utils;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * 字符串工具类，相关方法在这里扩充.
 * 
 * @author Huanglinhui
 */
public class StringUtils extends org.apache.commons.lang.StringUtils{
	/**
	 * 随机数对象.
	 */
	private static final Random random = new Random(); 
	
	/**
	 * 空值字符串.
	 */
	public static final String EMPTY_STRING = "";
	
	/**
	 * 空格字符串.
	 */
	public static final String SPACE_STRING = " ";
	
	/**
	 * 空值整型默认值.
	 */
	public static final int EMPTY_INTEGER = -1;
	
	/**
	 * 0.
	 */
	public static final int ZERO = 0;
	
	/**
	 * 回车换行符.
	 */
	public static final String ENTER_KEY = " \r\n ";
	/**
	 * 获取指定实体类的属性对应的数据库表字段名.
	 * 
	 * @param <T>
	 * @param entityClass
	 *             实体类类型.
	 * @param entityClassProperty
	 *             实体类属性名称.
	 *             
	 * @return 数据库表字段名.
	 */
	/*public static String getTableFieldName(String dtoName, String entityClassProperty) {
		return ContextHolder.getDBColumnNameByFieldName(dtoName, entityClassProperty);
	}*/
	
	/**
	 * 检查字符串对象是否为空或字符串长度是否为0.
	 * 
	 * @param strTemp 
	 *            被检查的字符串.
	 * 
	 * @return 字符串对象是为空或字符串长度是为0返回false，否则返回true.
	 */
	public static boolean isValidateString(String strTemp) {
		boolean b = false;
		if ((strTemp != null) && (!(strTemp.trim().equals("")))
				&& (!(strTemp.trim().equalsIgnoreCase("null")))) {
			b = true;
		}
		return b;
	}
	
	/**
	 * 返回合法字符串，字符串对象非空为合法.
	 * 
	 * @param strTemp 被检查的字符串.
	 * 
	 * @return 合法字符串，如果字符串对象为空返回长度为0的字符串.
	 */
	public static String validateString(String strTemp) {
		String b = "";
		if ((strTemp != null) && (!(strTemp.trim().equals("")))
				&& (!(strTemp.trim().equalsIgnoreCase("null")))) {
			b = strTemp.trim();
		}
		return b;
	}
	
	/**
	 * 返回唯一字符串序列号.
	 * 
	 * @return 唯一字符串序列号.
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}
	
	/**
	 * 对象转换为字符串.
	 * 
	 * @param object
	 *             对象.
	 * @param defaultValue
	 *             对象为空的字符串转换默认值.
	 *             
	 * @return 字符串.
	 */
	public static String toString(Object object, String defaultValue) {
		return object == null ? defaultValue : object.toString();
	}
	
	/**
	 * 对象转换为字符串.
	 * 
	 * @param object
	 *             对象.
	 *             
	 * @return 对象转换后的字符串，如果对象为空返回空字符串.
	 */
	public static String toString(Object object) {
		return toString(object, "");
	}
	
	/**
	 * 对象转换为字符串.
	 * 
	 * @param object
	 *             对象.
	 * @param defaultValue
	 *             对象为空的字符串转换默认值.
	 *             
	 * @return 字符串.
	 */
	public static int toInteger(Object object, int defaultValue) {
		return (object != null && isNumeric(object.toString())) ? Integer.valueOf(object.toString()) : defaultValue;
	}
	
	/**
	 * 对象转换为字符串.
	 * 
	 * @param object
	 *             对象.
	 *             
	 * @return 对象转换后的字符串，如果对象为空返回空字符串.
	 */
	public static int toInteger(Object object) {
		return toInteger(object, -1);
	}
	
	/**
	 * 对象转换为字符串.
	 * 
	 * @param object
	 *             对象.
	 *             
	 * @return 对象转换后的字符串，如果对象为空返回空字符串.
	 */
	public static boolean toBoolean(Object object) {
		return toBoolean(object, false);
	}
	
	/**
	 * 对象转换为字符串.
	 * 
	 * @param object
	 *             对象.
	 *             
	 * @return 对象转换后的字符串，如果对象为空返回空字符串.
	 */
	public static boolean toBoolean(Object object, boolean defaultValue) {
		boolean returnValue = defaultValue;
		
		if (object != null && isNumeric(object.toString())) {
			returnValue = toInteger(object) == 0 ? false : true;
		}
		
		return returnValue;
	}
	
	/**
	 * 判断对象是否可以转换为整数.
	 * 
	 * @param str
	 *             转换的字符串.
	 *             
	 * @return 对象可以转换为整数返回true，否则返回false.
	 */
	public static boolean isNumeric(String str) {
		boolean isNumber = true;
		if (str == null || "".equals(str)) {
			isNumber = false;
		} else {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(str);
			isNumber = isNum.matches();
		}
		
		return isNumber;
	}
	
	/**
	 * 判断字符串数组是否非空.
	 * 
	 * @param array
	 *             字符串数组.
	 *             
	 * @return 字符串数组空返回false，非空返回true.
	 */
	public static boolean judgeStringArrayNotNull(String[] array){
		if(null!=array && array.length>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 移除数组中的重复数据 
	 * @param array
	 * @return
	 */
	public static String[] removeDuplitiedElement(String[] array){
		if(array!=null && array.length>0){
			Map<String,String> map = new HashMap<String,String>();
			for(String str : array){
				map.put(str, str);
			}
			return map.values().toArray(new String[]{});
		}else{
			return null;
		}
	}
	
	/**
	 * 将GBK转换为UTF.
	 * 
	 * @param from
	 *             GBK.
	 *             
	 * @return UTF.
	 */
	public static String toUTF8FromGBK(String from){
		String result = null;
		try {
			result =  new String(from.getBytes("gbk"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 把字符串转化成枚举
	 * @param cls 枚举类
	 * @param str 字符串
	 * @return 枚举类
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Enum<?> transformStringToEnum(Class<? extends Enum> cls,String str){
		return (Enum<?>) Enum.valueOf(cls, str);
	}
	
	/**
	 * 获取指定实体类的属性对应的数据库表字段名.
	 * 
	 * @param <T>
	 * @param entityClass
	 *             实体类类型.
	 * @param entityClassProperty
	 *             实体类属性名称.
	 *             
	 * @return 数据库表字段名.
	 */
	/*public static String getTableFieldName(String dtoName, String entityClassProperty) {
		return ContextBeanFactory.getDBColumnNameByFieldName(dtoName, entityClassProperty);
	}*/
	
	/**
	 * 转化从前端传过来的参数，使其成为utf-8格式的编码
	 * @param test
	 * @return
	 */
	public static String decodeToUTF8(String test){
		try {
			return java.net.URLDecoder.decode(test , "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 中文转Unicode
	 * @param s
	 * @return
	 */
	public static String chineseToUnicode(String s) {
	 String as[] = new String[s.length()];
	 String s1 = "";
	 for (int i = 0; i < s.length(); i++) {
	  as[i] = Integer.toHexString(s.charAt(i) & 0xffff);
	  s1 = s1 + "\\u" + as[i];
	 }
	 return s1;
	}

	/**
	 * Unicode转中文
	 * @param string
	 * @return
	 */
	public static String unicodeToChinese(String string) {
	 String str = string.replace("\\u", ",");
	 String[] s2 = str.split(",");
	 String s1 = "";
	 for (int i = 1; i < s2.length; i++) {
	  s1 = s1 + (char) Integer.parseInt(s2[i], 16);
	 }
	 return s1;
	}
	/**
	 * 将字符串转化成utf-8字符串
	 * @param s
	 * @return
	 */
	public static String toUTF8String(String s) {
	    StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();    
	}
	/**
	 * 把传进去的字符串的第一个字母变小写并且返回
	 * @param str
	 * @return
	 */
	public static String lowerFirstChar(String str){
		return str.replaceFirst(str.valueOf(str.charAt(0)), str.valueOf(str.charAt(0)).toLowerCase());
	}
	/**
	 * 获取随机数.
	 * 
	 * @return 随机数.
	 */
	public static String getRandomValue() {
		return String.valueOf(Math.abs(random.nextLong()));
	}
	/**
	 * 
	 * @TODO	将string{0}a{1}的字符串转化为string+arguments[0]+a+arguments[0]这样的字符串
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午2:19:13 2016年3月14日
	 * @example
	 * @param formatStr
	 * @param arguments
	 * @return	
	 *
	 */
	public static String format(String formatStr,Object... arguments){
		return MessageFormat.format(formatStr,arguments);
	}
}
