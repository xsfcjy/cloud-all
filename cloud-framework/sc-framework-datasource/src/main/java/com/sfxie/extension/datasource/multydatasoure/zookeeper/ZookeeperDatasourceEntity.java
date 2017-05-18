package com.sfxie.extension.datasource.multydatasoure.zookeeper;

import org.codehaus.jackson.map.annotate.JsonRootName;


/**
 * 用于集群负载均衡控制
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	上午8:45:39 2016年4月8日
 * @example		
 *
 */
@JsonRootName("mycat")
public class ZookeeperDatasourceEntity {
	
	public ZookeeperDatasourceEntity(){}
	
	public ZookeeperDatasourceEntity(String uniqueResourceName ,String url ,String userName,String password){
		this.uniqueResourceName = uniqueResourceName;
		this.url = url;
		this.userName = userName;
		this.password = password;
	}

	private String uniqueResourceName;
	
	private String url;
	
	private String userName;
	
	private String password;
	
	private String driverClassName;

	private int maxActive;
	private int initialSize;
	private int maxWait;
	private int minIdle;
	private Boolean removeAbandoned;
	private int removeAbandonedTimeout;
	private String connectionProperties;
	private String validationQuery;
	
	
	
	/**
	 * 默认:com.mysql.jdbc.Driver
	 */
	public String getDriverClassName() {
		return null!=driverClassName?driverClassName:"com.mysql.jdbc.Driver";
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	/**
	 * 默认:20
	 */
	public int getMaxActive() {
		return maxActive>0?maxActive:20;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	/**
	 * 默认:10
	 */
	public int getInitialSize() {
		return initialSize>0?initialSize:10;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}
	/**
	 * 默认:60000
	 */
	public int getMaxWait() {
		return  maxWait>0?maxWait:60000;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}
	/**
	 * 默认:3
	 */
	public int getMinIdle() {
		return minIdle>0?minIdle:3;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
	/**
	 * 默认:
	 */
	public Boolean isRemoveAbandoned() {
		return null==removeAbandoned?true:removeAbandoned;
	}

	public void setRemoveAbandoned(Boolean removeAbandoned) {
		this.removeAbandoned = removeAbandoned;
	}
	/**
	 * 默认:
	 */
	public int isRemoveAbandonedTimeout() {
		return removeAbandonedTimeout>0?removeAbandonedTimeout:180;
	}

	public void setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}
	/**
	 * 默认:
	 */
	public String getConnectionProperties() {
		return null!=connectionProperties?connectionProperties:"clientEncoding=UTF8";
	}

	public void setConnectionProperties(String connectionProperties) {
		this.connectionProperties = connectionProperties;
	}
	/**
	 * 默认:
	 */
	public String getValidationQuery() {
		return null!=validationQuery?validationQuery:"SELECT 'x'";
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}
	/**
	 * 默认:
	 */
	public String getUniqueResourceName() {
		return uniqueResourceName;
	}
	
	public void setUniqueResourceName(String uniqueResourceName) {
		this.uniqueResourceName = uniqueResourceName;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
