package com.sfxie.services.center.pojo;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sfxie_sys_userinfo
 *
 * @mbg.generated do_not_delete_during_merge Mon May 01 19:06:36 CST 2017
 */
public class SfxieSysUserinfo {
    /**
     *  用户代码 : 用户代码,用户登录名,所属表字段为sfxie_sys_userinfo.user_id
     */
    private String userId;

    /**
     *  用户密码 : 用户密码,加密后的,所属表字段为sfxie_sys_userinfo.user_password
     */
    private String userPassword;

    /**
     *  是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效,所属表字段为sfxie_sys_userinfo.is_valid
     */
    private String isValid;

    /**
     *  创建时间 : 创建时间,所属表字段为sfxie_sys_userinfo.create_time
     */
    private Date createTime;

    /**
     *  记录创建人 : 记录创建人,所属表字段为sfxie_sys_userinfo.create_user
     */
    private String createUser;

    /**
     *  最后修改人 : 最后修改人,所属表字段为sfxie_sys_userinfo.update_user
     */
    private String updateUser;

    /**
     *  最后修改时间 : 最后修改时间,所属表字段为sfxie_sys_userinfo.update_time
     */
    private Date updateTime;

    /**
     *  排序字段 : 排序字段,所属表字段为sfxie_sys_userinfo.sequence_no
     */
    private Integer sequenceNo;

    /**
     *  是否为超级管理员 : 是否为超级管理员，默认为否
Y-是
N-否

只级超级管理员才可以操作此字段,所属表字段为sfxie_sys_userinfo.is_superman
     */
    private String isSuperman;

    /**
     *  创建公司 : 创建公司,所属表字段为sfxie_sys_userinfo.create_company_id
     */
    private String createCompanyId;

    /**
     *  用户性别 : m-男,f-女,所属表字段为sfxie_sys_userinfo.sex
     */
    private String sex;

    /**
     *  分区字段 : 分区字段,从用户公司代码字段取值,所属表字段为sfxie_sys_userinfo.partition_company
     */
    private String partitionCompany;

    /**
     * 获取 用户代码 : 用户代码,用户登录名 字段:sfxie_sys_userinfo.user_id
     *
     * @return sfxie_sys_userinfo.user_id, 用户代码 : 用户代码,用户登录名
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置 用户代码 : 用户代码,用户登录名 字段:sfxie_sys_userinfo.user_id
     *
     * @param userId sfxie_sys_userinfo.user_id, 用户代码 : 用户代码,用户登录名
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取 用户密码 : 用户密码,加密后的 字段:sfxie_sys_userinfo.user_password
     *
     * @return sfxie_sys_userinfo.user_password, 用户密码 : 用户密码,加密后的
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置 用户密码 : 用户密码,加密后的 字段:sfxie_sys_userinfo.user_password
     *
     * @param userPassword sfxie_sys_userinfo.user_password, 用户密码 : 用户密码,加密后的
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    /**
     * 获取 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效 字段:sfxie_sys_userinfo.is_valid
     *
     * @return sfxie_sys_userinfo.is_valid, 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * 设置 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效 字段:sfxie_sys_userinfo.is_valid
     *
     * @param isValid sfxie_sys_userinfo.is_valid, 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    /**
     * 获取 创建时间 : 创建时间 字段:sfxie_sys_userinfo.create_time
     *
     * @return sfxie_sys_userinfo.create_time, 创建时间 : 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间 : 创建时间 字段:sfxie_sys_userinfo.create_time
     *
     * @param createTime sfxie_sys_userinfo.create_time, 创建时间 : 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 记录创建人 : 记录创建人 字段:sfxie_sys_userinfo.create_user
     *
     * @return sfxie_sys_userinfo.create_user, 记录创建人 : 记录创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置 记录创建人 : 记录创建人 字段:sfxie_sys_userinfo.create_user
     *
     * @param createUser sfxie_sys_userinfo.create_user, 记录创建人 : 记录创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 获取 最后修改人 : 最后修改人 字段:sfxie_sys_userinfo.update_user
     *
     * @return sfxie_sys_userinfo.update_user, 最后修改人 : 最后修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置 最后修改人 : 最后修改人 字段:sfxie_sys_userinfo.update_user
     *
     * @param updateUser sfxie_sys_userinfo.update_user, 最后修改人 : 最后修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 获取 最后修改时间 : 最后修改时间 字段:sfxie_sys_userinfo.update_time
     *
     * @return sfxie_sys_userinfo.update_time, 最后修改时间 : 最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 最后修改时间 : 最后修改时间 字段:sfxie_sys_userinfo.update_time
     *
     * @param updateTime sfxie_sys_userinfo.update_time, 最后修改时间 : 最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取 排序字段 : 排序字段 字段:sfxie_sys_userinfo.sequence_no
     *
     * @return sfxie_sys_userinfo.sequence_no, 排序字段 : 排序字段
     */
    public Integer getSequenceNo() {
        return sequenceNo;
    }

    /**
     * 设置 排序字段 : 排序字段 字段:sfxie_sys_userinfo.sequence_no
     *
     * @param sequenceNo sfxie_sys_userinfo.sequence_no, 排序字段 : 排序字段
     */
    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    /**
     * 获取 是否为超级管理员 : 是否为超级管理员，默认为否
Y-是
N-否

只级超级管理员才可以操作此字段 字段:sfxie_sys_userinfo.is_superman
     *
     * @return sfxie_sys_userinfo.is_superman, 是否为超级管理员 : 是否为超级管理员，默认为否
Y-是
N-否

只级超级管理员才可以操作此字段
     */
    public String getIsSuperman() {
        return isSuperman;
    }

    /**
     * 设置 是否为超级管理员 : 是否为超级管理员，默认为否
Y-是
N-否

只级超级管理员才可以操作此字段 字段:sfxie_sys_userinfo.is_superman
     *
     * @param isSuperman sfxie_sys_userinfo.is_superman, 是否为超级管理员 : 是否为超级管理员，默认为否
Y-是
N-否

只级超级管理员才可以操作此字段
     */
    public void setIsSuperman(String isSuperman) {
        this.isSuperman = isSuperman == null ? null : isSuperman.trim();
    }

    /**
     * 获取 创建公司 : 创建公司 字段:sfxie_sys_userinfo.create_company_id
     *
     * @return sfxie_sys_userinfo.create_company_id, 创建公司 : 创建公司
     */
    public String getCreateCompanyId() {
        return createCompanyId;
    }

    /**
     * 设置 创建公司 : 创建公司 字段:sfxie_sys_userinfo.create_company_id
     *
     * @param createCompanyId sfxie_sys_userinfo.create_company_id, 创建公司 : 创建公司
     */
    public void setCreateCompanyId(String createCompanyId) {
        this.createCompanyId = createCompanyId == null ? null : createCompanyId.trim();
    }

    /**
     * 获取 用户性别 : m-男,f-女 字段:sfxie_sys_userinfo.sex
     *
     * @return sfxie_sys_userinfo.sex, 用户性别 : m-男,f-女
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置 用户性别 : m-男,f-女 字段:sfxie_sys_userinfo.sex
     *
     * @param sex sfxie_sys_userinfo.sex, 用户性别 : m-男,f-女
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 获取 分区字段 : 分区字段,从用户公司代码字段取值 字段:sfxie_sys_userinfo.partition_company
     *
     * @return sfxie_sys_userinfo.partition_company, 分区字段 : 分区字段,从用户公司代码字段取值
     */
    public String getPartitionCompany() {
        return partitionCompany;
    }

    /**
     * 设置 分区字段 : 分区字段,从用户公司代码字段取值 字段:sfxie_sys_userinfo.partition_company
     *
     * @param partitionCompany sfxie_sys_userinfo.partition_company, 分区字段 : 分区字段,从用户公司代码字段取值
     */
    public void setPartitionCompany(String partitionCompany) {
        this.partitionCompany = partitionCompany == null ? null : partitionCompany.trim();
    }
}