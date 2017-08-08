package com.sfxie.services.center.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.sfxie.core.framework.mvc.handle.Result;
import com.sfxie.services.center.pojo.SfxieSysUserRelation;
import com.sfxie.services.center.service.impl.OrganizationTreeServiceImpl;
import com.sfxie.services.center.vo.SfxieSysCompanyVo;
import com.sfxie.services.center.vo.SfxieSysOrganizationVo;

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
	@RequestMapping(value = "/organization/{userId}/{partitionCompany}", method = {RequestMethod.POST})
	public @ResponseBody Object organizationByUser(@PathVariable String userId,@PathVariable String partitionCompany ) {
		return service.selectByUserId(userId,partitionCompany);
	}
	@RequestMapping(value = "/organization/sub/{parentCompanyCode}/{parentCompanyLevel}/{partitionCompany}", method = {RequestMethod.POST})
	public @ResponseBody Object organizationByParentCompanyCode(@PathVariable String parentCompanyCode, @PathVariable String parentCompanyLevel
			, @PathVariable String partitionCompany) {
		return service.selectByParentCompanyCode(parentCompanyCode,parentCompanyLevel,partitionCompany);
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
	/**
     *根据公司编码获取用户列表
     * @param record
     */
	@RequestMapping(value = "/organization/{company}/userList/{partitionCompany}", method = RequestMethod.POST)
    public Object queryUserinfos(@PathVariable String company,@PathVariable String partitionCompany,
    		@RequestBody SfxieSysUserRelation sfxieSysUserRelation){
		sfxieSysUserRelation.setCompanyCode(company);
		sfxieSysUserRelation.setPartitionCompany(partitionCompany);
		List<SfxieSysUserRelation>  list = service.selectUsersByCompanyCode(sfxieSysUserRelation);
		Result<List<SfxieSysUserRelation>> result = new Result.BuilderArray<List<SfxieSysUserRelation>>(list).build();
		return result;
    }
	
	/**
     *
     * @param record
     */
	@RequestMapping(value = "/organization/{code}/{partitionCompany}/userList", method = RequestMethod.POST)
    public Object queryUserinfos(@PathVariable String code,@PathVariable String partitionCompany
    		,@RequestBody SfxieSysOrganizationVo sfxieSysOrganizationVo
    		){
		PageHelper.ignorePaged();
		List<SfxieSysUserRelation>  list = new ArrayList<SfxieSysUserRelation> ();
//		for(SfxieSysCompanyVo entity : sfxieSysOrganizationVo.getData()){
//			if(entity.getLevel().equals("department")){
//				
//			}else if(entity.getLevel().equals("post")){
//				
//			}else {
				SfxieSysUserRelation sfxieSysUserRelation = new SfxieSysUserRelation();
				sfxieSysUserRelation.setPartitionCompany(partitionCompany);
				sfxieSysUserRelation.setCompanyCode(code);
				sfxieSysUserRelation.setUserNameCn(sfxieSysOrganizationVo.getUserNameCn());
				List<SfxieSysUserRelation>  listTemp =service.selectUsersByCompanyCode(sfxieSysUserRelation);
				list.addAll(listTemp);
				listTemp =service.selectUsersByCompanyCodeOnAuth(sfxieSysUserRelation);
				list.addAll(listTemp);
//			}
//		}
		Result<List<SfxieSysUserRelation>> result = new Result.BuilderArray<List<SfxieSysUserRelation>>(list).build();
		return result;
    }
}
