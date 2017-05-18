package com.sfxie.services.center.ui.easyui;

import com.sfxie.component.node.NodeDecorator;
import com.sfxie.component.ui.tree.ztree.ZTreeNode;
import com.sfxie.services.center.pojo.SfxieSysMenu;

public class ZTreeUIDecorator extends NodeDecorator<SfxieSysMenu,ZTreeNode,ZTreeNode> {

	@Override
	public void decorateParentMenu(SfxieSysMenu sfxieSysMenu, ZTreeNode parentNode) {
		parentNode.setUrl(sfxieSysMenu.getUrl());
		parentNode.setSequenceNo(sfxieSysMenu.getSequenceNo());
	}

	@Override
	public void decorateLeftMenu(SfxieSysMenu sfxieSysMenu, ZTreeNode leftNode) {
	}

}
