package com.sfxie.services.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.SfxieSysAuthDataMapper;
import com.sfxie.services.center.pojo.SfxieSysAuthData;

@Service
public class SfxieSysAuthDataServiceImpl extends TransactionService {

	@Resource
	private SfxieSysAuthDataMapper sfxieSysAuthDataMapper;
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_department
     *
     * @param id
     */
	public int deleteByPrimaryKey(SfxieSysAuthData record){
    	return sfxieSysAuthDataMapper.deleteByPrimaryKey(record);
    }

    /**
     *  新写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insert(SfxieSysAuthData record){
    	return sfxieSysAuthDataMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insertSelective(SfxieSysAuthData record){
    	return sfxieSysAuthDataMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_department
     *
     * @param id
     */
    public SfxieSysAuthData selectByPrimaryKey(SfxieSysAuthData record){
    	return sfxieSysAuthDataMapper.selectByPrimaryKey(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(SfxieSysAuthData record){
    	return sfxieSysAuthDataMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKey(SfxieSysAuthData record){
    	return sfxieSysAuthDataMapper.updateByPrimaryKey(record);
    }

    /**
     *  根据条件查询数据库记录,sfxie_sys_department
     */
    public List<SfxieSysAuthData> selectByCondition(SfxieSysAuthData record){
    	return sfxieSysAuthDataMapper.selectByCondition(record);
    }
}
