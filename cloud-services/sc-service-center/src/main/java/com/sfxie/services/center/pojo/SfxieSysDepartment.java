package com.sfxie.services.center.pojo;

import java.util.Date;

import com.sfxie.services.center.util.ServicesContext;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sfxie_sys_department
 *
 * @mbg.generated do_not_delete_during_merge Thu Jun 08 08:42:00 CST 2017
 */
public class SfxieSysDepartment {
    /**
     *  记录主键 : 记录主键,所属表字段为sfxie_sys_department.id_
     */
    private String id;

    /**
     *  部门代码 : 部门代码,所属表字段为sfxie_sys_department.department_code
     */
    private String departmentCode;

    /**
     *  部门名称 : 部门名称,所属表字段为sfxie_sys_department.department_name
     */
    private String departmentName;

    /**
     *  记录创建人 : 记录创建人,所属表字段为sfxie_sys_department.create_user
     */
    private String createUser;

    /**
     *  创建时间 : 创建时间,所属表字段为sfxie_sys_department.create_time
     */
    private Date createTime;

    /**
     *  最后修改人 : 最后修改人,所属表字段为sfxie_sys_department.update_user
     */
    private String updateUser;

    /**
     *  最后修改时间 : 最后修改时间,所属表字段为sfxie_sys_department.update_time
     */
    private Date updateTime;

    /**
     *  是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效,所属表字段为sfxie_sys_department.is_valid
     */
    private String isValid;

    /**
     *  排序字段 : 排序字段,所属表字段为sfxie_sys_department.sequence_no
     */
    private Integer sequenceNo;

    /**
     *  父主键 : 关联父主键,所属表字段为sfxie_sys_department.parent_id
     */
    private String parentId;

    /**
     *  创建公司 : 创建公司,所属表字段为sfxie_sys_department.create_company_code
     */
    private String createCompanyCode;

    /**
     *  公司代码 : 关联公司代码,表明创建公司,所属表字段为sfxie_sys_department.company_code
     */
    private String companyCode;

    /**
     *   父节点代码,所属表字段为sfxie_sys_department.parent_code
     */
    private String parentCode;

    /**
     *  分区字段 : 分区字段,从用户公司代码字段取值,所属表字段为sfxie_sys_department.partition_company
     */
    private String partitionCompany;

    /**
     * 获取 记录主键 : 记录主键 字段:sfxie_sys_department.id_
     *
     * @return sfxie_sys_department.id_, 记录主键 : 记录主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 记录主键 : 记录主键 字段:sfxie_sys_department.id_
     *
     * @param id sfxie_sys_department.id_, 记录主键 : 记录主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取 部门代码 : 部门代码 字段:sfxie_sys_department.department_code
     *
     * @return sfxie_sys_department.department_code, 部门代码 : 部门代码
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * 设置 部门代码 : 部门代码 字段:sfxie_sys_department.department_code
     *
     * @param departmentCode sfxie_sys_department.department_code, 部门代码 : 部门代码
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode == null ? null : departmentCode.trim();
    }

    /**
     * 获取 部门名称 : 部门名称 字段:sfxie_sys_department.department_name
     *
     * @return sfxie_sys_department.department_name, 部门名称 : 部门名称
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 设置 部门名称 : 部门名称 字段:sfxie_sys_department.department_name
     *
     * @param departmentName sfxie_sys_department.department_name, 部门名称 : 部门名称
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    /**
     * 获取 记录创建人 : 记录创建人 字段:sfxie_sys_department.create_user
     *
     * @return sfxie_sys_department.create_user, 记录创建人 : 记录创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置 记录创建人 : 记录创建人 字段:sfxie_sys_department.create_user
     *
     * @param createUser sfxie_sys_department.create_user, 记录创建人 : 记录创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 获取 创建时间 : 创建时间 字段:sfxie_sys_department.create_time
     *
     * @return sfxie_sys_department.create_time, 创建时间 : 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间 : 创建时间 字段:sfxie_sys_department.create_time
     *
     * @param createTime sfxie_sys_department.create_time, 创建时间 : 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 最后修改人 : 最后修改人 字段:sfxie_sys_department.update_user
     *
     * @return sfxie_sys_department.update_user, 最后修改人 : 最后修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置 最后修改人 : 最后修改人 字段:sfxie_sys_department.update_user
     *
     * @param updateUser sfxie_sys_department.update_user, 最后修改人 : 最后修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 获取 最后修改时间 : 最后修改时间 字段:sfxie_sys_department.update_time
     *
     * @return sfxie_sys_department.update_time, 最后修改时间 : 最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 最后修改时间 : 最后修改时间 字段:sfxie_sys_department.update_time
     *
     * @param updateTime sfxie_sys_department.update_time, 最后修改时间 : 最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效 字段:sfxie_sys_department.is_valid
     *
     * @return sfxie_sys_department.is_valid, 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * 设置 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效 字段:sfxie_sys_department.is_valid
     *
     * @param isValid sfxie_sys_department.is_valid, 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    /**
     * 获取 排序字段 : 排序字段 字段:sfxie_sys_department.sequence_no
     *
     * @return sfxie_sys_department.sequence_no, 排序字段 : 排序字段
     */
    public Integer getSequenceNo() {
        return sequenceNo;
    }

    /**
     * 设置 排序字段 : 排序字段 字段:sfxie_sys_department.sequence_no
     *
     * @param sequenceNo sfxie_sys_department.sequence_no, 排序字段 : 排序字段
     */
    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    /**
     * 获取 父主键 : 关联父主键 字段:sfxie_sys_department.parent_id
     *
     * @return sfxie_sys_department.parent_id, 父主键 : 关联父主键
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置 父主键 : 关联父主键 字段:sfxie_sys_department.parent_id
     *
     * @param parentId sfxie_sys_department.parent_id, 父主键 : 关联父主键
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 获取 创建公司 : 创建公司 字段:sfxie_sys_department.create_company_code
     *
     * @return sfxie_sys_department.create_company_code, 创建公司 : 创建公司
     */
    public String getCreateCompanyCode() {
        return createCompanyCode;
    }

    /**
     * 设置 创建公司 : 创建公司 字段:sfxie_sys_department.create_company_code
     *
     * @param createCompanyCode sfxie_sys_department.create_company_code, 创建公司 : 创建公司
     */
    public void setCreateCompanyCode(String createCompanyCode) {
        this.createCompanyCode = createCompanyCode == null ? null : createCompanyCode.trim();
    }

    /**
     * 获取 公司代码 : 关联公司代码,表明创建公司 字段:sfxie_sys_department.company_code
     *
     * @return sfxie_sys_department.company_code, 公司代码 : 关联公司代码,表明创建公司
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * 设置 公司代码 : 关联公司代码,表明创建公司 字段:sfxie_sys_department.company_code
     *
     * @param companyCode sfxie_sys_department.company_code, 公司代码 : 关联公司代码,表明创建公司
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    /**
     * 获取  父节点代码 字段:sfxie_sys_department.parent_code
     *
     * @return sfxie_sys_department.parent_code,  父节点代码
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * 设置  父节点代码 字段:sfxie_sys_department.parent_code
     *
     * @param parentCode sfxie_sys_department.parent_code,  父节点代码
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    /**
     * 获取 分区字段 : 分区字段,从用户公司代码字段取值 字段:sfxie_sys_department.partition_company
     *
     * @return sfxie_sys_department.partition_company, 分区字段 : 分区字段,从用户公司代码字段取值
     */
    public String getPartitionCompany() {
        return partitionCompany == null ? ServicesContext.getPartitionCompany(companyCode)
				: partitionCompany.trim();
    }

    /**
     * 设置 分区字段 : 分区字段,从用户公司代码字段取值 字段:sfxie_sys_department.partition_company
     *
     * @param partitionCompany sfxie_sys_department.partition_company, 分区字段 : 分区字段,从用户公司代码字段取值
     */
    public void setPartitionCompany(String partitionCompany) {
        this.partitionCompany = partitionCompany == null ? null : partitionCompany.trim();
    }
}