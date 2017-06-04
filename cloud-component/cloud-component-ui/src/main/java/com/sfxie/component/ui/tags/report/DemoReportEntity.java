package com.sfxie.component.ui.tags.report;

public class DemoReportEntity {

	private String mmName;

	private String name;

	private String title;

	private String option;

	private String content;
	
	

	public DemoReportEntity(String mmName, String name, String title,
			String option, String content) {
		super();
		this.mmName = mmName;
		this.name = name;
		this.title = title;
		this.option = option;
		this.content = content;
	}

	public String getMmName() {
		return mmName;
	}

	public void setMmName(String mmName) {
		this.mmName = mmName;
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

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
