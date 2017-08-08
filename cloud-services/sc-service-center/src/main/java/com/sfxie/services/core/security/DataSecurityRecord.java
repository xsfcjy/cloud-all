package com.sfxie.services.core.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 数据行权限控制实体类 ,需要进行数据权限控制的实体类继承此类
 * 。<br> 通过mybatis的拦截器{@linkplain com.sfxie.services.core.security.DataSecurityRecordInterceptor}实现
 * 。<br>如果需要对mybatis查询出来的列表数据进行数据权限控制，需要在对应的mapper的sql语句中引入dataSecurityStatement.data_security
 * 。
 * <p>
 * <br>示例如下：
   <br>&lt;select id="selectByCondition" parameterType="com.sfxie.services.center.pojo.SfxieSysCompany" resultMap="BaseResultMap"&gt;
    <br>select *
    from sfxie_sys_company
    where company_name_cn like '%${companyNameCn}%'
    <br>&lt;include refid="dataSecurityStatement.data_security" /&gt;
  	<br>&lt;/select&gt;
 * </p>
 * dataSecurityStatement.data_security路径是com/sfxie/services/center/dao/sql/DataSecuritySqlStatement.xml
 * 
 * @author xiesf
 * @since 2017年7月25日 下午2:15:03
 */
public class DataSecurityRecord {

	/**
	 * 控制数据行权限sql，不返回到前端
	 */
	@JsonIgnore
	private String securitySql;
    /**
     *  记录创建人
     */
    private String createUser;
    /**
     *  创建公司 : 创建公司
     */
    private String createCompanyCode;

	public String getSecuritySql() {
		return securitySql;
	}

	public void setSecuritySql(String securitySql) {
		this.securitySql = securitySql;
	}
	/**
     * 获取 记录创建人
     *
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置 记录创建人 
     *
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }
    /**
     * 获取 创建公司 
     *
     */
    public String getCreateCompanyCode() {
        return createCompanyCode;
    }

    /**
     * 设置 创建公司
     *
     */
    public void setCreateCompanyCode(String createCompanyCode) {
        this.createCompanyCode = createCompanyCode == null ? null : createCompanyCode.trim();
    }
	
}
