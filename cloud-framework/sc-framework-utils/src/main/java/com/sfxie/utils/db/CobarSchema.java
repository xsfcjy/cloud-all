package com.sfxie.utils.db;

import java.util.HashMap;
import java.util.Map;

public class CobarSchema {
	private String userName;
	private String userPassword;
	
	private Map<String,String> dbMetaMap ;
	
	public Map<String, String> getDbMetaMap() {
		if(null==dbMetaMap)
			dbMetaMap = new HashMap<String, String>();
		return dbMetaMap;
	}

	public void setDbMetaMap(Map<String, String> dbMetaMap) {
		this.dbMetaMap = dbMetaMap;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	

}
