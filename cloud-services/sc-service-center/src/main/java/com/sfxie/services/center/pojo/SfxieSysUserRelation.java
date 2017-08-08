package com.sfxie.services.center.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sfxie_sys_user_relation
 *
 * @mbg.generated do_not_delete_during_merge Thu Jun 08 08:42:00 CST 2017
 */
public class SfxieSysUserRelation {
    /**
     *  记录主键 : 记录主键,所属表字段为sfxie_sys_user_relation.id_
     */
    private String id;

    /**
     *  用户代码 : 用户代码,所属表字段为sfxie_sys_user_relation.user_id
     */
    private String userId;

    /**
     *  用户职位 : 用户职位,所属表字段为sfxie_sys_user_relation.user_title
     */
    private String userTitle;

    /**
     *  是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效,所属表字段为sfxie_sys_user_relation.is_valid
     */
    private String isValid;

    /**
     *  创建时间 : 创建时间,所属表字段为sfxie_sys_user_relation.create_time
     */
    private Date createTime;

    /**
     *  最后修改时间 : 最后修改时间,所属表字段为sfxie_sys_user_relation.update_time
     */
    private Date updateTime;

    /**
     *  最后修改人 : 最后修改人,所属表字段为sfxie_sys_user_relation.update_user
     */
    private String updateUser;

    /**
     *  记录创建人 : 记录创建人,所属表字段为sfxie_sys_user_relation.create_user
     */
    private String createUser;

    /**
     *  创建公司 : 创建公司,所属表字段为sfxie_sys_user_relation.create_company_code
     */
    private String createCompanyCode;

    /**
     *  email : email,所属表字段为sfxie_sys_user_relation.email
     */
    private String email;

    /**
     *  手机号 : 手机号,所属表字段为sfxie_sys_user_relation.phone
     */
    private String phone;

    /**
     *  用户中文名 : 用户中文名,所属表字段为sfxie_sys_user_relation.user_name_cn
     */
    private String userNameCn;

    /**
     *  用户英文名称 : 用户名称 en,所属表字段为sfxie_sys_user_relation.user_name_en
     */
    private String userNameEn;

    /**
     *  排序字段 : 排序字段,所属表字段为sfxie_sys_user_relation.sequence_no
     */
    private Integer sequenceNo;

    /**
     *  用户类型 : 用户类型(超级管理员，普通用户)
超级管理员: S
管理员: M
普通用户: U,所属表字段为sfxie_sys_user_relation.user_type
     */
    private String userType;

    /**
     *  是否默认 : 系统默认的用户信息(只有一个默认值1)
控制默认登录系统后获取的权限(根据公司):
默认:1
不默认:0,所属表字段为sfxie_sys_user_relation.is_default
     */
    private String isDefault;

    /**
     *  公司代码 : 关联公司代码,表明创建公司,所属表字段为sfxie_sys_user_relation.company_code
     */
    private String companyCode;

    /**
     *  岗位代码 : 岗位代码,所属表字段为sfxie_sys_user_relation.post_code
     */
    private String postCode;

    /**
     *  部门代码 : 部门代码,所属表字段为sfxie_sys_user_relation.department_code
     */
    private String departmentCode;

    /**
     *  分区字段 : 分区字段,从用户公司代码字段取值,所属表字段为sfxie_sys_user_relation.partition_company
     */
    private String partitionCompany;
    
    @JsonIgnoreProperties
    private String autCompanyCode;

    /**
     * 获取 记录主键 : 记录主键 字段:sfxie_sys_user_relation.id_
     *
     * @return sfxie_sys_user_relation.id_, 记录主键 : 记录主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 记录主键 : 记录主键 字段:sfxie_sys_user_relation.id_
     *
     * @param id sfxie_sys_user_relation.id_, 记录主键 : 记录主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取 用户代码 : 用户代码 字段:sfxie_sys_user_relation.user_id
     *
     * @return sfxie_sys_user_relation.user_id, 用户代码 : 用户代码
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置 用户代码 : 用户代码 字段:sfxie_sys_user_relation.user_id
     *
     * @param userId sfxie_sys_user_relation.user_id, 用户代码 : 用户代码
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取 用户职位 : 用户职位 字段:sfxie_sys_user_relation.user_title
     *
     * @return sfxie_sys_user_relation.user_title, 用户职位 : 用户职位
     */
    public String getUserTitle() {
        return userTitle;
    }

    /**
     * 设置 用户职位 : 用户职位 字段:sfxie_sys_user_relation.user_title
     *
     * @param userTitle sfxie_sys_user_relation.user_title, 用户职位 : 用户职位
     */
    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle == null ? null : userTitle.trim();
    }

    /**
     * 获取 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效 字段:sfxie_sys_user_relation.is_valid
     *
     * @return sfxie_sys_user_relation.is_valid, 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * 设置 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效 字段:sfxie_sys_user_relation.is_valid
     *
     * @param isValid sfxie_sys_user_relation.is_valid, 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    /**
     * 获取 创建时间 : 创建时间 字段:sfxie_sys_user_relation.create_time
     *
     * @return sfxie_sys_user_relation.create_time, 创建时间 : 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间 : 创建时间 字段:sfxie_sys_user_relation.create_time
     *
     * @param createTime sfxie_sys_user_relation.create_time, 创建时间 : 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 最后修改时间 : 最后修改时间 字段:sfxie_sys_user_relation.update_time
     *
     * @return sfxie_sys_user_relation.update_time, 最后修改时间 : 最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 最后修改时间 : 最后修改时间 字段:sfxie_sys_user_relation.update_time
     *
     * @param updateTime sfxie_sys_user_relation.update_time, 最后修改时间 : 最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取 最后修改人 : 最后修改人 字段:sfxie_sys_user_relation.update_user
     *
     * @return sfxie_sys_user_relation.update_user, 最后修改人 : 最后修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置 最后修改人 : 最后修改人 字段:sfxie_sys_user_relation.update_user
     *
     * @param updateUser sfxie_sys_user_relation.update_user, 最后修改人 : 最后修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 获取 记录创建人 : 记录创建人 字段:sfxie_sys_user_relation.create_user
     *
     * @return sfxie_sys_user_relation.create_user, 记录创建人 : 记录创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置 记录创建人 : 记录创建人 字段:sfxie_sys_user_relation.create_user
     *
     * @param createUser sfxie_sys_user_relation.create_user, 记录创建人 : 记录创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 获取 创建公司 : 创建公司 字段:sfxie_sys_user_relation.create_company_code
     *
     * @return sfxie_sys_user_relation.create_company_code, 创建公司 : 创建公司
     */
    public String getCreateCompanyCode() {
        return createCompanyCode;
    }

    /**
     * 设置 创建公司 : 创建公司 字段:sfxie_sys_user_relation.create_company_code
     *
     * @param createCompanyCode sfxie_sys_user_relation.create_company_code, 创建公司 : 创建公司
     */
    public void setCreateCompanyCode(String createCompanyCode) {
        this.createCompanyCode = createCompanyCode == null ? null : createCompanyCode.trim();
    }

    /**
     * 获取 email : email 字段:sfxie_sys_user_relation.email
     *
     * @return sfxie_sys_user_relation.email, email : email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置 email : email 字段:sfxie_sys_user_relation.email
     *
     * @param email sfxie_sys_user_relation.email, email : email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取 手机号 : 手机号 字段:sfxie_sys_user_relation.phone
     *
     * @return sfxie_sys_user_relation.phone, 手机号 : 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置 手机号 : 手机号 字段:sfxie_sys_user_relation.phone
     *
     * @param phone sfxie_sys_user_relation.phone, 手机号 : 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取 用户中文名 : 用户中文名 字段:sfxie_sys_user_relation.user_name_cn
     *
     * @return sfxie_sys_user_relation.user_name_cn, 用户中文名 : 用户中文名
     */
    public String getUserNameCn() {
        return userNameCn;
    }

    /**
     * 设置 用户中文名 : 用户中文名 字段:sfxie_sys_user_relation.user_name_cn
     *
     * @param userNameCn sfxie_sys_user_relation.user_name_cn, 用户中文名 : 用户中文名
     */
    public void setUserNameCn(String userNameCn) {
        this.userNameCn = userNameCn == null ? null : userNameCn.trim();
    }

    /**
     * 获取 用户英文名称 : 用户名称 en 字段:sfxie_sys_user_relation.user_name_en
     *
     * @return sfxie_sys_user_relation.user_name_en, 用户英文名称 : 用户名称 en
     */
    public String getUserNameEn() {
        return userNameEn;
    }

    /**
     * 设置 用户英文名称 : 用户名称 en 字段:sfxie_sys_user_relation.user_name_en
     *
     * @param userNameEn sfxie_sys_user_relation.user_name_en, 用户英文名称 : 用户名称 en
     */
    public void setUserNameEn(String userNameEn) {
        this.userNameEn = userNameEn == null ? null : userNameEn.trim();
    }

    /**
     * 获取 排序字段 : 排序字段 字段:sfxie_sys_user_relation.sequence_no
     *
     * @return sfxie_sys_user_relation.sequence_no, 排序字段 : 排序字段
     */
    public Integer getSequenceNo() {
        return sequenceNo;
    }

    /**
     * 设置 排序字段 : 排序字段 字段:sfxie_sys_user_relation.sequence_no
     *
     * @param sequenceNo sfxie_sys_user_relation.sequence_no, 排序字段 : 排序字段
     */
    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    /**
     * 获取 用户类型 : 用户类型(超级管理员，普通用户)
超级管理员: S
管理员: M
普通用户: U 字段:sfxie_sys_user_relation.user_type
     *
     * @return sfxie_sys_user_relation.user_type, 用户类型 : 用户类型(超级管理员，普通用户)
超级管理员: S
管理员: M
普通用户: U
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 设置 用户类型 : 用户类型(超级管理员，普通用户)
超级管理员: S
管理员: M
普通用户: U 字段:sfxie_sys_user_relation.user_type
     *
     * @param userType sfxie_sys_user_relation.user_type, 用户类型 : 用户类型(超级管理员，普通用户)
超级管理员: S
管理员: M
普通用户: U
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     * 获取 是否默认 : 系统默认的用户信息(只有一个默认值1)
控制默认登录系统后获取的权限(根据公司):
默认:1
不默认:0 字段:sfxie_sys_user_relation.is_default
     *
     * @return sfxie_sys_user_relation.is_default, 是否默认 : 系统默认的用户信息(只有一个默认值1)
控制默认登录系统后获取的权限(根据公司):
默认:1
不默认:0
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置 是否默认 : 系统默认的用户信息(只有一个默认值1)
控制默认登录系统后获取的权限(根据公司):
默认:1
不默认:0 字段:sfxie_sys_user_relation.is_default
     *
     * @param isDefault sfxie_sys_user_relation.is_default, 是否默认 : 系统默认的用户信息(只有一个默认值1)
控制默认登录系统后获取的权限(根据公司):
默认:1
不默认:0
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }

    /**
     * 获取 公司代码 : 关联公司代码,表明创建公司 字段:sfxie_sys_user_relation.company_code
     *
     * @return sfxie_sys_user_relation.company_code, 公司代码 : 关联公司代码,表明创建公司
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * 设置 公司代码 : 关联公司代码,表明创建公司 字段:sfxie_sys_user_relation.company_code
     *
     * @param companyCode sfxie_sys_user_relation.company_code, 公司代码 : 关联公司代码,表明创建公司
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    /**
     * 获取 岗位代码 : 岗位代码 字段:sfxie_sys_user_relation.post_code
     *
     * @return sfxie_sys_user_relation.post_code, 岗位代码 : 岗位代码
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 设置 岗位代码 : 岗位代码 字段:sfxie_sys_user_relation.post_code
     *
     * @param postCode sfxie_sys_user_relation.post_code, 岗位代码 : 岗位代码
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    /**
     * 获取 部门代码 : 部门代码 字段:sfxie_sys_user_relation.department_code
     *
     * @return sfxie_sys_user_relation.department_code, 部门代码 : 部门代码
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * 设置 部门代码 : 部门代码 字段:sfxie_sys_user_relation.department_code
     *
     * @param departmentCode sfxie_sys_user_relation.department_code, 部门代码 : 部门代码
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode == null ? null : departmentCode.trim();
    }

    /**
     * 获取 分区字段 : 分区字段,从用户公司代码字段取值 字段:sfxie_sys_user_relation.partition_company
     *
     * @return sfxie_sys_user_relation.partition_company, 分区字段 : 分区字段,从用户公司代码字段取值
     */
    public String getPartitionCompany() {
        return partitionCompany;
    }

    /**
     * 设置 分区字段 : 分区字段,从用户公司代码字段取值 字段:sfxie_sys_user_relation.partition_company
     *
     * @param partitionCompany sfxie_sys_user_relation.partition_company, 分区字段 : 分区字段,从用户公司代码字段取值
     */
    public void setPartitionCompany(String partitionCompany) {
        this.partitionCompany = partitionCompany == null ? null : partitionCompany.trim();
    }

	public String getAutCompanyCode() {
		return autCompanyCode;
	}

	public void setAutCompanyCode(String autCompanyCode) {
		this.autCompanyCode = autCompanyCode;
	}
}