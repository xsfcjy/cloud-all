package com.sfxie.services.center.pojo.tree;

import com.sfxie.component.node.CustomerNodeEntity;

/**
 * 组织结构树实体
 * @author xiesf
 * @since 2017年7月18日 上午10:31:26
 */
public class OrganizationTreePojo extends CustomerNodeEntity {
	
	private String id;
	
	private String pid;

	private String name;

	private String level;

	private boolean isParent;

	private String partitionCompany;

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
		return partitionCompany;
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
		return this.name;
	}

	public String getPid() {
		return pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setPid(String parentId) {
		this.pid = parentId;
	}

	@Override
	public String getParentId() {
		// TODO Auto-generated method stub
		return this.pid;
	}

}