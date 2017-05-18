package com.sfxie.component.ui;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;


public class ResourceLoader {

	private ResourceList resourceList;
	
	
	public void writeResource(Writer writer,PageContext pageContext){
		if(null!=resourceList){
			List<ResourceEntity> resources = resourceList.getResources();
			if(null!=resources && resources.size()>0){
				String contextPath = pageContext.getServletContext().getContextPath();
				StringBuffer sb = new StringBuffer("");
				String pageUrl = ((HttpServletRequest)pageContext.getRequest()).getRequestURI();
				for(ResourceEntity resourceEntity : resources){
					List<String> pageUris = resourceEntity.getPageUri();
					//如果设置了pageUris属性，并且当前页面不在pageUris找到，则不加载此resourceEntity的网页资源
					if(pageUris.size() > 0 && !pageUris.contains(pageUrl)){
						continue;
					}
					contextPath = (null!=resourceEntity.getContextPath()?resourceEntity.getContextPath():contextPath);
					if(null!=resourceEntity.getDescription()){
						sb.append("<!--	").append(resourceEntity.getDescription()).append("	-->");
					}
					if(null!=resourceEntity.getCssPath() && resourceEntity.getCssPath().size()>0){
						for(String css : resourceEntity.getCssPath()){
							sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath)
							.append(css)
							.append("\">");
						}
					}
					if(null!=resourceEntity.getJsPath() && resourceEntity.getJsPath().size()>0){
						for(String js : resourceEntity.getJsPath()){
							sb.append("<script type=\"text/javascript\" src=\""+contextPath)
							.append(js)
							.append("\"></script>");
						}
					}
				}
				try {
					writer.write(sb.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public ResourceList getResourceList() {
		return resourceList;
	}

	public void setResourceList(ResourceList resourceList) {
		this.resourceList = resourceList;
	}
}
