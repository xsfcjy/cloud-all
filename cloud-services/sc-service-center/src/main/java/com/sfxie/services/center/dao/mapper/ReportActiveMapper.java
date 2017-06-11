package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.ReportActive;
import java.util.List;

public interface ReportActiveMapper {
    /**
     *  根据主键删除数据库的记录,report_active
     *
     * @param id
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新写入数据库记录,report_active
     *
     * @param record
     */
    int insert(ReportActive record);

    /**
     *  动态字段,写入数据库记录,report_active
     *
     * @param record
     */
    int insertSelective(ReportActive record);

    /**
     *  根据指定主键获取一条数据库记录,report_active
     *
     * @param id
     */
    ReportActive selectByPrimaryKey(Long id);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,report_active
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ReportActive record);

    /**
     *  根据主键来更新符合条件的数据库记录,report_active
     *
     * @param record
     */
    int updateByPrimaryKey(ReportActive record);

    /**
     *  根据条件查询数据库记录,report_active
     */
    List<ReportActive> selectByCondition(ReportActive record);
}