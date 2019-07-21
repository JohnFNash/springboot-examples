package com.johnfnash.learn.shiro.auth;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.johnfnash.learn.shiro.system.entity.SysUser;
import com.johnfnash.learn.shiro.system.service.SysPermissionService;
import com.johnfnash.learn.shiro.system.service.SysRoleService;
import com.johnfnash.learn.shiro.system.service.SysUserService;

@Component
public class AuthRealm extends AuthorizingRealm {

	/**
	 * <pre>
	 * 此处必须加 @Lazy 注解使 UserService 被 BeanPostProcessor 拦截，否则事务会失效
	 * 或者使用 SpringUtil.getInstance().getBean()方法获取实例
	 * 
	 * 在ShiroConfig中配置 LifecycleBeanPostProcessor 导致该问题出现 
	 * 
	 * https://www.cnblogs.com/siashan/p/9938737.html
	 * </pre>
	 */
	@Lazy
	@Autowired
	private SysUserService userService;
	
	@Lazy
	@Autowired
	private SysPermissionService permissionService;
	
	@Lazy
	@Autowired
	private SysRoleService roleService;
	
	// 角色权限和对应权限添加
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取session中的用户
		SysUser user = (SysUser) principals.fromRealm(this.getClass().getName()).iterator().next();
		// 取出用户的权限列表和角色列表
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(user.getStringPermissions());
		info.addRoles(user.getRoles());
		return info;
	}

	// 用户认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }
		
		UsernamePasswordToken uToken = (UsernamePasswordToken) token;
		String username = uToken.getUsername();
		
		// 获取用户信息
		SysUser user = userService.findUserByUserName(username);
		// 验证用户是否存在以及账号是否可用
		if(user == null) {
			throw new UnknownAccountException();
		}
		if (user != null && user.getState().intValue() != SysUser.State.ACTIVE) {
			throw new DisabledAccountException();
		}
		
		// 查询用户的权限以及角色
		List<String> permissions = permissionService.findPermissionCodesByUser(user.getId());
		user.getStringPermissions().addAll(permissions);
		List<String> roles = roleService.findRolesByUserId(user.getId());
		user.getRoles().addAll(roles);
		
		return new SimpleAuthenticationInfo(user, user.getPasswd(), this.getClass().getName());
	}
	
}
