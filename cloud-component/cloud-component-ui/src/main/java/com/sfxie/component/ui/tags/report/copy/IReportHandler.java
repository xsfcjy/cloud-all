package com.sfxie.component.ui.tags.report.copy;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 报表数据处理抽象类
 * 
 * @author xiesf
 * @since 2017-05-19
 *
 * @param <T>
 * 		返回值类型
 * @param <P>
 * 		参数类型
 */
public abstract class IReportHandler<T, P extends Object> {

	public abstract T dataset(P parameter);

	public Class<?> parameterClass;

	public Class<?> returnClass;

	/**
	 * 初始化报表处理类，主要设置参数和返回的实际类型
	 */
	public IReportHandler() {
		Type returnType = ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		Type parameterType = ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
		try {
			this.parameterClass = Class.forName(parameterType.getTypeName());
			this.returnClass = Class.forName(returnType.getTypeName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取参数实际类型
	 * @return
	 */
	protected Class<?> parameterClass() {
		return this.parameterClass;
	}

	/**
	 * 获取返回值实际类型
	 * @return
	 */
	protected Class<?> returnClass() {
		return this.returnClass;
	}
}
