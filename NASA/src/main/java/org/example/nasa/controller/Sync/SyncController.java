package org.example.nasa.controller.Sync;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.example.nasa.dao.ApproachDao;
import org.example.nasa.dao.AsteroidDao;
import org.example.nasa.dao.JPA.ApproachDaoImpl;
import org.example.nasa.dao.JPA.AsteroidDaoImpl;
import org.example.nasa.service.NasaService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sync-asteroids")
public class SyncController extends HttpServlet {
    private NasaService nasaService;

    @Override
    public void init() throws ServletException {
        AsteroidDao asteroidDao = new AsteroidDaoImpl();
        ApproachDao approachDao = new ApproachDaoImpl();
        nasaService = new NasaService(asteroidDao, approachDao);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        nasaService.syncAsteroids();
        resp.sendRedirect(req.getContextPath() + "/asteroids"); // Redirige al listado de asteroides
    }
}
