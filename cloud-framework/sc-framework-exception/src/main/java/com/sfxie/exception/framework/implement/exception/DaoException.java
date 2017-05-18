package com.sfxie.exception.framework.implement.exception;


/**
 * Dao层异常类
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015-7-14上午10:12:07
 */
public class DaoException extends MvcException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DaoException(String errorCode,Throwable t){
		super(errorCode,t);
	}
	public DaoException(String errorCode,Exception t){
		super(errorCode,t);
	}
	public DaoException(Exception t){
		super(t);
	}
	public DaoException(Throwable t){
		super(t);
	}
	public DaoException(String t){
		super(t);
	}
	
	/**
	 * 初始化Dao层异常处理EventHandler
	 */
	/*protected void initEventListener(){
		final DaoException e = this;
		this.addEventListener(new DaoExceptionEventHandler(){
			@Override
			public void handleEvent(DaoExceptionEvent event) {
				System.out.println("DaoExceptionEvent start");
				System.out.println(e.getLocalizedMessage());
				System.out.println("DaoExceptionEvent end");
			}
		});
	}*/
}
