package com.sfxie.component.ui.dataprovider;

/**
 * 数据提供者抽类象
 * @author xiesf
 *
 * @param <Parameter>
 * @param <Retrun>
 */
public abstract class AbstractDataProvider<Parameter extends DataProviderParameter,Retrun extends Object> implements DataProvider<Parameter, Retrun> {
	
	/**
	 * 参数处理
	 * @param dataProviderParameter
	 * @return
	 * @throws Exception
	 */
	public abstract Parameter mappingParameter(Parameter dataProviderParameter) throws Exception ;
	
}
