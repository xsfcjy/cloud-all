package com.sfxie.component.ui;

import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.sfxie.core.framework.core.SpringContext;

/**
 * 网页资源生成标签
 * 
 * @TODO
 * @author xsf
 * @since 2017年4月22日下午3:29:21
 *        {@link com.sfxie.component.ui.tags.select.golive.advert.common.tag.struts.select.SelectTag}
 */
public class ResourceLoaderTag extends TagSupport {

	private static final long serialVersionUID = 432308349113743852L;

	@Override
	public int doEndTag() throws JspException {
		try {
			start(pageContext.getOut(),pageContext);
		} catch (Exception e) {
			return super.doStartTag();
		}
		return super.doEndTag();
	}

	public void start(Writer writer,PageContext pageContext) {
		ResourceLoader resourceLoader = (ResourceLoader) SpringContext
				.getBeanByClass(ResourceLoader.class);
		try {
			resourceLoader.writeResource(writer,pageContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}