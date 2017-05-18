package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysAuthorization;
import java.util.List;

public interface SfxieSysAuthorizationMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_authorization
     *
     * @param id
     */
    int deleteByPrimaryKey(SfxieSysAuthorization record);

    /**
     *  新写入数据库记录,sfxie_sys_authorization
     *
     * @param record
     */
    int insert(SfxieSysAuthorization record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_authorization
     *
     * @param record
     */
    int insertSelective(SfxieSysAuthorization record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_authorization
     *
     * @param id
     */
    SfxieSysAuthorization selectByPrimaryKey(SfxieSysAuthorization record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_authorization
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysAuthorization record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_authorization
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysAuthorization record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_authorization
     */
    List<SfxieSysAuthorization> selectByCondition(SfxieSysAuthorization record);
}