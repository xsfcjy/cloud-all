package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.ReportDefinition;
import java.util.List;

public interface ReportDefinitionMapper {
    /**
     *  根据主键删除数据库的记录,report_definition
     *
     * @param id
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新写入数据库记录,report_definition
     *
     * @param record
     */
    int insert(ReportDefinition record);

    /**
     *  动态字段,写入数据库记录,report_definition
     *
     * @param record
     */
    int insertSelective(ReportDefinition record);

    /**
     *  根据指定主键获取一条数据库记录,report_definition
     *
     * @param id
     */
    ReportDefinition selectByPrimaryKey(Long id);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,report_definition
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ReportDefinition record);

    /**
     *  根据主键来更新符合条件的数据库记录,report_definition
     *
     * @param record
     */
    int updateByPrimaryKey(ReportDefinition record);

    /**
     *  根据条件查询数据库记录,report_definition
     */
    List<ReportDefinition> selectByCondition(ReportDefinition record);
}