package com.sfxie.component.ui.tags.report.copy;

/**
 * 报表使用公共类
 * @author xieshengfeng
 * 创建时间：2015-06-09
 */
public class IReportEntity {
	/**	编译后报表模板的文件名	*/
	private String location;
	/**	javabean collection数据源名称	*/
	private String dataSource;
	/**	导出文档类型:(pdf,doc,docx,xls,xlsx等)	*/
	private String format;
	/** ireport参数获取的环境变量名称	*/
	private String reportParameters;
	private String exportParameters;
	/**	connection数据源名称	*/
	private String connectionDataSource;
	
	
	/** 回传到前端easyui的datagrid的pagingnation组件的记录总数	*/
	private Integer total;
	
	private String fileSavePath;
	/** 导出文件的文件名	*/
	private String fileName;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getReportParameters() {
		return reportParameters;
	}

	public void setReportParameters(String reportParameters) {
		this.reportParameters = reportParameters;
	}

	public String getExportParameters() {
		return null==exportParameters?"exportParameters":exportParameters;
	}

	public void setExportParameters(String exportParameters) {
		this.exportParameters = exportParameters;
	}

	public String getConnectionDataSource() {
		return connectionDataSource;
	}

	public void setConnectionDataSource(String connectionDataSource) {
		this.connectionDataSource = connectionDataSource;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSavePath() {
		return fileSavePath;
	}

	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}


}