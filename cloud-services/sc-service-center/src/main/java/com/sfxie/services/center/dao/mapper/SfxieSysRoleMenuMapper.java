package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysRoleMenu;
import java.util.List;

public interface SfxieSysRoleMenuMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_role_menu
     *
     * @param id
     */
    int deleteByPrimaryKey(SfxieSysRoleMenu record);

    /**
     *  新写入数据库记录,sfxie_sys_role_menu
     *
     * @param record
     */
    int insert(SfxieSysRoleMenu record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_role_menu
     *
     * @param record
     */
    int insertSelective(SfxieSysRoleMenu record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_role_menu
     *
     * @param id
     */
    SfxieSysRoleMenu selectByPrimaryKey(SfxieSysRoleMenu record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_role_menu
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysRoleMenu record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_role_menu
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysRoleMenu record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_role_menu
     */
    List<SfxieSysRoleMenu> selectByCondition(SfxieSysRoleMenu record);
}