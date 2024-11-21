package org.example.nasaweb.controllers.asteroid;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.nasaweb.model.Asteroid;
import org.example.nasaweb.service.AsteroidService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Asteroids", value = "/asteroids")
public class AsteroidsList extends HttpServlet {

    private final AsteroidService asteroidService = new AsteroidService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Asteroid> asteroids = asteroidService.findAll();

            req.setAttribute("asteroids", asteroids);
            req.getRequestDispatcher("/asteroid/asteroids.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error retrieving asteroids: " + e.getMessage());
        }
    }
}
