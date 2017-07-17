package com.sfxie.web.tag.menu;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.sfxie.component.node.NodeComponent;
import com.sfxie.component.node.ParentNode;
import com.sfxie.component.ui.i18n.I18NUtil;
import com.sfxie.core.framework.core.SpringContext;


public class MenuTag extends TagSupport {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Override
	public int doEndTag() throws JspException {
		MenuRestClient menuRestClient = SpringContext.getBeanByClass(MenuRestClient.class);
		List<ParentNode> menus = menuRestClient.getMenus(); 
		I18NUtil i18NUtil = SpringContext.getBeanByClass(I18NUtil.class);
		try {
			StringBuffer level1String = new StringBuffer("");
			for(int i=0;i<menus.size();i++){
				NodeComponent menuComponent = menus.get(i);
				level1String.append(getLevel1Menu(i18NUtil.getMessage(menuComponent.getName()),menuComponent.getUrl(),""));
				List<NodeComponent> childNodes = menuComponent.getNodes();
				if(null!=childNodes){
					int length = childNodes.size();
					for(int j=0;j<childNodes.size();j++){
						NodeComponent children = childNodes.get(j);
						level1String.append(getLevel2Menu(i18NUtil.getMessage(children.getName()),children.getUrl(),""));
						if(j==(length-1)){
							level1String.append("</div>");
						}
						
					}
				}
			}
			try {
				pageContext.getOut().write(level1String.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			return super.doStartTag();
		}
		return super.doEndTag();
	}
	
	private String getLevel1Menu(String title,String url,String iconCls){
		String level1 = "<div title=\"{0}\" data-options=\"iconCls:''"+iconCls+"''\" style=\"width:100%;\">";
		return MessageFormat.format(level1, title , url , iconCls);
	}
	private String getLevel2Menu(String title,String url,String iconCls){
		String ddd = "<div class=\"golive-menu-level-2\" menuTitle=\"{0}\" href=\"{1}\" onclick=\"openMenu(this);\">"+
					 "		<div class=\"golive-menu-level-title\">{0}</div>"+
					 "		<div class=\"{2}\"></div>"+
					 "</div>";
		return MessageFormat.format(ddd, title , url , iconCls);
	}
}
