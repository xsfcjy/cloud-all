package com.sfxie.web.boot.util;

/**
 * 构件库控制类
 * @author xiesf
 * @since 2017年7月20日 下午3:07:45
 */
public class ComponentDerector {
	
	/**
	 * 获取具体组件
	 * 后期是准备做成构件库的形式保存在数据中
	 * @param componentName
	 * 			组件名称
	 * @return
	 */
	public static String getComponentPath(String componentName) {
		if (componentName.equals("jsp.center.organization.window.editorganization")) {
			return "organizationCompanyEdit.jsp";
		}
		return "";
	}

}
