package com.sfxie.extension.mybatis.lock;

import com.sfxie.extension.mybatis.exception.OptimisticLockOutOfTimesException;

/**
 * 乐观锁执行容器
 * @author sfxie
 *
 */
public class OptimisticLockContainer {

	public void execute(int retryTimes,OptimisticLockAction action) throws OptimisticLockOutOfTimesException{
			try {
				action.action();
			} catch (Exception e) {
				while(retryTimes>0){
					for(;retryTimes>0;retryTimes--){
						try {
							action.action();
							return ;
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					throw new OptimisticLockOutOfTimesException("执行数次超过，请重新执行。");
				}
			}
//		for(){
//			
//		}
	}
}
