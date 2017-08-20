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
import com.sfxie.services.center.pojo.SfxieSysPost;
import com.sfxie.services.center.service.impl.SfxieSysPostServiceImpl;
import com.sfxie.services.center.util.ServicesContext;

@RestController
public class SysPostController {

	@Resource
	private SfxieSysPostServiceImpl sfxieSysPostService;
	
	@RequestMapping(value = "/post/list", method = RequestMethod.POST)
	public @ResponseBody Object sfxiePosts(@RequestBody SfxieSysPost sfxieSysPost) {
		
		List<SfxieSysPost>  list = sfxieSysPostService.selectByCondition(sfxieSysPost);
		Result<List<SfxieSysPost>> result = new Result.BuilderArray<List<SfxieSysPost>>(list).build();
		return result;
	}
	
	@RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
	public Object selectByPrimaryKey(@PathVariable(value="id") String id ){
		SfxieSysPost sfxieSysPost = new SfxieSysPost ();
		sfxieSysPost.setId(id);
		sfxieSysPost.setPartitionCompany(ServicesContext.getUserDefaultPartitionCompany());
		Result<SfxieSysPost> result = new Result.BuilderObject<SfxieSysPost>(sfxieSysPostService.selectByPrimaryKey(sfxieSysPost)).build();
    	return result;
    }
	
	/**
     *  根据主键删除数据库的记录,sfxie_sys_company
     *
     * @param id
     */
	@RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public int deleteByPrimaryKey(@PathVariable(value="id") String id ){
		SfxieSysPost sfxieSysPost = new SfxieSysPost ();
		sfxieSysPost.setId(id);
		sfxieSysPost.setPartitionCompany(ServicesContext.getUserDefaultPartitionCompany());
    	return sfxieSysPostService.deleteByPrimaryKey(sfxieSysPost);
    }

    /**
     *  新写入数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/post/insert", method = RequestMethod.POST)
    public int insert(@RequestBody SfxieSysPost record){
    	return sfxieSysPostService.insert(record);
    }

    /**
     *  动态字段,写入数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/post", method = RequestMethod.POST)
    public int insertSelective(@RequestBody SfxieSysPost record){
    	return sfxieSysPostService.insertSelective(record);
    }

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/post", method = RequestMethod.PUT)
    public int updateByPrimaryKeySelective(@RequestBody SfxieSysPost record){
    	return sfxieSysPostService.updateByPrimaryKeySelective(record);
    }

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_company
     *
     * @param record
     */
	@RequestMapping(value = "/post/update", method = RequestMethod.PUT)
    public int updateByPrimaryKey(@RequestBody SfxieSysPost record){
    	return sfxieSysPostService.updateByPrimaryKey(record);
    }
}
