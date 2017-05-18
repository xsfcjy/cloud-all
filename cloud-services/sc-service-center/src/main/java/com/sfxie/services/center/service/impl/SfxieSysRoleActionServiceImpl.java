package com.sfxie.services.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.SfxieSysRoleActionMapper;
import com.sfxie.services.center.pojo.SfxieSysRoleAction;

@Service
public class SfxieSysRoleActionServiceImpl extends TransactionService {

	@Resource
	private SfxieSysRoleActionMapper sfxieSysRoleActionMapper;
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_department
     *
     * @param id
     */
	public int deleteByPrimaryKey(SfxieSysRoleAction record){
    	return sfxieSysRoleActionMapper.deleteByPrimaryKey(record);
    }

    /**
     *  新写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insert(SfxieSysRoleAction record){
    	return sfxieSysRoleActionMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insertSelective(SfxieSysRoleAction record){
    	return sfxieSysRoleActionMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_department
     *
     * @param id
     */
    public SfxieSysRoleAction selectByPrimaryKey(SfxieSysRoleAction record){
    	return sfxieSysRoleActionMapper.selectByPrimaryKey(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(SfxieSysRoleAction record){
    	return sfxieSysRoleActionMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKey(SfxieSysRoleAction record){
    	return sfxieSysRoleActionMapper.updateByPrimaryKey(record);
    }

    /**
     *  根据条件查询数据库记录,sfxie_sys_department
     */
    public List<SfxieSysRoleAction> selectByCondition(SfxieSysRoleAction record){
    	return sfxieSysRoleActionMapper.selectByCondition(record);
    }
}
