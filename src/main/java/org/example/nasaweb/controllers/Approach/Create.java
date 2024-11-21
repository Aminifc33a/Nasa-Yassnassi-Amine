package org.example.nasaweb.controllers.Approach;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.nasaweb.model.Approach;
import org.example.nasaweb.model.Asteroid;
import org.example.nasaweb.service.ApproachService;
import org.example.nasaweb.service.AsteroidService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet(name = "CreateApproach", value = "/approach/create")
public class Create extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long asteroidId = Long.parseLong(request.getParameter("asteroidId"));

        AsteroidService asteroidService = new AsteroidService();
        Asteroid asteroid = asteroidService.findById(asteroidId);
        if (asteroid == null) {
            request.setAttribute("errorMessage", "Incorrect asteroid ID");
            response.sendRedirect(request.getContextPath() + "/asteroids");
            return;
        }
        request.setAttribute("asteroidId", asteroidId);
        request.getRequestDispatcher("/Approach/create.jsp").forward(request, response);
        request.setAttribute("asteroidId", asteroidId);
        request.getRequestDispatcher("/Approach/create.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long asteroidId = Long.parseLong(request.getParameter("asteroidId"));
        LocalDate approachDate = LocalDate.parse(request.getParameter("date"));
        BigDecimal velocity = new BigDecimal(request.getParameter("velocity"));
        BigDecimal distance = new BigDecimal(request.getParameter("distance"));
        String orbitingBody = request.getParameter("orbitingBody");
        AsteroidService asteroidService = new AsteroidService();
        Asteroid asteroid = asteroidService.findById(asteroidId);  // Obtener el asteroide a partir del ID
        ApproachService approachService = new ApproachService();
        Approach approach = new Approach(asteroid, approachDate, velocity, distance, orbitingBody);
        approachService.create(approach, asteroid);
        response.sendRedirect(request.getContextPath() + "/asteroids?id=" + asteroidId);
    }
}
