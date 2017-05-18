package com.sfxie.core.boot.config.mybatis.business;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties(prefix = "test.jdbc", locations = {"config/yml/jdbc.yml"})

@ConfigurationProperties(prefix = "mybatis.business")
public class MybatisBusinessProperties {

	public MybatisBusinessProperties() {
	}

	private String typeAliasesPackage;
	private String mapperLocations;
	private String configLocation;

	public String getTypeAliasesPackage() {
		return typeAliasesPackage;
	}

	public void setTypeAliasesPackage(String typeAliasesPackage) {
		this.typeAliasesPackage = typeAliasesPackage;
	}

	public String getMapperLocations() {
		return mapperLocations;
	}

	public void setMapperLocations(String mapperLocations) {
		this.mapperLocations = mapperLocations;
	}

	public String getConfigLocation() {
		return configLocation;
	}

	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

}
