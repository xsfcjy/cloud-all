package com.sfxie.utils.db;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;



public class DefaultSqlType2JavaType implements IDecoratedGenEntity{

	@Override
	public String sqlType2JavaType(String sqlType) {
		if (sqlType.equalsIgnoreCase("bit")) {
			return "Boolean";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "byte";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "Short";
		} else if (sqlType.equalsIgnoreCase("int")) {
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "Long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "Float";
		} else if (sqlType.equalsIgnoreCase("decimal")
				|| sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real")
				|| sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney")) {
			return "Double";
		} else if (sqlType.equalsIgnoreCase("varchar")
				|| sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar")
				|| sqlType.equalsIgnoreCase("nchar")
				|| sqlType.equalsIgnoreCase("text")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("image")) {
			return "Blod";
		}

		return null;
	}
	public void decoratedImport(StringBuffer sb){
		sb.append("\nimport javax.xml.bind.annotation.XmlRootElement;");
		sb.append("\nimport javax.xml.bind.annotation.XmlAttribute;");
		sb.append("\n");
		sb.append("\nimport com.sfxie.extension.mybatis.annotation.TableName;");
		sb.append("\nimport com.sfxie.extension.mybatis.annotation.ColumnName;");
		
	}
	public void decoratedClass(StringBuffer sb,String tableName,String className){
		sb.append("\n@XmlRootElement");
		sb.append("\n@TableName(value=\""+tableName+"\")");
	}
	public void decoratedFiled(StringBuffer sb,String dbFiledName,String javaFiledName){
		sb.append("\n\t@ColumnName(field=\""+dbFiledName+"\")");
	}
	public void decoratedFiledSetter(StringBuffer sb,String dbFiledName,String javaFiledName){
	}
	public void decoratedFiledGetter(StringBuffer sb,String dbFiledName,String javaFiledName){
	}

}
