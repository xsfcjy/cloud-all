package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysPostRole;
import java.util.List;

public interface SfxieSysPostRoleMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_post_role
     *
     * @param id
     */
    int deleteByPrimaryKey(SfxieSysPostRole record);

    /**
     *  新写入数据库记录,sfxie_sys_post_role
     *
     * @param record
     */
    int insert(SfxieSysPostRole record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_post_role
     *
     * @param record
     */
    int insertSelective(SfxieSysPostRole record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_post_role
     *
     * @param id
     */
    SfxieSysPostRole selectByPrimaryKey(SfxieSysPostRole record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_post_role
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysPostRole record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_post_role
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysPostRole record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_post_role
     */
    List<SfxieSysPostRole> selectByCondition(SfxieSysPostRole record);
}