package com.sfxie.core.boot.config.datasource.center;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties(prefix = "test.jdbc", locations = {"config/yml/jdbc.yml"})

@ConfigurationProperties(prefix = "jdbc.center")
public class JdbcCrmCenterProperties {

	public JdbcCrmCenterProperties() {
		System.out.println("JdbcProperties init");
	}

	private String driverClassName;
	private String username;
	private String password;
	private String url;

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
