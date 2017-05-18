package com.sfxie.services.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.SfxieSysOrganizationMapper;
import com.sfxie.services.center.pojo.SfxieSysOrganization;

@Service
public class SfxieSysOrganizationServiceImpl extends TransactionService {

	@Resource
	private SfxieSysOrganizationMapper sfxieSysOrganizationMapper;
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_company
     *
     * @param id
     */
    public int deleteByPrimaryKey(String id){
    	return sfxieSysOrganizationMapper.deleteByPrimaryKey(id);
    }

    /**
     *  新写入数据库记录,sfxie_sys_company
     *
     * @param record
     */
    public int insert(SfxieSysOrganization record){
    	return sfxieSysOrganizationMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_company
     *
     * @param record
     */
    public int insertSelective(SfxieSysOrganization record){
    	return sfxieSysOrganizationMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_company
     *
     * @param id
     */
    public SfxieSysOrganization selectByPrimaryKey(String id){
    	return sfxieSysOrganizationMapper.selectByPrimaryKey(id);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_company
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(SfxieSysOrganization record){
    	return sfxieSysOrganizationMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_company
     *
     * @param record
     */
    public int updateByPrimaryKey(SfxieSysOrganization record){
    	return sfxieSysOrganizationMapper.updateByPrimaryKey(record);
    }
    

    /**
     *  根据条件查询数据库记录,sfxie_sys_company
     */
    public List<SfxieSysOrganization> selectByCondition(SfxieSysOrganization sfxieSysOrganization){
		List<SfxieSysOrganization> list = sfxieSysOrganizationMapper.selectByCondition(sfxieSysOrganization);
		return list;
    }
}
