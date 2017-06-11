package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.ReportVersion;
import com.sfxie.services.center.pojo.ReportVersionWithBLOBs;
import java.util.List;

public interface ReportVersionMapper {
    /**
     *  根据主键删除数据库的记录,report_version
     *
     * @param id
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新写入数据库记录,report_version
     *
     * @param record
     */
    int insert(ReportVersionWithBLOBs record);

    /**
     *  动态字段,写入数据库记录,report_version
     *
     * @param record
     */
    int insertSelective(ReportVersionWithBLOBs record);

    /**
     *  根据指定主键获取一条数据库记录,report_version
     *
     * @param id
     */
    ReportVersionWithBLOBs selectByPrimaryKey(Long id);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,report_version
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ReportVersionWithBLOBs record);

    /**
     * ,report_version
     *
     * @param record
     */
    int updateByPrimaryKeyWithBLOBs(ReportVersionWithBLOBs record);

    /**
     *  根据主键来更新符合条件的数据库记录,report_version
     *
     * @param record
     */
    int updateByPrimaryKey(ReportVersion record);

    /**
     *  根据条件查询数据库记录,report_version
     */
    List<ReportVersion> selectByCondition(ReportVersionWithBLOBs record);
}