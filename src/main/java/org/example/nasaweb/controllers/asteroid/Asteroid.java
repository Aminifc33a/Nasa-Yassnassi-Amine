package org.example.nasaweb.controllers.asteroid;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.nasaweb.service.AsteroidService;

import java.io.IOException;

@WebServlet(name = "Asteroid", value = "/asteroid")
public class Asteroid extends HttpServlet {

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
