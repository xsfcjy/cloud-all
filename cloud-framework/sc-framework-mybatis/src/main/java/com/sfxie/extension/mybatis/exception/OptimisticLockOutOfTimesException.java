package com.sfxie.extension.mybatis.exception;

public class OptimisticLockOutOfTimesException extends ExtensionMybatisException{

	public OptimisticLockOutOfTimesException(Exception e) {
		super(e);
	}
	public OptimisticLockOutOfTimesException(String e) {
		super(e);
	}
}
