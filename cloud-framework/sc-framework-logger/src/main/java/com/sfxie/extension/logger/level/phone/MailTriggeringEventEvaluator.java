package com.sfxie.extension.logger.level.phone;


import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.TriggeringEventEvaluator;

public final class MailTriggeringEventEvaluator  {
		public final static class FatalMockTriggeringEventEvaluator implements TriggeringEventEvaluator{
			@Override
			public boolean isTriggeringEvent(LoggingEvent arg0) {
				return true;
			}
		}
		public final static class InfoMockTriggeringEventEvaluator implements TriggeringEventEvaluator{
			public boolean isTriggeringEvent(LoggingEvent arg0) {
				return true; 
			}
		}
	}