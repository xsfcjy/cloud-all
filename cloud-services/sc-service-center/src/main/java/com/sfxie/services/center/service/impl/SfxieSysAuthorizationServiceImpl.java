package com.sfxie.services.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.SfxieSysAuthorizationMapper;
import com.sfxie.services.center.pojo.SfxieSysAuthorization;

@Service
public class SfxieSysAuthorizationServiceImpl extends TransactionService {

	@Resource
	private SfxieSysAuthorizationMapper sfxieSysAuthorizationMapper;
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_department
     *
     * @param id
     */
	public int deleteByPrimaryKey(SfxieSysAuthorization record){
    	return sfxieSysAuthorizationMapper.deleteByPrimaryKey(record);
    }

    /**
     *  新写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insert(SfxieSysAuthorization record){
    	return sfxieSysAuthorizationMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insertSelective(SfxieSysAuthorization record){
    	return sfxieSysAuthorizationMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_department
     *
     * @param id
     */
    public SfxieSysAuthorization selectByPrimaryKey(SfxieSysAuthorization record){
    	return sfxieSysAuthorizationMapper.selectByPrimaryKey(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(SfxieSysAuthorization record){
    	return sfxieSysAuthorizationMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKey(SfxieSysAuthorization record){
    	return sfxieSysAuthorizationMapper.updateByPrimaryKey(record);
    }

    /**
     *  根据条件查询数据库记录,sfxie_sys_department
     */
    public List<SfxieSysAuthorization> selectByCondition(SfxieSysAuthorization record){
    	return sfxieSysAuthorizationMapper.selectByCondition(record);
    }
}
