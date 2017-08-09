package com.sfxie.services.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.SfxieSysRoleMapper;
import com.sfxie.services.center.pojo.SfxieSysRole;
import com.sfxie.services.center.util.ServicesContext;
import com.sfxie.utils.StringUtils;

@Service
public class SfxieSysRoleServiceImpl extends TransactionService {

	@Resource
	private SfxieSysRoleMapper sfxieSysRoleMapper;
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_role
     *
     * @param id
     */
	public int deleteByPrimaryKey(SfxieSysRole record){
    	return sfxieSysRoleMapper.deleteByPrimaryKey(record);
    }

    /**
     *  新写入数据库记录,sfxie_sys_role
     *
     * @param record
     */
    public int insert(SfxieSysRole record){
    	return sfxieSysRoleMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_role
     *
     * @param record
     */
    public int insertSelective(SfxieSysRole record){
    	String createCompanyCode = StringUtils.isNotEmpty(record.getCreateCompanyCode())?record.getCreateCompanyCode():ServicesContext.getDefaultCreateCompanyCode();
    	String createUser = StringUtils.isNotEmpty(record.getCreateUser())?record.getCreateUser():ServicesContext.getDefaultCreateUserId();
    	String partitionCompany = ServicesContext.getPartitionCompany(record.getCreateCompanyCode());
    	record.setPartitionCompany(partitionCompany);
    	record.setCreateUser(createUser);
    	record.setCreateCompanyCode(createCompanyCode);
    	record.setId(StringUtils.getUUID());
    	return sfxieSysRoleMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_role
     *
     * @param id
     */
    public SfxieSysRole selectByPrimaryKey(SfxieSysRole record){
    	return sfxieSysRoleMapper.selectByPrimaryKey(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_role
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(SfxieSysRole record){
    	return sfxieSysRoleMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_role
     *
     * @param record
     */
    public int updateByPrimaryKey(SfxieSysRole record){
    	return sfxieSysRoleMapper.updateByPrimaryKey(record);
    }

    /**
     *  根据条件查询数据库记录,sfxie_sys_role
     */
    public List<SfxieSysRole> selectByCondition(SfxieSysRole record){
    	return sfxieSysRoleMapper.selectByCondition(record);
    }
    /**
     *  根据条件查询数据库记录,sfxie_sys_role
     */
    public List<SfxieSysRole> selectRolesByCompanyCodeOnAuth(SfxieSysRole record){
    	return sfxieSysRoleMapper.selectRolesByCompanyCodeOnAuth(record);
    }
}
