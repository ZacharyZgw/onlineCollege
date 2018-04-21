package com.zgw.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DisableUrlSessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)){
            filterChain.doFilter(request,response);
            return;
        }
        /*
        * HttpServletRequest和ServletRequest都是接口，前者继承与后者，
        * 前着针对http协议提供了一些额外的方法
        * */
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        /*
        * sessionid是标明session的id的（有点废话。。。），它是存在于cookie中的，
        * 一般情况下不会出现在url中，服务器会从客户端的cookie中取出来，
        * 但是如果客户端禁用了cookie的话，就要重写url了，
        * 显式的将jsessionid重写到Url中，方便服务器来通过这个找到session的id
        * */
        if (httpRequest.isRequestedSessionIdFromURL()){
            HttpSession session = httpRequest.getSession();
            if (session != null){
                session.invalidate();
            }
        }
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(httpResponse){
            @Override
            public String encodeURL(String url) {
                return super.encodeURL(url);
            }

            @Override
            public String encodeRedirectURL(String url) {
                return super.encodeRedirectURL(url);
            }

            @Override
            public String encodeUrl(String url) {
                return super.encodeUrl(url);
            }

            @Override
            public String encodeRedirectUrl(String url) {
                return super.encodeRedirectUrl(url);
            }
        };
        filterChain.doFilter(request,responseWrapper);
    }

    @Override
    public void destroy() {

    }
}
