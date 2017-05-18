package com.sfxie.sharecloud.apigateway.authentication.service.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import com.sfxie.core.framework.core.SpringContext;
import com.sfxie.core.framework.logger.log4j.LoggerUtil;
import com.sfxie.core.framework.mvc.exception.BusinessException;
import com.sfxie.sharecloud.apigateway.authentication.entity.AuthenticationModel;

/**
 * 鉴权过滤器链
 * @author xiesf
 *
 */
@ConditionalOnBean(value={SpringContext.class})
@Component
public class AuthFilterChain implements InitializingBean{
	
	private ThreadLocal<Integer> next = new ThreadLocal<Integer>();
	
	private static List<AbstractAuthFilter> filters = new ArrayList<AbstractAuthFilter>();
	/**
	 * 开始执行过滤器集合
	 * @param authenticationModel
	 */
	public void startFilter(AuthenticationModel authenticationModel){
		Long start = System.currentTimeMillis();
		clear();
		doFilter(authenticationModel);
		Long end = System.currentTimeMillis();
		LoggerUtil.console(AuthFilterChain.class,authenticationModel.getHeaders().get("_$URL")+"["+authenticationModel.getHeaders().get("_$URI")+"]"+"总共花费："+(end-start)+"毫秒");
	}
	/**
	 * 执行下一个动作
	 * @param authenticationModel
	 * @throws BusinessException
	 */
	public void doFilter(AuthenticationModel authenticationModel) throws BusinessException{
		int index = getIndex();
		if(index==filters.size()){
			return ;
		}
		filters.get(index).filter(authenticationModel, this);
	}
	/**
	 * 清除当前线程的计数
	 */
	private void clear(){
		next.remove();
	}
	
	private int getIndex(){
		if(null==next.get()){
			next.set(0);
		}else{
			 next.set(next.get()+1);
		}
		return  next.get();
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, AbstractAuthFilter> filtersMap = SpringContext.getBeansOfType(AbstractAuthFilter.class);
		for(String key : filtersMap.keySet() ){
			AbstractAuthFilter filter = filtersMap.get(key);
			AuthFilterAnnotatioin a = filter.getClass().getAnnotation(AuthFilterAnnotatioin.class);
			if(null==a){
				throw new Exception(key+" 过滤器的AuthFilterAnnotatioin注解不能为空，请重新设置！");
			}else{
				if(a.enable()){
					filter.order = a.order();
					filters.add(filter);
				}
			}
		}
		Collections.sort(filters);  
		System.out.println(filters);
	}
	 
}
