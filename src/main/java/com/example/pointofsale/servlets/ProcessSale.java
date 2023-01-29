package com.example.pointofsale.servlets;

import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.ejb.InvoiceBean;
import com.example.pointofsale.ejb.ProductBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "ProcessSale", value = "/ProcessSale")
public class ProcessSale extends HttpServlet {
    @Inject
    ProductBean productBean;
    @Inject
    InvoiceBean invoiceBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<ProductDto> products=productBean.findAllProducts();
//        request.setAttribute("products",products);

        if(!invoiceBean.getProductIds().isEmpty()){
            Collection<String> products=productBean.findProductnamesByIds(invoiceBean.getProductIds());
            request.setAttribute("invoices", products);
        }

        request.getRequestDispatcher("/WEB-INF/pages/processSale.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String valid=request.getParameter("form1");
        if(valid.equals("form1")){
            Long id = Long.parseLong(request.getParameter("search_input"));
            ProductDto product = productBean.findProductById(id);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/WEB-INF/pages/processSale.jsp").forward(request, response);
        }
        else if(valid.equals("form2")) {

            String productIdAsString = request.getParameter("product-id");
            if (productIdAsString != null) {
                List<Long> productIds = new ArrayList<>();
                productIds.add(Long.parseLong(productIdAsString));
                invoiceBean.getProductIds().addAll(productIds);
            }
        }

        response.sendRedirect(request.getContextPath() + "/ProcessSale");
    }
}
