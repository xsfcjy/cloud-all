package com.sfxie.component.ui.tags.report;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.sfxie.component.ui.tags.report.netty.ReportWebSocketEntity;
import com.sfxie.component.ui.tags.report.netty.client.ReportWebSocketClient;
import com.sfxie.component.ui.tags.report.netty.client.ReportWebSocketClientHandlerCallback;
import com.sfxie.utils.jacson.codehaus.JsonUtil;


/**
 * ireprot报表和easyui表格datagrid控件结合使用的标签
 * @TODO 
 * @author xsf
 * 2017年5月19日下午4:01:56
 * {@link com.sfxie.component.ui.tags.report.IReportTag}
 */
public class IReportTag extends TagSupport {
	private static final long serialVersionUID = 432308349113743852L;

	/**	查询条件表单id	*/
	private String queryFormId;
	/**	报表模块的处理前的处理接口 ({@link com.golive.report.IReportPreDealer})*/
	private String reportDealer;
	/**	页面北边区域的style属性	*/
	private String northStyle;
	/**	页面北边区域内查询区域的style属性	*/
	private String queryAreaStyle;
	/**	页面北边区域内分页栏区域的style属性	*/
	private String paginationAreaStyle;
	
	/**	页面北边区域内分页栏区域的style属性	*/
	private String autoQuery;
	/**	easyui文件根目录	*/
	private String easyUIRootPath;
	/** 报表模板存放路径	*/
	private String templatePath;
	/** html查询处理url */
	private String url;
	/** 导出文件处理url */
	private String exportUrl;

	private String debug = "false";
	
	private static String jsString = null;
	private static String htmlString =null;
	
	@Override
	public int doEndTag() throws JspException {
		String contextPath = pageContext.getRequest().getServletContext().getContextPath();
		String dir = pageContext.getRequest().getServletContext().getRealPath("/");
		if(dir.endsWith(File.separator))
			dir = dir.substring(0, dir.lastIndexOf(File.separator));
//		E:\git\fork\third\cloud-all\cloud-web\sc-web-center\src\main\webapp\
		watchReportTemplates(dir);
		try{
			start(pageContext.getOut(),contextPath) ;
		} catch (Exception e) {
			return super.doStartTag();
		}
		return super.doEndTag();
	}
	
	public void start(Writer writer,String contextPath) {
		
		try {
			if(null==northStyle || northStyle.equals("")){
				northStyle = " style=\"height:100px;\"";
			}
			if(null==queryAreaStyle || queryAreaStyle.equals("")){
				queryAreaStyle = " style=\"vertical-align: middle;height: 65%;\"";
			}
			if(null==paginationAreaStyle || paginationAreaStyle.equals("")){
				paginationAreaStyle = " style=\"vertical-align: middle;height: 35%;\"";
			}
			InputStream i = this.getClass().getResource("report.resource.js").openStream();
			if(null==jsString)
				jsString = inputStream2String(i);
			if(debug.equals("true"))
				jsString = inputStream2String(i);
			
			String js = jsString.replaceAll("\\$\\{ctx\\}", contextPath);
			writer.write(js.replaceAll("\\$\\{queryFormId\\}", queryFormId)
							.replaceAll("\\$\\{url\\}", url)
							.replaceAll("\\$\\{exportUrl\\}", exportUrl)
							.replaceAll("\\$\\{easyUIRootPath\\}", easyUIRootPath)
							.replaceAll("\\$\\{reportEntityMap\\}", getReportEntityMapString())
					     );
			if(null==htmlString)
				htmlString = inputStream2String(this.getClass().getResourceAsStream("report.html.txt"));
			if(debug.equals("true"))
				htmlString = inputStream2String(this.getClass().getResourceAsStream("report.html.txt"));
			writer.write(htmlString
					      .replaceAll("\\$\\{northStyle\\}", northStyle)
				          .replaceAll("\\$\\{queryAreaStyle\\}", queryAreaStyle)
				          .replaceAll("\\$\\{paginationAreaStyle\\}", paginationAreaStyle)
				          .replaceAll("\\$\\{ctx\\}", contextPath)
				          .replaceAll("\\$\\{reportSelectedList\\}", getReportSelectedList()));
			
			if(null==autoQuery || autoQuery.equals("true")){
				writer.write(getAutoQueryScript());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * 生成报表下拉选项
	 * @return
	 */
	private String getReportSelectedList(){
		StringBuffer sb = new StringBuffer();
		Map<String, ReportWebSocketEntity> reportsMap = ReportWebSocketClient.getInstance().getReports();
		for(String key : reportsMap.keySet()){
			ReportWebSocketEntity report = reportsMap.get(key);
			String option = "<option value=\""+report.getReportName()+"\">"+report.getReportText()+"</option>";
			sb.append(option);
		}
		return sb.toString();
	}

	/**
	 * 获取自动查询js代码
	 * xsf
	 * 2017年5月19日下午1:23:26
	 * TODO
	 * String
	 */
	public String getAutoQueryScript(){
		StringBuffer sb = new StringBuffer();
		sb.append("doHtmlReport(\"single\");");
		return getEvalScriptForJqueryOnReady(sb.toString());
	}
	public static String inputStream2String(InputStream is) throws IOException{
	   BufferedReader in = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	   StringBuffer buffer = new StringBuffer();
	   String line = "";
	   while ((line = in.readLine()) != null){
	     buffer.append(line);
	   }
	   is.close();
	   return buffer.toString();
	}
	/**
	 * 获取返回到前台可自动执行的jquery的 ready内的js代码
	 * @param jsString	自定义可执行的js内容
	 * @return
	 */
	private static String getEvalScriptForJqueryOnReady(String jsString){
		StringBuffer sb = new StringBuffer();
		sb.append(" <script type=\"text/javascript\">")
		  .append("eval('"+"$(document).ready(function(){")
		  .append(jsString)
		  .append("});"+"\');")
		  .append(" </script>");
		return sb.toString();
	}
	public String getQueryFormId() {
		return queryFormId;
	}

	public void setQueryFormId(String queryFormId) {
		this.queryFormId = queryFormId;
	}

	public String getReportDealer() {
		return reportDealer;
	}

	public void setReportDealer(String reportDealer) {
		this.reportDealer = reportDealer;
	}

	public String getNorthStyle() {
		return northStyle;
	}

	public void setNorthStyle(String northStyle) {
		this.northStyle = northStyle;
	}

	public String getQueryAreaStyle() {
		return queryAreaStyle;
	}

	public void setQueryAreaStyle(String queryAreaStyle) {
		this.queryAreaStyle = queryAreaStyle;
	}

	public String getPaginationAreaStyle() {
		return paginationAreaStyle;
	}

	public void setPaginationAreaStyle(String paginationAreaStyle) {
		this.paginationAreaStyle = paginationAreaStyle;
	}

	public String getAutoQuery() {
		return autoQuery;
	}

	public void setAutoQuery(String autoQuery) {
		this.autoQuery = autoQuery;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEasyUIRootPath() {
		return easyUIRootPath;
	}

	public void setEasyUIRootPath(String easyUIRootPath) {
		this.easyUIRootPath = easyUIRootPath;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	/**
	 * 实时更新报表模板
	 * @param contextPath
	 */
	public void watchReportTemplates(String contextPath){
		if(!ReportWebSocketClient.getInstance().isStart()){
			ReportFactory.getInstance().setTemplatePath(templatePath);
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(contextPath+(null!=templatePath?templatePath:""));
						ReportWebSocketClientHandlerCallback  callback = new ReportWebSocketClientHandlerCallback(){
							@Override
							public void callback() {
								
							}};
							ReportWebSocketClient.getInstance().start(contextPath+(null!=templatePath?templatePath:""),
									callback	);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			t.start();
		}
	}
	
	private String getReportEntityMapString(){
//		${reportEntityMap}
		Map<String,ReportWebSocketEntity> reportJsMap = new HashMap<String,ReportWebSocketEntity>();
		for(String key : ReportWebSocketClient.getInstance().getReports().keySet()){
			ReportWebSocketEntity t =  ReportWebSocketClient.getInstance().getReports().get(key);
			ReportWebSocketEntity entity = new ReportWebSocketEntity();
			entity.setReportName(t.getReportName());
			entity.setReportText(t.getReportText());
			entity.addParameter(t.getParameters());
			reportJsMap.put(key, entity);
		}
		String queryParameters = JsonUtil.toJSONNotUnicode(reportJsMap);
//		System.out.println(queryParameters);
		return queryParameters;
	}

	public String getDebug() {
		return debug;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}

	public String getExportUrl() {
		return exportUrl;
	}

	public void setExportUrl(String exportUrl) {
		this.exportUrl = exportUrl;
	}
	
	
    
}