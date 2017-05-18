package com.sfxie.services.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.SfxieSysUserinfoMapper;
import com.sfxie.services.center.pojo.SfxieSysUserinfo;

@Service
public class SfxieSysUserinfoServiceImpl extends TransactionService {

	@Resource
	private SfxieSysUserinfoMapper sfxieSysUserinfoMapper;
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_department
     *
     * @param id
     */
	public int deleteByPrimaryKey(SfxieSysUserinfo record){
    	return sfxieSysUserinfoMapper.deleteByPrimaryKey(record);
    }

    /**
     *  新写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insert(SfxieSysUserinfo record){
    	return sfxieSysUserinfoMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insertSelective(SfxieSysUserinfo record){
    	return sfxieSysUserinfoMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_department
     *
     * @param id
     */
    public SfxieSysUserinfo selectByPrimaryKey(SfxieSysUserinfo record){
    	return sfxieSysUserinfoMapper.selectByPrimaryKey(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(SfxieSysUserinfo record){
    	return sfxieSysUserinfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKey(SfxieSysUserinfo record){
    	return sfxieSysUserinfoMapper.updateByPrimaryKey(record);
    }

    /**
     *  根据条件查询数据库记录,sfxie_sys_department
     */
    public List<SfxieSysUserinfo> selectByCondition(SfxieSysUserinfo record){
    	return sfxieSysUserinfoMapper.selectByCondition(record);
    }
}
