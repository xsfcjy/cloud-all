package com.sfxie.services.center.dao.mapper;

import java.util.List;
import java.util.Map;

import com.sfxie.services.center.pojo.tree.OrganizationTreePojo;


public interface OrganizationTreeMapper {
	/**
	 * 根据用户id获取组织结构数据
	 * @param map
	 * @return
	 */
	public List<OrganizationTreePojo> selectByUserId(Map<String,String> map);
	public List<OrganizationTreePojo> selectByParentCompanyCode(Map<String,Object> map);
}