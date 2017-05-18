package com.sfxie.utils;

public class ExceptionUtil {
	/**
	 * 获取异常的完整异常信息
	 * @param e
	 * @return
	 */
    public static String getExceptionMsg(Throwable e){
		StringBuffer msg = new StringBuffer();
		if (e != null) {  
            String message = e.toString();  
            int length = e.getStackTrace().length;  
            if (length > 0) {  
                msg.append(message + "\n");  
                for (int i = 0; i < length; i++) {  
            		msg.append("\t" + e.getStackTrace()[i] + "\n"); 
                }  
            } else {  
                msg.append(message);  
            }  
        } 
		return msg.toString();
	}
}
