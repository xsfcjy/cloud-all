package com.sfxie.extension.mybatis.interceptor;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.MappedStatement.Builder;

public class MappedStatmentHelper {
	public static MappedStatement copyFromMappedStatement(MappedStatement ms,
			SqlSource newSqlSource) {
		Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
				ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		// builder.keyProperty(ms.getKeyProperties().toString());
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.cache(ms.getCache());
		MappedStatement newMs = builder.build();
		return newMs;
	}
	public static Class<?> getEntityClazz(String resource) throws ClassNotFoundException{
		 String className = resource.substring(0, resource.indexOf(".")).replace("/", ".");
	     return Class.forName(className);
	}
	
	 public static MappedStatement copyFromMappedStatement(MappedStatement ms, 
            SqlSource newSqlSource,Object parameter) { 
//		 	List<ParameterMapping>  parameterMappingList = new ArrayList<ParameterMapping> ();
//	    	ParameterMapping.Builder parameterMappingBuilder1 = new ParameterMapping.Builder(ms.getConfiguration(),"NAME_",String.class);
//	    	ParameterMapping.Builder parameterMappingBuilder2 = new ParameterMapping.Builder(ms.getConfiguration(),"ID_",Long.class);
//	    	ParameterMapping.Builder parameterMappingBuilder3 = new ParameterMapping.Builder(ms.getConfiguration(),"PARTITION_STRING1",String.class);
//	    	ParameterMapping.Builder parameterMappingBuilder4 = new ParameterMapping.Builder(ms.getConfiguration(),"PARTITION_STRING2",String.class);
//	    	parameterMappingList.add(parameterMappingBuilder1.build());
//	    	parameterMappingList.add(parameterMappingBuilder2.build());
//	    	parameterMappingList.add(parameterMappingBuilder3.build());
//	    	parameterMappingList.add(parameterMappingBuilder4.build());
//	    	parameterMappingBuilder1.build();
	    	ParameterMap parameterMap = new ParameterMap.Builder(ms.getConfiguration(), ms.getParameterMap().getId(), parameter.getClass(), newSqlSource.getBoundSql(parameter).getParameterMappings()).build();
	    	
           Builder builder = new MappedStatement.Builder(ms.getConfiguration(), 
           ms.getId(), newSqlSource, ms.getSqlCommandType()); 
           builder.resource(ms.getResource()); 
           builder.fetchSize(ms.getFetchSize()); 
           builder.parameterMap(parameterMap);
           builder.statementType(ms.getStatementType()); 
           builder.keyGenerator(ms.getKeyGenerator()); 
           //builder.keyProperty(ms.getKeyProperties().toString()); 
           builder.timeout(ms.getTimeout()); 
//            builder.parameterMap(ms.getParameterMap()); 
           builder.resultMaps(ms.getResultMaps()); 
           builder.cache(ms.getCache()); 
           MappedStatement newMs = builder.build(); 
           return newMs; 
   } 
}
