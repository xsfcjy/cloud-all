package com.sfxie.utils.db;

import java.util.ArrayList;
import java.util.List;


public class PojoGenerater {

	public static void main(String[] args) {
		CobarSchema cs1 = new CobarSchema ();
		cs1.getDbMetaMap().put("golivetest", "jdbc:mysql://183.60.142.159:3306/golivetest");
		cs1.setUserName("cmstest");
		cs1.setUserPassword("!mI65DU9B5");
		List<Table> tableList = new ArrayList<Table>();
		
		Table table3 = new Table();
		table3.setDataNode("golivetest");
		table3.setName("g_ad3_goods");
		tableList.add(table3);
		
		String packagePath = "com.sfxie.services.center.pojo";
		new GenEntity(packagePath,"",cs1,tableList,new DefaultSqlType2JavaType());
		
		/*try {
			GenEntity.printAllTableName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
