package com.sfxie.services.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.SfxieSysRoleMenuMapper;
import com.sfxie.services.center.pojo.SfxieSysRoleMenu;

@Service
public class SfxieSysRoleMenuServiceImpl extends TransactionService {

	@Resource
	private SfxieSysRoleMenuMapper sfxieSysRoleMenuMapper;
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_department
     *
     * @param id
     */
	public int deleteByPrimaryKey(SfxieSysRoleMenu record){
    	return sfxieSysRoleMenuMapper.deleteByPrimaryKey(record);
    }

    /**
     *  新写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insert(SfxieSysRoleMenu record){
    	return sfxieSysRoleMenuMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insertSelective(SfxieSysRoleMenu record){
    	return sfxieSysRoleMenuMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_department
     *
     * @param id
     */
    public SfxieSysRoleMenu selectByPrimaryKey(SfxieSysRoleMenu record){
    	return sfxieSysRoleMenuMapper.selectByPrimaryKey(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(SfxieSysRoleMenu record){
    	return sfxieSysRoleMenuMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKey(SfxieSysRoleMenu record){
    	return sfxieSysRoleMenuMapper.updateByPrimaryKey(record);
    }

    /**
     *  根据条件查询数据库记录,sfxie_sys_department
     */
    public List<SfxieSysRoleMenu> selectByCondition(SfxieSysRoleMenu record){
    	return sfxieSysRoleMenuMapper.selectByCondition(record);
    }
}
