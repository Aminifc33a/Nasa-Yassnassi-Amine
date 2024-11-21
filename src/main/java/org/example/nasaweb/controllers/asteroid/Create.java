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
            // Redirigir al formulario de creación
            request.getRequestDispatcher("/asteroid/create.jsp").forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                // Obtener parámetros del formulario
                long id = Long.parseLong(request.getParameter("id")); // El usuario lo proporciona
                String name = request.getParameter("name");
                BigDecimal diameter = new BigDecimal(request.getParameter("diameter"));
                boolean isPotentiallyHazardous = Boolean.parseBoolean(request.getParameter("hazardous"));

                // Crear objeto Asteroid
                Asteroid asteroid = new Asteroid();
                asteroid.setId(id);
                asteroid.setName(name);
                asteroid.setDiameterKmAverage(diameter);
                asteroid.setIsPotentiallyHazardous(isPotentiallyHazardous);

                // Guardar asteroide en la base de datos
                AsteroidService asteroidService = new AsteroidService();
                asteroidService.create(asteroid);

                // Redirigir a la lista de asteroides
                response.sendRedirect(request.getContextPath() + "/asteroids");
            } catch (NumberFormatException e) {
                // Manejar errores de formato en el ID o diámetro
                request.setAttribute("errorMessage", "Invalid number format: " + e.getMessage());
                request.getRequestDispatcher("/asteroid/create.jsp").forward(request, response);
            } catch (Exception e) {
                // Manejar otros errores
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                request.setAttribute("errorMessage", "Error creating asteroid: " + e.getMessage());
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }
    }
