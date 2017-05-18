package com.sfxie.services.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.SfxieSysPostRoleMapper;
import com.sfxie.services.center.pojo.SfxieSysPostRole;

@Service
public class SfxieSysPostRoleServiceImpl extends TransactionService {

	@Resource
	private SfxieSysPostRoleMapper sfxieSysPostRoleMapper;
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_department
     *
     * @param id
     */
	public int deleteByPrimaryKey(SfxieSysPostRole record){
    	return sfxieSysPostRoleMapper.deleteByPrimaryKey(record);
    }

    /**
     *  新写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insert(SfxieSysPostRole record){
    	return sfxieSysPostRoleMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insertSelective(SfxieSysPostRole record){
    	return sfxieSysPostRoleMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_department
     *
     * @param id
     */
    public SfxieSysPostRole selectByPrimaryKey(SfxieSysPostRole record){
    	return sfxieSysPostRoleMapper.selectByPrimaryKey(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(SfxieSysPostRole record){
    	return sfxieSysPostRoleMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKey(SfxieSysPostRole record){
    	return sfxieSysPostRoleMapper.updateByPrimaryKey(record);
    }

    /**
     *  根据条件查询数据库记录,sfxie_sys_department
     */
    public List<SfxieSysPostRole> selectByCondition(SfxieSysPostRole record){
    	return sfxieSysPostRoleMapper.selectByCondition(record);
    }
}
