package com.sfxie.component.ui.tags.report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 报表配置类
 * @author xiesf
 * @since 2017-05-21
 *
 */
@Configuration
public class ReportConfiguration {

	@Bean
	public ReportFactory reportFactory(){
		ReportFactory reportFactory = new ReportFactory();
		return reportFactory;
	}
	
	@Bean
	public ReportUIController reportUIController(ReportFactory reportFactory){
		ReportUIController reportUIController = new ReportUIController();
		reportUIController.setReportFactory(reportFactory);
		return reportUIController;
	}
	
	
}
