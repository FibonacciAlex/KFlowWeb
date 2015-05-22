package com.model.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 过滤器
 * @author Alex
 *
 */
public class KGLoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		
		HttpServletRequest servletReq = (HttpServletRequest) request;
		HttpServletResponse servletResp = (HttpServletResponse) response;
		HttpSession session = servletReq.getSession();
		String path = servletReq.getRequestURI();
		System.out.println("角色请求path:" + path);

		if(path.indexOf("login.jsp") > -1){
			chain.doFilter(servletReq, servletResp);
			return;
		}
		
		String user = (String)session.getAttribute("user");
		if(user == null || user.equals("")){
			servletResp.sendRedirect("/login.jsp");
		}else{
			chain.doFilter(servletReq, servletResp);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
