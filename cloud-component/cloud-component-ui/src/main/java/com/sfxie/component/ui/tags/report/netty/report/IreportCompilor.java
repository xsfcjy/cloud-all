package com.sfxie.component.ui.tags.report.netty.report;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import net.sf.jasperreports.engine.JasperCompileManager;

public class IreportCompilor {

	public static void compileReport(String sourceFileName,String destFileName) throws Exception{
		JasperCompileManager.compileReportToFile(sourceFileName, destFileName); 
	}
	public static void compileReport(InputStream inputStream,String destFileName) throws Exception{
		OutputStream outputStream  = new FileOutputStream(destFileName);
		compileReport(inputStream, outputStream); 
	}
	public static void compileReport(InputStream inputStream,OutputStream outputStream) throws Exception{
		JasperCompileManager.compileReportToStream(inputStream, outputStream); 
	}
	public static void compileReportForString(String inputSting,String destFileName) throws Exception{
		OutputStream outputStream  = new FileOutputStream(destFileName);
		JasperCompileManager.compileReportToStream(stringToInputStream(inputSting), outputStream); 
	}
	/** 
     * 将String转换成InputStream 
     * @param in 
     * @return 
     * @throws Exception 
     */  
    private static InputStream stringToInputStream(String in) throws Exception{  
        ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes("UTF-8"));  
        return is;  
    }  
	public static void main(String[] args) {
		String sourceFileName = "C:\\Users\\xiesf\\Desktop\\notuse\\certificatesAll.jrxml";
		String destFileName = "C:\\Users\\xiesf\\Desktop\\notuse\\certificatesAll.jasper";
		try {
//			compileReport(sourceFileName,destFileName);
			compileReport(new FileInputStream(new File(sourceFileName)),destFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
