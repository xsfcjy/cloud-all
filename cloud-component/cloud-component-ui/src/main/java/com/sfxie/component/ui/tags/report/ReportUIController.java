package com.sfxie.component.ui.tags.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * UI组件控制器类
 * 
 * @author xiesf
 * @since 2017-05-10
 *
 */
@Controller
public class ReportUIController {
	
	private ReportFactory reportFactory;
	

	public void setReportFactory(ReportFactory reportFactory) {
		this.reportFactory = reportFactory;
	}

	/**
	 * easyui表格数据提供
	 * 
	 * @param paramEntity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/ui/report", method = RequestMethod.POST)
	public void report(@RequestBody ReportControllerParameter reportControllerParameter,HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		dealReport(reportControllerParameter,request, response);
	}
	@RequestMapping(value = "/ui/report/export", method = RequestMethod.POST)
	public void reportHref(ReportControllerParameter reportControllerParameter,HttpServletRequest request, HttpServletResponse response) throws Exception {
		dealReport(reportControllerParameter,request, response);
	}
	
	/**
	 * 处理报表
	 * @param reportControllerParameter
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void dealReport(ReportControllerParameter reportControllerParameter,HttpServletRequest request, HttpServletResponse response) throws Exception{
		reportFactory.doReport(reportControllerParameter,request, response);
	}
}
