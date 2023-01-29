package com.example.pointofsale.servlets;

import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.ejb.ProductBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductCategory", value = "/ProductCategory")
public class ProductCategory extends HttpServlet {

    @Inject
    ProductBean productBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        Long categoryId = productBean.getCategoryId(category);
        List<ProductDto> productDto = productBean.findProductsByCategory(categoryId);
        request.setAttribute("products", productDto);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
