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
	
}
