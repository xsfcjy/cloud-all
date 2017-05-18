package com.sfxie.services.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.SfxieSysMenuMapper;
import com.sfxie.services.center.pojo.SfxieSysMenu;

@Service
public class SfxieSysMenuServiceImpl extends TransactionService {

	@Resource
	private SfxieSysMenuMapper sfxieSysMenuMapper;
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_department
     *
     * @param id
     */
	public int deleteByPrimaryKey(SfxieSysMenu record){
    	return sfxieSysMenuMapper.deleteByPrimaryKey(record);
    }

    /**
     *  新写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insert(SfxieSysMenu record){
    	return sfxieSysMenuMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insertSelective(SfxieSysMenu record){
    	return sfxieSysMenuMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_department
     *
     * @param id
     */
    public SfxieSysMenu selectByPrimaryKey(SfxieSysMenu record){
    	return sfxieSysMenuMapper.selectByPrimaryKey(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(SfxieSysMenu record){
    	return sfxieSysMenuMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKey(SfxieSysMenu record){
    	return sfxieSysMenuMapper.updateByPrimaryKey(record);
    }

    /**
     *  根据条件查询数据库记录,sfxie_sys_department
     */
    public List<SfxieSysMenu> selectByCondition(SfxieSysMenu record){
    	return sfxieSysMenuMapper.selectByCondition(record);
    }
}
