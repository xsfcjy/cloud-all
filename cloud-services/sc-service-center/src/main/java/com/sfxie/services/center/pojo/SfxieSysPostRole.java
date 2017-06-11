package com.sfxie.services.center.pojo;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sfxie_sys_post_role
 *
 * @mbg.generated do_not_delete_during_merge Thu Jun 08 08:42:00 CST 2017
 */
public class SfxieSysPostRole {
    /**
     *  记录主键 : 记录主键,所属表字段为sfxie_sys_post_role.id_
     */
    private String id;

    /**
     *  上一级公司 : 上上一级公司，也就是这一级的上一级公司,所属表字段为sfxie_sys_post_role.owner_company_code
     */
    private String ownerCompanyCode;

    /**
     *  是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效,所属表字段为sfxie_sys_post_role.is_valid
     */
    private String isValid;

    /**
     *  岗位代码 : 岗位代码,所属表字段为sfxie_sys_post_role.post_code
     */
    private String postCode;

    /**
     *  角色代码 : 角色代码,所属表字段为sfxie_sys_post_role.role_code
     */
    private String roleCode;

    /**
     *  分区字段 : 分区字段,从用户公司代码字段取值,所属表字段为sfxie_sys_post_role.partition_company
     */
    private String partitionCompany;

    /**
     * 获取 记录主键 : 记录主键 字段:sfxie_sys_post_role.id_
     *
     * @return sfxie_sys_post_role.id_, 记录主键 : 记录主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 记录主键 : 记录主键 字段:sfxie_sys_post_role.id_
     *
     * @param id sfxie_sys_post_role.id_, 记录主键 : 记录主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取 上一级公司 : 上上一级公司，也就是这一级的上一级公司 字段:sfxie_sys_post_role.owner_company_code
     *
     * @return sfxie_sys_post_role.owner_company_code, 上一级公司 : 上上一级公司，也就是这一级的上一级公司
     */
    public String getOwnerCompanyCode() {
        return ownerCompanyCode;
    }

    /**
     * 设置 上一级公司 : 上上一级公司，也就是这一级的上一级公司 字段:sfxie_sys_post_role.owner_company_code
     *
     * @param ownerCompanyCode sfxie_sys_post_role.owner_company_code, 上一级公司 : 上上一级公司，也就是这一级的上一级公司
     */
    public void setOwnerCompanyCode(String ownerCompanyCode) {
        this.ownerCompanyCode = ownerCompanyCode == null ? null : ownerCompanyCode.trim();
    }

    /**
     * 获取 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效 字段:sfxie_sys_post_role.is_valid
     *
     * @return sfxie_sys_post_role.is_valid, 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * 设置 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效 字段:sfxie_sys_post_role.is_valid
     *
     * @param isValid sfxie_sys_post_role.is_valid, 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    /**
     * 获取 岗位代码 : 岗位代码 字段:sfxie_sys_post_role.post_code
     *
     * @return sfxie_sys_post_role.post_code, 岗位代码 : 岗位代码
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 设置 岗位代码 : 岗位代码 字段:sfxie_sys_post_role.post_code
     *
     * @param postCode sfxie_sys_post_role.post_code, 岗位代码 : 岗位代码
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    /**
     * 获取 角色代码 : 角色代码 字段:sfxie_sys_post_role.role_code
     *
     * @return sfxie_sys_post_role.role_code, 角色代码 : 角色代码
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置 角色代码 : 角色代码 字段:sfxie_sys_post_role.role_code
     *
     * @param roleCode sfxie_sys_post_role.role_code, 角色代码 : 角色代码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    /**
     * 获取 分区字段 : 分区字段,从用户公司代码字段取值 字段:sfxie_sys_post_role.partition_company
     *
     * @return sfxie_sys_post_role.partition_company, 分区字段 : 分区字段,从用户公司代码字段取值
     */
    public String getPartitionCompany() {
        return partitionCompany;
    }

    /**
     * 设置 分区字段 : 分区字段,从用户公司代码字段取值 字段:sfxie_sys_post_role.partition_company
     *
     * @param partitionCompany sfxie_sys_post_role.partition_company, 分区字段 : 分区字段,从用户公司代码字段取值
     */
    public void setPartitionCompany(String partitionCompany) {
        this.partitionCompany = partitionCompany == null ? null : partitionCompany.trim();
    }
}