package com.sfxie.component.ui.tags.report.netty;

import java.util.ArrayList;
import java.util.List;

public class ReportWebSocketEntity {

	/**	报表下拉框名称(传送到后台)	*/
	private String reportName;
	/**	报表下拉框显示名称	*/
	private String reportText;
	/**	报表源代码 	*/
	private String reportJrxml;
	/**	报表数据提供类 	*/
	private String datasetJava;
	
	private boolean notupdated = true;
	/**	报表参数	*/
	private List<ReportParameter> parameters ;
	
	public ReportWebSocketEntity(){}

	public ReportWebSocketEntity(String reportText,String reportName) {
		super();
		this.reportName = reportName;
		this.reportText = reportText;
	}
	
	public ReportWebSocketEntity addParameter(String name, String title, String type,String elementType, Object value){
		getParameters().add(new ReportParameter(name, title, type,elementType, value));
		return this;
	}
	public ReportWebSocketEntity addParameter(List<ReportParameter> reportParameters){
		getParameters().addAll(reportParameters);
		return this;
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
	

	public String getDatasetJava() {
		return datasetJava;
	}

	public void setDatasetJava(String datasetJava) {
		this.datasetJava = datasetJava;
	}

	public List<ReportParameter> getParameters() {
		if(null==parameters)
			parameters = new ArrayList<ReportParameter>();
		return parameters;
	}

	public void setParameters(List<ReportParameter> parameters) {
		this.parameters = parameters;
	}
	
	
}
