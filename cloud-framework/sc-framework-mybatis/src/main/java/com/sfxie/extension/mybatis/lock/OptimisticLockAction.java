package com.sfxie.extension.mybatis.lock;
/**
 * 乐观锁执行者
 * @author Administrator
 *
 */
public interface OptimisticLockAction {
	public void action() throws Exception;
}
