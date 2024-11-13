package org.example.nasa.interceptor;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(servletNames = {"createMovie", "editMovie", "deleteMovie"})
public class NasaInterceptor implements Filter {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            HttpSession session = httpRequest.getSession(false);
            boolean loggedIn = (session != null && session.getAttribute("user") != null);

            // Si no está autenticado, redirigir a la página de inicio de sesión
            if (!loggedIn) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            } else {
                chain.doFilter(request, response);
            }
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            Filter.super.init(filterConfig);
        }

        @Override
        public void destroy() {
            Filter.super.destroy();
        }
    }

