package com.sfxie.web.boot.util;

public class ServerPathUtil {

	public static String getServerPath(ServiceName serviceName) {
		switch (serviceName) {
		case centerServer:
			return "http://localhost:8083";
		case easyuiDataProviderServer:
			return "http://localhost:8083/ui/easyui/gridColumnDP";
		default:
			return "http://localhost:8083";
		}
	}

	public static enum ServiceName {
		centerServer,easyuiDataProviderServer;
	}
}
