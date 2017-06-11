package com.sfxie.services.center.dao.mapper;

import com.sfxie.services.center.pojo.ReportServerRegister;
import java.util.List;

public interface ReportServerRegisterMapper {
    /**
     *  根据主键删除数据库的记录,report_server_register
     *
     * @param id
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新写入数据库记录,report_server_register
     *
     * @param record
     */
    int insert(ReportServerRegister record);

    /**
     *  动态字段,写入数据库记录,report_server_register
     *
     * @param record
     */
    int insertSelective(ReportServerRegister record);

    /**
     *  根据指定主键获取一条数据库记录,report_server_register
     *
     * @param id
     */
    ReportServerRegister selectByPrimaryKey(Long id);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,report_server_register
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ReportServerRegister record);

    /**
     *  根据主键来更新符合条件的数据库记录,report_server_register
     *
     * @param record
     */
    int updateByPrimaryKey(ReportServerRegister record);

    /**
     *  根据条件查询数据库记录,report_server_register
     */
    List<ReportServerRegister> selectByCondition(ReportServerRegister record);
}