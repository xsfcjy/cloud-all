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
import com.sfxie.services.center.pojo.SfxieSysRole;
import com.sfxie.services.center.pojo.SfxieSysUserRelation;
import com.sfxie.services.center.service.impl.OrganizationTreeServiceImpl;
import com.sfxie.services.center.service.impl.SfxieSysRoleServiceImpl;
import com.sfxie.services.center.service.impl.SfxieSysUserRelationServiceImpl;
import com.sfxie.services.center.util.ServicesContext;
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
	@Resource
	private SfxieSysRoleServiceImpl sfxieSysRoleServiceImpl;
	@Resource
	private SfxieSysUserRelationServiceImpl sfxieSysUserRelationServiceImpl;
	
	
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
	public @ResponseBody Object organizationByParentCompanyCode(@PathVariable String parentCompanyCode, @PathVariable String parentCompanyLevel) {
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
    }
	/**
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
	/*@RequestMapping(value = "/organization/{company}/userList", method = RequestMethod.POST)
    public Object queryUserinfos(@PathVariable String company,
    		@RequestBody SfxieSysUserRelation sfxieSysUserRelation){
		sfxieSysUserRelation.setCompanyCode(company);
		List<SfxieSysUserRelation>  list = service.selectUsersByCompanyCode(sfxieSysUserRelation);
		Result<List<SfxieSysUserRelation>> result = new Result.BuilderArray<List<SfxieSysUserRelation>>(list).build();
		return result;
    }*/
	
	/**
     *
     * @param record
     */
	@RequestMapping(value = "/organization/{companyCode}/userList", method = RequestMethod.POST)
    public Object queryUserinfos(@PathVariable String companyCode,@RequestBody SfxieSysOrganizationVo sfxieSysOrganizationVo
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
				sfxieSysUserRelation.setPartitionCompany(ServicesContext.getPartitionCompany(companyCode));
				sfxieSysUserRelation.setCompanyCode(companyCode);
				sfxieSysUserRelation.setUserNameCn(sfxieSysOrganizationVo.getUserNameCn());
				List<SfxieSysUserRelation>  listTemp =service.selectUsersByCompanyCode(sfxieSysUserRelation);
				if(null!=listTemp && listTemp.size()>0)
					list.addAll(listTemp);
				listTemp =service.selectUsersByCompanyCodeOnAuth(sfxieSysUserRelation);
				if(null!=listTemp && listTemp.size()>0)
					list.addAll(listTemp);
//			}
//		}
		Result<List<SfxieSysUserRelation>> result = new Result.BuilderArray<List<SfxieSysUserRelation>>(list).build();
		return result;
    }
	
	/**
	 * 
	 * @param code
	 * @param partitionCompany
	 * @param sfxieSysOrganizationVo
	 * @return
	 */
	@RequestMapping(value = "/organization/{companyCode}/roleList", method = RequestMethod.POST)
   public Object queryRoles(@PathVariable String companyCode,@RequestBody SfxieSysOrganizationVo sfxieSysOrganizationVo
   		){
		PageHelper.ignorePaged();
		List<SfxieSysRole>  list = new ArrayList<SfxieSysRole> ();
		SfxieSysRole entity = new SfxieSysRole();
		entity.setPartitionCompany(ServicesContext.getPartitionCompany(companyCode));
		entity.setCreateCompanyCode(companyCode);
		entity.setRoleName(sfxieSysOrganizationVo.getRoleName());
		List<SfxieSysRole>  listTemp =sfxieSysRoleServiceImpl.selectByCondition(entity);
		if(null!=listTemp && listTemp.size()>0)
			list.addAll(listTemp);

		PageHelper.ignorePaged();
		listTemp =sfxieSysRoleServiceImpl.selectRolesByCompanyCodeOnAuth(entity);
		if(null!=listTemp && listTemp.size()>0)
			list.addAll(listTemp);
		Result<List<SfxieSysRole>> result = new Result.BuilderArray<List<SfxieSysRole>>(list).build();
		return result;
   }
	

	/**
     *  新写入数据库记录,sfxie_sys_userrelation
     *
     * @param record
     */
	@RequestMapping(value = "/organization/user", method = RequestMethod.POST)
    public int insertUserRelation(@RequestBody SfxieSysUserRelation record){
    	return sfxieSysUserRelationServiceImpl.insertSelective(record);
    }
	/**
     *  新写入数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/organization/role", method = RequestMethod.POST)
   public int insertRole(@RequestBody SfxieSysRole record){
   	return sfxieSysRoleServiceImpl.insertSelective(record);
   }
}
