package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysAuthData;
import java.util.List;

public interface SfxieSysAuthDataMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_auth_data
     *
     * @param id
     */
    int deleteByPrimaryKey(SfxieSysAuthData record);

    /**
     *  新写入数据库记录,sfxie_sys_auth_data
     *
     * @param record
     */
    int insert(SfxieSysAuthData record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_auth_data
     *
     * @param record
     */
    int insertSelective(SfxieSysAuthData record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_auth_data
     *
     * @param id
     */
    SfxieSysAuthData selectByPrimaryKey(SfxieSysAuthData record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_auth_data
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysAuthData record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_auth_data
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysAuthData record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_auth_data
     */
    List<SfxieSysAuthData> selectByCondition(SfxieSysAuthData record);
}