package com.sfxie.component.ui.tags.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportControllerParameter {
	private int pageSize;
	private int pageNumber;
	private String exportDataType;
	private String exportFileType;
	private String reportName;
	private String reportTitle;
	private String queryFormParameter;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getExportDataType() {
		return exportDataType;
	}

	public void setExportDataType(String exportDataType) {
		this.exportDataType = exportDataType;
	}

	public String getExportFileType() {
		return exportFileType.toUpperCase();
	}

	public void setExportFileType(String exportFileType) {
		this.exportFileType = exportFileType;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getQueryFormParameter() {
		return queryFormParameter;
	}

	public void setQueryFormParameter(String queryFormParameter) {
		this.queryFormParameter = queryFormParameter;
	}

}
