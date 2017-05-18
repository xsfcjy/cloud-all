package com.sfxie.extension.mybatis.interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
 
/**
 * 主要用于批量更新拦截
 * @since 2017-03
 * @author xiesf
 *
 */
@Intercepts( {
       @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class}) })
public class PrepareSqlInterceptor implements Interceptor {
 
   
    /**
     * 拦截后要执行的方法
     */
    public Object intercept(Invocation invocation) throws Throwable {
    
       RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
       StatementHandler delegate = (StatementHandler)ReflectUtil.getFieldValue(handler, "delegate");
       BoundSql boundSql = delegate.getBoundSql();
       Object obj = boundSql.getParameterObject();
       MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(delegate,"mappedStatement");
       SqlCommandType sqlCommandType = (SqlCommandType)ReflectUtil.getFieldValue(mappedStatement,"sqlCommandType");
       if(sqlCommandType.toString().equals("UPDATE")){
    	   String sql = boundSql.getSql();
    	   if(sql.contains("${idName}")){
    		   String sqlTemp = sql.replaceAll("\\$\\{idName\\}", ReflectUtil.getFieldValue(obj, "idName").toString());
    		   ReflectUtil.setFieldValue(boundSql, "sql", sqlTemp);
    	   }
       }
       return invocation.proceed();
    }
 
    /**
     * 利用反射进行操作的一个工具类
     *
     */
    private static class ReflectUtil {
       /**
        * 利用反射获取指定对象的指定属性
        * @param obj 目标对象
        * @param fieldName 目标属性
        * @return 目标属性的值
        */
       public static Object getFieldValue(Object obj, String fieldName) {
           Object result = null;
           Field field = ReflectUtil.getField(obj, fieldName);
           if (field != null) {
              field.setAccessible(true);
              try {
                  result = field.get(obj);
              } catch (IllegalArgumentException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              } catch (IllegalAccessException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }
           }
           return result;
       }
      
       /**
        * 利用反射获取指定对象里面的指定属性
        * @param obj 目标对象
        * @param fieldName 目标属性
        * @return 目标字段
        */
       private static Field getField(Object obj, String fieldName) {
           Field field = null;
          for (Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()) {
              try {
                  field = clazz.getDeclaredField(fieldName);
                  break;
              } catch (NoSuchFieldException e) {
                  //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
              }
           }
           return field;
       }
 
       /**
        * 利用反射设置指定对象的指定属性为指定的值
        * @param obj 目标对象
        * @param fieldName 目标属性
         * @param fieldValue 目标值
        */
       public static void setFieldValue(Object obj, String fieldName,
              String fieldValue) {
           Field field = ReflectUtil.getField(obj, fieldName);
           if (field != null) {
              try {
                  field.setAccessible(true);
                  field.set(obj, fieldValue);
              } catch (IllegalArgumentException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              } catch (IllegalAccessException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }
           }
        }
    }

	@Override
	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	@Override
	public void setProperties(Properties arg0) {
		
	}
 
}