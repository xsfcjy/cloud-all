package com.sfxie.extension.logger.dyncpath;


import org.apache.log4j.Level;

public class TestCustomerLogger extends DyncPathLogger {

	@Override
	protected Level level() {
		return Level.INFO;
	}

	@Override
	protected String conversionPattern() {
		return "%m%n";
	}

}
