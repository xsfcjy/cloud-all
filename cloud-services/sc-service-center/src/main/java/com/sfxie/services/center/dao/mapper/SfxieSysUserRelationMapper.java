package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysUserRelation;
import java.util.List;

public interface SfxieSysUserRelationMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_user_relation
     *
     * @param id
     */
    int deleteByPrimaryKey(SfxieSysUserRelation record);

    /**
     *  新写入数据库记录,sfxie_sys_user_relation
     *
     * @param record
     */
    int insert(SfxieSysUserRelation record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_user_relation
     *
     * @param record
     */
    int insertSelective(SfxieSysUserRelation record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_user_relation
     *
     * @param id
     */
    SfxieSysUserRelation selectByPrimaryKey(SfxieSysUserRelation record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_user_relation
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysUserRelation record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_user_relation
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysUserRelation record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_user_relation
     */
    List<SfxieSysUserRelation> selectByCondition(SfxieSysUserRelation record);
}