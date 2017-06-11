package com.sfxie.services.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.services.center.dao.mapper.ReportDefinitionMapper;
import com.sfxie.services.center.pojo.ReportDefinition;

@Service
public class ReportDefinitionServiceImpl extends TransactionService {

	@Resource
	private ReportDefinitionMapper reportDefinitionMapper;
	
	/**
     *  根据主键删除数据库的记录,report_definition
     *
     * @param id
     */
    public int deleteByPrimaryKey(Long id){
    	return reportDefinitionMapper.deleteByPrimaryKey(id);
    }

    /**
     *  新写入数据库记录,report_definition
     *
     * @param record
     */
    public int insert(ReportDefinition record){
    	return reportDefinitionMapper.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,report_definition
     *
     * @param record
     */
    public int insertSelective(ReportDefinition record){
    	return reportDefinitionMapper.insertSelective(record);
    }

    /**
     *  根据指定主键获取一条数据库记录,report_definition
     *
     * @param id
     */
    public ReportDefinition selectByPrimaryKey(Long id){
    	return reportDefinitionMapper.selectByPrimaryKey(id);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,report_definition
     *
     * @param record
     */
    public int updateByPrimaryKeySelective(ReportDefinition record){
    	return reportDefinitionMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,report_definition
     *
     * @param record
     */
    public int updateByPrimaryKey(ReportDefinition record){
    	return reportDefinitionMapper.updateByPrimaryKey(record);
    }
    

    /**
     *  根据条件查询数据库记录,report_definition
     */
    public List<ReportDefinition> selectByCondition(ReportDefinition sfxieSysCompany){
		List<ReportDefinition> list = reportDefinitionMapper.selectByCondition(sfxieSysCompany);
		return list;
    }
}
