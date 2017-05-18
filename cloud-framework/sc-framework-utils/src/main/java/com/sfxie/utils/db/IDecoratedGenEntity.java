package com.sfxie.utils.db;

/**
 * 生成java class的装潢接口
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午5:49:39 2015年10月15日
 * @example		
 *
 */
public interface IDecoratedGenEntity {

	/**
	 * 功能：获得列的数据类型
	 * 
	 * @param sqlType
	 * @return
	 */
	public String sqlType2JavaType(String sqlType) ;
	/**		装潢import信息	*/
	public void decoratedImport(StringBuffer sb);
	/**		装潢Class头部信息	*/
	public void decoratedClass(StringBuffer sb,String tableName,String className);
	/**		装潢字段声明信息	*/
	public void decoratedFiled(StringBuffer sb,String dbFiledName,String javaFiledName);
	/**		装潢set方法信息	*/
	public void decoratedFiledSetter(StringBuffer sb,String dbFiledName,String javaFiledName);
	/**		装潢get方法信息	*/
	public void decoratedFiledGetter(StringBuffer sb,String dbFiledName,String javaFiledName);
}
