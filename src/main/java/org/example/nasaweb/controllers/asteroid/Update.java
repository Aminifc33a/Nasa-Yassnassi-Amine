package org.example.nasaweb.controllers.asteroid;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.nasaweb.model.Asteroid;
import org.example.nasaweb.service.AsteroidService;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "UpdateAsteroid", value = "/asteroid/update")
public class Update extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        AsteroidService asteroidService = new AsteroidService();
        Asteroid asteroid = asteroidService.findById(id);
        request.setAttribute("asteroid", asteroid);
        response.setContentType("text/html;charset=UTF-8");
        response.sendRedirect(request.getContextPath() + "/asteroid/update?id=" + id);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        BigDecimal absoluteMagnitude = new BigDecimal(request.getParameter("absoluteMagnitude"));
        BigDecimal diameter = new BigDecimal(request.getParameter("diameter"));
        Boolean isPotentiallyHazardous = Boolean.valueOf(request.getParameter("isPotentiallyHazardous"));
        Asteroid asteroid = new Asteroid(id, name, absoluteMagnitude, diameter, isPotentiallyHazardous);
        AsteroidService asteroidService = new AsteroidService();
        asteroidService.update(asteroid);
        response.sendRedirect(request.getContextPath() + "/asteroids");
    }
}
