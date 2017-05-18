package com.sfxie.services.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.SfxieSysAuthRoleMenuMapper;
import com.sfxie.services.center.pojo.SfxieSysAuthRoleMenu;

@Service
public class SfxieSysAuthRoleMenuServiceImpl extends TransactionService {

	@Resource
	private SfxieSysAuthRoleMenuMapper sfxieSysAuthRoleMenuMapper;
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_department
     *
     * @param id
     */
	public int deleteByPrimaryKey(SfxieSysAuthRoleMenu record){
    	return sfxieSysAuthRoleMenuMapper.deleteByPrimaryKey(record);
    }

    /**
     *  新写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insert(SfxieSysAuthRoleMenu record){
    	return sfxieSysAuthRoleMenuMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insertSelective(SfxieSysAuthRoleMenu record){
    	return sfxieSysAuthRoleMenuMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_department
     *
     * @param id
     */
    public SfxieSysAuthRoleMenu selectByPrimaryKey(SfxieSysAuthRoleMenu record){
    	return sfxieSysAuthRoleMenuMapper.selectByPrimaryKey(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(SfxieSysAuthRoleMenu record){
    	return sfxieSysAuthRoleMenuMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKey(SfxieSysAuthRoleMenu record){
    	return sfxieSysAuthRoleMenuMapper.updateByPrimaryKey(record);
    }

    /**
     *  根据条件查询数据库记录,sfxie_sys_department
     */
    public List<SfxieSysAuthRoleMenu> selectByCondition(SfxieSysAuthRoleMenu record){
    	return sfxieSysAuthRoleMenuMapper.selectByCondition(record);
    }
}
