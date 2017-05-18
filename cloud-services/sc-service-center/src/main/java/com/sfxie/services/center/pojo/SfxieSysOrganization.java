package com.sfxie.services.center.pojo;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sfxie_sys_organizition
 *
 * @mbg.generated do_not_delete_during_merge Mon May 01 19:06:36 CST 2017
 */
public class SfxieSysOrganization {
    /**
     *  记录主键 : 记录主键,所属表字段为sfxie_sys_organizition.id_
     */
    private String id;

    /**
     *  是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效,所属表字段为sfxie_sys_organizition.is_valid
     */
    private String isValid;

    /**
     *  无效时间 : 无效时间,所属表字段为sfxie_sys_organizition.invalid_date
     */
    private Date invalidDate;

    /**
     *  创建时间 : 创建时间,所属表字段为sfxie_sys_organizition.create_time
     */
    private Date createTime;

    /**
     *  记录创建人 : 记录创建人,所属表字段为sfxie_sys_organizition.create_user
     */
    private String createUser;

    /**
     *  最后修改时间 : 最后修改时间,所属表字段为sfxie_sys_organizition.update_time
     */
    private Date updateTime;

    /**
     *  最后修改人 : 最后修改人,所属表字段为sfxie_sys_organizition.update_user
     */
    private String updateUser;

    /**
     *  排序字段 : 排序字段,所属表字段为sfxie_sys_organizition.sequence_no
     */
    private Integer sequenceNo;

    /**
     *  公司代码 : 关联公司代码,表明创建公司,所属表字段为sfxie_sys_organizition.company_code
     */
    private String companyCode;

    /**
     *  公司代码 : 关联公司代码,表明创建公司,所属表字段为sfxie_sys_organizition.parent_company_code
     */
    private String parentCompanyCode;

    /**
     * 获取 记录主键 : 记录主键 字段:sfxie_sys_organizition.id_
     *
     * @return sfxie_sys_organizition.id_, 记录主键 : 记录主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 记录主键 : 记录主键 字段:sfxie_sys_organizition.id_
     *
     * @param id sfxie_sys_organizition.id_, 记录主键 : 记录主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效 字段:sfxie_sys_organizition.is_valid
     *
     * @return sfxie_sys_organizition.is_valid, 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * 设置 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效 字段:sfxie_sys_organizition.is_valid
     *
     * @param isValid sfxie_sys_organizition.is_valid, 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    /**
     * 获取 无效时间 : 无效时间 字段:sfxie_sys_organizition.invalid_date
     *
     * @return sfxie_sys_organizition.invalid_date, 无效时间 : 无效时间
     */
    public Date getInvalidDate() {
        return invalidDate;
    }

    /**
     * 设置 无效时间 : 无效时间 字段:sfxie_sys_organizition.invalid_date
     *
     * @param invalidDate sfxie_sys_organizition.invalid_date, 无效时间 : 无效时间
     */
    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    /**
     * 获取 创建时间 : 创建时间 字段:sfxie_sys_organizition.create_time
     *
     * @return sfxie_sys_organizition.create_time, 创建时间 : 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间 : 创建时间 字段:sfxie_sys_organizition.create_time
     *
     * @param createTime sfxie_sys_organizition.create_time, 创建时间 : 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 记录创建人 : 记录创建人 字段:sfxie_sys_organizition.create_user
     *
     * @return sfxie_sys_organizition.create_user, 记录创建人 : 记录创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置 记录创建人 : 记录创建人 字段:sfxie_sys_organizition.create_user
     *
     * @param createUser sfxie_sys_organizition.create_user, 记录创建人 : 记录创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 获取 最后修改时间 : 最后修改时间 字段:sfxie_sys_organizition.update_time
     *
     * @return sfxie_sys_organizition.update_time, 最后修改时间 : 最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 最后修改时间 : 最后修改时间 字段:sfxie_sys_organizition.update_time
     *
     * @param updateTime sfxie_sys_organizition.update_time, 最后修改时间 : 最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取 最后修改人 : 最后修改人 字段:sfxie_sys_organizition.update_user
     *
     * @return sfxie_sys_organizition.update_user, 最后修改人 : 最后修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置 最后修改人 : 最后修改人 字段:sfxie_sys_organizition.update_user
     *
     * @param updateUser sfxie_sys_organizition.update_user, 最后修改人 : 最后修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 获取 排序字段 : 排序字段 字段:sfxie_sys_organizition.sequence_no
     *
     * @return sfxie_sys_organizition.sequence_no, 排序字段 : 排序字段
     */
    public Integer getSequenceNo() {
        return sequenceNo;
    }

    /**
     * 设置 排序字段 : 排序字段 字段:sfxie_sys_organizition.sequence_no
     *
     * @param sequenceNo sfxie_sys_organizition.sequence_no, 排序字段 : 排序字段
     */
    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    /**
     * 获取 公司代码 : 关联公司代码,表明创建公司 字段:sfxie_sys_organizition.company_code
     *
     * @return sfxie_sys_organizition.company_code, 公司代码 : 关联公司代码,表明创建公司
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * 设置 公司代码 : 关联公司代码,表明创建公司 字段:sfxie_sys_organizition.company_code
     *
     * @param companyCode sfxie_sys_organizition.company_code, 公司代码 : 关联公司代码,表明创建公司
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    /**
     * 获取 公司代码 : 关联公司代码,表明创建公司 字段:sfxie_sys_organizition.parent_company_code
     *
     * @return sfxie_sys_organizition.parent_company_code, 公司代码 : 关联公司代码,表明创建公司
     */
    public String getParentCompanyCode() {
        return parentCompanyCode;
    }

    /**
     * 设置 公司代码 : 关联公司代码,表明创建公司 字段:sfxie_sys_organizition.parent_company_code
     *
     * @param parentCompanyCode sfxie_sys_organizition.parent_company_code, 公司代码 : 关联公司代码,表明创建公司
     */
    public void setParentCompanyCode(String parentCompanyCode) {
        this.parentCompanyCode = parentCompanyCode == null ? null : parentCompanyCode.trim();
    }
}