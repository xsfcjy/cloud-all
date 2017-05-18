package com.sfxie.services.center.test;

import java.util.ArrayList;
import java.util.List;

import com.sfxie.utils.db.CobarSchema;
import com.sfxie.utils.db.DefaultSqlType2JavaType;
import com.sfxie.utils.db.GenEntity;
import com.sfxie.utils.db.Table;


public class PojoGenerater {

	public static void main(String[] args) {
		CobarSchema cs1 = new CobarSchema ();
		cs1.getDbMetaMap().put("sc_center", "jdbc:mysql://xsfcjy.oicp.net:8066/sc_center");
		cs1.setUserName("center_root");
		cs1.setUserPassword("123456");
		List<Table> tableList = new ArrayList<Table>();
		
		Table table3 = new Table();
		table3.setDataNode("sc_center");
		table3.setName("sfxie_sys_userinfo");
		tableList.add(table3);
		
		String packagePath = "com.sfxie.services.center.pojo";
		new GenEntity(packagePath,"\\main\\java",cs1,tableList,new DefaultSqlType2JavaType());
		
		/*try {
			GenEntity.printAllTableName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
