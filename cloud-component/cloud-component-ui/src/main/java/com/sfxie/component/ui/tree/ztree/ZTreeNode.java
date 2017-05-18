package com.sfxie.component.ui.tree.ztree;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sfxie.component.node.NodeComponent;
import com.sfxie.component.node.ParentNode;

public class ZTreeNode extends ParentNode {
	

	
	public ZTreeNode(String menuCode, String menuName) {
		super(menuCode, menuName);
	}

	@JsonInclude(Include.NON_NULL)
	private Boolean open;

	@JsonInclude(Include.NON_NULL)
	private Boolean isParent;
	
	private String pId;

	@JsonInclude(Include.NON_NULL)
	private String name;
	

	@JsonInclude(Include.NON_NULL)
	protected List<NodeComponent> getChildren(){
		return this.getNodes();
	}


	public boolean isOpen() {
		return open;
	}


	public void setOpen(boolean open) {
		this.open = open;
	}


	public boolean isParent() {
		return isParent;
	}


	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}


	public String getPId() {
		return this.pId;
	}


	public void setPId(String pId) {
		this.pId = pId;
	}
	
	
	
	

}
