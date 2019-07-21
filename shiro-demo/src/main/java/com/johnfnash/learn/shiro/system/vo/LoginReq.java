package com.johnfnash.learn.shiro.system.vo;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class LoginReq {

	@ApiModelProperty(value = "登录名", required = true, example = "admin")
	@NotBlank(message = "登录名不能为空")
	private String loginName;

	@ApiModelProperty(value = "密码", required = true, example = "e10adc3949ba59abbe56e057f20f883e")
	@NotBlank(message = "密码不能为空")
	private String passwd;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
