package com.example.pointofsale.servlets;

import com.example.pointofsale.ejb.ProductBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
@DeclareRoles({"Manager", "Cashier","Director"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"Manager"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed =
                {"Manager"})})
@WebServlet(name = "AddProduct", value = "/AddProduct")
public class AddProduct extends HttpServlet {

    @Inject
    ProductBean productBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/AddProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String name = request.getParameter("name");
            Double price = Double.valueOf(request.getParameter("price"));
            Integer quantity = Integer.valueOf(request.getParameter("quantity"));
            Long category = Long.valueOf(request.getParameter("category"));
            Integer tva = Integer.valueOf(request.getParameter("tva"));

            productBean.createProduct(name, price, quantity, category, tva);

            response.sendRedirect(request.getContextPath() + "/Products");

    }
}
