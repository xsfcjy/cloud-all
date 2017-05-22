package com.sfxie.component.ui.tags.report.netty.report;

import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.sfxie.utils.jacson.codehaus.JsonUtil;

public class ReportTask implements Runnable {
	
	private ChannelGroup group;
	
	private List<ReportWebSocketEntity> reports = new ArrayList<ReportWebSocketEntity>();

	public ReportTask(ChannelGroup group) {
		reports.add(new ReportWebSocketEntity("certificates","certificates.jrxml"));
		reports.add(new ReportWebSocketEntity("certificatesAll","certificatesAll.jrxml"));
		this.group = group;
	}

	@Override
	public void run() {
		while(true){
			try {
				System.out.println("group size: "+group.size());
				if(group.size() > 0 ){
					for(ReportWebSocketEntity  reportWebSocketEntity : reports){
						reportWebSocketEntity.setReportJrxml(getJrxmlString(reportWebSocketEntity.getReportName()));
						group.writeAndFlush(new TextWebSocketFrame(JsonUtil.toJSON(reportWebSocketEntity)));
					}
				}
	        	Thread.sleep(30000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private String getJrxmlString(String reportName) throws IOException{
    	InputStream i = this.getClass().getResource(reportName).openStream();
		String dd = inputStream2String(i);
		return dd;
    }
    
    private String inputStream2String(InputStream is) throws IOException{
 	   BufferedReader in = new BufferedReader(new InputStreamReader(is,"UTF-8"));
 	   StringBuffer buffer = new StringBuffer();
 	   String line = "";
 	   while ((line = in.readLine()) != null){
 	     buffer.append(line);
 	   }
 	   return buffer.toString();
 	}

}
