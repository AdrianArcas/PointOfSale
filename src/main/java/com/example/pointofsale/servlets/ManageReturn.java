package com.example.pointofsale.servlets;

import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.common.ReceiptDto;
import com.example.pointofsale.ejb.ProductBean;
import com.example.pointofsale.ejb.ReceiptBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@DeclareRoles({"Cashier"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"Cashier"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"Cashier"})})
@WebServlet(name = "ManageReturn", value = "/ManageReturn")
public class ManageReturn extends HttpServlet {
    @Inject
    ProductBean productBean;
    @Inject
    ReceiptBean receiptBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/pages/manageReturn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String valid = request.getParameter("form1");
        String[] receiptIdsAsString = request.getParameterValues("receipt_ids");
        if (valid.equals("form1")) {
            Long id=Long.parseLong(request.getParameter("search_input"));

            ReceiptDto receipt = receiptBean.findReceiptById(id);
            request.setAttribute("receipt", receipt);
            request.getRequestDispatcher("/WEB-INF/pages/manageReturn.jsp").forward(request, response);

        } else if (valid.equals("form2")) {

            String productIdAsString = request.getParameter("product-id");
            Long quantity= Long.valueOf(request.getParameter("quantity"));
            request.setAttribute("productQuantity", quantity);
            String name = request.getParameter("productName");
            ProductDto product2 = productBean.findProductByName(name);
            request.setAttribute("product", product2);
//            request.getRequestDispatcher("/WEB-INF/pages/processSale.jsp").forward(request, response);
            if (productIdAsString != null) {
                List<Long> productIds = new ArrayList<>();
                productIds.add(Long.parseLong(productIdAsString));
//                invoiceBean.getProductIds().addAll(productIds);
            }
//            request.getRequestDispatcher("/WEB-INF/pages/processSale.jsp").forward(request, response);
//            response.sendRedirect(request.getContextPath() + "/ProcessSale");
        }
        response.sendRedirect(request.getContextPath() + "/ManageReturn");
    }
}
