package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysDepartment;
import java.util.List;

public interface SfxieSysDepartmentMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_department
     *
     * @param id
     */
    int deleteByPrimaryKey(SfxieSysDepartment record);

    /**
     *  新写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    int insert(SfxieSysDepartment record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_department
     *
     * @param record
     */
    int insertSelective(SfxieSysDepartment record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_department
     *
     * @param id
     */
    SfxieSysDepartment selectByPrimaryKey(SfxieSysDepartment record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysDepartment record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_department
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysDepartment record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_department
     */
    List<SfxieSysDepartment> selectByCondition(SfxieSysDepartment record);
}