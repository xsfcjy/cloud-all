package com.sfxie.services.center.pojo;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sfxie_sys_system
 *
 * @mbg.generated do_not_delete_during_merge Thu Jun 08 08:42:00 CST 2017
 */
public class SfxieSysSystem {
    /**
     *  记录主键 : 记录主键,所属表字段为sfxie_sys_system.id_
     */
    private String id;

    /**
     *  系统代码 : 系统代码,所属表字段为sfxie_sys_system.system_code
     */
    private String systemCode;

    /**
     *  系统名称 : 系统名称,所属表字段为sfxie_sys_system.system_name
     */
    private String systemName;

    /**
     *  描述 : 描述,所属表字段为sfxie_sys_system.description
     */
    private String description;

    /**
     *  sys_url,所属表字段为sfxie_sys_system.sys_url
     */
    private String sysUrl;

    /**
     *  sys_inner_url,所属表字段为sfxie_sys_system.sys_inner_url
     */
    private String sysInnerUrl;

    /**
     *  排序字段 : 排序字段,所属表字段为sfxie_sys_system.sequence_no
     */
    private Integer sequenceNo;

    /**
     *  是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效,所属表字段为sfxie_sys_system.is_valid
     */
    private String isValid;

    /**
     *  创建时间 : 创建时间,所属表字段为sfxie_sys_system.create_time
     */
    private Date createTime;

    /**
     *  记录创建人 : 记录创建人,所属表字段为sfxie_sys_system.create_user
     */
    private String createUser;

    /**
     *  最后修改时间 : 最后修改时间,所属表字段为sfxie_sys_system.update_time
     */
    private Date updateTime;

    /**
     *  最后修改人 : 最后修改人,所属表字段为sfxie_sys_system.update_user
     */
    private String updateUser;

    /**
     *  分区字段 : 分区字段,从用户公司代码字段取值,所属表字段为sfxie_sys_system.partition_company
     */
    private String partitionCompany;

    /**
     * 获取 记录主键 : 记录主键 字段:sfxie_sys_system.id_
     *
     * @return sfxie_sys_system.id_, 记录主键 : 记录主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 记录主键 : 记录主键 字段:sfxie_sys_system.id_
     *
     * @param id sfxie_sys_system.id_, 记录主键 : 记录主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取 系统代码 : 系统代码 字段:sfxie_sys_system.system_code
     *
     * @return sfxie_sys_system.system_code, 系统代码 : 系统代码
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * 设置 系统代码 : 系统代码 字段:sfxie_sys_system.system_code
     *
     * @param systemCode sfxie_sys_system.system_code, 系统代码 : 系统代码
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    /**
     * 获取 系统名称 : 系统名称 字段:sfxie_sys_system.system_name
     *
     * @return sfxie_sys_system.system_name, 系统名称 : 系统名称
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * 设置 系统名称 : 系统名称 字段:sfxie_sys_system.system_name
     *
     * @param systemName sfxie_sys_system.system_name, 系统名称 : 系统名称
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName == null ? null : systemName.trim();
    }

    /**
     * 获取 描述 : 描述 字段:sfxie_sys_system.description
     *
     * @return sfxie_sys_system.description, 描述 : 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置 描述 : 描述 字段:sfxie_sys_system.description
     *
     * @param description sfxie_sys_system.description, 描述 : 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取 sys_url 字段:sfxie_sys_system.sys_url
     *
     * @return sfxie_sys_system.sys_url, sys_url
     */
    public String getSysUrl() {
        return sysUrl;
    }

    /**
     * 设置 sys_url 字段:sfxie_sys_system.sys_url
     *
     * @param sysUrl sfxie_sys_system.sys_url, sys_url
     */
    public void setSysUrl(String sysUrl) {
        this.sysUrl = sysUrl == null ? null : sysUrl.trim();
    }

    /**
     * 获取 sys_inner_url 字段:sfxie_sys_system.sys_inner_url
     *
     * @return sfxie_sys_system.sys_inner_url, sys_inner_url
     */
    public String getSysInnerUrl() {
        return sysInnerUrl;
    }

    /**
     * 设置 sys_inner_url 字段:sfxie_sys_system.sys_inner_url
     *
     * @param sysInnerUrl sfxie_sys_system.sys_inner_url, sys_inner_url
     */
    public void setSysInnerUrl(String sysInnerUrl) {
        this.sysInnerUrl = sysInnerUrl == null ? null : sysInnerUrl.trim();
    }

    /**
     * 获取 排序字段 : 排序字段 字段:sfxie_sys_system.sequence_no
     *
     * @return sfxie_sys_system.sequence_no, 排序字段 : 排序字段
     */
    public Integer getSequenceNo() {
        return sequenceNo;
    }

    /**
     * 设置 排序字段 : 排序字段 字段:sfxie_sys_system.sequence_no
     *
     * @param sequenceNo sfxie_sys_system.sequence_no, 排序字段 : 排序字段
     */
    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    /**
     * 获取 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效 字段:sfxie_sys_system.is_valid
     *
     * @return sfxie_sys_system.is_valid, 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * 设置 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效 字段:sfxie_sys_system.is_valid
     *
     * @param isValid sfxie_sys_system.is_valid, 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    /**
     * 获取 创建时间 : 创建时间 字段:sfxie_sys_system.create_time
     *
     * @return sfxie_sys_system.create_time, 创建时间 : 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间 : 创建时间 字段:sfxie_sys_system.create_time
     *
     * @param createTime sfxie_sys_system.create_time, 创建时间 : 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 记录创建人 : 记录创建人 字段:sfxie_sys_system.create_user
     *
     * @return sfxie_sys_system.create_user, 记录创建人 : 记录创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置 记录创建人 : 记录创建人 字段:sfxie_sys_system.create_user
     *
     * @param createUser sfxie_sys_system.create_user, 记录创建人 : 记录创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 获取 最后修改时间 : 最后修改时间 字段:sfxie_sys_system.update_time
     *
     * @return sfxie_sys_system.update_time, 最后修改时间 : 最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 最后修改时间 : 最后修改时间 字段:sfxie_sys_system.update_time
     *
     * @param updateTime sfxie_sys_system.update_time, 最后修改时间 : 最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取 最后修改人 : 最后修改人 字段:sfxie_sys_system.update_user
     *
     * @return sfxie_sys_system.update_user, 最后修改人 : 最后修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置 最后修改人 : 最后修改人 字段:sfxie_sys_system.update_user
     *
     * @param updateUser sfxie_sys_system.update_user, 最后修改人 : 最后修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 获取 分区字段 : 分区字段,从用户公司代码字段取值 字段:sfxie_sys_system.partition_company
     *
     * @return sfxie_sys_system.partition_company, 分区字段 : 分区字段,从用户公司代码字段取值
     */
    public String getPartitionCompany() {
        return partitionCompany;
    }

    /**
     * 设置 分区字段 : 分区字段,从用户公司代码字段取值 字段:sfxie_sys_system.partition_company
     *
     * @param partitionCompany sfxie_sys_system.partition_company, 分区字段 : 分区字段,从用户公司代码字段取值
     */
    public void setPartitionCompany(String partitionCompany) {
        this.partitionCompany = partitionCompany == null ? null : partitionCompany.trim();
    }
}