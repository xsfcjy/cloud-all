package com.sfxie.extension.logger.exception;

import com.sfxie.exception.framework.ExceptionEvent;
import com.sfxie.exception.framework.ExceptionEventListener;
import com.sfxie.exception.framework.FrameworkException;

public class Log4jException extends FrameworkException {

	public Log4jException(String errorMsg) {
		super(errorMsg);
	}
	@Override
	public void addEventListener(
			ExceptionEventListener<? extends ExceptionEvent> listener) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeEventListener(
			ExceptionEventListener<? extends ExceptionEvent> listener) {
		// TODO Auto-generated method stub
		
	}

}
