package org.example.nasaweb.controllers.Approach;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.nasaweb.service.ApproachService;

import java.io.IOException;
@WebServlet(name = "DeleteApproach", value = "/approach/delete")
public class Delete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String approachIdParam = request.getParameter("approachId");
            if (approachIdParam == null || approachIdParam.isEmpty()) {
                throw new IllegalArgumentException("Approach ID is missing or empty.");
            }
            long approachId = Long.parseLong(approachIdParam);

            String asteroidId = request.getParameter("asteroidId");
            if (asteroidId == null || asteroidId.isEmpty()) {
                throw new IllegalArgumentException("Asteroid ID is missing or empty.");
            }

            ApproachService approachService = new ApproachService();
            approachService.deleteById(approachId);

            response.sendRedirect(request.getContextPath() + "/asteroid?id=" + asteroidId);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorMessage", "Invalid ID format.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
