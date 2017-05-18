package com.sfxie.component.ui.dataprovider;




/**
 * 数据提供者抽象类
 * @TODO 
 * @author xsf
 * 2015年4月22日下午3:35:44
 * {@link com.sfxie.component.ui.dataprovider.advert.common.tag.struts.select.SelectDataProvider}
 */
public interface DataProvider<Parameter,Retrun> {
	
	/**
	 * 查询数据
	 * @param parameter
	 * 		参数
	 * @return
	 * 		返回数据
	 * @throws Exception
	 */
	public abstract Retrun query(Parameter parameter) throws Exception ;
	
}
