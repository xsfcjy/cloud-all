package com.sfxie.extension.mybatis.inform;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Invocation;

import com.sfxie.utils.DateHelper;
import com.sfxie.utils.ReflectUtils;

public abstract class AbstractInformInterceptor {
	/**
	 * 执行sql拦截通知
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午1:04:14 2016年3月15日
	 * @example
	 * @param invocation
	 * @param startTimeMillionSecord
	 * @param endTimeMillionSecord	
	 *
	 */
	protected void inform(String sqlType,Invocation invocation,Long startTimeMillionSecord,Long endTimeMillionSecord){
		List<IInformInterceptor>  informInterceptorList = getInformInterceptors();
		if(null!=informInterceptorList && informInterceptorList.size()>0){
			for(IInformInterceptor informInterceptor : informInterceptorList){
				if(informInterceptor.canIntercepted(sqlType,startTimeMillionSecord , endTimeMillionSecord)){
					MappedStatement mappedStatement=(MappedStatement)invocation.getArgs()[0];        
					Object parameterObject = invocation.getArgs()[1]; 
					BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);    
					String mapperSQL = boundSql.getSql();
				    List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
				    String sql = extractParameterListName(mapperSQL,parameterMappings,parameterObject);
					if(!informInterceptor.inform(sqlType, sql, startTimeMillionSecord, endTimeMillionSecord)){
						return ;
					}
				}
			}
		}
	}
	
    private String extractParameterListName(String sql,List<ParameterMapping> parameterMappings,Object parameterObject){
    	if(null!=parameterMappings){
    		StringBuffer sb = new StringBuffer();
    		String [] sqlArray = sql.split("\\?");
    		for(int i=0;i< parameterMappings.size();i++){
    			ParameterMapping parameterMapping = parameterMappings.get(i);
    			sb.append(sqlArray[i]).append(" ").append(getValue(ReflectUtils.getFieldValue(parameterMapping.getProperty(), parameterObject))).append(" ");
    		}
    		if(sqlArray.length!=parameterMappings.size()){
    			sb.append(sqlArray[sqlArray.length-1]);
    		}
    		return sb.toString();
    	}
    	return sql;
    }
    private Object getValue(Object value){
    		if(null==value )
    			return null;
    		if(value.getClass().equals(String.class)){
				return "'"+value+"'";
			}else if(value.getClass().equals(Date.class)){
				return DateHelper.formatLongDate((Date) value);
			}else 
				return value;
    }
	public abstract List<IInformInterceptor> getInformInterceptors();
}
