package com.johnfnash.learn.shiro.system.vo;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("新增用户数据")
@Data
public class AddUserReq {
	@ApiModelProperty(value = "所属机构编码", required = true)
	@NotEmpty(message = "所属机构不能为空")
	private String orgCode;
	
	@ApiModelProperty(value = "登录名", required = true)
	@NotEmpty(message = "登录名不能为空")
	private String loginName;
	
	@ApiModelProperty(value = "用户名", required = true)
	@NotEmpty(message = "用户名不能为空")
	private String userName;
	
//	@NotEmpty(message = "联系电话不能为空")
	private String phone;
	
	// 所属角色
	@ApiModelProperty(value = "用户角色id列表，列表可以为空，但是不能传null", required = true)
	@NotNull(message = "角色不能为空")
	private List<String> roleIds;
	
}
