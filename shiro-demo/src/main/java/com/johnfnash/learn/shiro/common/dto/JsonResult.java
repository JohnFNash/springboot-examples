package com.johnfnash.learn.shiro.common.dto;

import java.util.Optional;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.johnfnash.learn.shiro.common.enums.StatusCodeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(value = {"success", "optional"})
@ApiModel("通用结构体")
public class JsonResult<T> {
	/**
	 * 0-success 其他-失败
	 * 见 StatusCodeEnum
	 */
	@ApiModelProperty("响应码, 0为成功，其它为失败")
	protected int code;

	@ApiModelProperty("响应消息, 当code不为0时，此为失败消息")
	protected String msg;

	@ApiModelProperty("响应数据, 成功时返回的数据对象")
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
	 * code != 0时，返回 other
	 * @param other
	 * @return
	 */
	public T orElse(T other) {
		return this.code == 0 ? this.data : other;
	}
	/**
	 * code != 0时返回null
	 * @return
	 */
	public T orNull() {
		return this.code == 0 ? this.data : null;
	}

	@ApiModelProperty(hidden = true)
	public Optional<T> getOptional() {
	    return Optional.ofNullable(data);
	}

	@ApiModelProperty(hidden = true)
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SIMPLE_STYLE);
	}

}
