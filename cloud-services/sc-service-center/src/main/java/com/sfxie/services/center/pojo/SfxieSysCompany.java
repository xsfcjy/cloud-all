package com.sfxie.services.center.pojo;

import java.util.Date;

import com.sfxie.services.center.util.ServicesContext;
import com.sfxie.services.core.security.DataSecurityField;
import com.sfxie.services.core.security.DataSecurityRecord;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sfxie_sys_company
 *
 * @mbg.generated do_not_delete_during_merge Thu Jun 08 08:42:00 CST 2017
 */
public class SfxieSysCompany extends DataSecurityRecord{
    /**
     *  记录主键 : 记录主键,所属表字段为sfxie_sys_company.id_
     */
    private String id;

    /**
     *  公司代码 : 关联公司代码,表明创建公司,所属表字段为sfxie_sys_company.company_code
     */
    private String companyCode;

    /**
     *  公司中文名称 : 公司中文名称,所属表字段为sfxie_sys_company.company_name_cn
     */
    private String companyNameCn;

    /**
     *  公司英文名称,所属表字段为sfxie_sys_company.company_name_en
     */
    private String companyNameEn;

    /**
     *  公司地址 : 公司地址,所属表字段为sfxie_sys_company.address
     */
    private String address;

    /**
     *  公司电话,所属表字段为sfxie_sys_company.tel
     */
    private String tel;

    /**
     *  公司传真,所属表字段为sfxie_sys_company.fax
     */
    private String fax;

    /**
     *  主页URL : 主页URL,所属表字段为sfxie_sys_company.url
     */
    private String url;

    /**
     *  email : email,所属表字段为sfxie_sys_company.email
     */
    private String email;

    /**
     *  备注 : 备注,所属表字段为sfxie_sys_company.remark
     */
    private String remark;

    /**
     *  创建时间 : 创建时间,所属表字段为sfxie_sys_company.create_time
     */
    private Date createTime;

    /**
     *  最后修改人 : 最后修改人,所属表字段为sfxie_sys_company.update_user
     */
    private String updateUser;

    /**
     *  最后修改时间 : 最后修改时间,所属表字段为sfxie_sys_company.update_time
     */
    private Date updateTime;

    /**
     *  排序字段 : 排序字段,所属表字段为sfxie_sys_company.sequence_no
     */
    private Integer sequenceNo;

    /**
     *  是否有效 : 是否有效,Y:是;N:否;控制是否在公司任职有效,所属表字段为sfxie_sys_company.is_valid
     */
    private String isValid;

    /**
     *  公司中文名称简称 : 公司中文名称简称,所属表字段为sfxie_sys_company.short_name_cn
     */
    private String shortNameCn;

    /**
     *  公司英文名称简称 : 公司英文名称简称,所属表字段为sfxie_sys_company.short_name_en
     */
    private String shortNameEn;

    /**
     *  公司级别 : 用来控制公司下面管理员的级别。
超级管理员: 值为"上级超级管理的级别+1";
普通用户: 默认值为 -1

作用:用于创建无限级超级管理员,所属表字段为sfxie_sys_company.company_level
     */
    private Short companyLevel;

    /**
     * 获取 记录主键 : 记录主键 字段:sfxie_sys_company.id_
     *
     * @return sfxie_sys_company.id_, 记录主键 : 记录主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 记录主键 : 记录主键 字段:sfxie_sys_company.id_
     *
     * @param id sfxie_sys_company.id_, 记录主键 : 记录主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取 公司代码 : 关联公司代码,表明创建公司 字段:sfxie_sys_company.company_code
     *
     * @return sfxie_sys_company.company_code, 公司代码 : 关联公司代码,表明创建公司
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * 设置 公司代码 : 关联公司代码,表明创建公司 字段:sfxie_sys_company.company_code
     *
     * @param companyCode sfxie_sys_company.company_code, 公司代码 : 关联公司代码,表明创建公司
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    /**
     * 获取 公司中文名称 : 公司中文名称 字段:sfxie_sys_company.company_name_cn
     *
     * @return sfxie_sys_company.company_name_cn, 公司中文名称 : 公司中文名称
     */
    @DataSecurityField
    public String getCompanyNameCn() {
        return companyNameCn;
    }

    /**
     * 设置 公司中文名称 : 公司中文名称 字段:sfxie_sys_company.company_name_cn
     *
     * @param companyNameCn sfxie_sys_company.company_name_cn, 公司中文名称 : 公司中文名称
     */
    public void setCompanyNameCn(String companyNameCn) {
        this.companyNameCn = companyNameCn == null ? null : companyNameCn.trim();
    }

    /**
     * 获取 公司英文名称 字段:sfxie_sys_company.company_name_en
     *
     * @return sfxie_sys_company.company_name_en, 公司英文名称
     */
    public String getCompanyNameEn() {
        return companyNameEn;
    }

    /**
     * 设置 公司英文名称 字段:sfxie_sys_company.company_name_en
     *
     * @param companyNameEn sfxie_sys_company.company_name_en, 公司英文名称
     */
    public void setCompanyNameEn(String companyNameEn) {
        this.companyNameEn = companyNameEn == null ? null : companyNameEn.trim();
    }

    /**
     * 获取 公司地址 : 公司地址 字段:sfxie_sys_company.address
     *
     * @return sfxie_sys_company.address, 公司地址 : 公司地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置 公司地址 : 公司地址 字段:sfxie_sys_company.address
     *
     * @param address sfxie_sys_company.address, 公司地址 : 公司地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取 公司电话 字段:sfxie_sys_company.tel
     *
     * @return sfxie_sys_company.tel, 公司电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置 公司电话 字段:sfxie_sys_company.tel
     *
     * @param tel sfxie_sys_company.tel, 公司电话
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * 获取 公司传真 字段:sfxie_sys_company.fax
     *
     * @return sfxie_sys_company.fax, 公司传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置 公司传真 字段:sfxie_sys_company.fax
     *
     * @param fax sfxie_sys_company.fax, 公司传真
     */
    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    /**
     * 获取 主页URL : 主页URL 字段:sfxie_sys_company.url
     *
     * @return sfxie_sys_company.url, 主页URL : 主页URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置 主页URL : 主页URL 字段:sfxie_sys_company.url
     *
     * @param url sfxie_sys_company.url, 主页URL : 主页URL
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取 email : email 字段:sfxie_sys_company.email
     *
     * @return sfxie_sys_company.email, email : email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置 email : email 字段:sfxie_sys_company.email
     *
     * @param email sfxie_sys_company.email, email : email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取 备注 : 备注 字段:sfxie_sys_company.remark
     *
     * @return sfxie_sys_company.remark, 备注 : 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注 : 备注 字段:sfxie_sys_company.remark
     *
     * @param remark sfxie_sys_company.remark, 备注 : 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取 创建时间 : 创建时间 字段:sfxie_sys_company.create_time
     *
     * @return sfxie_sys_company.create_time, 创建时间 : 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间 : 创建时间 字段:sfxie_sys_company.create_time
     *
     * @param createTime sfxie_sys_company.create_time, 创建时间 : 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 最后修改人 : 最后修改人 字段:sfxie_sys_company.update_user
     *
     * @return sfxie_sys_company.update_user, 最后修改人 : 最后修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置 最后修改人 : 最后修改人 字段:sfxie_sys_company.update_user
     *
     * @param updateUser sfxie_sys_company.update_user, 最后修改人 : 最后修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 获取 最后修改时间 : 最后修改时间 字段:sfxie_sys_company.update_time
     *
     * @return sfxie_sys_company.update_time, 最后修改时间 : 最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 最后修改时间 : 最后修改时间 字段:sfxie_sys_company.update_time
     *
     * @param updateTime sfxie_sys_company.update_time, 最后修改时间 : 最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取 排序字段 : 排序字段 字段:sfxie_sys_company.sequence_no
     *
     * @return sfxie_sys_company.sequence_no, 排序字段 : 排序字段
     */
    public Integer getSequenceNo() {
        return sequenceNo;
    }

    /**
     * 设置 排序字段 : 排序字段 字段:sfxie_sys_company.sequence_no
     *
     * @param sequenceNo sfxie_sys_company.sequence_no, 排序字段 : 排序字段
     */
    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    /**
     * 获取 是否有效 : 是否有效,Y:是;N:否;控制是否在公司任职有效 字段:sfxie_sys_company.is_valid
     *
     * @return sfxie_sys_company.is_valid, 是否有效 : 是否有效,Y:是;N:否;控制是否在公司任职有效
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * 设置 是否有效 : 是否有效,Y:是;N:否;控制是否在公司任职有效 字段:sfxie_sys_company.is_valid
     *
     * @param isValid sfxie_sys_company.is_valid, 是否有效 : 是否有效,Y:是;N:否;控制是否在公司任职有效
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    /**
     * 获取 公司中文名称简称 : 公司中文名称简称 字段:sfxie_sys_company.short_name_cn
     *
     * @return sfxie_sys_company.short_name_cn, 公司中文名称简称 : 公司中文名称简称
     */
    public String getShortNameCn() {
        return shortNameCn;
    }

    /**
     * 设置 公司中文名称简称 : 公司中文名称简称 字段:sfxie_sys_company.short_name_cn
     *
     * @param shortNameCn sfxie_sys_company.short_name_cn, 公司中文名称简称 : 公司中文名称简称
     */
    public void setShortNameCn(String shortNameCn) {
        this.shortNameCn = shortNameCn == null ? null : shortNameCn.trim();
    }

    /**
     * 获取 公司英文名称简称 : 公司英文名称简称 字段:sfxie_sys_company.short_name_en
     *
     * @return sfxie_sys_company.short_name_en, 公司英文名称简称 : 公司英文名称简称
     */
    public String getShortNameEn() {
        return shortNameEn;
    }

    /**
     * 设置 公司英文名称简称 : 公司英文名称简称 字段:sfxie_sys_company.short_name_en
     *
     * @param shortNameEn sfxie_sys_company.short_name_en, 公司英文名称简称 : 公司英文名称简称
     */
    public void setShortNameEn(String shortNameEn) {
        this.shortNameEn = shortNameEn == null ? null : shortNameEn.trim();
    }

    /**
     * 获取 公司级别 : 用来控制公司下面管理员的级别。
超级管理员: 值为"上级超级管理的级别+1";
普通用户: 默认值为 -1

作用:用于创建无限级超级管理员 字段:sfxie_sys_company.company_level
     *
     * @return sfxie_sys_company.company_level, 公司级别 : 用来控制公司下面管理员的级别。
超级管理员: 值为"上级超级管理的级别+1";
普通用户: 默认值为 -1

作用:用于创建无限级超级管理员
     */
    public Short getCompanyLevel() {
        return companyLevel;
    }

    /**
     * 设置 公司级别 : 用来控制公司下面管理员的级别。
超级管理员: 值为"上级超级管理的级别+1";
普通用户: 默认值为 -1

作用:用于创建无限级超级管理员 字段:sfxie_sys_company.company_level
     *
     * @param companyLevel sfxie_sys_company.company_level, 公司级别 : 用来控制公司下面管理员的级别。
超级管理员: 值为"上级超级管理的级别+1";
普通用户: 默认值为 -1

作用:用于创建无限级超级管理员
     */
    public void setCompanyLevel(Short companyLevel) {
        this.companyLevel = companyLevel;
    }
    
    public String getPartitionCompany(){
		return ServicesContext.getPartitionCompany(getCompanyCode());
    }
    
}