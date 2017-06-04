package com.sfxie.component.ui.tags.report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 报表配置类(服务器端)
 * @author xiesf
 * @since 2017-05-21
 *
 */
@Configuration
public class ReportServerConfiguration {

	@Bean
	public ReportFactory reportFactory() throws Exception{
		ReportFactory reportFactory = new ReportFactory();
		return reportFactory;
	}
	
}
