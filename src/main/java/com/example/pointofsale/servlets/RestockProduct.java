package com.example.pointofsale.servlets;

import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.ejb.ProductBean;
import com.example.pointofsale.entities.Product;
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
@WebServlet(name = "RestockProduct", value = "/RestockProduct")
public class RestockProduct extends HttpServlet {
    @Inject
    ProductBean productBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = Long.parseLong(request.getParameter("product_id"));
        ProductDto productDto = productBean.findProductById(productId);
        request.setAttribute("product", productDto);

        request.getRequestDispatcher("/WEB-INF/pages/restockProduct.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = Long.valueOf(request.getParameter("product_id"));
        Integer quantity = Integer.valueOf(request.getParameter("quantity"));

        productBean.restockProduct(productId, quantity);

        response.sendRedirect(request.getContextPath() + "/Notifications");

    }
}
