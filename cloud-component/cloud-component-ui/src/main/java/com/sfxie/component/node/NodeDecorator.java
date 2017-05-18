package com.sfxie.component.node;



/**
 * 生成菜单工具生成菜单时的辅助抽象类
 * @author xiesf
 *
 * @param <T>
 * 		实际返回到前端的菜单数据类
 */
public abstract class NodeDecorator<T extends CustomerNodeEntity,P extends NodeComponent,L extends NodeComponent> {

	/**
	 * 父菜单生成辅助方法 
	 * @param <T>
	 * 		实际返回到前端的菜单数据类
	 * @param parentNode
	 * 		父菜单结点类
	 */
	public abstract void decorateParentMenu(T menu, P parentNode);
	
	/**
	 * 子菜单生成辅助方法 
	 * @param <T>
	 * 		实际返回到前端的菜单数据类
	 * @param leftNode
	 * 		叶子菜单节点类
	 */
	public abstract void decorateLeftMenu(T menu, L leftNode);
}
