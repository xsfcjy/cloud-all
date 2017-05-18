package com.sfxie.extension.datasource.mycat;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.sfxie.core.boot.config.datasource.JdbcProperties;


@ConfigurationProperties(prefix = "jdbc.mycat")
public class JdbcMycatProperties extends JdbcProperties {

	private int timeBetweenEvictionRunsMillis = 30000000;
	private int minEvictableIdleTimeMillis = 18000000;
	private int maxActive = 10;
	private int initialSize = 5;
	private int transactionQueryTimeout = 600000000;
	private int maxIdle = 10;
	private int minIdle = 5;
	private int queryTimeout = 600000000;
	
	
	public int getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}
	public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}
	public int getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}
	public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}
	public int getMaxActive() {
		return maxActive;
	}
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	public int getInitialSize() {
		return initialSize;
	}
	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}
	public int getTransactionQueryTimeout() {
		return transactionQueryTimeout;
	}
	public void setTransactionQueryTimeout(int transactionQueryTimeout) {
		this.transactionQueryTimeout = transactionQueryTimeout;
	}
	public int getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public int getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
	public int getQueryTimeout() {
		return queryTimeout;
	}
	public void setQueryTimeout(int queryTimeout) {
		this.queryTimeout = queryTimeout;
	}
	

}
