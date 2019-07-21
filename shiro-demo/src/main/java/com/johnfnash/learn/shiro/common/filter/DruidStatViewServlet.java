package com.johnfnash.learn.shiro.common.filter;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

@WebServlet(
        urlPatterns = {"/druid/*"},
        initParams = {
                @WebInitParam(name = "loginUsername", value = "admin"),
                @WebInitParam(name = "loginPassword", value = "test"),
                @WebInitParam(name = "resetEnable", value = "false")
//      @WebInitParam(name = "allow", value = "127.0.0.1")
    }
)
public class DruidStatViewServlet extends StatViewServlet {

	private static final long serialVersionUID = 1L;

}
