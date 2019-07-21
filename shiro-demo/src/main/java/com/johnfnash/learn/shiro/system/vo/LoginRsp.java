package com.johnfnash.learn.shiro.system.vo;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
public class LoginRsp {

	@Getter
	@Setter
	private String sessionId;
	
	@Getter
	@Setter
	protected Set<String> roleCodes = new HashSet<String>(0);

	@Getter
	@Setter
	protected Set<String> permissionCodes = new HashSet<String>(0);
	
	@Getter
	@Setter
	private String name;
	
}
