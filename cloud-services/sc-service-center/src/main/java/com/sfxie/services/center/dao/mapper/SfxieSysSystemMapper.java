package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysSystem;
import java.util.List;

public interface SfxieSysSystemMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_system
     *
     * @param id
     */
    int deleteByPrimaryKey(SfxieSysSystem record);

    /**
     *  新写入数据库记录,sfxie_sys_system
     *
     * @param record
     */
    int insert(SfxieSysSystem record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_system
     *
     * @param record
     */
    int insertSelective(SfxieSysSystem record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_system
     *
     * @param id
     */
    SfxieSysSystem selectByPrimaryKey(SfxieSysSystem record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_system
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysSystem record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_system
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysSystem record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_system
     */
    List<SfxieSysSystem> selectByCondition(SfxieSysSystem record);
}