package com.sfxie.component.ui;

import java.util.ArrayList;
import java.util.List;

public class ResourceEntity {

	private List<String> jsPath;

	private List<String> cssPath;
	
	private String contextPath;
	
	private List<String> pageUri;

	private String description;
	
	public ResourceEntity(){}

	public ResourceEntity(String contextPath) {
		this.contextPath = contextPath;
	}

	public ResourceEntity addDescription(String description){
		this.description = description;
		return this;
	}

	public ResourceEntity addJsPath(String jsPath){
		getJsPath().add(jsPath);
		return this;
	}
	
	public ResourceEntity addCssPath(String cssPath){
		getCssPath().add(cssPath);
		return this;
	}
	
	public ResourceEntity addPageUri(String pageUri){
		getPageUri().add(pageUri);
		return this;
	}

	public List<String> getJsPath() {
		if(null==jsPath)
			jsPath = new ArrayList<String>();
		return jsPath;
	}

	public void setJsPath(List<String> jsPath) {
		this.jsPath = jsPath;
	}

	public List<String> getCssPath() {
		if(null==cssPath)
			cssPath = new ArrayList<String>();
		return cssPath;
	}

	public void setCssPath(List<String> cssPath) {
		this.cssPath = cssPath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public List<String> getPageUri() {
		if(null==pageUri)
			pageUri = new ArrayList<String>();
		return pageUri;
	}

	public void setPageUri(List<String> pageUri) {
		this.pageUri = pageUri;
	}
}
