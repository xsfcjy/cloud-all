package com.sfxie.component.ui.tree.ztree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sfxie.component.node.CustomerNodeEntity;
import com.sfxie.component.node.NodeComponent;
import com.sfxie.component.node.NodeComponentSort;
import com.sfxie.component.node.NodeDecorator;

/**
 * 菜单工具类
 * @author xiesf
 * @since 2017-05-05
 *
 */
public class TreeUtil {

	/**
	 * 生成菜单结构数据
	 * @param menus
	 * @return
	 * @throws Exception 
	 */
	public static List<NodeComponent> generateMenus(List<? extends CustomerNodeEntity> menus,NodeDecorator<CustomerNodeEntity, ZTreeNode, ?> menuDecorator) throws Exception{
		List<NodeComponent> result = null;
		if(null!=menus){
			result = new ArrayList<NodeComponent>();
			Map<String,NodeComponent> nullParentMenus = new HashMap<String,NodeComponent> ();
			Map<String,NodeComponent> nodeKeyMenus = new HashMap<String,NodeComponent> ();
			Map<String,NodeComponent> parentAndleftKeyMenus = new HashMap<String,NodeComponent> ();
			
			
			//生成菜单临时数据节点
			for(CustomerNodeEntity menu : menus){
				ZTreeNode parentNode = new ZTreeNode(menu.getMenuCode(),menu.getMenuName());
				parentNode.setId(menu.getId());
				if(null==menu.getParentId()){
					String id = menu.getId();
					menuDecorator.decorateParentMenu(menu, parentNode);
					nullParentMenus.put(id, parentNode);
					nodeKeyMenus.put(id, parentNode);
				}else{
					String parentId = menu.getParentId();
					parentNode.setPId(parentId);
					String id = menu.getId();
					if(null==nodeKeyMenus.get(id)){
						menuDecorator.decorateParentMenu(menu, parentNode);
						nodeKeyMenus.put(id, parentNode);
					}
					if(null==parentAndleftKeyMenus.get(parentId+"_"+id)){
						menuDecorator.decorateParentMenu(menu, parentNode);
						parentAndleftKeyMenus.put(parentId+"_"+id, parentNode);
					}
				}
			}
			//构造树结构
			for(Entry<String, NodeComponent> menu : parentAndleftKeyMenus.entrySet()){
				String[] keys = menu.getKey().split("_");
				String parentKey = keys[0];
				String key = keys[1];
				NodeComponent parentNode = nodeKeyMenus.get(parentKey);
				if(null!=parentNode){
					parentNode.add(nodeKeyMenus.get(key));
					//排序子树
					parentNode.getNodes().sort(new NodeComponentSort());
				}
			}
			//排序父树
			for(Entry<String, NodeComponent> ParentNode : nullParentMenus.entrySet()){
				NodeComponent menuComponent = ParentNode.getValue();
				//排序子树
				if(null!=menuComponent.getNodes()){
					menuComponent.getNodes().sort(new NodeComponentSort());
				}
				result.add(menuComponent);
			}
			result.sort(new NodeComponentSort());
		}
		return result;
	}
}
