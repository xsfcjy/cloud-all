package com.sfxie.component.ui.tags;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.ognl.Ognl;

import com.sfxie.core.framework.core.SpringContext;
import com.sfxie.utils.jacson.codehaus.JsonUtil;

/**
 * 下拉框生成标签
 * @TODO 
 * @author xsf
 * @since 2017年4月22日下午3:29:21
 * {@link com.golive.advert.common.tag.struts.select.SelectTag}
 */
public class SelectTag extends TagSupport {
	
	private static final long serialVersionUID = 432308349113743852L;
	
	/** 是否是文本显示   true/false*/
	private String isText;
	/** 下拉框id	*/
    private String selectId;
    /** 下拉框名称  */
    private String selectName;
    /** 下拉框数据提供者	*/
    private String dataProvider;
    /** 是否添加空值选项  	*/
    private String nullable;
    /** 空值选项显示文本	*/
    private String nullText;
    /** 值字段名	*/
    private String valueField;
    /** 显示字段名	*/
    private String displayField;
    /** 下拉框类型	*/
    private String selectType;
    /** 提供给dataProvider的参数	*/
    private String parameter;
    /** 初始值字段(从struts2的valuestack中获取值)*/
    private String value;
    /** select标签的style属性	*/
    private String style;
    /** select标签的class属性	*/
    private String className;
    /**	下拉选项option的id前缀		*/
    private String optionIdPrefix;
    /**	easyui下拉框的配置项	*/
    private String dataOptions;
    
    private Object items;
    
    
    @Override
	public int doEndTag() throws JspException {
		try{
			start(pageContext.getOut()) ;
		} catch (Exception e) {
			return super.doStartTag();
		}
		return super.doEndTag();
	}
    
    
    @SuppressWarnings({"rawtypes","unchecked"})
    public void start(Writer writer) {
		try {
			SelectUIDataProvider dataProvider = (SelectUIDataProvider) SpringContext.getBeanByName(this.getDataProvider());
			SelecteDataProviderParameter dataProviderParameter = dataProvider.mappingParameter(generateParameter());
			List<?> dataList = null;
			if(null!=items){
				dataList = (List<?>) items;
			}else{
				dataList =  (List<?>) dataProvider.query(dataProviderParameter);
			}
			writeSelect(dataList,writer);
		}catch(Exception e){
			e.printStackTrace();
		}
    }

	private void writeSelect(List<?> dataList,Writer writer) throws Exception{
		if(null!=isText && isText.equals("true")){
			for(Object object : dataList){
				String stackValue = Ognl.getValue(this.getValueField(), object).toString();
				String stackText = Ognl.getValue(this.getDisplayField(), object).toString();
				String elementValue = value;
				if(null!= elementValue && elementValue.equals(stackValue)){
					writer.write(stackText);
					break;
				} 
			}
		}else{
			StringBuffer sb = new StringBuffer();
			sb.append("<select ");
			if(StringUtils.isNotEmpty(this.getClassName())){
				sb.append("class=\"").append(this.getClassName()).append("\" ");
			}
			if(StringUtils.isNotEmpty(this.getStyle())){
				sb.append("style=\"").append(this.getStyle()).append("\" ");
			}
			if(StringUtils.isNotEmpty(this.getDataOptions())){
				sb.append("data-options=\"").append(this.getDataOptions()).append("\" ");
			}
			if(StringUtils.isNotEmpty(this.getSelectId())){
				sb.append("id=\"").append(this.getSelectId()).append("\" ");
			}
			if(StringUtils.isNotEmpty(this.getSelectName())){
				sb.append("name=\"").append(this.getSelectName()).append("\" ");
			}
			if(StringUtils.isNotEmpty(this.getValue())){
				sb.append("value=\"").append(this.getValue()).append("\" ");
			}
			sb.append(">");
			List<OptionRecord> optionRecordList = new ArrayList<OptionRecord>();
			//如果标签设置了可空,则添加可空选择项
			if(null!=getNullable() && getNullable().equals("true")){
				sb.append("<option id=\""+(null!=optionIdPrefix?optionIdPrefix:"optionTagId")+"null"+"\" value=\"").append("\"");
				sb.append(">");
				sb.append(getNullText())
				.append("</option>");
			}
			for(Object object : dataList){
				String stackValue = Ognl.getValue(this.getValueField(), object).toString();
				String stackText = Ognl.getValue(this.getDisplayField(), object).toString();
				String elementValue = value;
				sb.append("<option id=\""+(null!=optionIdPrefix?optionIdPrefix:"optionTagId")+stackValue+"\" value=\"").append(stackValue).append("\"");
				if(null!= elementValue && elementValue.equals(stackValue)){
					sb.append(" selected = \"selected\" ");
				} 
				OptionRecord optionRecord = new OptionRecord();
				optionRecord.setId("optionId"+stackValue);
				optionRecord.setRecord(object);
				sb.append(">");
				sb.append(stackText)
				.append("</option>");
			}
			sb.append("</select>");
			sb.append(getScriptString(optionRecordList));
			writer.write(sb.toString());
		}
		//添加Map元素
//		m.put("team1", team1);
//		m.put("team2", team2);
//		try {
//		System.out.println(Ognl.getValue("team1.teamname", m));
//		System.out.println(Ognl.getValue("team2.person.name", m));
	}
	
	class OptionRecord {
			
			private String id;
			
			private Object record;
			
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public Object getRecord() {
				return record;
			}
			public void setRecord(Object record) {
				this.record = record;
			}
			
			
		}

	
	private String getScriptString(List<?> optionRecordList){
		
		StringBuffer sb = new StringBuffer();
		sb.append(" <script type=\"text/javascript\">")
		  .append("eval('"+"$(document).ready(function(){")
		  .append(" var optionRecordList = ").append(JsonUtil.toJSON(optionRecordList)).append(";")
		  .append("for(var i=0;i<optionRecordList.length;i++){")
		  .append("	var record = optionRecordList[i];")
		  .append("	$(\"#\"+record[\"id\"]).data(\"record\",record);")
		  .append("}")
		  .append("});"+"\');")
		  .append(" </script>");
		return sb.toString();
		
	}
	
    

	public String getSelectId() {
		return selectId;
	}

	public void setSelectId(String selectId) {
		this.selectId = selectId;
	}

	public String getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(String dataProvider) {
		this.dataProvider = dataProvider;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getNullText() {
		return nullText;
	}

	public void setNullText(String nullText) {
		this.nullText = nullText;
	}

	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	public String getDisplayField() {
		return displayField;
	}

	public void setDisplayField(String displayField) {
		this.displayField = displayField;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public SelecteDataProviderParameter generateParameter() {
		SelecteDataProviderParameter dataProviderParameter = new SelecteDataProviderParameter();
		return dataProviderParameter;
//		return null==parameter?"":parameter;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getSelectName() {
		return selectName;
	}

	public void setSelectName(String selectName) {
		this.selectName = selectName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getOptionIdPrefix() {
		return optionIdPrefix;
	}

	public void setOptionIdPrefix(String optionIdPrefix) {
		this.optionIdPrefix = optionIdPrefix;
	}

	public String getIsText() {
		return isText;
	}

	public void setIsText(String isText) {
		this.isText = isText;
	}

	public String getDataOptions() {
		return dataOptions;
	}

	public void setDataOptions(String dataOptions) {
		this.dataOptions = dataOptions;
	}

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
	}
	
	

}