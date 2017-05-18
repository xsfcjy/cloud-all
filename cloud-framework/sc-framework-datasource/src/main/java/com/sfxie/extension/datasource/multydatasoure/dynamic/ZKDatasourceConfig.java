package com.sfxie.extension.datasource.multydatasoure.dynamic;

public class ZKDatasourceConfig {

	private String multiDatasourcZkPath;

	private String multiDatasourcZkServiceName;

	public String getMultiDatasourcZkPath() {
		return multiDatasourcZkPath;
	}

	public void setMultiDatasourcZkPath(String multiDatasourcZkPath) {
		this.multiDatasourcZkPath = multiDatasourcZkPath;
	}

	public String getMultiDatasourcZkServiceName() {
		return multiDatasourcZkServiceName;
	}

	public void setMultiDatasourcZkServiceName(
			String multiDatasourcZkServiceName) {
		this.multiDatasourcZkServiceName = multiDatasourcZkServiceName;
	}

}
