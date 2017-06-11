package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysPost;
import java.util.List;

public interface SfxieSysPostMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_post
     *
     * @param id
     */
    int deleteByPrimaryKey(SfxieSysPost record);

    /**
     *  新写入数据库记录,sfxie_sys_post
     *
     * @param record
     */
    int insert(SfxieSysPost record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_post
     *
     * @param record
     */
    int insertSelective(SfxieSysPost record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_post
     *
     * @param id
     */
    SfxieSysPost selectByPrimaryKey(SfxieSysPost record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_post
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysPost record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_post
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysPost record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_post
     */
    List<SfxieSysPost> selectByCondition(SfxieSysPost record);
}