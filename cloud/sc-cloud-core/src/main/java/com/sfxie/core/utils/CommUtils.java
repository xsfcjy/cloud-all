package com.sfxie.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;


public class CommUtils {
	
	/** 
	 * 从request中获得参数Map，并返回可读的Map 
	 *  
	 * @param request 
	 * @return 
	 */  
	public static Map<String, Object> getParameterMap(HttpServletRequest request) {
    	Map<String, Object> map = new HashMap<String, Object>();
        Enumeration paramNames = request.getParameterNames();  
        while (paramNames.hasMoreElements()) {  
            String paramName = (String) paramNames.nextElement();  
            String[] paramValues = request.getParameterValues(paramName);  
            if (paramValues.length >= 1) {  
                String paramValue = paramValues[0];  
                if (paramValue.length() != 0) {  
                	map.put(paramName, paramValue);  
                }  
            }  
        }  
        return map;
    }  
    
	/**
	 * @Description: 请求对象解析工具
	 * @param  HttpServletRequest 对象
	 * @Author: luoml01
	 * @date: 2016年3月22日 
	 * @return:String字符串
     * @exception:
	 * @Copyright: Conpyright (c) 2016 共享云平台有限公司 版权所有
	 */
	public static String getStrParam(HttpServletRequest request){
		   InputStream input = null;
		   StringBuffer sb = new StringBuffer(); 
		   String buffer = null; 
			try {
				input = request.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(input, "UTF-8")); 
		           while ((buffer = br.readLine()) != null) { 
		               sb.append(buffer); 
		           } 
			} catch (IOException e) {
				e.printStackTrace();
			}
		return sb.toString();
	}
	
	/**
	* 获取客户IP
	* @param HttpServletRequest
	* 在获取客户IP时候,request.getRemoteAddr()取得客户端的IP地址,
	* 但是在通过了Apache,Squid等反向代理软件就不能获取到客户端的真实IP地址了。
	* 经过代理以后，由于在客户端和服务之间增加了中间层，因此服务器无法直接拿到客户端的IP，
	* 服务器端应用也无法直接通过转发请求的地址返回给客户端。但是在转发请求的HTTP头信息中，
	* 增加了X－FORWARDED－FOR信息用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址。
	* @return
	*/
	public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;     
        ipAddress = request.getHeader("x-forwarded-for");   
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
        	ipAddress = request.getHeader("Proxy-Client-IP");   
        }   
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
            ipAddress = request.getHeader("WL-Proxy-Client-IP");   
        }   
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
        	ipAddress = request.getRemoteAddr();   
        	if(ipAddress.equals("127.0.0.1")){   
        		//根据网卡取本机配置的IP   
        		InetAddress inet=null;   
        		try {   
        			inet = InetAddress.getLocalHost();   
        		} catch (UnknownHostException e) {   
        			e.printStackTrace();   
        		}   
        		ipAddress= inet.getHostAddress();   
        	}   
        }   
     
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割   
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15   
            if(ipAddress.indexOf(",")>0){   
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));   
            }   
        }   
        return ipAddress;     
    }
}
