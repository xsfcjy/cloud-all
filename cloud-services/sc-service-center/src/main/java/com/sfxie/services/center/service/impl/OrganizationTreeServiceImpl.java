package com.sfxie.services.center.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.services.center.dao.mapper.OrganizationTreeMapper;
import com.sfxie.services.center.pojo.tree.OrganizationTreePojo;

@Service
public class OrganizationTreeServiceImpl {
	


	@Resource
	private OrganizationTreeMapper mapper;
	/**
	 * 根据用户id获取组织结构数据
	 * @param map
	 * @return
	 */
	public List<OrganizationTreePojo> selectByUserId(String userId){
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", userId);
		return mapper.selectByUserId(map);
	}
	

	public List<OrganizationTreePojo> selectByParentCompanyCode(String parentCompanyCode,String parentCompanyLevel){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parentCompanyCode", parentCompanyCode);
		if(parentCompanyLevel.equals("department")){
			return null;
		}else if(parentCompanyLevel.equals("post")){
			return null;
		}else {
			map.put("parentCompanyLevel", Integer.valueOf(parentCompanyLevel));
			return mapper.selectByParentCompanyCode(map);
		}
	}
}