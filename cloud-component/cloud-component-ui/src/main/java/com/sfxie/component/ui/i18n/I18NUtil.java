package com.sfxie.component.ui.i18n;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 获取spring 环境中的i18n国际化资源
 * @author xiesf
 * @since 2017-07-07
 *
 */
@Component
public class I18NUtil  {
	
	@Autowired
	private MessageSource messageSource;
	
	private String getMsg(String code){
		Locale locale = LocaleContextHolder.getLocale();
		try {
			return messageSource.getMessage(code, null,locale);
		} catch (NoSuchMessageException e) {
			return code;
		}
	}
	
	/**
	 * 获取国际化资源
	 * @param code
	 * 			资源代码
	 * @return
	 * 			返回资源名称
	 */
	public String getMessage(String code){
		return getMsg(code);
	}
}
