package com.sfxie.services.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.SfxieSysUserRelationMapper;
import com.sfxie.services.center.pojo.SfxieSysUserRelation;

@Service
public class SfxieSysUserRelationServiceImpl extends TransactionService {

	@Resource
	private SfxieSysUserRelationMapper sfxieSysUserRelationMapper;
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_department
     *
     * @param id
     */
	public int deleteByPrimaryKey(SfxieSysUserRelation record){
    	return sfxieSysUserRelationMapper.deleteByPrimaryKey(record);
    }

    /**
     *  新写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insert(SfxieSysUserRelation record){
    	return sfxieSysUserRelationMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insertSelective(SfxieSysUserRelation record){
    	return sfxieSysUserRelationMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_department
     *
     * @param id
     */
    public SfxieSysUserRelation selectByPrimaryKey(SfxieSysUserRelation record){
    	return sfxieSysUserRelationMapper.selectByPrimaryKey(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(SfxieSysUserRelation record){
    	return sfxieSysUserRelationMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKey(SfxieSysUserRelation record){
    	return sfxieSysUserRelationMapper.updateByPrimaryKey(record);
    }

    /**
     *  根据条件查询数据库记录,sfxie_sys_department
     */
    public List<SfxieSysUserRelation> selectByCondition(SfxieSysUserRelation record){
    	return sfxieSysUserRelationMapper.selectByCondition(record);
    }
}
