package com.sfxie.web.tag.select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;

import com.golive.xmlbeans.data.select.RecordDocument.Record;
import com.golive.xmlbeans.data.select.SelectDocument.Select;
import com.golive.xmlbeans.data.select.SelectsDocument;

public class SelectDataXmlUtil {
	
	final static String m_namespaceDeclaration = "declare namespace xq='http://xmlbeans.golive.com/schema/data/select';";
	
	private static String pathSingleExpression = "$this/xq:selects/xq:select[@name='{0}']";
	
	private static String pathMultyExpression = "$this/xq:selects/xq:select[@name='{0}'and@state='{1}']";
	
	

	public static List<Map<String,Object>> queryFirstSelectDataList(String selectNodeName,String selectNodeState,String displyaField,String valueField) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			Select s =  null;
			SelectsDocument doc = SelectsDocument.Factory.parse(SelectDataXmlUtil.class.getResourceAsStream("/data-select.xml"));
			String path = pathMultyExpression.replace("{0}", selectNodeName).replace("{1}", selectNodeState);
			XmlObject[] dd = doc.selectPath(m_namespaceDeclaration + path);
			s = (Select) dd[0];
			Record[] records = s.getRecordArray();
			for(int i=0;i<records.length;i++){
				Record record = records[i];
				Map<String,Object> map = new HashMap<String,Object>();
				map.put(displyaField, record.getText());
				map.put(valueField, record.getValue());
				resultList.add(map);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultList;
	}
	public static Select[] queryMultySelectList(String name) {
		try {
			SelectsDocument doc = SelectsDocument.Factory.parse(SelectDataXmlUtil.class.getResourceAsStream("/data-select.xml"));
			String path = pathSingleExpression.replace("{0}", name);
			XmlObject[] dd = doc.selectPath(m_namespaceDeclaration + path);
			return (Select[]) dd;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static Select[] queryXpathSelectList(String xpath) {
		try {
			SelectsDocument doc = SelectsDocument.Factory.parse(SelectDataXmlUtil.class.getResourceAsStream("/data-select.xml"));
			XmlObject[] dd = doc.selectPath(m_namespaceDeclaration + xpath);
			return (Select[]) dd;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
//		String pathExpression = "$this/xq:selects/xq:select[@name='upgrade_mode'and@state='1']";
//		queryXpathSelectList(pathExpression);
//		queryFirstSelect("upgrade_mode","1");
	}
}