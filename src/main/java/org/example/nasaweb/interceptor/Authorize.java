package org.example.nasaweb.interceptor;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.nasaweb.model.User;

import java.io.IOException;

@WebFilter(servletNames = {"AsteroidEdit", "AsteroidSync"})
public class Authorize implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user != null && user.getRole().equals("astronomer")){
            chain.doFilter(request, response);
        } else {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this feature.");
        }
    }
}
