package com.sfxie.services.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.SfxieSysUserRoleMapper;
import com.sfxie.services.center.pojo.SfxieSysUserRole;

@Service
public class SfxieSysUserRoleServiceImpl extends TransactionService {

	@Resource
	private SfxieSysUserRoleMapper sfxieSysUserRoleMapper;
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_department
     *
     * @param id
     */
	public int deleteByPrimaryKey(SfxieSysUserRole record){
    	return sfxieSysUserRoleMapper.deleteByPrimaryKey(record);
    }

    /**
     *  新写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insert(SfxieSysUserRole record){
    	return sfxieSysUserRoleMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insertSelective(SfxieSysUserRole record){
    	return sfxieSysUserRoleMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_department
     *
     * @param id
     */
    public SfxieSysUserRole selectByPrimaryKey(SfxieSysUserRole record){
    	return sfxieSysUserRoleMapper.selectByPrimaryKey(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(SfxieSysUserRole record){
    	return sfxieSysUserRoleMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKey(SfxieSysUserRole record){
    	return sfxieSysUserRoleMapper.updateByPrimaryKey(record);
    }

    /**
     *  根据条件查询数据库记录,sfxie_sys_department
     */
    public List<SfxieSysUserRole> selectByCondition(SfxieSysUserRole record){
    	return sfxieSysUserRoleMapper.selectByCondition(record);
    }
}
