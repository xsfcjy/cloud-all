package com.sfxie.extension.mybatis.interceptor;

import java.util.ArrayList;
import java.util.List;

public class QueryGroupConditionEntity {
	
	
	/** 条件所在的组名  */
	private String parentGroupName;
	/** 条件的类型:and/or  */
	private String coditionType;
	
	private String name;
	
	private String finalSql;
	
	private List<QueryGroupConditionEntity> childGroup ;
	
	public QueryGroupConditionEntity(){
		
	}
	public QueryGroupConditionEntity(String parentGroupName, String coditionType) {
		this.parentGroupName = parentGroupName;
		this.coditionType = coditionType;
	}
	
	public String getParentGroupName() {
		return parentGroupName;
	}
	public String getCoditionType() {
		return null==coditionType?"":coditionType;
	}

	public List<QueryGroupConditionEntity> getChildGroup() {
		if(null==childGroup)
			childGroup = new ArrayList<QueryGroupConditionEntity>();
		return childGroup;
	}

	public void setChildGroup(List<QueryGroupConditionEntity> childGroup) {
		this.childGroup = childGroup;
	}

	public String getFinalSql() {
		return finalSql;
	}

	public void setFinalSql(String finalSql) {
		this.finalSql = finalSql;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentGroupName(String parentGroupName) {
		this.parentGroupName = parentGroupName;
	}

	public void setCoditionType(String coditionType) {
		this.coditionType = coditionType;
	}

	public String createQueryConditionSql(List<QueryConditionEntity> queryConditionEntityList){
		StringBuffer conditionSql = new StringBuffer();
		boolean isFirst = true ;
		for(QueryConditionEntity conditionEntity : queryConditionEntityList){
			if(null!=conditionEntity.getGroupName() && conditionEntity.getGroupName().equals(parentGroupName)){
				if(isFirst){
					isFirst = false;
					conditionSql.append(" ")
//					   .append(coditionType)
//					   .append(" ")
//					   .append("(")
//					   .append(" ")
					   .append(conditionEntity.getDbFieldName())
					   .append(" ")
					   .append(conditionEntity.getOperator())
					   .append(" ? ")
					   ;
				}else{
					conditionSql.append(" ")
					   .append(conditionEntity.getCoditionType())
					   .append(" ")
					   .append(conditionEntity.getDbFieldName())
					   .append(" ")
					   .append(conditionEntity.getOperator())
					   .append(" ? ")
					   ;
				}
			}
			
		}
		return conditionSql.toString();
		
	}
	

	
	
}
