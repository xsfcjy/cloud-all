package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.ReportParameter;
import java.util.List;

public interface ReportParameterMapper {
    /**
     *  根据主键删除数据库的记录,report_parameter
     *
     * @param id
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新写入数据库记录,report_parameter
     *
     * @param record
     */
    int insert(ReportParameter record);

    /**
     *  动态字段,写入数据库记录,report_parameter
     *
     * @param record
     */
    int insertSelective(ReportParameter record);

    /**
     *  根据指定主键获取一条数据库记录,report_parameter
     *
     * @param id
     */
    ReportParameter selectByPrimaryKey(Long id);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,report_parameter
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ReportParameter record);

    /**
     * ,report_parameter
     *
     * @param record
     */
    int updateByPrimaryKeyWithBLOBs(ReportParameter record);

    /**
     *  根据主键来更新符合条件的数据库记录,report_parameter
     *
     * @param record
     */
    int updateByPrimaryKey(ReportParameter record);

    /**
     *  根据条件查询数据库记录,report_parameter
     */
    List<ReportParameter> selectByCondition(ReportParameter record);
}