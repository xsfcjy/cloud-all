package com.sfxie.services.center.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.services.center.dao.mapper.MenuTreeMapper;
import com.sfxie.services.center.pojo.PartitionData;
import com.sfxie.services.center.pojo.tree.MenuTreePojo;
import com.sfxie.services.center.util.ServicesContext;

@Service
public class MenuTreeServiceImpl {

	@Resource
	private MenuTreeMapper mapper;
	/**
	 * 根据用户id获取组织结构数据
	 * @param map
	 * @return
	 */
	public List<MenuTreePojo> selectByUserId(String userId){
		PartitionData record = new PartitionData();
		record.setCreateUser(userId);
		record.setPartitionCompany(ServicesContext.getUserDefaultPartitionCompany());
		return mapper.selectByUserId(record);
	}
	

	public List<MenuTreePojo> selectByParentCode(String menuType,String parentCode){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parentCode", parentCode);
		map.put("partitionCompany", ServicesContext.getUserDefaultPartitionCompany());
		map.put("menuType", menuType);
		return mapper.selectByParentCode(map);
	}
	

}