package com.sfxie.component.ui.tags.report;

import java.util.List;

import com.sfxie.component.ui.tags.report.netty.ReportParameter;
import com.sfxie.component.ui.tags.report.netty.ReportWebSocketEntity;

/**
 * code source loader
 * @author xuxueli 2016-1-2 20:01:39
 */
public interface ReportGlueLoader {

	/**
	 * 获取报表定义
	 * @return
	 */
	public List<ReportWebSocketEntity> loadAll();

	/**
	 * 获取报表定义文件(xml)
	 * @param reportId
	 * @param versionId
	 * @return
	 */
	public String loadJrxml(int reportId,int versionId);
	/**
	 * 获取报表数据源处理类
	 * @param reportId
	 * @param versionId
	 * @return
	 */
	public String loadDatasetJava(int reportId,int versionId);
	/**
	 * 获取报表的参数列表
	 * @param reportId
	 * @param versionId
	 * @return
	 */
	public List<ReportParameter> loadParameters(int reportId,int versionId);
	
}
