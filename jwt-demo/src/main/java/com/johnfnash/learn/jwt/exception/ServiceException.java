package com.johnfnash.learn.jwt.exception;

import com.johnfnash.learn.jwt.enums.StatusCodeEnum;

/**
 * 业务异常
 * 
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1680024163686668304L;
	
	private int errCode = StatusCodeEnum.ERROR.getCode();
	
	private String errMsg;

	public ServiceException() {
	}
	
	public ServiceException(int errCode, String errMsg) {
		super(errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public int getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}
