package com.sfxie.extension.mybatis.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import com.sfxie.extension.mybatis.annotation.ColumnName;
import com.sfxie.extension.mybatis.annotation.ConditionColumn;
import com.sfxie.extension.mybatis.annotation.ITableNameGenetator;
import com.sfxie.extension.mybatis.annotation.KeyGenerator;
import com.sfxie.extension.mybatis.annotation.NotDBField;
import com.sfxie.extension.mybatis.annotation.PartitionField;
import com.sfxie.extension.mybatis.annotation.TableName;
import com.sfxie.extension.mybatis.exception.ConditionEmptyException;
import com.sfxie.extension.mybatis.exception.DeleteEntityConditionMoreException;
import com.sfxie.extension.mybatis.exception.PartitionEmptyException;
import com.sfxie.extension.mybatis.exception.TableNameGeneratedException;
import com.sfxie.utils.ReflectUtils;

public class MapperSqlHelper {
	
	private static MapperSqlHelper instance = new MapperSqlHelper();
//	public static final Class<? extends Annotation> MYBATISREPOSITORY = MyBatisRepository.class;
	public static final Class<? extends Annotation> MYBATISTABLE = TableName.class;
	public static final Class<? extends Annotation> MYBATISCOLUMN = ColumnName.class;
	public static final Class<? extends Annotation> PARTITIONFIELD = PartitionField.class;
	public static final Class<? extends Annotation> CONDITIONCOLUMN = ConditionColumn.class;
	public static final Class<? extends Annotation> MYBATISNOTDBFIELD = NotDBField.class;
	public static final Class<? extends Annotation> KEYGENERATOR = KeyGenerator.class;
	
	
	
	private final static String PARAMETER_PLACEHOLDER = "[parameter]";

	public String getUpdateSQL() {
		return null;
	}

	/**
	 * 传入mapper接口class
	 * 
	 * @param mapperclazz
	 * @return
	 */
	private String insertEntity(Class<?> clazz, Object param)
			throws PartitionEmptyException {

		StringBuilder sql = new StringBuilder();
		sql.append(AutoSqlStatementHandlerInterceptor._sql_regex_update);
		StringBuilder intosql = new StringBuilder();
		StringBuilder valuessql = new StringBuilder();
		if (!clazz.isAnnotationPresent(MYBATISTABLE)) {
			sql.append("INSERT INTO " + clazz.getName());
		} else {
			String tableName = "";
			TableName antable = (TableName) clazz.getAnnotation(MYBATISTABLE);
			Class<?> paritionTabel =  antable.partitionName();
			Object partitionObj = null;
			try {
				partitionObj = paritionTabel.newInstance();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
			if(partitionObj instanceof ITableNameGenetator){
				try {
					ITableNameGenetator tableNameGenetator = (ITableNameGenetator) paritionTabel.newInstance();
					tableName = tableNameGenetator.tableName();
				} catch (Exception e) {
					throw new TableNameGeneratedException(new Exception("获取分区表名错误" ));
				}
			}else{
				if (antable.value() == "") {
					tableName = clazz.getName();
				} else {
					tableName = antable.value();
				}
			}
			sql.append("INSERT INTO " + tableName);
		}
		Field[] fields = clazz.getDeclaredFields();
		intosql.append("(");
		valuessql.append(" values (");
		for (Field field : fields) {
			//非数据库字段跳过sql生成
			if (field.isAnnotationPresent(MYBATISNOTDBFIELD)) {
				continue;
			}
			String fieldName = field.getName();
			if (field.isAnnotationPresent(PARTITIONFIELD)) {
				if (null==ReflectUtils.getFieldValue(fieldName,param)) {
					throw new PartitionEmptyException(new Exception(clazz.getName() + "类的"
							+ field.getName()
							+ "字段标记有分区注解@PartitionEmptyException,但是字段的值为空,请设置值!"));
				}
			}
			
			if (null != ReflectUtils.getFieldValue(field.getName(),param)) {
				if (field.isAnnotationPresent(MYBATISCOLUMN)) {
					ColumnName columnName = (ColumnName) field
							.getAnnotation(MYBATISCOLUMN);
					if (columnName.field().equals("")) {
						intosql.append(field.getName() + ",");
					} else {
						intosql.append(columnName.field() + ",");
					}
				} else {
					intosql.append(field.getName() + ",");
				}
				valuessql.append(PARAMETER_PLACEHOLDER + ",");

			}
		}
		return sql.append(intosql.substring(0, intosql.length() - 1))
				.append(") ")
				.append(valuessql.substring(0, valuessql.length() - 1))
				.append(")").toString();
	}

	private String updateEntity(Class<?> clazz,Object param) throws PartitionEmptyException, ConditionEmptyException {
		StringBuilder sql = new StringBuilder();
		StringBuilder set = new StringBuilder();
		StringBuilder wheresql = new StringBuilder();
		sql.append(AutoSqlStatementHandlerInterceptor._sql_regex_update);
		if (!clazz.isAnnotationPresent(MYBATISTABLE)) {
			sql.append("UPDATE  " + clazz.getName());
		} else {
			TableName antable = (TableName) clazz.getAnnotation(MYBATISTABLE);
			if (antable.value() == "") {
				sql.append("UPDATE  " + clazz.getName());
			} else {
				sql.append("UPDATE  " + antable.value());
			}
		}
		Field[] files = clazz.getDeclaredFields();
		set.append(" set ");
		wheresql.append(" where 1=1 ");
		for (Field field : files) {
			//非数据库字段跳过sql生成
			if (field.isAnnotationPresent(MYBATISNOTDBFIELD)) {
				continue;
			}
			String fieldName = field.getName();
			Object fieldVaule = ReflectUtils.getFieldValue(fieldName,param);
			if (null != fieldVaule) {
				ColumnName columnName = (ColumnName) field.getAnnotation(MYBATISCOLUMN);
				if (field.isAnnotationPresent(CONDITIONCOLUMN) || field.isAnnotationPresent(PARTITIONFIELD)) {
					if (null==columnName || columnName.field().equals("")) {
						wheresql.append(" and " + field.getName() + " = "+PARAMETER_PLACEHOLDER+"  ");
					}else {
						wheresql.append(" and " + columnName.field() + " = "+PARAMETER_PLACEHOLDER+"  ");
					}
				}else {
					if (null==columnName || columnName.field().equals("")) {
						set.append(field.getName() + " = "+PARAMETER_PLACEHOLDER+"  ,");
					} else {
						set.append(columnName.field() + " = "+PARAMETER_PLACEHOLDER+"  ,");
					}
				}

			}else{
				if (field.isAnnotationPresent(PARTITIONFIELD)) {
					throw new PartitionEmptyException(new Exception(clazz.getName() + "类的"
							+ field.getName()
							+ "字段标记有分区注解@PartitionEmptyException,但是字段的值为空,请设置值!"));
				}
				if (field.isAnnotationPresent(CONDITIONCOLUMN)) {
					throw new ConditionEmptyException(new Exception(clazz.getName() + "类的"
							+ field.getName()
							+ "字段标记有条件注解@ConditionColumn,但是字段的值为空,请设置值!"));
				}
			}
		}
		if (wheresql.equals(" where 1=1 ")) {
			throw new RuntimeException("实体变量没有设置ID字段值");
		}
		sql.append(set.substring(0, set.length() - 1)).append(
				wheresql.toString());
		return sql.toString();
	}

	public String deleteEntity(Class<?> clazz,Object param) throws PartitionEmptyException, ConditionEmptyException, DeleteEntityConditionMoreException {
		StringBuilder sql = new StringBuilder();
		sql.append(AutoSqlStatementHandlerInterceptor._sql_regex_update);
		StringBuilder wheresql = new StringBuilder(" where 1=1 ");
		if (!clazz.isAnnotationPresent(MYBATISTABLE)) {
			sql.append("delete from " + clazz.getName());
		} else {
			TableName antable = (TableName) clazz.getAnnotation(MYBATISTABLE);
			if (antable.value() == "") {
				sql.append("delete from " + clazz.getName());
			} else {
				sql.append("delete from " + antable.value());
			}
		}
		Field[] files = clazz.getDeclaredFields();
		for (Field field : files) {
			//非数据库字段跳过sql生成
			if (field.isAnnotationPresent(MYBATISNOTDBFIELD)) {
				continue;
			}
			String fieldName = field.getName();
			Object fieldVaule = ReflectUtils.getFieldValue(fieldName,param);
			if (null != fieldVaule) {
				if (field.isAnnotationPresent(CONDITIONCOLUMN) || field.isAnnotationPresent(PARTITIONFIELD)) {
					ColumnName columnName = (ColumnName) field
							.getAnnotation(MYBATISCOLUMN);
					if (null==columnName || columnName.field().equals("")) {
						wheresql.append(" and " + field.getName() + " = "+PARAMETER_PLACEHOLDER+"  ");
					} else {
						wheresql.append(" and " + columnName.field() + " = "+PARAMETER_PLACEHOLDER+"  ");
					}
				}else{
					throw new DeleteEntityConditionMoreException(new Exception(clazz.getName() + "类的"
							+ field.getName()
							+ "字段不是条件(@ConditionColumn)字段或者分区(@PartitionField)字段,其值只能为空,请清空此值!"));
				}
			}else{
				if (field.isAnnotationPresent(PARTITIONFIELD)) {
					throw new PartitionEmptyException(new Exception(clazz.getName() + "类的"
							+ field.getName()
							+ "字段标记有分区注解@PartitionEmptyException,但是字段的值为空,请设置值!"));
				}
				if (field.isAnnotationPresent(CONDITIONCOLUMN)) {
					throw new ConditionEmptyException(new Exception(clazz.getName() + "类的"
							+ field.getName()
							+ "字段标记有条件注解@ConditionColumn,但是字段的值为空,请设置值!"));
				}
			}
		}
		if (wheresql.equals(" where 1=1 ")) {
			throw new RuntimeException("实体变量没有设置ID字段值");
		}
		sql.append(wheresql.toString());
		return sql.toString();
	}
	private String findEntityById(Class<?> clazz){
	    StringBuilder sql = new StringBuilder();
	    if(!clazz.isAnnotationPresent(MYBATISTABLE)){
	      sql.append("select * from  "+clazz.getName());
	    }else{
	    	TableName antable = (TableName)clazz.getAnnotation(MYBATISTABLE);
	      if(antable.value() == ""){
	        sql.append("select * from  "+clazz.getSimpleName());
	      }else{
	        sql.append("select * from  "+antable.value());
	      }
	    }
	    sql.append("  where 1=1 ");
	    Field[] files = clazz.getDeclaredFields();
	    boolean falg = false;
	    for(Field file : files){
	      file.setAccessible(true);
	      if(file.isAnnotationPresent(ColumnName.class)){
	    	  ColumnName anColumn = (ColumnName)file.getAnnotation(MYBATISCOLUMN);
	        if(anColumn.isId()){//判断字段为主键
	          falg = true;
	          if(anColumn.field().equals("")){
	            sql.append(" and "+file.getName()+" = #{"+file.getName()+"},");
	          }else{
	            sql.append(" and "+file.getName()+" = #{"+file.getName()+"},");
	          }
	        }
	      }
	    }
	    if(!falg){
	      throw new RuntimeException("不能通过主键获取实体,因为实体中沒有定义主键!");
	    }
	    
	    return sql.subSequence(0, sql.length()-1).toString();
	  }
	/*private String selectEntity(Class<?> clazz ){
		return "";
	}*/
	public static String getExecuSQL( String mapperDBsql,
			Object param) throws PartitionEmptyException, ConditionEmptyException, DeleteEntityConditionMoreException {
		mapperDBsql = mapperDBsql.replaceAll(" ", "");
		Class<?> clazz = param.getClass();
		if (mapperDBsql.equals("cniemp.mybatis.autosql.update.insert.entity")) {
			return instance.insertEntity(clazz, param);// 保存单一实体
		} else if (mapperDBsql.equals("cniemp.mybatis.autosql.update.update.entity")) {
			return instance.updateEntity(clazz, param);// 保存单一实体
		}else if(mapperDBsql.equals("cniemp.mybatis.autosql.update.delete.entity")){
			return instance.deleteEntity(clazz,param);
	    }else if(mapperDBsql.equals("cniemp.mybatis.autosql.find.entity.byId")){
	      return instance.findEntityById(clazz);//id查询实体
	    }
		/*else if(mapperDBsql.equals("cniemp.mybatis.autosql.query.select.entity")){
			return instance.selectEntity(clazz);	//查询单一实体
	    }*/
		
		 /*if(mapperDBsql.equals("MapperGD.find.entitys")){
	      return MapperSqlHelper.App().findEntityAll(clazz,param,false);//条件查询实体列表
	    }else if(mapperDBsql.equals("MapperGD.find.entityById")){
	      return MapperSqlHelper.App().findEntityById(clazz);//id查询实体
	    }else if(mapperDBsql.equals("MapperGD.find.ListByLike")){
	      return MapperSqlHelper.App().findEntityAll(clazz,param,true);//条件查询实体列表
	    }else if(mapperDBsql.equals("MapperGD.insert.entity")){
	      return MapperSqlHelper.App().insertEntity(clazz);//保存单一实体
	    }else if(mapperDBsql.equals("MapperGD.update.entity")){
	      return MapperSqlHelper.App().updateEntityById(clazz);//保存单一实体
	    }else if(mapperDBsql.equals("MapperGD.update.entity.condistion")){
	      return mapperDBsql;
	    }else if(mapperDBsql.equals("MapperGD.delete.id")){
	      return MapperSqlHelper.App().deleteById(clazz);
	    }else if(mapperDBsql.equals("MapperGD.delete.condition")){
	      return MapperSqlHelper.App().deleteByCondition(clazz,param);
	    }else if(mapperDBsql.equals("MapperGD.count.condition")){
	      return MapperSqlHelper.App().count(clazz,param);
	    }else if(mapperDBsql.equals("MapperGD.find.entity.queryByVo")){
	      return MapperSqlHelper.App().queryByVo(clazz,param,false);
	    }else if(mapperDBsql.equals("MapperGD.find.entity.queryByVoLike")){
	      return MapperSqlHelper.App().queryByVo(clazz,param,true);
	    }*/
		return null;
	}

	// 预留接口
	/*private String updateEntityByConditions(Class<?> clazz, Object param) {

		return null;
	}*/

	public void getParam(Object param) {
		StringBuffer bf = new StringBuffer();
		if (isPrimitiveType(param.getClass())) {
			bf.append(param);
		} else if (param instanceof Map) {
			Map<String, Object> map = (Map) param;
		}
		
	}

	public static boolean isPrimitiveType(Class clazz) {
		return clazz != null
				&& (clazz.isPrimitive() || clazz.equals(Long.class)
						|| clazz.equals(Integer.class)
						|| clazz.equals(Short.class)
						|| clazz.equals(Byte.class)
						|| clazz.equals(Double.class)
						|| clazz.equals(Float.class)
						|| clazz.equals(Boolean.class)
						|| clazz.equals(Character.class) || clazz
							.equals(String.class));
	}
	
	
	/**
	   * 传入mapper接口class
	   * @param mapperclazz
	   * @return
	 * @throws PartitionEmptyException 
	   */
	  /*private  String updateEntityById(Class<?> clazz){
	    StringBuilder sql = new StringBuilder();
	    StringBuilder set = new StringBuilder();
	    StringBuilder wheresql = new StringBuilder();
	    if(!clazz.isAnnotationPresent(MYBATISTABLE)){
	      sql.append("UPDATE  "+clazz.getName());
	    }else{
	      MybatisTable antable = (MybatisTable)clazz.getAnnotation(MYBATISTABLE);
	      if(antable.value() == ""){
	        sql.append("UPDATE  "+clazz.getName());
	      }else{
	        sql.append("UPDATE  "+antable.value());
	      }
	    }
	    Field[] files = clazz.getDeclaredFields();
	    set.append(" set ");
	    wheresql.append(" where 1=1 ");
	    for(Field file : files){
	      file.setAccessible(true);
	      if(file.isAnnotationPresent(MYBATISCOLUMN)){
	        MyBatisColumn anColumn = (MyBatisColumn)file.getAnnotation(MYBATISCOLUMN);
	        if(!anColumn.isID()){//判断字段不为主键
	          if(anColumn.value().equals("")){
	            set.append(file.getName()+" = #{"+file.getName()+"} ,");
	          }else{
	            set.append(anColumn.value()+" = #{"+file.getName()+"} ,");
	          }
	        }else{
	          if(anColumn.value().equals("")){
	            wheresql.append(" and "+file.getName()+" = #{"+file.getName()+"},");
	          }else{
	            wheresql.append(" and "+anColumn.value()+" = #{"+anColumn.value()+"},");
	          }
	        }
	      }else{
	        set.append(file.getName()+" = #{"+file.getName()+"} ,");
	      }
	    }
	    if(wheresql.equals(" where 1=1 ")){
	      throw new RuntimeException("实体变量没有设置ID字段值");
	    }
	    sql.append(set.substring(0, set.length()-1)).append(wheresql.substring(0, wheresql.length()-1));
	    return sql.toString();
	  }
	  private String findEntityAll(Class<?> clazz,Object args,boolean islike){
	    StringBuilder sql = new StringBuilder();
	    StringBuilder whereSQL = new StringBuilder(" where 1=1 ");
	    StringBuffer orderby = new StringBuffer();
	    if(!clazz.isAnnotationPresent(MYBATISTABLE)){
	      sql.append("select * from  "+clazz.getName());
	    }else{
	      MybatisTable antable = (MybatisTable)clazz.getAnnotation(MYBATISTABLE);
	      if(antable.value() == ""){
	        sql.append("select * from  "+clazz.getSimpleName());
	      }else{
	        sql.append("select * from  "+antable.value());
	      }
	    }
	    Object[] paramObjs = (Object[]) ((Map)args).get("array");
	    if(paramObjs != null && paramObjs.length>0){
	      Object param = paramObjs[0];
	      if(param != null ){
	        if(param instanceof Map){
	          Map<String,Object> map = (Map)param;
	          if(map.containsKey("orderby")){
	            orderby.append(" order by "+map.get("orderby"));
	          }
	          if(map.containsKey("sortby")){
	            orderby.append(" "+map.get("sortby")+" ");
	          }
	          for(String key : map.keySet())
	          {
	            if(islike)
	            whereSQL.append(" and "+key + " like '%" + map.get(key)+"%',");
	            else whereSQL.append(" and "+key + " = '" + map.get(key)+"',");
	          }
	          sql.append(whereSQL.subSequence(0,whereSQL.length()-1)).append(orderby);
	        }else if(param instanceof Conditions){
	          Conditions<String,Object> map = (Conditions)param;
	          if(map.containsKey("orderby")){
	            orderby.append(" order by "+map.get("orderby"));
	          }
	          if(map.containsKey("sortby")){
	            orderby.append(" "+map.get("sortby")+" ");
	          }
	          for(String key : map.keySet())
	          {
	            if(key.equals("orderby") || key.equals("sortby")){
	              continue;
	            }
	            if(islike)
	            whereSQL.append(" and "+key + " like '%" + map.get(key)+"%',");
	            else whereSQL.append(" and "+key + " = '" + map.get(key)+"',");
	          }
	          sql.append(whereSQL.subSequence(0,whereSQL.length()-1)).append(orderby);
	        }
	      }
	    }
	    return sql.toString();
	  }
	  private String findEntityById(Class<?> clazz){
	    StringBuilder sql = new StringBuilder();
	    if(!clazz.isAnnotationPresent(MYBATISTABLE)){
	      sql.append("select * from  "+clazz.getName());
	    }else{
	      MybatisTable antable = (MybatisTable)clazz.getAnnotation(MYBATISTABLE);
	      if(antable.value() == ""){
	        sql.append("select * from  "+clazz.getSimpleName());
	      }else{
	        sql.append("select * from  "+antable.value());
	      }
	    }
	    sql.append("  where 1=1 ");
	    Field[] files = clazz.getDeclaredFields();
	    boolean falg = false;
	    for(Field file : files){
	      file.setAccessible(true);
	      if(file.isAnnotationPresent(MyBatisColumn.class)){
	        MyBatisColumn anColumn = (MyBatisColumn)file.getAnnotation(MYBATISCOLUMN);
	        if(anColumn.isID()){//判断字段不为主键
	          falg = true;
	          if(anColumn.value().equals("")){
	            sql.append(" and "+file.getName()+" = #{"+file.getName()+"},");
	          }else{
	            sql.append(" and "+file.getName()+" = #{"+file.getName()+"},");
	          }
	        }
	      }
	    }
	    if(!falg){
	      throw new RuntimeException("不能通過id查詢實體,實體中沒有定義@mybatisID");
	    }
	    
	    return sql.subSequence(0, sql.length()-1).toString();
	  }
	  public String deleteById(Class<?> clazz){
	    StringBuilder sql = new StringBuilder();
	    StringBuilder wheresql = new StringBuilder(" where 1=1 ");
	    if(!clazz.isAnnotationPresent(MYBATISTABLE)){
	      sql.append("delete  "+clazz.getName());
	    }else{
	      MybatisTable antable = (MybatisTable)clazz.getAnnotation(MYBATISTABLE);
	      if(antable.value() == ""){
	        sql.append("delete  "+clazz.getName());
	      }else{
	        sql.append("delete  "+antable.value());
	      }
	    }
	    Field[] files = clazz.getDeclaredFields();
	    for(Field file : files){
	      file.setAccessible(true);
	      if(file.isAnnotationPresent(MYBATISCOLUMN)){
	        MyBatisColumn anColumn = (MyBatisColumn)file.getAnnotation(MYBATISCOLUMN);
	        if(anColumn.isID()){//判断字段不为主键
	          if(anColumn.value().equals("")){
	            wheresql.append(" and "+file.getName()+" = #{"+file.getName()+"},");
	          }else{
	            wheresql.append(" and "+anColumn.value()+" = #{"+anColumn.value()+"},");
	          }
	        }
	      }
	    }
	    if(wheresql.equals(" where 1=1 ")){
	      throw new RuntimeException("实体变量没有设置ID字段值");
	    }
	    sql.append(wheresql.substring(0,wheresql.length()-1));
	    return sql.toString();
	  }
	  public String queryByVo(Class<?> clazz,Object args,boolean islike){
	    StringBuilder sql = new StringBuilder();
	    StringBuilder whereSQL = new StringBuilder(" where 1=1 ");
	    StringBuilder orderbySQL = new StringBuilder("");
	    if(!clazz.isAnnotationPresent(MYBATISTABLE)){
	      sql.append("select *  from "+clazz.getName());
	    }else{
	      MybatisTable antable = (MybatisTable)clazz.getAnnotation(MYBATISTABLE);
	      if(antable.value() == ""){
	        sql.append("select * from "+clazz.getName());
	      }else{
	        sql.append("select * from "+antable.value());
	      }
	    }
	    if(args instanceof Map){
	      Map map = (Map)args;
	      Object[] arr = (Object[])map.get("param3");
	      if(arr.length>0){
	        Map<String,Object> params = (Map)arr[0];
	        if(params.containsKey("orderby")){
	          orderbySQL.append(" order by "+params.get("orderby"));
	        }
	        if(params.containsKey("sortby")){
	          orderbySQL.append(" "+params.get("sortby")+" ");
	        }
	        for(String key:params.keySet()){
	          if(key.equals("orderby") || key.equals("sortby")){
	            continue;
	          }
	          if(islike)
	            whereSQL.append(" and "+key + " like '%" + params.get(key)+"%',");
	            else whereSQL.append(" and "+key + " = '" + params.get(key)+"',");
	        }
	      }
	    }
	    
	    return sql.append(whereSQL.substring(0, whereSQL.length()-1)).append(orderbySQL).toString();
	  }
	  public String count(Class<?> clazz,Object args){
	    StringBuilder sql = new StringBuilder();
	    StringBuilder whereSQL = new StringBuilder(" where 1=1 ");
	    if(!clazz.isAnnotationPresent(MYBATISTABLE)){
	      sql.append("select count(*)  from "+clazz.getName());
	    }else{
	      MybatisTable antable = (MybatisTable)clazz.getAnnotation(MYBATISTABLE);
	      if(antable.value() == ""){
	        sql.append("select count(*) from "+clazz.getName());
	      }else{
	        sql.append("select count(*) from "+antable.value());
	      }
	    }
	    Object[] paramObjs = (Object[]) ((Map)args).get("array");
	    if(paramObjs != null && paramObjs.length>0){
	      Object param = paramObjs[0];
	      if(param != null ){
	        if(param instanceof Map){
	          Map<String,Object> map = (Map)param;
	          for(String key : map.keySet())
	          {
	            whereSQL.append(" and "+key + " = '" + map.get(key)+"',");
	          }
	          sql.append(whereSQL.subSequence(0,whereSQL.length()-1));
	        }else if(param instanceof Conditions){
	          Conditions<String,Object> map = (Conditions)param;
	          for(String key : map.keySet())
	          {
	            whereSQL.append(" and "+key + " = '" + map.get(key)+"',");
	          }
	          sql.append(whereSQL.subSequence(0,whereSQL.length()-1));
	        }
	      }
	    }
	    return sql.toString();
	  }
	  public String deleteByCondition(Class<?> clazz,Object param){
	    StringBuilder sql = new StringBuilder();
	    StringBuilder whereSQL = new StringBuilder(" where 1=1 ");
	    if(!clazz.isAnnotationPresent(MYBATISTABLE)){
	      sql.append("delete  "+clazz.getName());
	    }else{
	      MybatisTable antable = (MybatisTable)clazz.getAnnotation(MYBATISTABLE);
	      if(antable.value() == ""){
	        sql.append("delete  "+clazz.getName());
	      }else{
	        sql.append("delete  "+antable.value());
	      }
	    }
	    if(param != null ){
	      if(param instanceof Map){
	        Map<String,Object> map = (Map)param;
	        for(String key : map.keySet())
	        {
	          whereSQL.append(" and "+key + " = '" + map.get(key)+"',");
	        }
	        sql.append(whereSQL.subSequence(0,whereSQL.length()-1));
	      }else if(param instanceof Conditions){
	        Conditions<String,Object> map = (Conditions)param;
	        for(String key : map.keySet())
	        {
	          whereSQL.append(" and "+key + " = '" + map.get(key)+"',");
	        }
	        sql.append(whereSQL.subSequence(0,whereSQL.length()-1));
	      }
	    }
	    if(whereSQL.equals(" where 1=1 ")){
	      throw new RuntimeException("实体变量没有设置ID字段值");
	    }
	    return sql.toString();
	  }
	  */
}
