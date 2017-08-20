package com.sfxie.services.center.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sfxie.services.center.service.impl.MenuTreeServiceImpl;

/**
 * 组织结构管理控制器
 * @author xiesf
 * @since 2017年7月18日 下午2:42:10
 */
@RestController
public class MenuController {

	@Resource
	private MenuTreeServiceImpl service;
	
	
	/**
	 * 根据用户id获取用户的组织结构数据
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/menuTree/{userId}", method = {RequestMethod.POST})
	public @ResponseBody Object organizationByUser(@PathVariable String userId ) {
		return service.selectByUserId(userId);
	}
	@RequestMapping(value = "/menuTree/{menuTypye}/sub/{parentCode}", method = {RequestMethod.POST})
	public @ResponseBody Object menuByParentCode(@PathVariable String menuTypye,@PathVariable String parentCode) {
		return service.selectByParentCode(menuTypye,parentCode);
	}
	
}
