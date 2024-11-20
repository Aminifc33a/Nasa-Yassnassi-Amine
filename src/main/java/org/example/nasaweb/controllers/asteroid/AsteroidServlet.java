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

@WebServlet(name = "Asteroid", value = "/asteroid")
public class AsteroidServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException, IOException {
        AsteroidService asteroidService = new AsteroidService();

        String id = request.getParameter("id");

        if (id != null && asteroidService.findById(Long.parseLong(id)) != null) {
            request.setAttribute("asteroid", asteroidService.findById(Long.parseLong(id)));
            request.getRequestDispatcher("/asteroids/asteroidDetails.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/asteroids/asteroids.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException, IOException {
        String action = request.getParameter("action");
        if (action!= null) {
            switch (action) {
                case "create":
                    handleCreate(request, response);
                    break;
                case "update":
                    handleUpdate(request, response);
                    break;
                case "delete":
                    handleDelete(request, response);
                    break;
                case "edit":
                    handleEdit(request, response);
                    break;
            }
        }
    }

    private void handleCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Asteroid asteroid = new Asteroid();
        asteroid.setName(request.getParameter("name"));
        asteroid.setDiameterKmAverage(new BigDecimal(request.getParameter("diameter")));
        asteroid.setAbsoluteMagnitude(new BigDecimal(request.getParameter("magnitude")));
        asteroid.setIsPotentiallyHazardous(Boolean.parseBoolean(request.getParameter("hazardous")));

        AsteroidService asteroidService = new AsteroidService();
        asteroidService.create(asteroid);
        response.sendRedirect(request.getContextPath() +"/asteroids");
    }

    // handle edit
    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        AsteroidService asteroidService = new AsteroidService();
        String id = request.getParameter("id");

        if (id!= null && asteroidService.findById(Long.parseLong(id))!= null) {
            asteroidService.deleteById(Long.parseLong(id));
            response.sendRedirect("/asteroids");
        } else {
            response.sendRedirect("/asteroids/asteroids.jsp");
        }
    }
    // handle edit
    private void handleEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AsteroidService asteroidService = new AsteroidService();
        String id = request.getParameter("id");

        if (id!= null && asteroidService.findById(Long.parseLong(id))!= null) {
            request.setAttribute("asteroid", asteroidService.findById(Long.parseLong(id)));
            request.getRequestDispatcher("/asteroids/editAsteroid.jsp").forward(request, response);
        } else {
            response.sendRedirect("/asteroids/asteroids.jsp");
        }
    }
}
