package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysAuthRoleMenu;
import java.util.List;

public interface SfxieSysAuthRoleMenuMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_auth_role_menu
     *
     * @param id
     */
    int deleteByPrimaryKey(SfxieSysAuthRoleMenu record);

    /**
     *  新写入数据库记录,sfxie_sys_auth_role_menu
     *
     * @param record
     */
    int insert(SfxieSysAuthRoleMenu record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_auth_role_menu
     *
     * @param record
     */
    int insertSelective(SfxieSysAuthRoleMenu record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_auth_role_menu
     *
     * @param id
     */
    SfxieSysAuthRoleMenu selectByPrimaryKey(SfxieSysAuthRoleMenu record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_auth_role_menu
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysAuthRoleMenu record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_auth_role_menu
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysAuthRoleMenu record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_auth_role_menu
     */
    List<SfxieSysAuthRoleMenu> selectByCondition(SfxieSysAuthRoleMenu record);
}