package com.sfxie.services.center.vo;

import java.util.List;

public class SfxieSysOrganizationVo {
	
	private String userNameCn;
	
	private String roleName;

    public List<SfxieSysCompanyVo> data;

	public List<SfxieSysCompanyVo> getData() {
		return data;
	}

	public void setData(List<SfxieSysCompanyVo> data) {
		this.data = data;
	}

	public String getUserNameCn() {
		return userNameCn;
	}

	public void setUserNameCn(String userNameCn) {
		this.userNameCn = userNameCn;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
    
}
