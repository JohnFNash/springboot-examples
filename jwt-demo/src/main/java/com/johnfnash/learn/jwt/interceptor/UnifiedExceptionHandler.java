package com.johnfnash.learn.jwt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.johnfnash.learn.jwt.dto.JsonResult;
import com.johnfnash.learn.jwt.enums.StatusCodeEnum;
import com.johnfnash.learn.jwt.exception.ServiceException;

/**
 * 统一异常处理
 *
 */
@ControllerAdvice
@ResponseBody
public class UnifiedExceptionHandler	{

	@ExceptionHandler(value = ServiceException.class)
	public JsonResult<?> handleAuthorizationException(HttpServletRequest request,
			HttpServletResponse response, ServiceException ex) {
		
		JsonResult<?> jr = JsonResult.of(StatusCodeEnum.AUTH_ERR.getCode(), null);
		if (ex instanceof ServiceException) {
			jr.setMsg(ex.getMessage() != null ? ex.getMessage() : "授权校验失败");
		} else {
			jr.setMsg("授权校验失败");
		}

		return jr;
	}

}
