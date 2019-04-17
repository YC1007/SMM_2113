package com.bdqn.ssm.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤编码格式
 * @author bi
 *
 */
public class CharacterEncodingFilter implements Filter {
    //过滤器销毁方法
	public void destroy() { }
	//设置过滤功能方法
	public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
		 //设置请求时的编码方式
		request.setCharacterEncoding("UTF-8");
		//调用Web资源，也可以调用其他过滤器
		chain.doFilter(request, response);
		//设置响应时的编码方式
	 	response.setCharacterEncoding("UTF-8");
	}
	//过滤器初始化方法
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		//
	}

}
