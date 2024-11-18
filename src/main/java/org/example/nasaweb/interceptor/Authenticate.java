//package org.example.nasaweb.interceptor;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//
//@WebFilter("/*")
//public class Authenticate implements Filter {
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpReq = (HttpServletRequest) req;
//        HttpServletResponse httpResp = (HttpServletResponse) resp;
//        HttpSession session = httpReq.getSession(false);
//
//        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
//        String loginURI = httpReq.getContextPath() + "/login";
//        if (!isLoggedIn) {
//            httpResp.sendRedirect(loginURI);
//        } else {
//            chain.doFilter(req, resp);
//        }
//    }
//}
