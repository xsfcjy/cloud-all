package com.sfxie.extension.mybatis.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.sfxie.extension.mybatis.annotation.MyBatisRepository;
import com.sfxie.utils.ReflectUtils;

  @Intercepts({
     /* @Signature(
          type = Executor.class,
          method = "query",
          args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
      ,*/
      @Signature(
          type = Executor.class,
          method = "update",
          args = {MappedStatement.class, Object.class})
//	  @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class})
  })
  /**
   * 
   * @TODO		添加更新记录操作拦截器
   * @author 	xieshengfeng
   * @email  	xsfcy@126.com
   * @since 	上午11:25:01 2015-7-27	
   *
   */
@SuppressWarnings("unused")
public class AutoSqlMappedStatementInterceptor implements Interceptor{
	/** IMybatisDao操作标志*/
    private final static String _sql_regex_update = ".*cniemp.mybatis.autosql.update.*";
    
    private void processIntercept(Invocation invocation) throws Exception {
    	MappedStatement mappedStatement=(MappedStatement)invocation.getArgs()[0];        
    	Object parameterObject = invocation.getArgs()[1]; 
    	String mapperSQL = mappedStatement.getBoundSql(parameterObject).getSql();
    	boolean interceptorUpdate = mapperSQL.matches(_sql_regex_update);
    	//实体增删改操作
    	if(interceptorUpdate){
    		Class<?> entityclazz = MappedStatmentHelper.getEntityClazz(mappedStatement.getResource());
            BoundSql boundSql = mappedStatement.getBoundSql(parameterObject); 
            String originalSql = boundSql.getSql().trim(); 
            Object[] ddd = invocation.getArgs();
            BoundSql boundSQL = mappedStatement.getBoundSql(parameterObject);
            String new_sql = MapperSqlHelper.getExecuSQL(mapperSQL,parameterObject);
            List<ParameterMapping> parameterMappingList =   createUpdateParameterMappingList(parameterObject,mappedStatement);
            BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), new_sql,parameterMappingList,boundSql.getParameterObject()); 
            MappedStatement newMs = MappedStatmentHelper.copyFromMappedStatement(mappedStatement,new BoundSqlSqlSource(newBoundSql),parameterObject); 
            invocation.getArgs()[0]= newMs; 
    	}
    	/*else if(invocation.getArgs().length == 4){
    		BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
    		List<ParameterMapping> parameterMappingList = createQueryParameterMappingList(parameterObject,mappedStatement);
    		ReflectUtil.setFieldValue(boundSql, "parameterMappings", parameterMappingList);
    		System.out.println("query");
    	}*/
    }
    /**
     * 重新构造增删改sql的参数列表集合
     * @param parameterObject
     * @param mappedStatement
     * @return
     */
    public  List<ParameterMapping> createUpdateParameterMappingList(Object parameterObject,MappedStatement mappedStatement){
    	Field[] fields = parameterObject.getClass().getDeclaredFields();
    	List<ParameterMapping>  parameterMappingList = new ArrayList<ParameterMapping> ();
    	for(Field field : fields){
    		String fName = field.getName();
    		if(null!=ReflectUtils.getFieldValue(fName,parameterObject)){
				try {
					Class<?> fClass = ReflectUtils.getFieldGenericType(field);
					ParameterMapping.Builder parameterMappingBuilder = new ParameterMapping.Builder(mappedStatement.getConfiguration(),fName,fClass);
					parameterMappingList.add(parameterMappingBuilder.build());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
    		}
    	}
     	return parameterMappingList;
    }
    
    /**
     * 重新构造查询sql的参数列表集合
     * @param parameterObject
     * @param mappedStatement
     * @return
     */
    /*public  List<ParameterMapping> createQueryParameterMappingList(Object parameterObject,MappedStatement mappedStatement){
    	
    	List<ParameterMapping>  parameterMappingList = mappedStatement.getBoundSql(parameterObject).getParameterMappings();
    	
    	if(parameterMappingList.size()==0){
    		parameterMappingList = new ArrayList<ParameterMapping>();
    	}
    	Iterator<ParameterMapping> iterator = parameterMappingList.iterator();
    	//把之前的动态生成的参数列表移除
    	while(iterator.hasNext()){
    		if(iterator.next().getMode().equals(ParameterMode.INOUT)){
    			iterator.remove();
			}
    	}
    	//重新构造动态参数列表
    	if(null!=mybatisSelectSql){
    		List<QueryConditionEntity> queryConditionEntityList = mybatisSelectSql.getQueryConditionEntityList();
    		if(null!=queryConditionEntityList){
    			for(QueryConditionEntity queryConditionEntity: queryConditionEntityList){
    				Class<?> fClass = queryConditionEntity.getType();
    				String name = queryConditionEntity.getFieldName();
    				ParameterMapping.Builder parameterMappingBuilder = new ParameterMapping.Builder(mappedStatement.getConfiguration(),name,fClass);
//    				parameterMappingBuilder.resultMapId(queryConditionEntity.getDbFieldName());
    				parameterMappingBuilder.expression(queryConditionEntity.getOperator());
    				parameterMappingBuilder.jdbcTypeName(queryConditionEntity.getDbFieldName());
    				parameterMappingBuilder.mode(ParameterMode.INOUT);
    				parameterMappingList.add(parameterMappingBuilder.build());
    			}
    		}
    	}
     	return parameterMappingList;
    }
    */
    public Object intercept(Invocation invocation) throws Throwable {
      
      processIntercept(invocation);
      
      return invocation.proceed();
    }
    public Object plugin(Object o) {
    	  try{
          return Plugin.wrap(o, this);
    	  }catch(Exception e){
    		  e.printStackTrace();
    	  }
    	  return o;
    }
    public void setProperties(Properties arg0) {
    }
    public static class BoundSqlSqlSource implements SqlSource { 
        BoundSql boundSql; 
  
        public BoundSqlSqlSource(BoundSql boundSql) { 
            this.boundSql = boundSql; 
        } 
  
        public BoundSql getBoundSql(Object parameterObject) { 
            return boundSql; 
        } 
    }
    
  }