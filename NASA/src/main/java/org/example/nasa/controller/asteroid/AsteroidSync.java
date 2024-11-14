package org.example.nasa.controller.asteroid;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.example.nasa.service.ApproachService;
import org.example.nasa.service.AsteroidService;
import org.example.nasa.service.NasaAPIService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sync-asteroids")
public class AsteroidSync extends HttpServlet {
    private NasaAPIService nasaAPIService;

    @Override
    public void init() throws ServletException {
        AsteroidService asteroidService = new AsteroidService();
        ApproachService approachService = new ApproachService();
        nasaAPIService = new NasaAPIService(asteroidService, approachService);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        nasaAPIService.syncAsteroids();
        resp.sendRedirect(req.getContextPath() + "/asteroids"); // Redirige al listado de asteroides
    }
}
