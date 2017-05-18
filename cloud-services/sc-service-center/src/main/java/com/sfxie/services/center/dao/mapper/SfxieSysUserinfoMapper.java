package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysUserinfo;
import java.util.List;

public interface SfxieSysUserinfoMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_userinfo
     *
     * @param userId
     */
    int deleteByPrimaryKey(SfxieSysUserinfo record);

    /**
     *  新写入数据库记录,sfxie_sys_userinfo
     *
     * @param record
     */
    int insert(SfxieSysUserinfo record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_userinfo
     *
     * @param record
     */
    int insertSelective(SfxieSysUserinfo record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_userinfo
     *
     * @param userId
     */
    SfxieSysUserinfo selectByPrimaryKey(SfxieSysUserinfo record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_userinfo
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysUserinfo record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_userinfo
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysUserinfo record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_userinfo
     */
    List<SfxieSysUserinfo> selectByCondition(SfxieSysUserinfo record);
}