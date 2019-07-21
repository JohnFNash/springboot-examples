package com.johnfnash.learn.shiro.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnfnash.learn.shiro.common.action.BaseAction;
import com.johnfnash.learn.shiro.common.dto.JsonResult;
import com.johnfnash.learn.shiro.system.entity.SysUser;
import com.johnfnash.learn.shiro.system.vo.LoginReq;
import com.johnfnash.learn.shiro.system.vo.LoginRsp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 登录登出
 */
@Api(tags = "登录登出")
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthAction extends BaseAction {
	@Value("${auth.adminSessionExpiresInSec:1800}")
	private long adminSessionExpiresInSec;
	
	@ApiOperation(value = "登录", notes = "登录")
	@PostMapping(value = "/login")
	public JsonResult<LoginRsp> login(@Valid @RequestBody LoginReq loginReq, HttpServletRequest request) {
		log.info("login: {}", loginReq);

		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			String username = loginReq.getLoginName();
			String password = loginReq.getPasswd();

			UsernamePasswordToken token = new UsernamePasswordToken();
			token.setUsername(username);
			token.setPassword(password.toCharArray());

			subject.login(token);

			Session session = getSession(false);
			session.setTimeout(adminSessionExpiresInSec * 1000L);
		}

		SysUser principal = (SysUser) getPrincipal();

		LoginRsp loginRsp = new LoginRsp();
		loginRsp.setSessionId((String) getSession(false).getId());
		loginRsp.getRoleCodes().addAll(principal.getRoles());
		loginRsp.getPermissionCodes().addAll(principal.getStringPermissions());
		loginRsp.setName(principal.getUserName());
		
		return JsonResult.of(loginRsp);
	}
	
	@ApiOperation(value = "注销")
	@GetMapping(value = "/logout")
	@RequiresAuthentication
	public JsonResult<Void> logout() {
		getSubject().logout();
		return JsonResult.of();
	}

}
