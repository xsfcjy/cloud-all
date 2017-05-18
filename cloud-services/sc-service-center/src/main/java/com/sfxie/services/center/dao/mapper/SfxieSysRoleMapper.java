package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysRole;
import java.util.List;

public interface SfxieSysRoleMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_role
     *
     * @param id
     */
    int deleteByPrimaryKey(SfxieSysRole record);

    /**
     *  新写入数据库记录,sfxie_sys_role
     *
     * @param record
     */
    int insert(SfxieSysRole record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_role
     *
     * @param record
     */
    int insertSelective(SfxieSysRole record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_role
     *
     * @param id
     */
    SfxieSysRole selectByPrimaryKey(SfxieSysRole record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_role
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysRole record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_role
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysRole record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_role
     */
    List<SfxieSysRole> selectByCondition(SfxieSysRole record);
}