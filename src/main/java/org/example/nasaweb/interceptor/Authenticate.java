package org.example.nasaweb.interceptor;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebFilter("/*")
public class Authenticate implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        HttpSession session = httpReq.getSession(false);

        // Verifica si el usuario está autenticado
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        // URI que no requiere autenticación
        String loginURI = httpReq.getContextPath() + "/login";
        String registerURI = httpReq.getContextPath() + "/register";

        // Excluir recursos públicos
        boolean isPublicResource = httpReq.getRequestURI().startsWith(httpReq.getContextPath() + "/public");

        // Permitir acceso a recursos no restringidos
        if (isLoggedIn || httpReq.getRequestURI().equals(loginURI) || httpReq.getRequestURI().equals(registerURI) || isPublicResource) {
            chain.doFilter(req, resp); // Continúa con la cadena de filtros
        } else {
            httpResp.sendRedirect(loginURI); // Redirige al inicio de sesión
        }
    }
}
