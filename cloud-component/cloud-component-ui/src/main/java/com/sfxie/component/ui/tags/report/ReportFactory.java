package com.sfxie.component.ui.tags.report;

import groovy.lang.GroovyClassLoader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.JRQueryChunk;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.views.jasperreports.JasperReportConstants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;

import com.sfxie.component.ui.tags.report.netty.ReportParameter;
import com.sfxie.component.ui.tags.report.netty.client.ReportWebSocketClient;
import com.sfxie.core.framework.core.SpringContext;
import com.sfxie.utils.DateHelper;
import com.sfxie.utils.ObjectUtil;
import com.sfxie.utils.jacson.codehaus.JsonUtil;

public class ReportFactory implements JasperReportConstants,ApplicationContextAware {
	
	public static final String FORMAT_DOC = "DOC";
	public static final String FORMAT_DOCX = "DOCX";
	public static final String FORMAT_XLSX = "XLSX";
    private final static Logger LOG = null;
    
    
    private String templatePath;
    
    /**
	 * groovy class loader
	 */
	private GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doReport(ReportControllerParameter reportControllerParameter,HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> reportParameter =  (Map<String, Object>) buildMapParameter(reportControllerParameter);
//		String handlerString = SerializeUtil.getFileString( "ListReportHandler.txt",this.getClass());
//		IReportHandler reportHandler = (IReportHandler) SpringContext.getBeanByName("");
		String reportName = reportControllerParameter.getReportName();
//		Integer reportId = Integer.valueOf(reportName[0]);
//		Integer version = Integer.valueOf(reportName[1]);
		String handlerString = ReportWebSocketClient.getInstance().getReports().get(reportName).getDatasetJava();
		IReportHandler reportHandler = loadNewInstance(handlerString);
		Object dataSource = reportHandler.dataset(reportParameter);
		doExecute(dataSource,request,response, reportControllerParameter,reportParameter);
	}
	
	protected void doExecute(Object dataSource,HttpServletRequest request,HttpServletResponse response,ReportControllerParameter reportControllerParameter ,Map<String,Object> reportParameter) throws Exception {
        // Will throw a runtime exception if no "datasource" property. TODO Best place for that is...?
        String format = reportControllerParameter.getExportFileType();
        String stencilName = reportControllerParameter.getReportName();
        String imageServletUrl = "";
        
//        if (LOG.isDebugEnabled()) {
//            LOG.debug("Creating JasperReport for dataSource = " + dataSource + ", format = " + format);
//        }

//        response.setCharacterEncoding("utf-8");
        String reportTemplatePath = templatePath;
        String dirPath = request.getSession().getServletContext().getRealPath("/")+reportTemplatePath;
        // Handle IE special case: it sends a "contype" request first.
        // TODO Set content type to config settings?
        if ("contype".equals(request.getHeader("User-Agent"))) {
            try {
                response.setContentType("application/pdf");
                response.setContentLength(0);

                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.close();
            } catch (IOException e) {
                LOG.error("Error writing report output", e);
                throw new ServletException(e.getMessage(), e);
            }
            return;
        }

        // Construct the data source for the report.
//        ValueStackDataSource stackDataSource = null;
        
        JRDataSource jrdataSource = null;
        Connection conn = null;

        Map parameters = new HashMap();
        int listSize = ((List<?>) dataSource).size();
        
      //设置是否忽略分页
        if (null!=reportParameter.get("isIgnorePage") && !new Boolean(reportParameter.get("isIgnorePage").toString()) ) {
        	parameters.put("IS_IGNORE_PAGINATION", Boolean.TRUE);
        	
        	int start = 0;
            int end = 0;
            //设置mysql分页参数(limit start, end)
            if(reportControllerParameter.getPageNumber() > 0 && reportControllerParameter.getPageSize() > 0){
            	int pageSize = reportControllerParameter.getPageSize();
            	int pageNumber = reportControllerParameter.getPageNumber();
            	start = pageSize*(pageNumber-1);
            	end = pageSize*(pageNumber);
            	reportParameter.put("start",start);
            	reportParameter.put("end", end);
            }
            
            dataSource = ((List<?>) dataSource).subList(start, end>listSize ? listSize : end);
        }
        
        if (!(dataSource instanceof Connection)){
        	jrdataSource =  new JRBeanCollectionDataSource((Collection<?>) dataSource);
        }else{
        	conn = (Connection) dataSource;
        }


        // Determine the directory that the report file is in and set the reportDirectory parameter
        // For WW 2.1.7:
        //  ServletContext servletContext = ((ServletConfig) invocation.getInvocationContext().get(ServletActionContext.SERVLET_CONFIG)).getServletContext();
//        ServletContext ervletContext = (ServletContext) invocation.getInvocationContext().get(ServletActionContext.SERVLET_CONTEXT);
//        String systemId = servletContext.getRealPath(finalLocation);
        String systemId = dirPath +File.separator+ stencilName;
//        File directory = new File(systemId.substring(0, systemId.lastIndexOf(File.separator)));
        File directory = new File(dirPath);
        parameters.put("reportDirectory", directory);
        parameters.put(JRParameter.REPORT_LOCALE, request.getLocale());
        parameters.put("SUBREPORT_DIR", dirPath);
        
        // put timezone in jasper report parameter
        /*if (timeZone != null) {
            timeZone = conditionalParse(timeZone, invocation);
            final TimeZone tz = TimeZone.getTimeZone(timeZone);
            if (tz != null) {
                // put the report time zone
                parameters.put(JRParameter.REPORT_TIME_ZONE, tz);
            }
        }*/


        
        // Add any report parameters from action to param map.
        parameters.putAll(reportParameter);

        byte[] output;
        JasperPrint jasperPrint;

        // Fill the report and produce a print object
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(systemId));
        try {
            if (conn == null)
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrdataSource);
            else
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
        } catch (Exception e) {
        	e.printStackTrace();
            LOG.error("Error building report for uri " + systemId, e);
            throw new ServletException(e.getMessage(), e);
        }
        // Export the print object to the desired output format
        try {
            
            String fileName = "";
            if(StringUtils.isNotEmpty(reportControllerParameter.getReportTitle())){
          	  fileName = new String(reportControllerParameter.getReportTitle().getBytes("gb2312"), "ISO8859-1")+"."+format.toLowerCase(); 
          	  response.setHeader("Content-disposition",
          			  "attachment;filename="+ fileName);
          	  
            }
            
            JRExporter exporter;

            if (format.equals(FORMAT_PDF)) {
                response.setContentType("application/pdf");
                exporter = new JRPdfExporter();
            } else if (format.equals(FORMAT_CSV)) {
                response.setContentType("text/csv");
                exporter = new JRCsvExporter();
            } else if (format.equals(FORMAT_HTML)) {

                Map imagesMap = new HashMap();
                request.getSession(true).setAttribute("IMAGES_MAP", imagesMap);

//                exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
//                exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
//                exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
//                exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME,outPutDir);
//                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,sourceDir); 
//                System.out.println(request.getContextPath()+": "+request.getContextPath());
                exporter = new JRHtmlExporter();
                exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, imagesMap);
                exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + imageServletUrl);

                // Needed to support chart images:
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                request.getSession().setAttribute("net.sf.jasperreports.j2ee.jasper_print", jasperPrint);
            } else if (format.equals(FORMAT_XLS)) {
                response.setContentType("application/x-octet-stream");
                exporter = new JRXlsExporter();
            }else if (format.equals(FORMAT_XLSX)) {
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                exporter = new JRXlsxExporter();
            } else if (format.equals(FORMAT_XML)) {
                response.setContentType("text/xml");
                exporter = new JRXmlExporter();
            } else if (format.equals(FORMAT_RTF)) {
                response.setContentType("application/rtf");
                exporter = new JRRtfExporter();
            } else if(format.equals(FORMAT_DOCX)){
            	exporter = new JRRtfExporter();
            	response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.template");
//        		response.setHeader("Content-disposition", "attachment; filename="
//        				+ fileName.replace(".word", "")+".docx");
            }else if(format.equals(FORMAT_DOC)){
            	exporter = new JRRtfExporter();
            	response.setContentType("application/msword");
//        		response.setHeader("Content-disposition", "attachment; filename="
//        				+ fileName.replace(".word", "")+".doc");
            } else {
                throw new ServletException("Unknown report format: " + format);
            }

            Map exportParams = new HashMap();
            if (exportParams != null) {
//                if (LOG.isDebugEnabled()) {
//                    LOG.debug("Found export parameters; adding to exporter parameters...");
//                }
                exporter.getParameters().putAll(exportParams);
            }
            int total = -1;
            if(null!=conn){
            	total = generateTotalCount(jasperReport,parameters,conn); 
            }else if(dataSource instanceof List){
            	total = listSize;
            }else if(dataSource instanceof Object){
            	total = 1;
            }
            
//            if(null!=reportParameter.get("exportDataType") && reportParameter.get("exportDataType").toString().equals("all")){
            reportParameter.put("total", total);
//            }else{
//            	reportParameter.put("total",reportParameter.get("pageSize"));
//            }
            
            if(format.equals(FORMAT_HTML)){
            	String data = exportReportToStringBuffer(jasperPrint, exporter);
        		writeReport(format,reportParameter,response, data);
            }else{
            	
            	output = exportReportToBytes(jasperPrint, exporter);
//            if(null!=reportParams.get("saveInLocal") && reportParams.get("saveInLocal").equals("true")){
//            	String saveInLocalPath = "D:\\golive\\report\\excel\\";
//            	File fs  = new File(saveInLocalPath+fileName);
//        	    FileUtils.writeByteArrayToFile(fs, output);
//            }else{
            	try{
            		response.setContentLength(output.length);
            		writeReport(format,reportParameter,response, output);
            	}catch(Exception e){
            		e.printStackTrace();
            	}
//            }
            }
        } catch (JRException e) {
            String message = "Error producing " + format + " report for uri " + systemId;
            LOG.error(message, e);
            e.printStackTrace();
            throw new ServletException(e.getMessage(), e);
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	if(conn != null && !conn.isClosed()){
        		try{
        			conn.close();
        		}catch(Exception e){
        			e.printStackTrace();
        		}
        	}
        		
        }
    }

    /**
     * Writes report bytes to response output stream.
     *
     * @param response Current response.
     * @param output   Report bytes to write.
     * @throws ServletException on stream IOException.
     */
    private void writeReport(String format,Map reportParameter,HttpServletResponse response, Object output) throws ServletException {
        ServletOutputStream outputStream = null;
        try {
            
            if(format.equals(FORMAT_HTML)){
//            	response.setContentType("text/html");
            	/*Map<String,Object> map = new HashMap<String ,Object>();
            	map.put("total", action.getIreportEntity().getTotal());
            	map.put("success","true");
            	map.put("content",output);
            	String ddd = JsonUtils.toJson(map);
            	response.setContentLength(ddd.length());*/
            	Map<String,Object> map = new HashMap<String ,Object>();
            	map.put("success","true");
            	map.put("total", reportParameter.get("total"));
            	map.put("content",output);
                response.getWriter().write(JsonUtil.toJSON(map));
                response.flushBuffer();
                response.getWriter().close();
            }else{
            	outputStream = response.getOutputStream();
                outputStream.write((byte[])output);
                outputStream.flush();
            }
        } catch (IOException e) {
            LOG.error("Error writing report output", e);
            throw new ServletException(e.getMessage(), e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                LOG.error("Error closing report output stream", e);
                throw new ServletException(e.getMessage(), e);
            }
        }
    }


    /**
     * Run a Jasper report to CSV format and put the results in a byte array
     *
     * @param jasperPrint The Print object to render as CSV
     * @param exporter    The exporter to use to export the report
     * @return A CSV formatted report
     * @throws net.sf.jasperreports.engine.JRException
     *          If there is a problem running the report
     */
    private byte[] exportReportToBytes(JasperPrint jasperPrint, JRExporter exporter) throws JRException {
        byte[] output;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
//        if (delimiter != null) {
//            exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, delimiter);
//        }

        exporter.exportReport();

        output = baos.toByteArray();

        return output;
    }

    /**
     * Run a Jasper report to CSV format and put the results in a byte array
     *
     * @param jasperPrint The Print object to render as CSV
     * @param exporter    The exporter to use to export the report
     * @return A CSV formatted report
     * @throws net.sf.jasperreports.engine.JRException
     *          If there is a problem running the report
     */
    private String exportReportToStringBuffer(JasperPrint jasperPrint, JRExporter exporter) throws JRException {
        byte[] output;
        StringBuffer sb = new StringBuffer();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sb);
//        if (delimiter != null) {
//            exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, delimiter);
//        }
        exporter.exportReport();
        return sb.toString();
    }
    /**
     * add by xieshengfeng
     * @since 2014-05-21
     */
    private byte[] deractorDocument(byte[] output){
    	ByteArrayInputStream dd = new ByteArrayInputStream(output);
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	Workbook hssfWorkbook = null ;
    	try {
    		try{
    			hssfWorkbook = new XSSFWorkbook(dd);
    		}catch(Exception E){
    			dd = new ByteArrayInputStream(output);
    			hssfWorkbook = new HSSFWorkbook(dd);
    		}
			// 循环工作表Sheet
		    for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){
		      Sheet hssfSheet = hssfWorkbook.getSheetAt( numSheet);
		      if(hssfSheet == null){
		        continue;
		      }
		      
		      // 循环行Row 
		      for(int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++){
		    	Row hssfRow = hssfSheet.getRow( rowNum);
		        if(hssfRow == null){
		          continue;
		        }
		        
		        // 循环列Cell  
		        for(int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++){
		          Cell hssfCell = hssfRow.getCell( cellNum);
		          if(hssfCell == null){
		            continue;
		          }
		          System.out.print("    " + getValue( hssfCell));
		        }
		      }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			hssfWorkbook.write(baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
    }
    
    @SuppressWarnings("static-access")  
	private String getValue(Cell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
    /**
     * 导出word
     * @author xieshengfeng
     * @param jasperPrint
     * @param defaultFilename
     * @param request
     * @param response
     * @throws JRException
     * @throws IOException
     */
    private static void exportWord(JasperPrint jasperPrint,
			String defaultFilename, HttpServletRequest request,
			HttpServletResponse response) throws JRException, IOException {
		response.setContentType("application/msword;charset=utf-8");
		String defaultname = null;
		if (defaultFilename.trim() != null && defaultFilename != null) {
			defaultname = defaultFilename + ".doc";
		} else {
			defaultname = "export.doc";
		}
		String fileName = new String(defaultname.getBytes("GBK"), "utf-8");
		response.setHeader("Content-disposition", "attachment; filename="
				+ fileName);
		JRExporter exporter = new JRRtfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response
				.getOutputStream());
		exporter.exportReport();
	}
    
	/**
	 * 如果主报表的query是sql类型,则设置总记录到IReportEntity中
	 * xsf
	 * 2015年7月3日下午4:56:22
	 * TODO
	 * void
	 */
	private int generateTotalCount(JasperReport jasperReport,Map<String,Object> parameters,Connection conn) throws SQLException{
        JRQuery  jjj = jasperReport.getQuery();
        String se = jjj.getText();
        if(jjj.getLanguage().toUpperCase().equals("SQL")){
        	JRQueryChunk[] jrQueryChunkArray = jjj.getChunks();
        	if(null!=jrQueryChunkArray && jrQueryChunkArray.length>0){
        		List<Object> paramList = new ArrayList<Object>();
        		StringBuffer sb = new StringBuffer();
        		JRParameter[] jrp = jasperReport.getParameters();
        		Map<String,JRParameter> jrParameterMap = new HashMap<String,JRParameter>();
        		for(JRParameter jRParameter : jrp){
        			jrParameterMap.put(jRParameter.getName(), jRParameter);
        		}
        		for(int i=0;i<jrQueryChunkArray.length;i++){
        			JRQueryChunk jrQueryChunk = jrQueryChunkArray[i];
        			int type = jrQueryChunk.getType();
        			String text = jrQueryChunk.getText();
        			if(type == 1){
        				if(text.toUpperCase().contains("LIMIT")){
        					text = text.replaceAll("(?i)limit", "");
        					sb.append(text);
        					break;
        				}else{
        					sb.append(text);
        				}
        			}else if(type == 2){
        				Class<?> clazz = jrParameterMap.get(text).getValueClass();
        				Object value = parameters.get(text);
        				if(null!=value){
        					paramList.add(transformParameter(value.toString(),clazz));
        				}else{
        					paramList.add(value);
        				}
        				sb.append(" ? ");
        			}
        		}
//        		.replaceAll("(?i)limit *\\? *, *\\?", "")
        		int totalCount = queryTotalCount("SELECT count(*) FROM ( "+sb.toString()+" ) a",conn,paramList);
        		return totalCount;
        	}
        }
        return -1;
	}
	private Object transformParameter(String text,Class<?> clazz){
		Object value = null;
		if(clazz.isAssignableFrom(String.class)){
			value = text;
		}else if(clazz.isAssignableFrom(Integer.class)){
			value = Integer.parseInt(text);
		}else if(clazz.isAssignableFrom(Long.class)){
			value = Long.parseLong(text);
		}else if(clazz.isAssignableFrom(Double.class)){
			value = Double.parseDouble(text);
		}else if(clazz.isAssignableFrom(Float.class)){
			value = Float.parseFloat(text);
		}else if(clazz.isAssignableFrom(Date.class)){
			value = DateHelper.parseDate(text);
		}
		return value;
	}
	
	private int queryTotalCount(String sql,Connection conn,List<Object> paramList) throws SQLException{
		PreparedStatement ps = conn.prepareStatement(sql);
		for(int i=0;i< paramList.size();i++){
			Object obj = paramList.get(i);
			ps.setObject(i+1, obj);
		}
		ResultSet rs = ps.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
        }
		rs.close();
		ps.close();
		return count;
	}
	
	
	
	private ReportParameter buildParameter(HttpServletRequest request){
		String reportParameters = request.getParameter("reportParameters");
		String reportHandlerString = request.getParameter("reportHandler");
		IReportHandler reportHandler = (IReportHandler) SpringContext.getBeanByName(reportHandlerString);
		ReportParameter reportParameter = (ReportParameter) JsonUtil.fromJSON(reportParameters, reportHandler.parameterClass);
		return reportParameter;
	}
	
	private IReportHandler<?, ReportParameter> buildReportHandler(String handler){
		IReportHandler<?, ReportParameter> reportHandler = null;
		return reportHandler;
	}
	
	private Map<String,Object> buildMapParameter(ReportControllerParameter reportControllerParameter){
		String reportParameters = reportControllerParameter.getQueryFormParameter();
		Map<String,Object> reportParameter = (Map<String,Object>) JsonUtil.fromJSON(reportParameters, Map.class);
		return reportParameter;
	}
	
	
	
	
	// ----------------------------- spring support -----------------------------
		private static ApplicationContext applicationContext;
		private static ReportFactory reportFactory;
		public static ReportFactory getInstance(){
			return reportFactory;
		}
		
		@Override
		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			ReportFactory.applicationContext = applicationContext;
			ReportFactory.reportFactory = (ReportFactory) applicationContext.getBean("reportFactory");
		}
		
		/**
		 * inject action of spring
		 * @param instance
		 */
		public void injectService(Object instance){
			if (instance==null) {
				return;
			}
		    
			Field[] fields = instance.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (Modifier.isStatic(field.getModifiers())) {
					continue;
				}
				
				Object fieldBean = null;
				// with bean-id, bean could be found by both @Resource and @Autowired, or bean could only be found by @Autowired
				if (AnnotationUtils.getAnnotation(field, Resource.class) != null) {
					try {
						Resource resource = AnnotationUtils.getAnnotation(field, Resource.class);
						if (resource.name()!=null && resource.name().length()>0){
							fieldBean = applicationContext.getBean(resource.name());
						} else {
							fieldBean = applicationContext.getBean(field.getName());
						}
					} catch (Exception e) {
					}
					if (fieldBean==null ) {
						fieldBean = applicationContext.getBean(field.getType());
					}
				} else if (AnnotationUtils.getAnnotation(field, Autowired.class) != null) {
					Qualifier qualifier = AnnotationUtils.getAnnotation(field, Qualifier.class);
					if (qualifier!=null && qualifier.value()!=null && qualifier.value().length()>0) {
						fieldBean = applicationContext.getBean(qualifier.value());
					} else {
						fieldBean = applicationContext.getBean(field.getType());
					}
				}
				
				if (fieldBean!=null) {
					field.setAccessible(true);
					try {
						field.set(instance, fieldBean);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		// ----------------------------- load instance -----------------------------
		// load new instance, prototype
		public IReportHandler loadNewInstance(String codeSource) throws Exception{
			if ( codeSource == null ||  codeSource.equals("")) {
				return null;
			}
			if (codeSource!=null && codeSource.trim().length()>0) {
				Class<?> clazz = groovyClassLoader.parseClass(codeSource);
				if (clazz != null) {
					Object instance = clazz.newInstance();
					if (instance!=null) {
						if (instance instanceof IReportHandler) {
							this.injectService(instance);
							return (IReportHandler) instance;
						} else {
							throw new IllegalArgumentException(">>>>>>>>>>> xxl-glue, loadNewInstance error, "
									+ "cannot convert from instance["+ instance.getClass() +"] to IJobHandler");
						}
					}
				}
			}
			throw new IllegalArgumentException(">>>>>>>>>>> xxl-glue, loadNewInstance error, instance is null");
		}

		public void setTemplatePath(String templatePath) {
			this.templatePath = templatePath;
		}
		
}
