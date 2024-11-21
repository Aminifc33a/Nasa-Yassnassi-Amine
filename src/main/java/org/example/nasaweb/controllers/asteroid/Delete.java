package org.example.nasaweb.controllers.asteroid;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.nasaweb.service.AsteroidService;

import java.io.IOException;

@WebServlet(name = "DeleteAsteroid", value = "/asteroid/delete")
public class Delete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        AsteroidService asteroidService = new AsteroidService();
        asteroidService.deleteById(id);
        response.sendRedirect(request.getContextPath() + "/asteroids");
    }
}
