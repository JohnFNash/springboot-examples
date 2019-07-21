package com.johnfnash.learn.shiro.common.config;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.johnfnash.learn.shiro.auth.AuthRealm;

@Configuration
public class ShiroConfig {

	@Bean
	public ServletContainerSessionManager shiroServletContainerSessionManager() {
		return new ServletContainerSessionManager();
	}

	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		/**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
		creator.setProxyTargetClass(true);
		return creator;
	}

	// 加入注解的使用，不加入这个注解不生效
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager manager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(manager);
		return advisor;
	}

	// 配置核心安全事务管理器
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager(ServletContainerSessionManager shiroServletContainerSessionManager,
			@Qualifier("authRealm") AuthRealm authRealm) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setCacheManager(new MemoryConstrainedCacheManager());
		manager.setSessionManager(shiroServletContainerSessionManager);
		manager.setRealm(authRealm);
		return manager;
	}
	
	//Filter工厂
	@Bean
	public ShiroFilterFactoryBean shiroFilter(org.apache.shiro.mgt.SecurityManager shiroSecurityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(shiroSecurityManager);
		return shiroFilterFactoryBean;
	}

}
