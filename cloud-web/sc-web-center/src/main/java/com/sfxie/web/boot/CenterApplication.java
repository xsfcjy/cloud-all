package com.sfxie.web.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.sfxie.component.ui.UIConfiguration;
import com.sfxie.component.ui.tags.report.ReportClientConfiguration;
import com.sfxie.core.framework.boot.BaseApplicationConfig;
import com.sfxie.utils.IpUtil;
import com.sfxie.web.boot.i18n.I18nConfiguration;
import com.sfxie.web.boot.util.ServerPathUtil;

@ComponentScan(basePackages = { 
		"com.sfxie.core.framework",
		"com.sfxie.core.framework.core",
		"com.sfxie.core.framework.mvc.controller" ,
		"com.sfxie.services.center",
		"com.sfxie.web.tag",
		"com.sfxie.web.ui"
		})
@SpringBootApplication
@EnableAutoConfiguration(exclude={
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		XADataSourceAutoConfiguration.class
})
public class CenterApplication  extends BaseApplicationConfig{
	
	@Autowired  
    private RestTemplateBuilder builder; 
	
	@Bean  
    public RestTemplate restTemplate() {  
        return builder.build();  
    } 
	

	public static void main(String[] args) throws Exception {
		SpringApplication.run(new Object []{
				CenterApplication.class,
				ReportClientConfiguration.class,
				UIConfiguration.class,
				I18nConfiguration.class,
				ClientResourceConfiguration.class
		}, args);
	}
	public void run(String... arg0) throws Exception {
		String centerServerPath = "http://"+IpUtil.getIpPort(8083);
		logger.info("centerServerPath: "+centerServerPath);
		ServerPathUtil.setCenterServerPath(centerServerPath);
		ServerPathUtil.setEasyuiDataProviderServerPath(centerServerPath);
	}

}