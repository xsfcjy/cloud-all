package com.sfxie.services.center.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sfxie.core.framework.mvc.handle.Result;
import com.sfxie.services.center.pojo.ReportDefinition;
import com.sfxie.services.center.service.impl.ReportDefinitionServiceImpl;

@RestController
public class ReportDefinitionController {

	@Resource
	private ReportDefinitionServiceImpl reportDefinitionServiceImpl;
	
	@RequestMapping(value = "/report/definition/list", method = RequestMethod.POST)
	public @ResponseBody Object sfxieSysCompanys(@RequestBody ReportDefinition entity) {
		List<ReportDefinition>  list = reportDefinitionServiceImpl.selectByCondition(entity);
		Result<List<ReportDefinition>> result = new Result.BuilderArray<List<ReportDefinition>>(list).build();
		return result;
	}
	@RequestMapping(value = "/report/definition/{id}", method = RequestMethod.GET)
	public Object selectByPrimaryKey(@PathVariable(value="id") Long id){
		ReportDefinition entity = reportDefinitionServiceImpl.selectByPrimaryKey(id);
		Result<ReportDefinition> result = new Result.BuilderObject<ReportDefinition>(entity).build();
    	return result;
    }
	
	/**
     *  根据主键删除数据库的记录,report_definition
     *
     * @param id
     */
	@RequestMapping(value = "/report/definition/{id}", method = RequestMethod.DELETE)
    public int deleteByPrimaryKey(@PathVariable(value="id") Long id){
    	return reportDefinitionServiceImpl.deleteByPrimaryKey(id);
    }

    /**
     *  新写入数据库记录,report_definition
     *
     * @param record
     */
	@RequestMapping(value = "/report/definition/insert", method = RequestMethod.POST)
    public int insert(@RequestBody ReportDefinition record){
    	return reportDefinitionServiceImpl.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,report_definition
     *
     * @param record
     */
	@RequestMapping(value = "/report/definition/insertSelective", method = RequestMethod.POST)
    public int insertSelective(@RequestBody ReportDefinition record){
    	return reportDefinitionServiceImpl.insertSelective(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,report_definition
     *
     * @param record
     */
	@RequestMapping(value = "/report/definition/updateSelective", method = RequestMethod.PUT)
    public int updateByPrimaryKeySelective(@RequestBody ReportDefinition record){
    	return reportDefinitionServiceImpl.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,report_definition
     *
     * @param record
     */
	@RequestMapping(value = "/report/definition/update", method = RequestMethod.PUT)
    public int updateByPrimaryKey(@RequestBody ReportDefinition record){
    	return reportDefinitionServiceImpl.updateByPrimaryKey(record);
    }
}
