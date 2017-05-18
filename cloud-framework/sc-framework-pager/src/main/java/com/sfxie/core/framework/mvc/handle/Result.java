package com.sfxie.core.framework.mvc.handle;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.pagehelper.Page;
import com.sfxie.core.framework.mvc.exception.MvcException;

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

	/**
	 * 构造Object返回值对象
	 * @param builder
	 */
	public Result(BuilderObject<T> builder) {
		this.data = builder.data;
	}
	/**
	 * 构造Array返回值对象
	 * @param builder
	 */
	public Result(BuilderArray<T> builder) {
		this.data = builder.data;
		this.pageSize = builder.pageSize;
		this.pageNumber = builder.pageNumber;
		this.total = builder.total;
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

	public static class BuilderObject<T> {

		private T data;


		/**
		 * 构造对象返回值
		 * @param data
		 */
		public BuilderObject(T data) {
			this.data = data;
		}

		public Result<T> build() {
			return new Result<T>(this);
		}

	}
	public static class BuilderArray<T> {

		private T data;

		private Integer pageSize;

		private Integer pageNumber;

		private Long total;
		/**
		 * 构造数组形式返回值
		 * @param data
		 */
		public BuilderArray(T data) {
			this.data = data;
			if(data instanceof Page)
				generatePageInfo();
		}

		private BuilderArray<T> generatePageInfo() {
			if (!(data instanceof List))
				throw new MvcException("data不是List!!");
			Page<?> page = ((Page<?>) data);
			this.pageNumber = page.getPageNum();
			this.pageSize = page.getPageSize();
			this.total = page.getTotal();
			return this;
		}

		public BuilderArray<T> pageNumber(Integer pageNumber) {
			this.pageNumber = pageNumber;
			return this;
		}

		public BuilderArray<T> pageSize(Integer pageSize) {
			this.pageSize = pageSize;
			return this;
		}

		public BuilderArray<T> total(Long total) {
			this.total = total;
			return this;
		}

		public Result<T> build() {
			return new Result<T>(this);
		}

	}
}
