package com.sfxie.services.center.pojo;

import com.sfxie.services.core.security.DataSecurityRecord;

public class PartitionData extends DataSecurityRecord{


    /**
     *  分区字段 : 分区字段,从用户公司代码字段取值,所属表字段为partition_company
     */
    private String partitionCompany;

	public String getPartitionCompany() {
		return partitionCompany;
	}

	public void setPartitionCompany(String partitionCompany) {
		this.partitionCompany = partitionCompany;
	}
    
    
}
