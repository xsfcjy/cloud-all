package com.sfxie.component.ui.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sfxie.component.node.CustomerNodeEntity;
import com.sfxie.component.node.LeftNode;
import com.sfxie.component.node.NodeComponent;
import com.sfxie.component.node.NodeComponentSort;
import com.sfxie.component.node.NodeDecorator;
import com.sfxie.component.node.ParentNode;

/**
 * 菜单工具类
 * @author xiesf
 * @since 2017-05-05
 *
 */
public class MenuUtil {

	/**
	 * 生成菜单结构数据
	 * @param menus
	 * @return
	 * @throws Exception 
	 */
	public static List<NodeComponent> generateMenus(List<? extends CustomerNodeEntity> menus,NodeDecorator menuDecorator) throws Exception{
		List<NodeComponent> result = null;
		if(null!=menus){
			result = new ArrayList<NodeComponent>();
			Map<String,NodeComponent> parentResultMenus = new HashMap<String,NodeComponent> ();
			Map<String,CustomerNodeEntity> parentMenus = new HashMap<String,CustomerNodeEntity> ();
			Map<String,List<CustomerNodeEntity>> childMenus = new HashMap<String,List<CustomerNodeEntity>> ();
			//生成菜单临时数据节点
			for(CustomerNodeEntity menu : menus){
				if(null==menu.getParentId()){
					String menuId = menu.getId();
					parentMenus.put(menuId, menu );
				}else{
					String parentId = menu.getParentId();
					if(null==childMenus.get(parentId)){
						childMenus.put(parentId, new ArrayList<CustomerNodeEntity>());
					}
					childMenus.get(parentId).add(menu);
				}
			}

			//生成父菜单
			for(String key : parentMenus.keySet()){
				CustomerNodeEntity menu = parentMenus.get(key);
				ParentNode parentNode = new ParentNode(menu.getMenuCode(),menu.getMenuName());
				menuDecorator.decorateParentMenu(menu, parentNode);;
				parentResultMenus.put(key, parentNode);
			}
			//生成子菜单
			for(String key : childMenus.keySet()){
				ParentNode parentNode = (ParentNode) parentResultMenus.get(key);
				for(CustomerNodeEntity menu : childMenus.get(key)){
					LeftNode leftNode = new LeftNode(menu.getMenuCode(),menu.getMenuName());
					menuDecorator.decorateLeftMenu(menu, leftNode);
					parentNode.add(leftNode);
				}
			}
			
			for(Entry<String, NodeComponent> ParentNode : parentResultMenus.entrySet()){
				NodeComponent menuComponent = ParentNode.getValue();
				//排序子菜单
				if(null!=menuComponent.getNodes()){
					menuComponent.getNodes().sort(new NodeComponentSort());
				}
				result.add(menuComponent);
			}
			//排序父菜单
			result.sort(new NodeComponentSort());
		}
		return result;
	}
}
