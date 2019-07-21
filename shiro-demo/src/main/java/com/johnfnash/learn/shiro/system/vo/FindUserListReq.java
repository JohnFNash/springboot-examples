package com.johnfnash.learn.shiro.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class FindUserListReq extends PageReq {
	
	@ApiModelProperty(value = "用户名称")
	private String userName;
	
}
