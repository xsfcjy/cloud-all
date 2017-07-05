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
import com.sfxie.services.center.pojo.SfxieSysCompany;
import com.sfxie.services.center.service.impl.SfxieSysCompanyServiceImpl;

@RestController
public class SfxieSysCompanyController {

	@Resource
	private SfxieSysCompanyServiceImpl sfxieSysCompanyService;
	
	@RequestMapping(value = "/company/list", method = RequestMethod.POST)
	public @ResponseBody Object sfxieSysCompanys(@RequestBody SfxieSysCompany sfxieSysCompany) {
		List<SfxieSysCompany>  list = sfxieSysCompanyService.selectByCondition(sfxieSysCompany);
		Result<List<SfxieSysCompany>> result = new Result.BuilderArray<List<SfxieSysCompany>>(list).build();
		return result;
	}
	@RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
	public Object selectByPrimaryKey(@PathVariable(value="id") String id){
		SfxieSysCompany sfxieSysCompany = sfxieSysCompanyService.selectByPrimaryKey(id);
		Result<SfxieSysCompany> result = new Result.BuilderObject<SfxieSysCompany>(sfxieSysCompany).build();
    	return result;
    }
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_company
     *
     * @param id
     */
	@RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
    public int deleteByPrimaryKey(@PathVariable(value="id") String id){
    	return sfxieSysCompanyService.deleteByPrimaryKey(id);
    }

    /**
     *  新写入数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/company", method = RequestMethod.POST)
    public int insert(@RequestBody SfxieSysCompany record){
    	return sfxieSysCompanyService.insertSelective(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/company", method = RequestMethod.PUT)
    public int updateByPrimaryKeySelective(@RequestBody SfxieSysCompany record){
    	return sfxieSysCompanyService.updateByPrimaryKeySelective(record);
    }
}
