package org.example.nasaweb.controllers.asteroid;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.nasaweb.service.NasaAPIService;

import javax.inject.Inject;
import java.io.IOException;

@WebServlet(name = "AsteroidSync", value = "/sync-asteroids")
public class AsteroidSync extends HttpServlet {

    @Inject
    private NasaAPIService nasaAPIService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            nasaAPIService = new NasaAPIService();
            nasaAPIService.syncAsteroidsToDatabase();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.sendRedirect(req.getContextPath() + "/asteroids");
        } catch (Exception e) {
            e.printStackTrace(); // Agrega esto para ver el error completo
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error during asteroid synchronization: " + e.getMessage());
        }
    }
}
