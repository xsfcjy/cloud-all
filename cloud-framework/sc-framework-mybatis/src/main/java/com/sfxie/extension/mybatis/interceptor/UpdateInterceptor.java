package com.sfxie.extension.mybatis.interceptor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.sfxie.context.SpringContextHolder;
import com.sfxie.extension.logger.LoggerUtil;
import com.sfxie.extension.mybatis.inform.AbstractInformInterceptor;
import com.sfxie.extension.mybatis.inform.IInformInterceptor;
import com.sfxie.utils.ReflectUtils;
  @Intercepts({
      @Signature(
          type = Executor.class,
          method = "update",
          args = {MappedStatement.class, Object.class})
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
public class UpdateInterceptor extends AbstractInformInterceptor implements Interceptor{
	/** IMybatisDao操作标志*/
    private final static String _sql_regex_update = ".*cniemp.mybatis.autosql.update.*";
    /** sql监控拦截器*/
    private String informInterceptorList = null;
    /**	判断是否启动sql监控功能	*/
    private boolean openSqlInterceptor = false;
	
	private List<IInformInterceptor> informInterceptors;
    
    private void  processIntercept(Invocation invocation) throws Exception {
    	MappedStatement mappedStatement=(MappedStatement)invocation.getArgs()[0];        
    	Object parameterObject = invocation.getArgs()[1]; 
    	String mapperSQL = mappedStatement.getBoundSql(parameterObject).getSql();
    	boolean interceptorUpdate = mapperSQL.matches(_sql_regex_update);
    	List<ParameterMapping> parameterMappingList =  null;
    	//实体增删改操作
    	if(interceptorUpdate){
    		Class<?> entityclazz = MappedStatmentHelper.getEntityClazz(mappedStatement.getResource());
            BoundSql boundSql = mappedStatement.getBoundSql(parameterObject); 
            String originalSql = boundSql.getSql().trim(); 
            Object[] ddd = invocation.getArgs();
            BoundSql boundSQL = mappedStatement.getBoundSql(parameterObject);
            parameterMappingList =   createUpdateParameterMappingList(parameterObject,mappedStatement);
            String new_sql = MapperSqlHelper.getExecuSQL(mapperSQL,parameterObject);
            BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), new_sql,parameterMappingList,boundSql.getParameterObject()); 
            MappedStatement newMs = MappedStatmentHelper.copyFromMappedStatement(mappedStatement,new BoundSqlSqlSource(newBoundSql),parameterObject); 
            invocation.getArgs()[0]= newMs; 
    	}
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
    
    public Object intercept(Invocation invocation) throws Throwable {
    	Long startTimeMillionSecord = System.currentTimeMillis();
    	processIntercept(invocation);
        Object obj = invocation.proceed();
        if(openSqlInterceptor){
        	Long endTimeMillionSecord = System.currentTimeMillis();
        	inform("update",invocation,startTimeMillionSecord,endTimeMillionSecord);
        }
        return obj;
    }
    public Object plugin(Object o) {
    	  try{
          return Plugin.wrap(o, this);
    	  }catch(Exception e){
    		  e.printStackTrace();
    	  }
    	  return o;
    }
    public void setProperties(Properties properties) {
    	if(null==informInterceptorList && null!=properties.getProperty("informInterceptorList")){
    		this.informInterceptorList = properties.getProperty("informInterceptorList");
    	}
    	if(null!=properties.getProperty("openSqlInterceptor")){
    		this.openSqlInterceptor = Boolean.parseBoolean(properties.getProperty("openSqlInterceptor"));
    	}
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
	public String getInformInterceptorList() {
		return informInterceptorList;
	}
	public void setInformInterceptorList(String informInterceptorList) {
		this.informInterceptorList = informInterceptorList;
	}
	public List<IInformInterceptor> getInformInterceptors() {
		if(null==informInterceptors){
			try{
				informInterceptors = SpringContextHolder.getSpringBean(this.informInterceptorList);
			}catch(Exception e){
				LoggerUtil.console(UpdateInterceptor.class,e.getMessage());
			}
		}
		return informInterceptors;
	}
	public void setOpenSqlInterceptor(boolean openSqlInterceptor) {
		this.openSqlInterceptor = openSqlInterceptor;
	}
    
  }