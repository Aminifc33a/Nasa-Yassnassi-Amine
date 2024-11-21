package org.example.nasaweb.controllers.Approach;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.nasaweb.service.ApproachService;

import java.io.IOException;

@WebServlet(name = "DeleteApproach", value = "/approach/delete")
public class Delete  extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long approachId = Long.parseLong(request.getParameter("approachId"));
        ApproachService approachService = new ApproachService();
        approachService.deleteById(approachId);
        response.sendRedirect(request.getContextPath() + "/asteroids");
    }
}
