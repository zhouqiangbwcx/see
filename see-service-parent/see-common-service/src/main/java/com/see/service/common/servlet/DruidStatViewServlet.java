package com.see.service.common.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 *
 * @author wujing
 */
@WebServlet(urlPatterns = { "/druid/*" }, initParams = { @WebInitParam(name = "loginUsername", value = "see-eva"), @WebInitParam(name = "loginPassword", value = "123123") })
public class DruidStatViewServlet extends StatViewServlet {

	private static final long serialVersionUID = 1L;

}

