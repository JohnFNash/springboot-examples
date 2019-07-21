package com.johnfnash.learn.shiro.system.action;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnfnash.learn.shiro.common.action.BaseAction;
import com.johnfnash.learn.shiro.common.dto.JsonResult;
import com.johnfnash.learn.shiro.common.dto.PageInfo;
import com.johnfnash.learn.shiro.system.service.SysUserService;
import com.johnfnash.learn.shiro.system.vo.FindUserListReq;
import com.johnfnash.learn.shiro.system.vo.SysUserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "系统管理-用户管理")
@RestController
@RequestMapping("/sys/user")
@RequiresAuthentication
public class UserAction extends BaseAction {

	@Autowired
	private SysUserService userService;
	
	@ApiOperation(value = "分页查询用户列表", notes = "分页查询用户列表")
	@PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequiresPermissions(value = "per_01_01_03_01")
	public JsonResult<PageInfo<SysUserVo>> list(@RequestBody @Valid FindUserListReq req) {
		return JsonResult.of(userService.list(req));
	}
	
}
