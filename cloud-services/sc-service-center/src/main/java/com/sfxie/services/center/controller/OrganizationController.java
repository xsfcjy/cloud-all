package com.sfxie.services.center.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sfxie.services.center.service.impl.OrganizationTreeServiceImpl;
import com.sfxie.services.center.vo.SfxieSysCompanyVo;

/**
 * 组织结构管理控制器
 * @author xiesf
 * @since 2017年7月18日 下午2:42:10
 */
@RestController
public class OrganizationController {

	@Resource
	private OrganizationTreeServiceImpl service;
	
	
	/**
	 * 根据用户id获取用户的组织结构数据
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/organization/{userId}", method = {RequestMethod.POST})
	public @ResponseBody Object organizationByUser(@PathVariable String userId ) {
		return service.selectByUserId(userId);
	}
	@RequestMapping(value = "/organization/sub/{parentCompanyCode}/{parentCompanyLevel}", method = {RequestMethod.POST})
	public @ResponseBody Object organizationByUser(@PathVariable String parentCompanyCode, @PathVariable String parentCompanyLevel) {
		return service.selectByParentCompanyCode(parentCompanyCode,parentCompanyLevel);
	}
	
	/**
     *  新写入数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/organization/company", method = RequestMethod.POST)
    public int insertCompany(@RequestBody SfxieSysCompanyVo record){
    	return service.insertSelective(record);
    }/**
     *  新写入数据库记录,sfxie_sys_company
    *
    * @param record
    */
	@RequestMapping(value = "/organization/department", method = RequestMethod.POST)
   public int insertDepartment(@RequestBody SfxieSysCompanyVo record){
   	return service.insertSelective(record);
   }

		
}
