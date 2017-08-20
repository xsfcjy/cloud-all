package com.sfxie.services.center.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.services.center.dao.mapper.OrganizationTreeMapper;
import com.sfxie.services.center.dao.mapper.SfxieSysCompanyMapper;
import com.sfxie.services.center.dao.mapper.SfxieSysOrganizationMapper;
import com.sfxie.services.center.pojo.PartitionData;
import com.sfxie.services.center.pojo.SfxieSysCompany;
import com.sfxie.services.center.pojo.SfxieSysOrganization;
import com.sfxie.services.center.pojo.SfxieSysUserRelation;
import com.sfxie.services.center.pojo.tree.OrganizationTreePojo;
import com.sfxie.services.center.util.ServicesContext;
import com.sfxie.services.center.vo.SfxieSysCompanyVo;
import com.sfxie.utils.StringUtils;

@Service
public class OrganizationTreeServiceImpl {

	@Resource
	private OrganizationTreeMapper mapper;
	@Resource
	private SfxieSysCompanyMapper sfxieSysCompanyMapper;
	@Resource
	private SfxieSysOrganizationMapper sfxieSysOrganizationMapper;
	/**
	 * 根据用户id获取组织结构数据
	 * @param map
	 * @return
	 */
	public List<OrganizationTreePojo> selectByUserId(String userId){
		PartitionData record = new PartitionData();
		record.setCreateUser(userId);
		record.setPartitionCompany(ServicesContext.getUserDefaultPartitionCompany());
		return mapper.selectByUserId(record);
	}
	

	public List<OrganizationTreePojo> selectByParentCompanyCode(String parentCompanyCode,String parentCompanyLevel){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parentCompanyCode", parentCompanyCode);
		map.put("partitionCompany", ServicesContext.getPartitionCompany(parentCompanyCode));
		if(parentCompanyLevel.equals("department")){
			return null;
		}else if(parentCompanyLevel.equals("post")){
			return null;
		}else {
			map.put("parentCompanyLevel", Integer.valueOf(parentCompanyLevel));
			return mapper.selectByParentCompanyCode(map);
		}
	}
	
	/**
     *  新增组织结构
     *
     * @param record
     */
    public int insertSelective(SfxieSysCompany record){
    	SfxieSysCompanyVo sfxieSysCompanyVo = (SfxieSysCompanyVo) record;
    	String createCompanyCode = StringUtils.isNotEmpty(sfxieSysCompanyVo.getCreateCompanyCode())?sfxieSysCompanyVo.getCreateCompanyCode():ServicesContext.getDefaultCreateCompanyCode();
    	String createUser = StringUtils.isNotEmpty(sfxieSysCompanyVo.getCreateUser())?sfxieSysCompanyVo.getCreateUser():ServicesContext.getDefaultCreateUserId();
    	sfxieSysCompanyVo.setCreateCompanyCode(createCompanyCode);
    	sfxieSysCompanyVo.setCreateUser(createUser);
    	sfxieSysCompanyVo.setId(StringUtils.getUUID());
    	sfxieSysCompanyVo.setCompanyLevel((short) (sfxieSysCompanyVo.getParentCompanyLevel()+1));
    	
    	SfxieSysOrganization sfxieSysOrganization = new SfxieSysOrganization ();
    	sfxieSysOrganization.setCompanyCode(sfxieSysCompanyVo.getCompanyCode());
    	sfxieSysOrganization.setParentCompanyCode(sfxieSysCompanyVo.getParentCompanyCode());
    	sfxieSysOrganization.setId(StringUtils.getUUID());
    	sfxieSysOrganization.setCreateUser(createUser);
    	sfxieSysCompanyMapper.insertSelective(sfxieSysCompanyVo);
    	return sfxieSysOrganizationMapper.insertSelective(sfxieSysOrganization);
    }


	public List<SfxieSysUserRelation> selectUsersByCompanyCode(SfxieSysUserRelation sfxieSysUserRelation){
		sfxieSysUserRelation.setPartitionCompany(ServicesContext.getPartitionCompany(sfxieSysUserRelation.getCompanyCode()));
		return mapper.selectUsersByCompanyCode(sfxieSysUserRelation);
	}

	public List<SfxieSysUserRelation> selectUsersByCompanyCodeOnAuth(SfxieSysUserRelation sfxieSysUserRelation){
		return mapper.selectUsersByCompanyCodeOnAuth(sfxieSysUserRelation);
	}

}