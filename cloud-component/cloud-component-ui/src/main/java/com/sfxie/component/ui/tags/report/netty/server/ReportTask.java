package com.sfxie.component.ui.tags.report.netty.server;

import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.ArrayList;
import java.util.List;

import com.sfxie.component.ui.tags.report.ReportGlueLoader;
import com.sfxie.component.ui.tags.report.netty.ReportWebSocketEntity;
import com.sfxie.core.framework.core.SpringContext;
import com.sfxie.utils.jacson.codehaus.JsonUtil;

public class ReportTask implements Runnable {
	
	private ChannelGroup group;
	
	private List<ReportWebSocketEntity> reports = new ArrayList<ReportWebSocketEntity>();

	public ReportTask(ChannelGroup group) {
		ReportGlueLoader reportGlueLoader = SpringContext.getBeanByClass(ReportGlueLoader.class);
		
		List<ReportWebSocketEntity> reportList = reportGlueLoader.loadAll();
		
		for(ReportWebSocketEntity reportWebSocketEntity : reportList){
			String [] reportAndVersionId = reportWebSocketEntity.getReportName().split("_");
			reportWebSocketEntity.addParameter(reportGlueLoader.loadParameters( 
					Integer.valueOf(reportAndVersionId[0]),Integer.valueOf(reportAndVersionId[1])));
		}
		
		reports.addAll(reportList);
		/*reports.add(new ReportWebSocketEntity("测试报表1","1_1")
					.addParameter("name", "名称", "string", "input","sfxie")
					.addParameter("sex", "性别", "string","select", "M")
					.addParameter("age", "年纪", "string","input", "M")
					.addParameter("add", "地址", "string","input", "M")
					.addParameter("cer", "身份证", "string","input", "M")
		);
		reports.add(new ReportWebSocketEntity("测试报表2","1_2")
			.addParameter("name", "名称", "string","input", "sfxie")
		);*/
		this.group = group;

//		String[] reportName = reportControllerParameter.getReportName().split("_");
//		Integer reportId = Integer.valueOf(reportName[0]);
//		Integer version = Integer.valueOf(reportName[1]);
//		String handlerString = reportGlueLoader.loadDatasetJava(reportId, version);
	}

	@Override
	public void run() {
		while(true){
			try {
				System.out.println("group size: "+group.size());
				if(group.size() > 0 ){
					for(ReportWebSocketEntity  reportWebSocketEntity : reports){
//						reportWebSocketEntity.setReportJrxml(SerializeUtil.getFileString(this.getClass(), reportWebSocketEntity.getReportName()));
						group.writeAndFlush(new TextWebSocketFrame(JsonUtil.toJSON(reportWebSocketEntity)));
					}
				}
	        	Thread.sleep(30000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*private String getJrxmlString(String reportName) throws IOException{
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
 	  is.close();
 	  in.close();
 	   return buffer.toString();
 	}
*/
}
