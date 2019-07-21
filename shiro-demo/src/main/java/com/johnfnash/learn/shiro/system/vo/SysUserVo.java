package com.johnfnash.learn.shiro.system.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("系统用户数据")
@Data
public class SysUserVo {
	@ApiModelProperty("用户id")
	private String id;
	
	@ApiModelProperty("创建人")
	private String createdBy;
	
	@ApiModelProperty("创建时间")
	private Date createdDate;
	
	@ApiModelProperty("登录账号")
	private String loginName;
	
	@ApiModelProperty("机构编码")
	private String orgCode;
	
	@ApiModelProperty("机构名称")
	private String orgName;

	@ApiModelProperty("用户手机号码")
	private String phone;

	@ApiModelProperty("用户状态")
	private Integer state;

	@ApiModelProperty("最后修改人")
	private String updatedBy;
	
	@ApiModelProperty("最后修改时间")
	private Date updatedDate;
	
	@ApiModelProperty("用户名称")
	private String userName;
	
	private Integer userType;
	
	@ApiModelProperty("用户角色")
	private String userRole;
	
}
