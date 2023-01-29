package com.example.pointofsale.servlets;

import jakarta.annotation.security.DeclareRoles;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
@DeclareRoles({"Manager", "Cashier","Director"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"Director"}))
@WebServlet(name = "Statistics", value = "/Statistics")
public class Statistics extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/statistics.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
