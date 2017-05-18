package com.sfxie.utils;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
//import java.beans.IntrospectionException;
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//import org.apache.commons.beanutils.BeanUtils;
//import org.apache.commons.beanutils.PropertyUtils;
//import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;



/**
 * 反射工具类.
 * 
 * 注意:JDK5中不能传递二个可变参数,如:methodInvoke()方法.
 */
public class ReflectUtils {

	/**
	 * 暴力反射获取字段值.
	 * 
	 * @param fieldName 
	 *             属性名.
	 * @param obj
	 *             实例对象.
	 *             
	 * @return 属性值，不存在值返回null.
	 */
	public static Object getFieldValue(String fieldName, Object obj) {
		Object value = null;
		if(obj.getClass().equals(Map.class) || obj.getClass().equals(HashMap.class)){
			return ((Map)obj).get(fieldName);
		}
		try {
			//获取对象字段.
			Field field = null;
			try{
				
				field = obj.getClass().getDeclaredField(fieldName);
			}catch(Exception ex){
				Collection<Field> fields = new ArrayList<Field>();
				ReflectUtils.getBeanAllFields(fields,obj.getClass(),null);
				Field[] srcfields = new Field[fields.size()];
				fields.toArray(srcfields);
				for(Field f : srcfields){
					if(f.getName().equals(fieldName)){
						field = f;
						break;
					}
				}
			}
			//找不到字段不处理.
			if (field != null) {
				field.setAccessible(true);
				value = field.get(obj);
			}else{
				value = obj;
			}
		} catch (Exception ex) {
			LoggerUtil.instance(ReflectUtils.class).error(ex.getMessage());
			ex.printStackTrace();
			throw new RuntimeException();
		}

		return value;
	}
	
	/**
	 * 设置字段值.
	 * 
	 * @param obj
	 *             实例对象.
	 * @param fieldName
	 *             字段名.
	 * @param value
	 *             字段值.
	 */
	public static void setFieldValue(Object obj, String fieldName, Object value) {
		try {
			//获取对象字段.
			Field field = obj.getClass().getDeclaredField(fieldName);
			//找不到字段不处理.
			if (field != null) {
				field.setAccessible(true);
				field.set(obj, dealBoolean(field,value));
			}
		} catch (Exception ex) {
			LoggerUtil.instance(ReflectUtils.class).error(ex.getMessage());
			ex.printStackTrace();
			throw new RuntimeException();
		}
	}
	/**
	 * 转化true或false为"1"或"0"
	 * 解决field如果为字符串类型时转化出错问题
	 * @param str
	 * @return
	 */
	private static Object dealBoolean(Field field,Object value){
		try {
			if(value instanceof Boolean){
				if(field.getType().newInstance() instanceof String){
					return (Boolean)value?"1":"0";
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return value;
	}
	/**
	 * 判断对象是否存在指定的属性.
	 * 
	 * @param obj
	 *             对象.
	 * @param fieldName
	 *             属性名称.
	 *             
	 * @return 存在属性返回true，不存在或发生异常返回false.
	 */
	public static boolean hasField(Object obj, String fieldName) {
		boolean hasField = false;
		
		try {
			hasField = obj.getClass().getDeclaredField(fieldName) != null;
		} catch (Exception e) {
//			LoggerUtil.instance(ReflectUtils.class).error(e.getMessage());
//			e.printStackTrace();
		}
		
		return hasField;
	}
	public static void getBeanAllMethods(Collection<Method> col,Class<?> clazz,List<Class<?>> classList,Class<?> parentClazz){
		if(null==clazz)
			throw new RuntimeErrorException(new Error(ReflectUtils.class.getName()+"'s method getBeanAllFields exception at line 110 : clazz is null!"));
		if(null!=parentClazz){
			if(clazz.getName().equals(parentClazz.getName()))
				return;
		}
		Method [] methods = clazz.getDeclaredMethods();
		for(Method method : methods){
			col.add(method);
		}
		if(null!=clazz.getSuperclass()){
			getBeanAllMethods(col,clazz.getSuperclass(),classList,parentClazz);
		}
	}
	/**
	 * 获取对象的所有成员变量(包括父类成员变量)<br>
	 * @param col 装载被获取类的所有成员变量集合<br>
	 * @param clazz 被获取成员变量的类  <br>
	 * @param parentClazz　被获取成员变量的类的父类，<br>
	 * 	此参数表示获取clazz的所有成员变量，但是排除parentClazz的成员变量<br>
	 */
	public static void getBeanAllFields(Collection<Field> col,Class<?> clazz,Class<?> parentClazz){
		if(null==clazz)
			throw new RuntimeErrorException(new Error(ReflectUtils.class.getName()+"'s method getBeanAllFields exception at line 110 : clazz is null!"));
		if(null!=parentClazz){
			if(clazz.getName().equals(parentClazz.getName()))
				return;
		}
		Field [] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			col.add(field);
		}
		if(null!=clazz.getSuperclass()){
			getBeanAllFields(col,clazz.getSuperclass(),parentClazz);
		}
	}
	
	/**
	 * 获取对象的所有成员变量(包括父类成员变量)<br>
	 * @param col 装载被获取类的所有成员变量集合(每个属性被一个Map对象装载，Map对象有成员变量的类型字符串、clazz对象、变量名)<br>
	 * @param clazz 被获取成员变量的类  <br>
	 * @param parentClazz　被获取成员变量的类的父类，
	 * 	此参数表示获取clazz的所有成员变量，但是排除parentClazz的成员变量<br>
	 */
	public static void getBeanAllFieldAndFieldTypeAndFieldClazz(Collection<Map<String,Object>> col,Class<?> clazz,Class<?> parentClazz){
		if(null==clazz)
			throw new RuntimeErrorException(new Error(ReflectUtils.class.getName()+"'s method getBeanAllFields exception at line 135 : clazz is null!"));
		if(null!=parentClazz){
			if(clazz.getName().equals(parentClazz.getName()))
				return;
		}
		Field [] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("fieldName", field.getName());
			map.put("fieldType", field.getType().getSimpleName());
			map.put("fieldClazz", field.getType());
			col.add(map);
		}
		if(null!=clazz.getSuperclass()){
			getBeanAllFieldAndFieldTypeAndFieldClazz(col,clazz.getSuperclass(),parentClazz);
		}
	}
	
	/**
	 * 
	 * @param col 
	 * 		
	 * @param clazz 
	 * 		
	 * @param parentClazz　
	 * 		
	 * 	
	 */
	/**
	 * 获取对象的所有成员变量(包括父类成员变量)<br>
	 * @param col
	 * 		装载被获取类的所有成员变量集合(每个属性被一个Map对象装载，Map对象有成员变量的类型字符串、clazz对象、变量名)<br>
	 * @param clazz
	 * 		被获取成员变量的类  <br>
	 * @param parentClazz
	 * 		被获取成员变量的类的父类(此参数表示获取clazz的所有成员变量，但是排除parentClazz的成员变量<br>)
	 * @param keyName1
	 * 		map主键值1
	 * @param keyName2
	 * 		map主键值2
	 * 
	 */
	public static void getBeanAllFieldAndFieldType(Collection<Map<String,Object>> col,Class<?> clazz,Class<?> parentClazz, String keyName1, String keyName2){
		if(null==clazz)
			throw new RuntimeErrorException(new Error(ReflectUtils.class.getName()+"'s method getBeanAllFields exception at line 135 : clazz is null!"));
		if(null!=parentClazz){
			if(clazz.getName().equals(parentClazz.getName()))
				return;
		}
		Field [] fields = clazz.getDeclaredFields();
		for(Field field : fields){// 遍历所有属性
			Map<String,Object> map = new HashMap<String,Object>();
			map.put(keyName1, field.getName());// 获取属性的名字
			map.put(keyName2, field.getType().getSimpleName().toLowerCase()); //获取属性的类型,并转成小写字母
			col.add(map);
		}
		if(null!=clazz.getSuperclass()){
			getBeanAllFieldAndFieldType(col,clazz.getSuperclass(),parentClazz,keyName1,keyName2);
		}
	}
	
	/**
	 * 获取对象的所有成员变量的属性名和属性类型
	 * @param cls
	 * 		被获取成员变量的类
	 * @return
	 * 		返回所有成员变量的属性名和属性类型的LIST对象
	 */		
	public static List fieldsReflect(Class cls) {
		Field[] field = cls.getDeclaredFields();// 获取实体类的所有属性，返回Field数组
		
		List<Object> fields = new ArrayList<Object>();
		for (int j = 0; j < field.length; j++) { // 遍历所有属性
			Map<String, Object> map = new HashMap<String, Object>();
			String name = field[j].getName(); // 获取属性的名字
			String fieldsType = field[j].getType().getSimpleName().toLowerCase(); // 获取属性的类型,并转成小写字母
			map.put("name", name);
			map.put("type", fieldsType);
			fields.add(map);
		}
		
		return fields;
	}
	
	/**
	 * 获取对象的所有成员变量的属性名
	 * @param cls
	 * 			被获取成员变量的类
	 * @return 实体属性名数组
	 */
	public static List fieldsNameReflect(Class cls) {
		Field[] field = cls.getDeclaredFields();// 获取实体类的所有属性，返回Field数组	
		List<Object> fields = new ArrayList<Object>();
		
		for (int j = 0; j < field.length; j++) { // 遍历所有属性
			String name = field[j].getName(); // 获取属性的名字
			fields.add(name);
		}
		
		return fields;
	}
//  没有用到的方法不对外发布，用到时再开启.
//	/**
//	 * 通过构造函数实例化对象.
//	 * 
//	 * @param className
//     *             类的全路径名称.	
//	 * @param parameterTypes
//	 *             参数类型.
//	 * @param initargs
//	 *             参数值.
//	 * @return 实例化对象.
//	 */
//	@SuppressWarnings("unchecked")
//	public static Object constructorNewInstance(String className,Class [] parameterTypes,Object[] initargs) { 
//		try {
//			Constructor<?> constructor = (Constructor<?>) Class
//					.forName(className).getDeclaredConstructor(parameterTypes);					    //暴力反射
//			constructor.setAccessible(true);
//			return constructor.newInstance(initargs);
//		} catch (Exception ex) {
//			throw new RuntimeException();
//		}
//
//	}
//
//	/**
//	 * 暴力反射获取字段值.
//	 * 
//	 * @param propertyName
//	 *             属性名.
//	 * @param object
//	 *             实例对象.
//	 *             
//	 * @return 字段值.
//	 */
//	public static Object getProperty(String propertyName, Object object) {
//		try {
//			
//			PropertyDescriptor pd = new PropertyDescriptor(propertyName,object.getClass());
//			Method method = pd.getReadMethod();
//			return method.invoke(object);
//			
//			//其它方式
//			/*BeanInfo beanInfo =  Introspector.getBeanInfo(object.getClass());
//			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
//			Object retVal = null;
//			for(PropertyDescriptor pd : pds){
//				if(pd.getName().equals(propertyName))
//				{
//					Method methodGetX = pd.getReadMethod();
//					retVal = methodGetX.invoke(object);
//					break;
//				}
//			}
//			return retVal;*/
//		} catch (Exception ex) {
//			throw new RuntimeException();
//		}
//	}
//	
//	/**
//	 * 通过BeanUtils工具包获取反射获取字段值,注意此值是以字符串形式存在的,它支持属性连缀操作:如,.对象.属性.
//	 * 
//	 * @param propertyName
//	 *             属性名.
//	 * @param object
//	 *             实例对象.
//	 *             
//	 * @return 字段值.
//	 */
//	public static Object getBeanInfoProperty(String propertyName, Object object) {
//		try {			
//			return BeanUtils.getProperty(object, propertyName);
//		} catch (Exception ex) {
//			throw new RuntimeException();
//		}
//	}
//	
	/**
	 * 通过BeanUtils工具包获取反射获取字段值.
	 * 注意此值是以字符串形式存在的.
	 * 
	 * @param object
	 *             实例对象.
	 * @param propertyName
	 *             属性名.
	 * @param value
	 *             字段值.
	 *             
	 * @return 字段值.     
	 */
	public static void setBeanInfoProperty(Object object,String propertyName,String value) {
		try {			
			BeanUtils.setProperty(object, propertyName,value);
		} catch (Exception ex) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 通过BeanUtils工具包获取反射获取字段值,注意此值是以对象属性的实际类型.
	 * 
	 * @param propertyName
	 *             属性名.
	 * @param object
	 *             实例对象.
	 *             
	 * @return 字段值.
	 */
	public static Object getPropertyUtilByName(String propertyName, Object object) {
		try {			
			return PropertyUtils.getProperty(object, propertyName);
		} catch (Exception ex) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 通过BeanUtils工具包设置反射获取字段值,注意此值是以对象属性的实际类型,这是PropertyUtils与BeanUtils的根本区别.
	 * 
	 * @param object
	 *             实例对象.
	 * @param propertyName
	 *             属性名.
	 * @param value
	 *             字段值.
	 *             
	 * @return         
	 */
	public static void setPropertyUtilByName(Object object,String propertyName,Object value) {
		try {		
			Class fieldType = PropertyUtils.getPropertyType(object, propertyName);
			if(fieldType == boolean.class){
				if(value.getClass() == boolean.class)
					PropertyUtils.setProperty(object, propertyName,value);
				else
					PropertyUtils.setProperty(object, propertyName,
						value.toString().equals("1")|| value.toString().equals("0")
						?new Boolean(value.toString().equals("1")?"true":"false"):value);
			}else
				PropertyUtils.setProperty(object, propertyName,value);
		} catch (Exception ex) {
			try {
				ex.printStackTrace(new PrintStream("reflectError:fieldName-"+propertyName+";fieldType"+value.getClass()+";fieldValue:"+value));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			throw new RuntimeException();
		}
	}
	/**
	 * 通过反射,获得Field泛型参数的实际类型. 如: public Map<String, Buyer> names;
	 * 
	 * @param Field
	 *            field 字段
	 * @param int index 泛型参数所在索引,从0开始.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 * @throws ClassNotFoundException 
	 */
	public static Class getFieldGenericType(Field field, int index) throws ClassNotFoundException {
		Type genericFieldType = field.getGenericType();

		if (genericFieldType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericFieldType;
			Type[] fieldArgTypes = aType.getActualTypeArguments();
			if (index >= fieldArgTypes.length || index < 0) {
				throw new RuntimeException("你输入的索引"
						+ (index < 0 ? "不能小于0" : "超出了参数的总数"));
			}
			return (Class) fieldArgTypes[index];
		}else{
			String typeString = genericFieldType.toString().replaceAll("class ", "");
			return Class.forName(getRealClassName(typeString));
		}
	}
	private static String getRealClassName(String typeString){
		if(typeString.equals("long"))
			return java.lang.Long.class.toString().replaceAll("class ", "");
		else if(typeString.equals("int"))
			return java.lang.Integer.class.toString().replaceAll("class ", "");
		else if(typeString.equals("double"))
			return java.lang.Double.class.toString().replaceAll("class ", "");
		else if(typeString.equals("float"))
			return java.lang.Float.class.toString().replaceAll("class ", "");
		return typeString;
	}
	/**
	 * 通过反射,获得Field泛型参数的实际类型. 如: public Map<String, Buyer> names;
	 * 
	 * @param Field
	 *            field 字段
	 * @param int index 泛型参数所在索引,从0开始.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 * @throws ClassNotFoundException 
	 */
	public static Class getFieldGenericType(Field field) throws ClassNotFoundException {
		return getFieldGenericType(field, 0);
	}
}

