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
    @WebServlet(name = "CreateAsteroid", value = "/asteroids/create")
    public class Create extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("/asteroid/create.jsp").forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                long id = Long.parseLong(request.getParameter("id"));
                String name = request.getParameter("name");
                BigDecimal magnitude = new BigDecimal(request.getParameter("magnitude"));
                BigDecimal diameter = new BigDecimal(request.getParameter("diameter"));
                boolean isPotentiallyHazardous = Boolean.parseBoolean(request.getParameter("hazardous"));

                Asteroid asteroid = new Asteroid();
                asteroid.setId(id);
                asteroid.setName(name);
                asteroid.setAbsoluteMagnitude(magnitude);
                asteroid.setDiameterKmAverage(diameter);
                asteroid.setIsPotentiallyHazardous(isPotentiallyHazardous);

                AsteroidService asteroidService = new AsteroidService();
                asteroidService.create(asteroid);

                response.sendRedirect(request.getContextPath() + "/asteroids");

            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid number format: " + e.getMessage());
                request.getRequestDispatcher("/asteroid/create.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                request.setAttribute("errorMessage", "Error creating asteroid: " + e.getMessage());
                request.getRequestDispatcher("/asteroid/create.jsp").forward(request, response);
            }
        }
    }
