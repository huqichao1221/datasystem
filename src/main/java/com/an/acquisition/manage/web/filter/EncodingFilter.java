package com.an.acquisition.manage.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	
	private String encoding = "UTF-8";//默认编码
	private boolean ignoreExistEncoding = true;	//忽略已存在的编码

	public void init(FilterConfig config) throws ServletException {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		long start = System.currentTimeMillis();
//		log.debug("do encoding Filter ...");
		if (ignoreExistEncoding || request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
			request.getAttribute("");
			response.getCharacterEncoding();
//			log.debug("comId4Name == "+request.getParameter("comId4Name"));
		}
		//log.debug("comId4Name == "+request.getParameter("comId4Name"));
		chain.doFilter(request, response);
//		log.debug("end encoding Filter ...use Time: " 
//				+ (System.currentTimeMillis() - start) + "ms");
	}

	
	public void destroy() {
		
	}


}
