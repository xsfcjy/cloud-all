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
		if (componentName.equals("jsp.center.organization.window.organizationCompanyEdit")) {
			return "organizationCompanyEdit.jsp";
		}else if (componentName.equals("jsp.center.organization.window.organizationDepartmentEdit")) {
			return "organizationDepartmentEdit.jsp";
		}else if (componentName.equals("jsp.center.organization.window.organizationPostEdit")) {
			return "organizationPostEdit.jsp";
		}else if (componentName.equals("jsp.center.organization.list.organizationUserList")) {
			return "organizationUserList.jsp";
		}else if (componentName.equals("jsp.center.organization.list.organizationRoleList")) {
			return "organizationRoleList.jsp";
		}else if (componentName.equals("jsp.center.organization.list.organizationUserrelationEdit")) {
			return "organizationUserrelationEdit.jsp";
		}else if (componentName.equals("jsp.center.organization.list.organizationRoleEdit")) {
			return "organizationRoleEdit.jsp";
		}else if (componentName.equals("jsp.center.menu.list.actionList")) {
			return "actionList.jsp";
		}else if (componentName.equals("jsp.center.menu.list.dataList")) {
			return "dataList.jsp";
		}else if (componentName.equals("jsp.center.menu.list.menuEdit")) {
			return "menuEdit.jsp";
		}else if (componentName.equals("jsp.center.organization.list.organizationDataAuthList")) {
			return "";
		}
		return "";
	}

}
