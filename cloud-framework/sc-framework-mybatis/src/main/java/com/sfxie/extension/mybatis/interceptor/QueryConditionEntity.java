package com.sfxie.extension.mybatis.interceptor;


public class QueryConditionEntity {
	/** 条件对应的java成员变量名   */
	private String fieldName;
	/** 条件对应的数据库字段名   */
	private String dbFieldName;
	/** 条件的操作符   */
	private String operator;
	/** 条件的类型:and/or  */
	private String coditionType;
	/** 条件字段的java类型   */
	private Class<?> fieldClass;
	/** 条件所在的组名  */
	private String groupName;
	
	
	public QueryConditionEntity(){}
	
	/** 查询操作符: = */
	public final static String OPERATOR_EQUAL = "=";
	/** 查询操作符: like */
	public final static String OPERATOR_LIKE = "like";
	/** 查询操作符: is null */
	public final static String OPERATOR_ISNULL = "is null";
	/** 查询操作符: is not null */
	public final static String OPERATOR_ISNOTNULL = "is not null";
	
	public final static String CONDITIONTYPE_EMPTY = "";
	public final static String CONDITIONTYPE_AND = "and";
	public final static String CONDITIONTYPE_OR = "or";
//	private Object value;
	/**
	 * 
	 * @param fieldName
	 * 		实体字段名
	 * @param value
	 * 		实体值
	 * @param operator
	 * 		条件操作符
	 * @param hqlVauleDecorater
	 * 		值装潢器
	 */
	public QueryConditionEntity(String dbFieldName,String fieldName,Class<?> fieldClass ,String operator ){
		this.dbFieldName = dbFieldName;
		this.fieldName = fieldName;
		this.fieldClass = fieldClass;
		this.operator = operator;
	}
	
	/**
	 * 
	 * @param fieldName
	 * @param fieldClass
	 * @param dbFieldName
	 * @param operator
	 * @param coditionType
	 * @param groupName
	 */
	public QueryConditionEntity(String fieldName, Class<?> fieldClass, String dbFieldName,String operator, String coditionType,String groupName) {
		super();
		this.fieldName = fieldName;
		this.dbFieldName = dbFieldName;
		this.operator = operator;
		this.coditionType = coditionType;
		this.fieldClass = fieldClass;
		this.groupName = groupName;
	}


	/**
	 * 
	 * @param fieldName
	 * 		实体字段名
	 * @param value
	 * 		实体值
	 * @param operator
	 * 		条件操作符
	 * @param hqlVauleDecorater
	 * 		值装潢器
	 */
	/*public QueryConditionEntity(String fieldName,Object value ,String operator ){
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}*/
	/**
	 * 
	 * @param fieldName
	 * 		实体字段名
	 * @param dbFieldName
	 * 		对应数据库字段名
	 * @param value
	 * 		实体值
	 * @param operator
	 * 		条件操作符
	 * @param hqlVauleDecorater
	 * 		值装潢器
	 */
	/*public QueryConditionEntity(String fieldName ,String dbFieldName,Object value,String operator ){
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
		this.dbFieldName = dbFieldName;
	}*/
	
	public String getFieldName() {
		return fieldName;
	}
	public String getMethodFieldName() {
		byte[] items = fieldName.getBytes();  
        items[0] = (byte) ((char) items[0] - 'a' + 'A');  
        return new String(items);  
	}
	public String getOperator() {
		return operator;
	}
	public String getDbFieldName() {
		return dbFieldName;
	}
//	public void setDbFieldName(String dbFieldName) {
//		this.dbFieldName = dbFieldName;
//	}
	public Class<?> getFieldClass() {
		return fieldClass;
	}
	public String getCoditionType() {
		return null==coditionType?"and":coditionType;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
//	public Object getValue() {
//		return value;
//	}

}
