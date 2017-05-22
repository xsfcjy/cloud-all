package com.sfxie.component.ui.tags.report.copy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


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
	
	private String easyUIRootPath;
	
	@Override
	public int doEndTag() throws JspException {
		try{
			start(pageContext.getOut(),pageContext.getRequest().getServletContext().getContextPath()) ;
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
			String dd = inputStream2String(i);
			String js = dd.replaceAll("\\$\\{ctx\\}", contextPath);
			writer.write(js.replaceAll("\\$\\{queryFormId\\}", queryFormId)
					       .replaceAll("\\$\\{reportDealer\\}", reportDealer)
						   .replaceAll("\\$\\{easyUIRootPath\\}", easyUIRootPath)
					     );
			writer.write(inputStream2String(this.getClass().getResourceAsStream("report.html.txt"))
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
	
	private String getReportSelectedList(){
		StringBuffer sb = new StringBuffer();
		String option = "<option value=\"1\">测试报表生成</option>";
		sb.append(option);
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

	public String getEasyUIRootPath() {
		return easyUIRootPath;
	}

	public void setEasyUIRootPath(String easyUIRootPath) {
		this.easyUIRootPath = easyUIRootPath;
	}

    
}