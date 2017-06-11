package com.sfxie.services.center.pojo;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sfxie_sys_role_menu
 *
 * @mbg.generated do_not_delete_during_merge Thu Jun 08 08:42:00 CST 2017
 */
public class SfxieSysRoleMenu {
    /**
     *  记录主键 : 记录主键,所属表字段为sfxie_sys_role_menu.id_
     */
    private String id;

    /**
     *  上一级公司 : 上上一级公司，也就是这一级的上一级公司,所属表字段为sfxie_sys_role_menu.owner_company_code
     */
    private String ownerCompanyCode;

    /**
     *  是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效,所属表字段为sfxie_sys_role_menu.is_valid
     */
    private String isValid;

    /**
     *  公司角色关联表id : 公司角色关联表id,所属表字段为sfxie_sys_role_menu.company_role_id
     */
    private String companyRoleId;

    /**
     *  创建公司 : 创建公司,所属表字段为sfxie_sys_role_menu.create_company_id
     */
    private String createCompanyId;

    /**
     *  继承的角色菜单id : 继承的角色菜单id，记录此条数据是从哪条角色菜单中继承而来的,所属表字段为sfxie_sys_role_menu.oraginal_role_menu_id
     */
    private String oraginalRoleMenuId;

    /**
     *  角色代码 : 角色代码,所属表字段为sfxie_sys_role_menu.role_code
     */
    private String roleCode;

    /**
     *  菜单代码 : 菜单代码,所属表字段为sfxie_sys_role_menu.menu_code
     */
    private String menuCode;

    /**
     *  分区字段 : 分区字段,从用户公司代码字段取值,所属表字段为sfxie_sys_role_menu.partition_company
     */
    private String partitionCompany;

    /**
     * 获取 记录主键 : 记录主键 字段:sfxie_sys_role_menu.id_
     *
     * @return sfxie_sys_role_menu.id_, 记录主键 : 记录主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 记录主键 : 记录主键 字段:sfxie_sys_role_menu.id_
     *
     * @param id sfxie_sys_role_menu.id_, 记录主键 : 记录主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取 上一级公司 : 上上一级公司，也就是这一级的上一级公司 字段:sfxie_sys_role_menu.owner_company_code
     *
     * @return sfxie_sys_role_menu.owner_company_code, 上一级公司 : 上上一级公司，也就是这一级的上一级公司
     */
    public String getOwnerCompanyCode() {
        return ownerCompanyCode;
    }

    /**
     * 设置 上一级公司 : 上上一级公司，也就是这一级的上一级公司 字段:sfxie_sys_role_menu.owner_company_code
     *
     * @param ownerCompanyCode sfxie_sys_role_menu.owner_company_code, 上一级公司 : 上上一级公司，也就是这一级的上一级公司
     */
    public void setOwnerCompanyCode(String ownerCompanyCode) {
        this.ownerCompanyCode = ownerCompanyCode == null ? null : ownerCompanyCode.trim();
    }

    /**
     * 获取 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效 字段:sfxie_sys_role_menu.is_valid
     *
     * @return sfxie_sys_role_menu.is_valid, 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * 设置 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效 字段:sfxie_sys_role_menu.is_valid
     *
     * @param isValid sfxie_sys_role_menu.is_valid, 是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    /**
     * 获取 公司角色关联表id : 公司角色关联表id 字段:sfxie_sys_role_menu.company_role_id
     *
     * @return sfxie_sys_role_menu.company_role_id, 公司角色关联表id : 公司角色关联表id
     */
    public String getCompanyRoleId() {
        return companyRoleId;
    }

    /**
     * 设置 公司角色关联表id : 公司角色关联表id 字段:sfxie_sys_role_menu.company_role_id
     *
     * @param companyRoleId sfxie_sys_role_menu.company_role_id, 公司角色关联表id : 公司角色关联表id
     */
    public void setCompanyRoleId(String companyRoleId) {
        this.companyRoleId = companyRoleId == null ? null : companyRoleId.trim();
    }

    /**
     * 获取 创建公司 : 创建公司 字段:sfxie_sys_role_menu.create_company_id
     *
     * @return sfxie_sys_role_menu.create_company_id, 创建公司 : 创建公司
     */
    public String getCreateCompanyId() {
        return createCompanyId;
    }

    /**
     * 设置 创建公司 : 创建公司 字段:sfxie_sys_role_menu.create_company_id
     *
     * @param createCompanyId sfxie_sys_role_menu.create_company_id, 创建公司 : 创建公司
     */
    public void setCreateCompanyId(String createCompanyId) {
        this.createCompanyId = createCompanyId == null ? null : createCompanyId.trim();
    }

    /**
     * 获取 继承的角色菜单id : 继承的角色菜单id，记录此条数据是从哪条角色菜单中继承而来的 字段:sfxie_sys_role_menu.oraginal_role_menu_id
     *
     * @return sfxie_sys_role_menu.oraginal_role_menu_id, 继承的角色菜单id : 继承的角色菜单id，记录此条数据是从哪条角色菜单中继承而来的
     */
    public String getOraginalRoleMenuId() {
        return oraginalRoleMenuId;
    }

    /**
     * 设置 继承的角色菜单id : 继承的角色菜单id，记录此条数据是从哪条角色菜单中继承而来的 字段:sfxie_sys_role_menu.oraginal_role_menu_id
     *
     * @param oraginalRoleMenuId sfxie_sys_role_menu.oraginal_role_menu_id, 继承的角色菜单id : 继承的角色菜单id，记录此条数据是从哪条角色菜单中继承而来的
     */
    public void setOraginalRoleMenuId(String oraginalRoleMenuId) {
        this.oraginalRoleMenuId = oraginalRoleMenuId == null ? null : oraginalRoleMenuId.trim();
    }

    /**
     * 获取 角色代码 : 角色代码 字段:sfxie_sys_role_menu.role_code
     *
     * @return sfxie_sys_role_menu.role_code, 角色代码 : 角色代码
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置 角色代码 : 角色代码 字段:sfxie_sys_role_menu.role_code
     *
     * @param roleCode sfxie_sys_role_menu.role_code, 角色代码 : 角色代码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    /**
     * 获取 菜单代码 : 菜单代码 字段:sfxie_sys_role_menu.menu_code
     *
     * @return sfxie_sys_role_menu.menu_code, 菜单代码 : 菜单代码
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 设置 菜单代码 : 菜单代码 字段:sfxie_sys_role_menu.menu_code
     *
     * @param menuCode sfxie_sys_role_menu.menu_code, 菜单代码 : 菜单代码
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    /**
     * 获取 分区字段 : 分区字段,从用户公司代码字段取值 字段:sfxie_sys_role_menu.partition_company
     *
     * @return sfxie_sys_role_menu.partition_company, 分区字段 : 分区字段,从用户公司代码字段取值
     */
    public String getPartitionCompany() {
        return partitionCompany;
    }

    /**
     * 设置 分区字段 : 分区字段,从用户公司代码字段取值 字段:sfxie_sys_role_menu.partition_company
     *
     * @param partitionCompany sfxie_sys_role_menu.partition_company, 分区字段 : 分区字段,从用户公司代码字段取值
     */
    public void setPartitionCompany(String partitionCompany) {
        this.partitionCompany = partitionCompany == null ? null : partitionCompany.trim();
    }
}