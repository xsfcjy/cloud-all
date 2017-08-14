package com.sfxie.services.center.dao.mapper;

import java.util.List;
import java.util.Map;

import com.sfxie.services.center.pojo.PartitionData;
import com.sfxie.services.center.pojo.tree.MenuTreePojo;


public interface MenuTreeMapper {
	/**
	 * 根据用户id获取组织结构数据
	 * @param map
	 * @return
	 */
	public List<MenuTreePojo> selectByUserId(PartitionData partitionData);
	public List<MenuTreePojo> selectByParentCode(Map<String,Object> map);
	
	
}