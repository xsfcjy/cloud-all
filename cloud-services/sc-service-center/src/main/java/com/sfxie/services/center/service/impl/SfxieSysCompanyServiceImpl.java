package com.sfxie.services.center.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.SfxieSysCompanyMapper;
import com.sfxie.services.center.pojo.SfxieSysCompany;
import com.sfxie.services.center.util.ServicesContext;
import com.sfxie.utils.StringUtils;

@Service
public class SfxieSysCompanyServiceImpl extends TransactionService {

	@Resource
	private SfxieSysCompanyMapper sfxieSysCompanyMapper;
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_company
     *
     * @param id
     */
    public int deleteByPrimaryKey(String id){
    	return sfxieSysCompanyMapper.deleteByPrimaryKey(id);
    }

    /**
     *  新写入数据库记录,sfxie_sys_company
     *
     * @param record
     */
    public int insert(SfxieSysCompany record){
    	return sfxieSysCompanyMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_company
     *
     * @param record
     */
    public int insertSelective(SfxieSysCompany record){
    	String createCompanyId = StringUtils.isNotEmpty(record.getCreateCompanyId())?record.getCreateCompanyId():ServicesContext.getDefaultCreateCompanyId();
    	String createUser = StringUtils.isNotEmpty(record.getCreateUser())?record.getCreateUser():ServicesContext.getDefaultCreateUserId();
    	record.setCreateCompanyId(createCompanyId);
    	record.setCreateUser(createUser);
    	record.setId(UUID.randomUUID().toString());
    	return sfxieSysCompanyMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_company
     *
     * @param id
     */
    public SfxieSysCompany selectByPrimaryKey(String id){
    	return sfxieSysCompanyMapper.selectByPrimaryKey(id);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_company
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(SfxieSysCompany record){
    	return sfxieSysCompanyMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_company
     *
     * @param record
     */
    public int updateByPrimaryKey(SfxieSysCompany record){
    	return sfxieSysCompanyMapper.updateByPrimaryKey(record);
    }
    

    /**
     *  根据条件查询数据库记录,sfxie_sys_company
     */
    public List<SfxieSysCompany> selectByCondition(SfxieSysCompany sfxieSysCompany){
		List<SfxieSysCompany> list = sfxieSysCompanyMapper.selectByCondition(sfxieSysCompany);
		return list;
    }
}
