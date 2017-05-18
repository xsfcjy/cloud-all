package com.sfxie.extension.mybatis.interceptor;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.sfxie.exception.framework.implement.exception.DaoException;
import com.sfxie.extension.mybatis.annotation.MyBatisRepository;
import com.sfxie.utils.ReflectUtils;
/**
 * 
 * @author xieshengfeng
 * @since 20150519
 * @todo  重新设置BoundSql包含cniemp.mybatis.autosql字符串的sql
 *
 */
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class AutoSqlStatementHandlerInterceptor implements Interceptor {

	public static final Class<? extends Annotation> MYBATISREPOSITORY = MyBatisRepository.class;
	/** 更新操作标志*/
	public final static String _sql_regex_update = "cniemp.mybatis.autosql.update";
	/** 查询操作标志*/
	public final static String _sql_regex_query = "cniemp.mybatis.autosql.query";
	/** 参数标志*/
	private final static String PARAMETER_PLACEHOLDER = "\\[parameter\\]";
	/** 数据库类型，不同的数据库有不同的分页方法*/
	private String databaseType;
	/** 查询操作的条件类,从前端传过来的参数*/
	private static IMybatisQueryCondition mybatisQueryCondition ;
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		try{
			RoutingStatementHandler handler = (RoutingStatementHandler) invocation
					.getTarget();
			// 通过反射获取到当前RoutingStatementHandler对象的delegate属性
			StatementHandler delegate = (StatementHandler) ReflectUtils.getFieldValue("delegate",handler);
			// 获取到当前StatementHandler的
			// boundSql，这里不管是调用handler.getBoundSql()还是直接调用delegate.getBoundSql()结果是一样的，因为之前已经说过了
			// RoutingStatementHandler实现的所有StatementHandler接口方法里面都是调用的delegate对应的方法。
			BoundSql boundSql = delegate.getBoundSql();
//			System.out.println(boundSql.getSql());
			//实体增删改操作sql处理
			if (boundSql.getSql().startsWith(_sql_regex_update)) {
				//执行新增和修改操作
				doSaveOrUpdate(boundSql);
			}
			//处理获取自增主键
			else if(boundSql.getSql().startsWith("cniemp.mybatis.autosql.selectKeyId")) {
				selectKeyId(boundSql);
			}else{
				MappedStatement mappedStatement = (MappedStatement)ReflectUtils.getFieldValue("mappedStatement",delegate);
				SqlCommandType sqlCommandType = (SqlCommandType)ReflectUtils.getFieldValue("sqlCommandType",mappedStatement);
				if(sqlCommandType.toString().equals("SELECT")){
					doSelectPage(invocation);
				}
				//执行分页函数操作
			}
			return invocation.proceed();
		}catch(Exception e){
			DaoException exception= new DaoException(e) ;
			exception.setStackTrace(e.getStackTrace());
			throw exception;
		}
	}
	private void selectKeyId(BoundSql boundSql){
		ReflectUtils.setFieldValue(boundSql, "sql", "SELECT LAST_INSERT_ID() AS ID ");
	}
	private void doSaveOrUpdate(BoundSql boundSql){
		ReflectUtils.setFieldValue(boundSql, "sql", boundSql.getSql().replaceAll(_sql_regex_update, "").replaceAll(PARAMETER_PLACEHOLDER, "?"));
	}
	private void doSelectPage(Invocation invocation) throws SQLException{
		//对于StatementHandler其实只有两个实现类，一个是RoutingStatementHandler，另一个是抽象类BaseStatementHandler，
	       //BaseStatementHandler有三个子类，分别是SimpleStatementHandler，PreparedStatementHandler和CallableStatementHandler，
	       //SimpleStatementHandler是用于处理Statement的，PreparedStatementHandler是处理PreparedStatement的，而CallableStatementHandler是
	       //处理CallableStatement的。Mybatis在进行Sql语句处理的时候都是建立的RoutingStatementHandler，而在RoutingStatementHandler里面拥有一个
	       //StatementHandler类型的delegate属性，RoutingStatementHandler会依据Statement的不同建立对应的BaseStatementHandler，即SimpleStatementHandler、
	       //PreparedStatementHandler或CallableStatementHandler，在RoutingStatementHandler里面所有StatementHandler接口方法的实现都是调用的delegate对应的方法。
	       //我们在PageInterceptor类上已经用@Signature标记了该Interceptor只拦截StatementHandler接口的prepare方法，又因为Mybatis只有在建立RoutingStatementHandler的时候
	       //是通过Interceptor的plugin方法进行包裹的，所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象。
	       RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
	       //通过反射获取到当前RoutingStatementHandler对象的delegate属性
	       StatementHandler delegate = (StatementHandler)ReflectUtils.getFieldValue("delegate",handler);
//	       获取到当前StatementHandler的 boundSql，这里不管是调用handler.getBoundSql()还是直接调用delegate.getBoundSql()结果是一样的，因为之前已经说过了
	       //RoutingStatementHandler实现的所有StatementHandler接口方法里面都是调用的delegate对应的方法。
	       BoundSql boundSql = delegate.getBoundSql();
	       //拿到当前绑定Sql的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象
	       Object parameterObject =  boundSql.getParameterObject();
	       //这里我们简单的通过传入的是Page对象就认定它是需要进行分页操作的。
	       MappedStatement mappedStatement = (MappedStatement)ReflectUtils.getFieldValue("mappedStatement",delegate);
    	    List<ParameterMapping> parameterMappingList = createQueryParameterMappingList(parameterObject,mappedStatement);
    	    if(null!=parameterMappingList){
    	    	ReflectUtils.setFieldValue(boundSql, "parameterMappings", parameterMappingList);
    	    	String restructQuerySql = restructQuerySql(boundSql);
    	    	ReflectUtils.setFieldValue(boundSql, "sql",restructQuerySql);
    	    }
	}
	 /**
     * 重新构造查询sql的参数列表集合
     * @param parameterObject
     * @param mappedStatement
     * @return
     */
	private  List<ParameterMapping> createQueryParameterMappingList(Object parameterObject,MappedStatement mappedStatement){
		List<ParameterMapping>  parameterMappingList = null;
		if(null!=mybatisQueryCondition){
	    	parameterMappingList = mappedStatement.getBoundSql(parameterObject).getParameterMappings();
	    	
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
    		List<QueryConditionEntity> queryConditionEntityList = mybatisQueryCondition.getQueryConditionEntityList();
    		if(null!=queryConditionEntityList && queryConditionEntityList.size()>0){
    			for(QueryConditionEntity queryConditionEntity: queryConditionEntityList){
    				Class<?> fClass = queryConditionEntity.getFieldClass();
    				String name = queryConditionEntity.getFieldName();
    				ParameterMapping.Builder parameterMappingBuilder = new ParameterMapping.Builder(mappedStatement.getConfiguration(),name,fClass);
//    				parameterMappingBuilder.resultMapId(queryConditionEntity.getDbFieldName());
    				parameterMappingBuilder.expression(queryConditionEntity.getOperator());
    				//由于mybatis的ParameterMapping类不可以被继承，并且没有其它成员变量可用
    				//所以只能借用jdbcTypeName这个成员变量来保存QueryConditionEntity的dbFieldName成员变量
    				parameterMappingBuilder.jdbcTypeName(queryConditionEntity.getDbFieldName());
    				//标记此参数是外带的最外层的查询参数
    				parameterMappingBuilder.mode(ParameterMode.INOUT);
    				parameterMappingList.add(parameterMappingBuilder.build());
    			}
    		}
    	}
     	return parameterMappingList;
    }
    /**
     * 重新构造查询操作sql,如果前端没有查询参数传过来,则返回原来的sql.
     * 重新构造的sql是在原来的sql上外面嵌套一层外带前端传过来的参数的查询条件sql
     * @param boundSql
     * @return
     */
	private String restructQuerySql(BoundSql boundSql){
    	StringBuffer querySql = new StringBuffer();
    	String realSql = boundSql.getSql();
    	List<ParameterMapping>  parameterMappingList = boundSql.getParameterMappings();
    	boolean firstFlag = true;
    	for(ParameterMapping parameterMapping : parameterMappingList){
    		//如果不是原来在mybatis中已经写好的查询条件字段
    		if(parameterMapping.getMode().equals(ParameterMode.INOUT)){
    			if(!firstFlag){
    				querySql.append(" and ")
    				.append(parameterMapping.getJdbcTypeName())
    				.append(parameterMapping.getExpression())
    				.append("?");
    			}else{
    				querySql.append(" select * from( ")
    				.append(realSql)
    				.append(") a ").append(" where ")
    				.append(parameterMapping.getJdbcTypeName())
    				.append(parameterMapping.getExpression())
    				.append("?");
    				firstFlag = false;
    			}
    		}
    	}
    	String resultSQL = querySql.toString().equals("")?realSql:querySql.toString();
//    	ReflectUtil.setFieldValue(boundSql, "sql", +boundSql.getSql());
    	System.out.println("restruct sql: "+resultSQL);
		return resultSQL;
    }
	/**
     * 拦截器对应的封装原始对象的方法
     */
    public Object plugin(Object target) {
       return Plugin.wrap(target, this);
    }
 
    /**
     * 设置注册拦截器时设定的属性
     */
    public void setProperties(Properties properties) {
       this.databaseType = properties.getProperty("databaseType");
    }
   
	public  IMybatisQueryCondition getMybatisQueryCondition() {
		return mybatisQueryCondition;
	}
	public  void setMybatisQueryCondition(
			IMybatisQueryCondition mybatisQueryCondition) {
		AutoSqlStatementHandlerInterceptor.mybatisQueryCondition = mybatisQueryCondition;
	}
   

    
}
