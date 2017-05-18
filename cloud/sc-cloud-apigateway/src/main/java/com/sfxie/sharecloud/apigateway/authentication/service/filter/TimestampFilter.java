package com.sfxie.sharecloud.apigateway.authentication.service.filter;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sfxie.core.framework.mvc.exception.BusinessException;
import com.sfxie.sharecloud.apigateway.authentication.Constants;
import com.sfxie.sharecloud.apigateway.authentication.entity.AuthenticationModel;

/**
 * 判断时间戳是否有效过滤器
 * 
 * @author xiesf
 * @since 2016-08-08
 */
@AuthFilterAnnotatioin(order = 20)
@Component
public class TimestampFilter extends AbstractAuthFilter {

	@Resource
	protected Constants constants;

	@Override
	public void filter(AuthenticationModel authenticationModel,AuthFilterChain authFilterChain) throws BusinessException {
		String ts = authenticationModel.getParameters().get("ts").get(0).toString();
		// 验证时间戳参数是否有效(ts与当前时间比较，当小于第二个参数的值则有效)
		validateTimeStamp(ts, this.constants.getAccessTimeout());
		authFilterChain.doFilter(authenticationModel);
	}
	/**
	 * 验证时间戳参数是否有效
	 * 
	 * @param timestamp
	 *            时间戳参数
	 * @param validSecord
	 *            有效时间范围(单位秒)
	 * @throws BusinessException
	 */
	private  void validateTimeStamp(Object timestamp, int validSecord)
			throws BusinessException {
		// 设置时间戳
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		// 当前时间戳
		Long currentTs = calendar.getTimeInMillis();
		
		long t = 0;
		try{
			t= Long.parseLong(String.valueOf(timestamp));
		}catch(NumberFormatException e){
			throw new BusinessException(this.msgErrorAuth.getTimeFormat(), this.codeErrorAuth.getTimeFormat());
		}
		// 请求的有效期
		if ((currentTs - t) > validSecord * 1000) {
			throw new BusinessException(this.msgErrorAuth.getTimeout(), this.codeErrorAuth.getTimeout());
		}
	}
	
}
