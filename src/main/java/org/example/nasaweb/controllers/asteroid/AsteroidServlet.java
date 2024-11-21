package org.example.nasaweb.controllers.asteroid;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.nasaweb.service.AsteroidService;

import java.io.IOException;

@WebServlet(name = "Asteroid", value = "/asteroid")
public class AsteroidServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsteroidService asteroidService = new AsteroidService();

        String id = request.getParameter("id");

        if (id != null && asteroidService.findById(Long.parseLong(id)) != null) {
            request.setAttribute("asteroid", asteroidService.findById(Long.parseLong(id)));
            request.getRequestDispatcher("/asteroid/asteroidDetails.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/asteroid/asteroids.jsp").forward(request, response);
        }
    }
}
