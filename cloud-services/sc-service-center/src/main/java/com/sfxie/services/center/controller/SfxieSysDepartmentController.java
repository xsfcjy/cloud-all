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
import com.sfxie.services.center.pojo.SfxieSysDepartment;
import com.sfxie.services.center.service.impl.SfxieSysDepartmentServiceImpl;
import com.sfxie.services.center.util.ServicesContext;

@RestController
public class SfxieSysDepartmentController {

	@Resource
	private SfxieSysDepartmentServiceImpl sfxieSysDepartmentService;
	
	@RequestMapping(value = "/department/list", method = RequestMethod.POST)
	public @ResponseBody Object sfxieDepartments(@RequestBody SfxieSysDepartment sfxieSysDepartment) {
		
		List<SfxieSysDepartment>  list = sfxieSysDepartmentService.selectByCondition(sfxieSysDepartment);
		Result<List<SfxieSysDepartment>> result = new Result.BuilderArray<List<SfxieSysDepartment>>(list).build();
		return result;
	}
	
	@RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
	public Object selectByPrimaryKey(@PathVariable(value="id") String id){
		SfxieSysDepartment sfxieSysDepartment = new SfxieSysDepartment ();
		sfxieSysDepartment.setId(id);
		sfxieSysDepartment.setPartitionCompany(ServicesContext.getUserDefaultPartitionCompany());
		Result<SfxieSysDepartment> result = new Result.BuilderObject<SfxieSysDepartment>(sfxieSysDepartmentService.selectByPrimaryKey(sfxieSysDepartment)).build();
    	return result;
    }
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_company
     *
     * @param id
     */
	@RequestMapping(value = "/department/{id}", method = RequestMethod.DELETE)
    public int deleteByPrimaryKey(@PathVariable(value="id") String id ){
		SfxieSysDepartment sfxieSysDepartment = new SfxieSysDepartment ();
		sfxieSysDepartment.setId(id);
		sfxieSysDepartment.setPartitionCompany(ServicesContext.getUserDefaultPartitionCompany());
    	return sfxieSysDepartmentService.deleteByPrimaryKey(sfxieSysDepartment);
    }

    /**
     *  新写入数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/department/insert", method = RequestMethod.POST)
    public int insert(@RequestBody SfxieSysDepartment record){
    	return sfxieSysDepartmentService.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/department", method = RequestMethod.POST)
    public int insertSelective(@RequestBody SfxieSysDepartment record){
    	return sfxieSysDepartmentService.insertSelective(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/department", method = RequestMethod.PUT)
    public int updateByPrimaryKeySelective(@RequestBody SfxieSysDepartment record){
    	return sfxieSysDepartmentService.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/department/update", method = RequestMethod.PUT)
    public int updateByPrimaryKey(@RequestBody SfxieSysDepartment record){
    	return sfxieSysDepartmentService.updateByPrimaryKey(record);
    }
}
