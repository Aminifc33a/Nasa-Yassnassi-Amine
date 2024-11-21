package org.example.nasaweb.controllers.asteroid;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.nasaweb.service.ApproachService;
import org.example.nasaweb.service.AsteroidService;

import java.io.IOException;

@WebServlet(name = "AsteroidReset", value = "/asteroids/reset")
public class Reset extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsteroidService asteroidService = new AsteroidService();
        ApproachService approachService = new ApproachService();
        approachService.deleteAll();
        asteroidService.deleteAll();
        response.sendRedirect(request.getContextPath() + "/asteroids");
    }
}
