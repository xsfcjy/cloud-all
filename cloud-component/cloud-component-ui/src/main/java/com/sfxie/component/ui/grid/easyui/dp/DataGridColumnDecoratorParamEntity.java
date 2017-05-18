package com.sfxie.component.ui.grid.easyui.dp;

import java.util.List;
import java.util.Map;

import com.sfxie.component.ui.dataprovider.DataProviderParameter;

public class DataGridColumnDecoratorParamEntity extends DataProviderParameter{
	
	private List<DataGridColumnDecoratorParamEntity> data;
	/**	字段名	*/
	private String field;
	private String type;
	private String dataRegister;
	private String valueListFieldName;
	private String valueListFieldType;
	
	private Map<String,Object> parameter;
	
	private Object[] valueList;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDataRegister() {
		return dataRegister;
	}

	public void setDataRegister(String dataRegister) {
		this.dataRegister = dataRegister;
	}

	public Map<String, Object> getParameter() {
		return parameter;
	}

	public void setParameter(Map<String, Object> parameter) {
		this.parameter = parameter;
	}

	public Object[] getValueList() {
		return valueList;
	}

	public void setValueList(Object[] valueList) {
		this.valueList = valueList;
	}

	public String getValueListFieldName() {
		return valueListFieldName;
	}

	public void setValueListFieldName(String valueListFieldName) {
		this.valueListFieldName = valueListFieldName;
	}

	public String getValueListFieldType() {
		return valueListFieldType;
	}

	public void setValueListFieldType(String valueListFieldType) {
		this.valueListFieldType = valueListFieldType;
	}

	public List<DataGridColumnDecoratorParamEntity> getData() {
		return data;
	}

	public void setData(List<DataGridColumnDecoratorParamEntity> data) {
		this.data = data;
	}
	
	
	
	
}
