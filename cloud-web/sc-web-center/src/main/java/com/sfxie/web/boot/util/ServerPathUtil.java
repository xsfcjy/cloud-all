package com.sfxie.web.boot.util;

public class ServerPathUtil {
	
	private static String centerServerPath = "http://localhost:8083";
	
	private static String easyuiDataProviderServerPath = "http://localhost:8083/ui/easyui/gridColumnDP";
	

	public static String getServerPath(ServiceName serviceName) {
		switch (serviceName) {
		case centerServer:
			return centerServerPath;
		case easyuiDataProviderServer:
			return easyuiDataProviderServerPath;
		default:
			return "http://localhost:8083";
		}
	}

	public static enum ServiceName {
		centerServer,easyuiDataProviderServer;
	}

	public static void setCenterServerPath(String centerServerPath) {
		ServerPathUtil.centerServerPath = centerServerPath;
	}

	public static void setEasyuiDataProviderServerPath(
			String easyuiDataProviderServerPath) {
		ServerPathUtil.easyuiDataProviderServerPath = easyuiDataProviderServerPath+"/ui/easyui/gridColumnDP";
	}
	
	
}
