package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.ReportParameterDefinitionVersion;
import java.util.List;

public interface ReportParameterDefinitionVersionMapper {
    /**
     *  根据主键删除数据库的记录,report_parameter_definition_version
     *
     * @param id
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新写入数据库记录,report_parameter_definition_version
     *
     * @param record
     */
    int insert(ReportParameterDefinitionVersion record);

    /**
     *  动态字段,写入数据库记录,report_parameter_definition_version
     *
     * @param record
     */
    int insertSelective(ReportParameterDefinitionVersion record);

    /**
     *  根据指定主键获取一条数据库记录,report_parameter_definition_version
     *
     * @param id
     */
    ReportParameterDefinitionVersion selectByPrimaryKey(Long id);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,report_parameter_definition_version
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ReportParameterDefinitionVersion record);

    /**
     *  根据主键来更新符合条件的数据库记录,report_parameter_definition_version
     *
     * @param record
     */
    int updateByPrimaryKey(ReportParameterDefinitionVersion record);

    /**
     *  根据条件查询数据库记录,report_parameter_definition_version
     */
    List<ReportParameterDefinitionVersion> selectByCondition(ReportParameterDefinitionVersion record);
}