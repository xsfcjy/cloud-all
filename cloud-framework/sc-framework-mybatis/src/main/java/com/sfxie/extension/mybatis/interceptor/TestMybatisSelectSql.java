package com.sfxie.extension.mybatis.interceptor;

import java.util.ArrayList;
import java.util.List;

public class TestMybatisSelectSql implements IMybatisQueryCondition {

	@Override
	public List<QueryConditionEntity> getQueryConditionEntityList() {
		// TODO Auto-generated method stub
		List<QueryConditionEntity> list = new ArrayList<QueryConditionEntity>();
		list.add(new QueryConditionEntity("name","condition.name",String.class,"="));
		return list;
	}

//	@Override
//	public Map<String, Object> getParameterMap() {
//		
//		Map<String, Object> parameterMap = new HashMap<String, Object>();
//		parameterMap.put("name_", "TEST_update");
//		return parameterMap;
//	}
//
//	@Override
//	public String getSelectSql() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
