package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysRoleAction;
import java.util.List;

public interface SfxieSysRoleActionMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_role_action
     *
     * @param id
     */
    int deleteByPrimaryKey(SfxieSysRoleAction record);

    /**
     *  新写入数据库记录,sfxie_sys_role_action
     *
     * @param record
     */
    int insert(SfxieSysRoleAction record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_role_action
     *
     * @param record
     */
    int insertSelective(SfxieSysRoleAction record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_role_action
     *
     * @param id
     */
    SfxieSysRoleAction selectByPrimaryKey(SfxieSysRoleAction record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_role_action
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysRoleAction record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_role_action
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysRoleAction record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_role_action
     */
    List<SfxieSysRoleAction> selectByCondition(SfxieSysRoleAction record);
}