package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysUserRole;
import java.util.List;

public interface SfxieSysUserRoleMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_user_role
     *
     * @param id
     */
    int deleteByPrimaryKey(SfxieSysUserRole record);

    /**
     *  新写入数据库记录,sfxie_sys_user_role
     *
     * @param record
     */
    int insert(SfxieSysUserRole record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_user_role
     *
     * @param record
     */
    int insertSelective(SfxieSysUserRole record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_user_role
     *
     * @param id
     */
    SfxieSysUserRole selectByPrimaryKey(SfxieSysUserRole record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_user_role
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysUserRole record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_user_role
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysUserRole record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_user_role
     */
    List<SfxieSysUserRole> selectByCondition(SfxieSysUserRole record);
}