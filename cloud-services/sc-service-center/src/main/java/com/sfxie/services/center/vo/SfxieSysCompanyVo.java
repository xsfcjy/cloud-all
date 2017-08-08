package com.sfxie.services.center.vo;

import com.sfxie.services.center.pojo.SfxieSysCompany;

public class SfxieSysCompanyVo extends SfxieSysCompany {


    /**
     *  父公司代码 
     */
    private String parentCompanyCode;
    /**
     *  父公司级别
     */
    private Integer parentCompanyLevel;
    
    private String code;
    private String level;
	private String partitionCompany;
    


	public String getParentCompanyCode() {
		return parentCompanyCode;
	}

	public void setParentCompanyCode(String parentCompanyCode) {
		this.parentCompanyCode = parentCompanyCode;
	}

	public Integer getParentCompanyLevel() {
		return parentCompanyLevel;
	}

	public void setParentCompanyLevel(Integer parentCompanyLevel) {
		this.parentCompanyLevel = parentCompanyLevel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPartitionCompany() {
		return partitionCompany;
	}

	public void setPartitionCompany(String partitionCompany) {
		this.partitionCompany = partitionCompany;
	}
	
	
}
