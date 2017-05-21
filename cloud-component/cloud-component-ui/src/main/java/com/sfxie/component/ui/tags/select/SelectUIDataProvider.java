package com.sfxie.component.ui.tags.select;

import java.util.List;

import com.sfxie.component.ui.dataprovider.AbstractDataProvider;

/**
 * 前端下拉框数据提供者抽象类
 * @author xiesf
 *
 */
public abstract class SelectUIDataProvider<P extends SelecteDataProviderParameter,R extends List<?>>  extends AbstractDataProvider<P,R>{

	
	@Override
	public abstract R query(P SelecteDataProviderParameter) throws Exception ;
	

	/**
	 * 参数处理
	 * @param dataProviderParameter
	 * @return
	 * @throws Exception
	 */
	@Override
	public abstract P mappingParameter(P dataProviderParameter) throws Exception ;
	
	
}