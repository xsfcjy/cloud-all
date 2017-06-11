package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysMenu;
import java.util.List;

public interface SfxieSysMenuMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_menu
     *
     * @param id
     */
    int deleteByPrimaryKey(SfxieSysMenu record);

    /**
     *  新写入数据库记录,sfxie_sys_menu
     *
     * @param record
     */
    int insert(SfxieSysMenu record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_menu
     *
     * @param record
     */
    int insertSelective(SfxieSysMenu record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_menu
     *
     * @param id
     */
    SfxieSysMenu selectByPrimaryKey(SfxieSysMenu record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_menu
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysMenu record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_menu
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysMenu record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_menu
     */
    List<SfxieSysMenu> selectByCondition(SfxieSysMenu record);
}