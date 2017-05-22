package com.sfxie.component.ui.tags.report.netty.report;

public class ReportWebSocketEntity {

	private String reportName;
	
	private String reportText;
	
	private String reportJrxml;
	
	private boolean notupdated = true;
	
	public ReportWebSocketEntity(){}

	public ReportWebSocketEntity(String reportText,String reportName) {
		super();
		this.reportName = reportName;
		this.reportText = reportText;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportJrxml() {
		return reportJrxml;
	}

	public void setReportJrxml(String reportJrxml) {
		this.reportJrxml = reportJrxml;
	}

	public String getReportText() {
		return reportText;
	}

	public void setReportText(String reportText) {
		this.reportText = reportText;
	}

	public boolean getNotupdated() {
		return notupdated;
	}

	public void setNotupdated(boolean notupdated) {
		this.notupdated = notupdated;
	}
	
	
}
