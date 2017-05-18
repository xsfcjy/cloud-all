package com.sfxie.component.ui;

public abstract class UI {
	
	/** 组件id属性	*/
	private String id;
	/** 组件class属性	*/
	private String cls;
	
	/** 组件data-options属性	*/
	private String dataOptions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}
	
	

	public String getDataOptions() {
		return dataOptions;
	}

	public void setDataOptions(String dataOptions) {
		this.dataOptions = dataOptions;
	}

	/**
	 * 生成html内容
	 * @return
	 */
	protected abstract String toHtml();

}
