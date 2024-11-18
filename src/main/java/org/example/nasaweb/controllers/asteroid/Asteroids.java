package org.example.nasaweb.controllers.asteroid;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.nasaweb.model.Asteroid;
import org.example.nasaweb.service.AsteroidService;
import org.example.nasaweb.service.NasaAPIService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Asteroids", value = "/asteroids")
public class Asteroids extends HttpServlet {

    private final AsteroidService asteroidService = new AsteroidService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Obtener asteroides desde la API de NASA
                List<Asteroid> asteroids = asteroidService.findAll();

            // Enviar la lista de asteroides al JSP
            req.setAttribute("asteroids", asteroids);

            // Redirigir al archivo JSP
            req.getRequestDispatcher("/asteroids/asteroids.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error retrieving asteroids: " + e.getMessage());
        }
    }
}
