package com.sfxie.component.ui.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 下拉框生成标签
 * @TODO 
 * @author xsf
 * @since 2017年4月22日下午3:29:21
 * {@link com.golive.advert.common.tag.struts.select.SelectTag}
 */
public class EasyUITag extends TagSupport {
	
	private static final long serialVersionUID = 432308349113743852L;
	
    private String grid;
    
    private String queryForm;
    
    private String editWindow;
    
    private String columnDecoratedUrl;
    
//    private String addFunction;
//    
//    private String delFunction;
//    
//    private String modifyFunction;
//    
//    private String queryFunction;
    
    
    @Override
	public int doEndTag() throws JspException {
		try{
		} catch (Exception e) {
			return super.doStartTag();
		}
		return super.doEndTag();
	}
    
    
	
	

}