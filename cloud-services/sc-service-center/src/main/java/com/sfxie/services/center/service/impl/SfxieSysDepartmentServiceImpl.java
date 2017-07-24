package com.sfxie.services.center.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.SfxieSysDepartmentMapper;
import com.sfxie.services.center.pojo.SfxieSysDepartment;
import com.sfxie.services.center.util.ServicesContext;
import com.sfxie.utils.StringUtils;

@Service
public class SfxieSysDepartmentServiceImpl extends TransactionService {

	@Resource
	private SfxieSysDepartmentMapper sfxieSysDepartmentMapper;
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_department
     *
     * @param id
     */
	public int deleteByPrimaryKey(SfxieSysDepartment record){
    	return sfxieSysDepartmentMapper.deleteByPrimaryKey(record);
    }

    /**
     *  新写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insert(SfxieSysDepartment record){
    	return sfxieSysDepartmentMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insertSelective(SfxieSysDepartment record){
    	if(StringUtils.isEmpty(record.getPartitionCompany())){
    		record.setPartitionCompany(ServicesContext.getPartitionCompany(record.getCompanyCode()));
    	}
    	String createCompanyId = StringUtils.isNotEmpty(record.getCreateCompanyId())?record.getCreateCompanyId():ServicesContext.getDefaultCreateCompanyId();
    	String createUser = StringUtils.isNotEmpty(record.getCreateUser())?record.getCreateUser():ServicesContext.getDefaultCreateUserId();
    	record.setCreateCompanyId(createCompanyId);
    	record.setCreateUser(createUser);
    	record.setId(UUID.randomUUID().toString());
    	return sfxieSysDepartmentMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_department
     *
     * @param id
     */
    public SfxieSysDepartment selectByPrimaryKey(SfxieSysDepartment record){
    	return sfxieSysDepartmentMapper.selectByPrimaryKey(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(SfxieSysDepartment record){
    	return sfxieSysDepartmentMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKey(SfxieSysDepartment record){
    	return sfxieSysDepartmentMapper.updateByPrimaryKey(record);
    }

    /**
     *  根据条件查询数据库记录,sfxie_sys_department
     */
    public List<SfxieSysDepartment> selectByCondition(SfxieSysDepartment record){
    	return sfxieSysDepartmentMapper.selectByCondition(record);
    }
}
