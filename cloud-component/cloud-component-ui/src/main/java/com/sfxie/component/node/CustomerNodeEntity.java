package com.sfxie.component.node;

/**
 * 前端菜单数据接口
 * 
 * @author xiesf
 *
 */
public abstract class CustomerNodeEntity {

	/**
	 * 菜单id
	 * 
	 * @return
	 */
	public abstract String getId();

	/**
	 * 菜单编码
	 * 
	 * @return
	 */
	public abstract String getMenuCode();

	/**
	 * 菜单名称
	 * 
	 * @return
	 */
	public abstract String getMenuName();

	/**
	 * 父菜单id
	 * 
	 * @return
	 */
	public abstract String getParentId();
}
