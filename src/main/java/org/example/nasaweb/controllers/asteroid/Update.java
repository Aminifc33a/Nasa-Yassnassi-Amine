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
        try {
            long id = Long.parseLong(request.getParameter("id"));

            AsteroidService asteroidService = new AsteroidService();
            Asteroid asteroid = asteroidService.findById(id);

            request.setAttribute("asteroid", asteroid);
            request.getRequestDispatcher("/asteroid/update.jsp").forward(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorMessage", "Invalid asteroid ID");
            request.getRequestDispatcher("/asteroid/update.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            BigDecimal absoluteMagnitude = new BigDecimal(request.getParameter("magnitude"));
            BigDecimal diameter = new BigDecimal(request.getParameter("diameter"));
            Boolean isPotentiallyHazardous = Boolean.valueOf(request.getParameter("hazardous"));

            Asteroid asteroid = new Asteroid(id, name, absoluteMagnitude, diameter, isPotentiallyHazardous);

            AsteroidService asteroidService = new AsteroidService();
            asteroidService.update(asteroid);

            response.sendRedirect(request.getContextPath() + "/asteroids");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("errorMessage", "Error updating asteroid: " + e.getMessage());
            request.getRequestDispatcher("/asteroid/update.jsp").forward(request, response);
        }
    }
}
