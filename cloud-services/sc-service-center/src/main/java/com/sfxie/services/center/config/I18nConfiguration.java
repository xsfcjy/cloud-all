package com.sfxie.services.center.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages={"com.sfxie.component.ui.i18n"})
public class I18nConfiguration extends WebMvcConfigurerAdapter {
}
