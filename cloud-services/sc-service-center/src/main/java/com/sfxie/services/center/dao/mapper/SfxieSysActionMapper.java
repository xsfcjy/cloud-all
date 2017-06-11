package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysAction;
import java.util.List;

public interface SfxieSysActionMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_action
     *
     * @param id
     */
    int deleteByPrimaryKey(SfxieSysAction record);

    /**
     *  新写入数据库记录,sfxie_sys_action
     *
     * @param record
     */
    int insert(SfxieSysAction record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_action
     *
     * @param record
     */
    int insertSelective(SfxieSysAction record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_action
     *
     * @param id
     */
    SfxieSysAction selectByPrimaryKey(SfxieSysAction record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_action
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysAction record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_action
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysAction record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_action
     */
    List<SfxieSysAction> selectByCondition(SfxieSysAction record);
}