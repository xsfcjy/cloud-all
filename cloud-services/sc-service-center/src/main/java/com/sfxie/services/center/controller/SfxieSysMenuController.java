package com.sfxie.services.center.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sfxie.component.node.LeftNode;
import com.sfxie.component.node.NodeComponent;
import com.sfxie.component.node.ParentNode;
import com.sfxie.component.ui.menu.MenuUtil;
import com.sfxie.core.framework.mvc.handle.Result;
import com.sfxie.services.center.pojo.SfxieSysMenu;
import com.sfxie.services.center.service.impl.SfxieSysMenuServiceImpl;
import com.sfxie.services.center.ui.easyui.EasyUIMenuDecorator;

@RestController
public class SfxieSysMenuController {

	@Resource
	private SfxieSysMenuServiceImpl sfxieSysMenuService;
	
	@RequestMapping(value = "/menu/list", method = RequestMethod.POST)
	public @ResponseBody Object sfxieMenus(@RequestBody SfxieSysMenu sfxieSysMenu) throws Exception {
		List<SfxieSysMenu>  list = sfxieSysMenuService.selectByCondition(sfxieSysMenu);
		List<NodeComponent> menus = MenuUtil.generateMenus(list,new EasyUIMenuDecorator());
		Result<List<NodeComponent>> result = new Result.BuilderArray<List<NodeComponent>>(menus).build();
		return result;
	}
	
	@RequestMapping(value = "/menu/{id}/{partitionCompany}", method = RequestMethod.GET)
	public Object selectByPrimaryKey(@PathVariable(value="id") String id ,@PathVariable(value="partitionCompany") String partitionCompany){
		SfxieSysMenu sfxieSysMenu = new SfxieSysMenu ();
		sfxieSysMenu.setId(id);
		sfxieSysMenu.setPartitionCompany(partitionCompany);
		Result<SfxieSysMenu> result = new Result.BuilderObject<SfxieSysMenu>(sfxieSysMenu).build();
    	return result;
    }
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_company
     *
     * @param id
     */
	@RequestMapping(value = "/menu/{id}/{partitionCompany}", method = RequestMethod.DELETE)
    public int deleteByPrimaryKey(@PathVariable(value="id") String id ,@PathVariable(value="partitionCompany") String partitionCompany){
		SfxieSysMenu sfxieSysMenu = new SfxieSysMenu ();
		sfxieSysMenu.setId(id);
		sfxieSysMenu.setPartitionCompany(partitionCompany);
    	return sfxieSysMenuService.deleteByPrimaryKey(sfxieSysMenu);
    }

    /**
     *  新写入数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/menu/insert", method = RequestMethod.POST)
    public int insert(@RequestBody SfxieSysMenu record){
    	return sfxieSysMenuService.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/menu/insertSelective", method = RequestMethod.POST)
    public int insertSelective(@RequestBody SfxieSysMenu record){
    	return sfxieSysMenuService.insertSelective(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/menu/updateSelective", method = RequestMethod.PUT)
    public int updateByPrimaryKeySelective(@RequestBody SfxieSysMenu record){
    	return sfxieSysMenuService.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/menu/update", method = RequestMethod.PUT)
    public int updateByPrimaryKey(@RequestBody SfxieSysMenu record){
    	return sfxieSysMenuService.updateByPrimaryKey(record);
    }
}
