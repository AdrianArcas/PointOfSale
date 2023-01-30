package com.example.pointofsale.servlets;

import jakarta.annotation.security.DeclareRoles;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


@DeclareRoles({"Cashier"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"Cashier"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"Cashier"})})
@WebServlet(name = "Cashier", value = "/Cashier")
public class Cashier extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/cashier.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
