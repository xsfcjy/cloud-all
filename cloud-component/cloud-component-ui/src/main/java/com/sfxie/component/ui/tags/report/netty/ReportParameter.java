package com.sfxie.component.ui.tags.report.netty;

/**
 * 报表参数基础类
 * 
 * @author xiesf
 *
 */
public class ReportParameter {

	/** 参数名称(form表单名称) */
	private String name;
	/** 参数名称(显示名称) */
	private String title;
	/** 参数类型 */
	private String type;
	/** 参数默认值 */
	private Object value;
	/** html元素类型 */
	private String elementType;
	/** html元素校验器 */
	private String validator;
	
	
	

	public ReportParameter() {
	}

	public ReportParameter(String name, String title, String type,String elementType,Object value) {
		this.name = name;
		this.title = title;
		this.type = type;
		this.elementType = elementType;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getValidator() {
		return validator;
	}

	public void setValidator(String validator) {
		this.validator = validator;
	}
	

}
