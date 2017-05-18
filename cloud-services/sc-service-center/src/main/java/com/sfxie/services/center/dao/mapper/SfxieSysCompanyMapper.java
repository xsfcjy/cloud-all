package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.SfxieSysCompany;
import java.util.List;

public interface SfxieSysCompanyMapper {
    /**
     *  根据主键删除数据库的记录,sfxie_sys_company
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     *  新写入数据库记录,sfxie_sys_company
     *
     * @param record
     */
    int insert(SfxieSysCompany record);

    /**
     *  动态字段,写入数据库记录,sfxie_sys_company
     *
     * @param record
     */
    int insertSelective(SfxieSysCompany record);

    /**
     *  根据指定主键获取一条数据库记录,sfxie_sys_company
     *
     * @param id
     */
    SfxieSysCompany selectByPrimaryKey(String id);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sfxie_sys_company
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SfxieSysCompany record);

    /**
     *  根据主键来更新符合条件的数据库记录,sfxie_sys_company
     *
     * @param record
     */
    int updateByPrimaryKey(SfxieSysCompany record);

    /**
     *  根据条件查询数据库记录,sfxie_sys_company
     */
    List<SfxieSysCompany> selectByCondition(SfxieSysCompany record);
}