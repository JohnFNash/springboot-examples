package com.johnfnash.learn.jwt.enums;

/**
 * 通用非业务错误码
 */
public enum StatusCodeEnum {

	SUCCESS(0, "成功"), 
	ERROR(1, "系统开小差了..."), 
	AUTH_ERR(10, "认证错误"), 
	VERIFY_CODE_ERR(11, "验证码错误"), 
	ILLEGAL_REQ(19, "非法请求"),
	ILLEGAL_PARAM(20, "请求参数错误"), 
	UN_AUTHEN(30, "未经认证"),
	NO_PERMISSION(31, "无权限操作");

	private int code;
	private String msg;

	StatusCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
}
