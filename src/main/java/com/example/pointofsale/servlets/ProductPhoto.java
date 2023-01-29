package com.example.pointofsale.servlets;

import com.example.pointofsale.common.ProductPhotoDto;
import com.example.pointofsale.ejb.ProductBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ProductPhoto", value = "/ProductPhoto")
public class ProductPhoto extends HttpServlet {

    @Inject
    ProductBean productBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer productId = Integer.parseInt(request.getParameter("product_id"));
        ProductPhotoDto photo = productBean.findPhotoByProductId(productId);
        if(photo != null){
            response.setContentType(photo.getFileType());
            response.setContentLength(photo.getFileContent().length);
            response.getOutputStream().write(photo.getFileContent());
        }else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
