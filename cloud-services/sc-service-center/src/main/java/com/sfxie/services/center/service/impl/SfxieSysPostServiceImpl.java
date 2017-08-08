package com.sfxie.services.center.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.SfxieSysPostMapper;
import com.sfxie.services.center.pojo.SfxieSysPost;
import com.sfxie.services.center.util.ServicesContext;
import com.sfxie.utils.StringUtils;

@Service
public class SfxieSysPostServiceImpl extends TransactionService {

	@Resource
	private SfxieSysPostMapper sfxieSysPostMapper;
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_department
     *
     * @param id
     */
	public int deleteByPrimaryKey(SfxieSysPost record){
    	return sfxieSysPostMapper.deleteByPrimaryKey(record);
    }

    /**
     *  新写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insert(SfxieSysPost record){
    	return sfxieSysPostMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int insertSelective(SfxieSysPost record){
    	if(StringUtils.isEmpty(record.getPartitionCompany())){
    		record.setPartitionCompany(ServicesContext.getPartitionCompany(record.getCompanyCode()));
    	}
    	String createCompanyCode = StringUtils.isNotEmpty(record.getCreateCompanyCode())?record.getCreateCompanyCode():ServicesContext.getDefaultCreateCompanyCode();
    	String createUser = StringUtils.isNotEmpty(record.getCreateUser())?record.getCreateUser():ServicesContext.getDefaultCreateUserId();
    	record.setCreateCompanyCode(createCompanyCode);
    	record.setCreateUser(createUser);
    	record.setId(UUID.randomUUID().toString());
    	return sfxieSysPostMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_department
     *
     * @param id
     */
    public SfxieSysPost selectByPrimaryKey(SfxieSysPost record){
    	return sfxieSysPostMapper.selectByPrimaryKey(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(SfxieSysPost record){
    	return sfxieSysPostMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    public int updateByPrimaryKey(SfxieSysPost record){
    	return sfxieSysPostMapper.updateByPrimaryKey(record);
    }

    /**
     *  根据条件查询数据库记录,sfxie_sys_department
     */
    public List<SfxieSysPost> selectByCondition(SfxieSysPost record){
    	return sfxieSysPostMapper.selectByCondition(record);
    }
}
