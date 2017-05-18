package com.sfxie.component.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 返回到客户端的结果类
 * @author xiesf
 * @since 2017-05-01
 * @param <T>
 */
public class Result<T> {

	@JsonInclude(Include.NON_NULL)
	private T data;

	@JsonInclude(Include.NON_NULL)
	private Integer pageSize;

	@JsonInclude(Include.NON_NULL)
	private Integer pageNumber;

	@JsonInclude(Include.NON_NULL)
	private Long total;


	public Result() {
		super();
	}

	public Result(T data) {
		super();
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
