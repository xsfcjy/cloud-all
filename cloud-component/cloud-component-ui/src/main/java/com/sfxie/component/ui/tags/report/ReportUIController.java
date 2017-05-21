package com.sfxie.component.ui.tags.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * UI组件控制器类
 * 
 * @author xiesf
 * @since 2017-05-10
 *
 */
public class ReportUIController {
	
	private ReportFactory reportFactory;

	/**
	 * easyui表格数据提供
	 * 
	 * @param paramEntity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/ui/report", method = RequestMethod.POST)
	public void report(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		reportFactory.doReport(request, response);
	}

	public void setReportFactory(ReportFactory reportFactory) {
		this.reportFactory = reportFactory;
	}
}
