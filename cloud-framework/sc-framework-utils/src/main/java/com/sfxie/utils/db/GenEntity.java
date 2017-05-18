package com.sfxie.utils.db;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAttribute;

import org.apache.commons.lang3.StringUtils;


/**
 * 自动生成pojo类
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午5:12:25 2015年10月15日
 * @example		
 *
 */
public class GenEntity {
	
	private IDecoratedGenEntity decoratedGenEntity;
	private String tablename = "";// 表名
	private String[] colnames; // 列名数组
	private String[] colTypes; // 列名类型数组
	private int[] colSizes; // 列名大小数组
	private boolean f_util = false; // 是否需要导入包java.util.*
	private boolean f_sql = false; // 是否需要导入包java.sql.*

	// 数据库连接
	private static String URL = "";
//	private static String NAME = "test";
//	private static String PASS = "test";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
//	private static final String packager = "com.sfxie.website.modules.sfxie.pojo.oa";
//	private static final String outputPath = "/com/sfxie/website/modules/sfxie/pojo/oa/";
	
	
	private static String packager = "";
	private static String prefixPackgePath = "";
//	private static final String outputPath = "/com/golive/website/modules/api3/common/pojo/";
	
	private static final String authod = "sfxie";
	
	private static  Map<String,String> commentMap = new HashMap<String,String>();
	

	// K:\xsf\workspace\maven\golive\20150724\cobar-master\server\src\main\resources\schema.xml
	/*
	 * 构造函数
	 */
	public GenEntity(String packagePath,String prefixPackgePath1,CobarSchema cs1 ,List<Table> tableList,IDecoratedGenEntity decoratedGenEntity) {
		try {
			prefixPackgePath = prefixPackgePath1;
			this.decoratedGenEntity = decoratedGenEntity;
//			cs1.getDbMetaMap().put("dn_sfxie_os", "jdbc:mysql://192.168.10.112:3306/sfxie_os");
//			cs1.getDbMetaMap().put("dn_sfxie_collection", "jdbc:mysql://192.168.10.112:3306/sfxie_collection");
//			cs1.getDbMetaMap().put("dn10", "jdbc:mysql://192.168.10.112:8066/TESTDB");
			
			for(Table table : tableList){
				// 创建连接
				f_util = false;
				f_sql = false; 
				URL = cs1.getDbMetaMap().get(table.getDataNode());
				commentMap.clear();
				tablename = table.getName();
				Connection con;
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, cs1.getUserName(), cs1.getUserPassword());
				
				packager = packagePath;
				generatedMetaInfo(con);
				String content = parse(colnames, colTypes, colSizes);
				File directory = new File("");
				String path = "/"+packager.replaceAll("\\.", "/");
				
				String javaFileName = directory.getAbsolutePath()
						+ File.separator
						+ "src"+prefixPackgePath
						+ path.substring(path.lastIndexOf("/com/", path.length()),
								path.length()).replaceAll("//", File.separator)+"/"
								+ transformName(tablename, "table") + ".java";
				FileWriter fw = new FileWriter(javaFileName);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(content);
				pw.flush();
				pw.close();
			}

		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}
	public static void printAllTableName() throws Exception{
		Connection con;
		Class.forName(DRIVER);
//		con = DriverManager.getConnection("jdbc:mysql://183.60.142.159:3306/golivetest", "cmstest", "!mI65DU9B5");
		con = DriverManager.getConnection("jdbc:mysql://192.168.10.112:3306/sfxie_oa", "root", "root");
		DatabaseMetaData dbmd=con.getMetaData();
		ResultSet tableRet = dbmd.getTables(null, "%","%",new String[]{"TABLE"});
		while(tableRet.next()) {
			String tableName =  tableRet.getString("TABLE_NAME");
			if(null!=tableName && tableName.startsWith("g_")){
				String dd = "<table name=\"#tableName#\" dataNode=\"datanodeGolive\" ></table>";
//				String dd = "<dataNode name=\"#tableName#\"   dataHost=\"hostGolive\" database=\"golivetest\" />";
				System.out.println(dd.replace("#tableName#",tableName));
			}
		}
	}
	private void generatedMetaInfo(Connection con) throws Exception{
		// 查要生成实体类的表
		String sql = "select * from " + tablename;
		PreparedStatement pStemt = null;
		pStemt = con.prepareStatement(sql);
		DatabaseMetaData dbmd=con.getMetaData();
        ResultSet resultSet = dbmd.getTables(null, "%", tablename, new String[] { "TABLE" });
        while (resultSet.next()) {
            String tableName=resultSet.getString("TABLE_NAME");
            ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
            while(rs.next()){
                commentMap.put(rs.getString("COLUMN_NAME"),rs.getString("REMARKS"));
            }
        }
		ResultSetMetaData rsmd = pStemt.getMetaData();
		int size = rsmd.getColumnCount(); // 统计列
		colnames = new String[size];
		colTypes = new String[size];
		colSizes = new int[size];
		for (int i = 0; i < size; i++) {
			colnames[i] = rsmd.getColumnName(i + 1);
			colTypes[i] = rsmd.getColumnTypeName(i + 1);
			
			if (colTypes[i].equalsIgnoreCase("datetime")) {
				f_util = true;
			}
			if (colTypes[i].equalsIgnoreCase("image")
					|| colTypes[i].equalsIgnoreCase("text")) {
				f_sql = true;
			}
			colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
		}
	}
	/**
	 * 功能：生成实体类主体代码
	 * 
	 * @param colnames
	 * @param colTypes
	 * @param colSizes
	 * @return
	 */
	private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
		StringBuffer sb = new StringBuffer();

		// 判断是否导入工具包
		sb.append("package "+packager+";\r\n");
		
//		sb.append("\r\nimport com.sfxie.soa.modules.dubbo.common.pojo.SecurityObject;");
		if (f_util) {
			sb.append("\r\nimport java.util.Date;");
		}
		if (f_sql) {
			sb.append("\r\nimport java.sql.*;");
		}
		decoratedGenEntity.decoratedImport(sb);
		sb.append("\r\n");
		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * " + transformName(tablename, "table") + " 实体类\r\n");
		sb.append(" * " + new Date() + "  "+authod+"\r\n");
		sb.append(" */");
		// 实体部分
		decoratedGenEntity.decoratedClass(sb, tablename, transformName(tablename, "table"));
		sb.append("\r\npublic class " + transformName(tablename, "table") //+ " extends SecurityObject"
				+ "{\r\n");
		processAllAttrs(sb);// 属性
		processAllMethod(sb);// get set方法
		sb.append("}\r\n");

		// System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * 功能：生成所有属性
	 * 
	 * @param sb
	 */
	private void processAllAttrs(StringBuffer sb) {

		for (int i = 0; i < colnames.length; i++) {
			if(StringUtils.isNotEmpty(commentMap.get(colnames[i]))){
				sb.append("\n\t/**\t").append(commentMap.get(colnames[i])).append("\t*/");
			}
			decoratedGenEntity.decoratedFiled(sb, colnames[i],transformName(colnames[i], "filed"));
			sb.append("\n\tprivate " + decoratedGenEntity.sqlType2JavaType(colTypes[i]) + " "
					+ transformName(colnames[i], "filed") + ";\r\n");
		}

	}

	/**
	 * 功能：生成所有方法
	 * 
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb) {

		for (int i = 0; i < colnames.length; i++) {
			if(StringUtils.isNotEmpty(commentMap.get(colnames[i]))){
				sb.append("\n\t/**\n\t* 设置").append(commentMap.get(colnames[i]))
				.append("\n\t* @param ").append(transformName(colnames[i], "filed"))
				.append("\n\t*/");
			}
			sb.append("\n\tpublic void set"
					+ initcap(transformName(colnames[i], "filed")) + "("
					+ decoratedGenEntity.sqlType2JavaType(colTypes[i]) + " " + transformName(colnames[i], "filed")
					+ "){\r\n");
			sb.append("\t\tthis." + transformName(colnames[i], "filed") + "="
					+ transformName(colnames[i], "filed") + ";\r\n");
			sb.append("\t}\r\n");
			if(StringUtils.isNotEmpty(commentMap.get(colnames[i]))){
				sb.append("\n\t/**\t获取").append(commentMap.get(colnames[i])).append("\t*/");
			}
			sb.append("\n\t @XmlAttribute").append("(name=\"").append(transformFieldName(colnames[i])).append("\")");
			//(name="isSuperman")
			sb.append("\n\tpublic " + decoratedGenEntity.sqlType2JavaType(colTypes[i]) + " get"
					+ initcap(transformName(colnames[i], "filed")) + "(){\r\n");
			sb.append("\t\treturn " + transformName(colnames[i], "filed")
					+ ";\r\n");
			sb.append("\t}\r\n");
		}

	}

	/**
	 * 功能：将输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcap(String str) {

		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}

		return new String(ch);
	}

	private String transformName(String name, String type) {
		String[] nameArray = name.toLowerCase().split("_");
		String newName = "";
		for (int i = 0; i < nameArray.length; i++) {

			if (type.equals("table")) {
				newName += initcap(nameArray[i]);
			} else {
				/*if (i == 0) {
					newName += nameArray[i];
				} else {
					newName += initcap(nameArray[i]);
				}*/
				newName = name;
			}
		}
		return newName;
	}
	private String transformFieldName(String name) {
		String[] nameArray = name.toLowerCase().split("_");
		String newName = "";
		for (int i = 0; i < nameArray.length; i++) {

			if (i!=0) {
				newName += initcap(nameArray[i]);
			} else {
				newName = nameArray[i];
			}
		}
		return newName;
	}
	
	/**
	 * 出口 TODO
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CobarSchema cs1 = new CobarSchema ();
		cs1.getDbMetaMap().put("golivetest", "jdbc:mysql://183.60.142.159:3306/golivetest");
		cs1.setUserName("cmstest");
		cs1.setUserPassword("!mI65DU9B5");
		List<Table> tableList = new ArrayList<Table>();
		Table table1 = new Table();
		table1.setDataNode("golivetest");
		table1.setName("g_ad3_goods_attr");
		tableList.add(table1);
		
		String packagePath = "com.sfxie.website.modules.api3.common.memcached.pojo";
		new GenEntity(packagePath,"",cs1,tableList,new DefaultSqlType2JavaType());
	}
	
	/*public static void generatedPojoCode(){
		CobarSchema cs1 = new CobarSchema ();
		cs1.getDbMetaMap().put("dn10", "jdbc:mysql://183.60.142.159:3306/golivetest");
		String packagePath = "com.sfxie.website.modules.api3.common.pojo";
		new GenEntity(packagePath,cs1,new DefaultSqlType2JavaType());
	}*/
}