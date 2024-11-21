package org.example.nasaweb.controllers.registration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.nasaweb.model.User;
import org.example.nasaweb.service.UserService;

import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/registration/register.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        UserService userService = new UserService();
        if (userService.existsByUsername(username)) {
            request.setAttribute("error", "Username already exists.");
            request.getRequestDispatcher("/registration/register.jsp").forward(request, response);
            return;
        }
        User user = new User(0, username, password, role);
        try {
            userService.create(user);
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/asteroids");
            request.setAttribute("error", null);
        } catch (Exception e){
            request.setAttribute("error", "Error creating the account");
            request.getRequestDispatcher("/registration/register.jsp").forward(request, response);
        }
    }
}
