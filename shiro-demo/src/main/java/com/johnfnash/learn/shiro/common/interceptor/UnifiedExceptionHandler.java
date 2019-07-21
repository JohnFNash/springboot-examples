package com.johnfnash.learn.shiro.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.johnfnash.learn.shiro.common.dto.JsonResult;
import com.johnfnash.learn.shiro.common.enums.StatusCodeEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * 统一异常处理
 *
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class UnifiedExceptionHandler {

	@ExceptionHandler(value = AuthorizationException.class)
	public JsonResult<?> handleAuthorizationException(HttpServletRequest request,
			HttpServletResponse response, AuthorizationException ex) {
		log.error(ex.getMessage());
		
		JsonResult<?> jr = JsonResult.of(StatusCodeEnum.AUTH_ERR.getCode(), null);
		if (ex instanceof org.apache.shiro.authz.UnauthenticatedException) {
			// 退出登录
			SecurityUtils.getSubject().logout();
			jr.setMsg("由于您长时间未操作，系统已自动退出，请重新登录");
		} else if (ex instanceof org.apache.shiro.authz.UnauthorizedException) {
			// 无权限操作
			jr.setCode(StatusCodeEnum.NO_PERMISSION.getCode());
			jr.setMsg("权限不足，请联系管理员");
		} else {
			// 退出登录
			SecurityUtils.getSubject().logout();
			jr.setMsg("授权校验失败");
		}

		return jr;
	}

	@ExceptionHandler(value = AuthenticationException.class)
	public JsonResult<?> handleAuthenticationException(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException ex) {
		log.error(ex.getMessage());
		
		SecurityUtils.getSubject().logout();
		JsonResult<?> jr = JsonResult.of(StatusCodeEnum.AUTH_ERR.getCode(), null);

		if (ex instanceof org.apache.shiro.authc.ConcurrentAccessException) {
			jr.setMsg("重复登陆");
		} else if (ex instanceof org.apache.shiro.authc.CredentialsException) {
			if (ex instanceof org.apache.shiro.authc.IncorrectCredentialsException) {
				jr.setMsg("密码错误");
			} else if (ex instanceof org.apache.shiro.authc.ExpiredCredentialsException) {
				jr.setMsg("过期证书");
			} else {
				jr.setMsg("证书错误");
			}
		} else if (ex instanceof org.apache.shiro.authc.DisabledAccountException) {
			jr.setMsg("账号不可用");
		} else if (ex instanceof org.apache.shiro.authc.ExcessiveAttemptsException) {
			jr.setMsg("失败次数过多");
		} else if (ex instanceof org.apache.shiro.authc.LockedAccountException) {
			jr.setMsg("账号被锁定");
		} else if (ex instanceof org.apache.shiro.authc.UnknownAccountException) {
			jr.setMsg(ex.getMessage() != null ? ex.getMessage() : "未知账号");
		} else {
			jr.setMsg("认证失败");
		}

		return jr;
	}
	
	@ExceptionHandler(value = ServletRequestBindingException.class)
	public JsonResult<?> handleServletRequestBindingException(HttpServletRequest request,
			HttpServletResponse response, ServletRequestBindingException ex) {
		log.error(ex.getMessage());
		
		SecurityUtils.getSubject().logout();
		JsonResult<?> jr = JsonResult.of(StatusCodeEnum.ILLEGAL_PARAM.getCode(), null);

		if(ex instanceof MissingServletRequestParameterException) {
			jr.setMsg(ex.getMessage() != null ? ex.getMessage() : "缺少请求参数");
		} else if(ex instanceof MissingPathVariableException) {
			jr.setMsg(ex.getMessage() != null ? ex.getMessage() : "缺少路径参数");
		} else {
			jr.setMsg("接口调用请求绑定异常");
		}
		
		return jr;
	}
	
}
