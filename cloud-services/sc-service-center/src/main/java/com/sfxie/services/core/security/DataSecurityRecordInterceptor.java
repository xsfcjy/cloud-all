package com.sfxie.services.core.security;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;


@Intercepts(@Signature(type = Executor.class, method = "query", 
args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class DataSecurityRecordInterceptor implements Interceptor {


	@Override
	public Object intercept(Invocation invocation) throws Throwable {
        Object param = invocation.getArgs()[1];
        if(null!=param && param instanceof DataSecurityRecord){
        	((DataSecurityRecord)param).setSecuritySql(" and 3=3 ");
        }
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
	}

	@Override
	public void setProperties(Properties properties) {
	}

}
