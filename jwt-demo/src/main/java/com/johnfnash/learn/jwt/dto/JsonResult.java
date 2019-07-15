package com.johnfnash.learn.jwt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.johnfnash.learn.jwt.enums.StatusCodeEnum;
import com.johnfnash.learn.jwt.exception.ServiceException;

@JsonIgnoreProperties(value = {"success", "optional"})
public class JsonResult<T> {

	/**
	 * 0-success 其他-失败
	 * 见 StatusCodeEnum
	 */
	protected int code;

	protected String msg;

    protected T data;

	public JsonResult() {
		this.msg = "success";
	}

	public JsonResult(T data) {
		this();
		this.data = data;
	}

	public JsonResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	/**
	 * 静态构建方法
	 * @param
	 * @return
	 */
	public static <T> JsonResult<T> of() {
		return new JsonResult<T>(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMsg());
	}
	
	/**
	 * 静态构建方法
	 * @param data
	 * @return
	 */
	public static <T> JsonResult<T> of(T data) {
		return new JsonResult<T>(data);
	}
	
	/**
	 * 静态构建方法
	 * @param code
	 * @param msg
	 * @return
	 */
	public static <T> JsonResult<T> of(int code, String msg) {
		return new JsonResult<T>(code, msg);
	}
	
	/**
	 * 当code != 0时，会抛出异常
	 * @return
	 */
	public T get() {
		if (this.code != 0) {
			throw new ServiceException(msg == null ? "No available data" : msg);
		}
		return this.data;
	}
	
	public boolean isSuccess() {
		return this.code == 0;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
