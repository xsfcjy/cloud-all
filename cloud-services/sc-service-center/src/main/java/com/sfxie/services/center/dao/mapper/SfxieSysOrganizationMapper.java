package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysOrganization;
import java.util.List;

public interface SfxieSysOrganizationMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_organization
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     *  新写入数据库记录,sfxie_sys_organization
     *
     * @param record
     */
    int insert(SfxieSysOrganization record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_organization
     *
     * @param record
     */
    int insertSelective(SfxieSysOrganization record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_organization
     *
     * @param id
     */
    SfxieSysOrganization selectByPrimaryKey(String id);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_organization
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysOrganization record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_organization
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysOrganization record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_organization
     */
    List<SfxieSysOrganization> selectByCondition(SfxieSysOrganization record);
}