package com.sfxie.services.center.pojo.tree;

import com.sfxie.component.node.CustomerNodeEntity;
import com.sfxie.component.ui.i18n.I18NUtil;
import com.sfxie.services.center.util.ServicesContext;

/**
 * 组织结构树实体
 * @author xiesf
 * @since 2017年7月18日 上午10:31:26
 */
public class MenuTreePojo extends CustomerNodeEntity {
	
	private String id;
	
	private String pid;

	private String name;

	private String menuLevel;

	private boolean isParent;

	private String partitionCompany;
	
	private String iconSkin;
	
	private String  menuType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public String getPartitionCompany() {
		return partitionCompany == null ? ServicesContext.getPartitionCompany(pid)
				: partitionCompany.trim();
	}

	public void setPartitionCompany(String partitionCompany) {
		this.partitionCompany = partitionCompany == null ? null
				: partitionCompany.trim();
	}

	@Override
	public String getMenuCode() {
		return this.id;
	}

	@Override
	public String getMenuName() {
		return I18NUtil.getProperty(this.name);
	}

	public String getPid() {
		return pid;
	}

	public String getName() {
		return I18NUtil.getProperty(this.name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public void setPid(String parentId) {
		this.pid = parentId;
	}

	@Override
	public String getParentId() {
		// TODO Auto-generated method stub
		return this.pid;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

}