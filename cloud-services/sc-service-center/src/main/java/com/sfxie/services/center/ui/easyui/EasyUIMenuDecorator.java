package com.sfxie.services.center.ui.easyui;

import com.sfxie.component.node.LeftNode;
import com.sfxie.component.node.NodeDecorator;
import com.sfxie.component.node.ParentNode;
import com.sfxie.services.center.pojo.SfxieSysMenu;

public class EasyUIMenuDecorator extends NodeDecorator<SfxieSysMenu,ParentNode,LeftNode> {

	@Override
	public void decorateParentMenu(SfxieSysMenu menu, ParentNode parentNode) {
		parentNode.setSequenceNo(menu.getSequenceNo());
	}

	@Override
	public void decorateLeftMenu(SfxieSysMenu sfxieSysMenu, LeftNode leftNode) {
		leftNode.setUrl(sfxieSysMenu.getUrl());
		leftNode.setSequenceNo(sfxieSysMenu.getSequenceNo());
	}

}
