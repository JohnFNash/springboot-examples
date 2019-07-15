package com.johnfnash.learn.jwt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import com.johnfnash.learn.jwt.exception.ServiceException;
import com.johnfnash.learn.jwt.util.JwtUtils;

import io.jsonwebtoken.Claims;

/**
 * JWT 校验 filter
 */
@WebFilter(filterName = "jwtFilter", urlPatterns = "/hello/*")
public class HTTPBearerAuthorizeAttribute implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if("options".equals(httpRequest.getMethod())) {
			chain.doFilter(request, response);
			return;
		}
		
		String auth = httpRequest.getHeader("Authorization");
		if(auth != null && auth.length() > 7) {
			String headStr = auth.substring(0, 6).toLowerCase();
			if (headStr.compareTo("bearer") == 0)
			{
				auth = auth.substring(7, auth.length());
				Claims claims = JwtUtils.parseJWT(auth);
				if(claims != null) {
					chain.doFilter(request, response);
					return;
				}
			}
		}
		
		// token 校验失败，抛出异常
		throw new ServiceException("token校验失败");
	}

	@Override
	public void destroy() {
	}

}
