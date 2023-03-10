package com.example.pointofsale.servlets;

import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.ejb.InvoiceBean;
import com.example.pointofsale.ejb.ProductBean;
import com.example.pointofsale.ejb.ReceiptBean;
import com.example.pointofsale.ejb.ReceiptProductsItemBean;
import com.example.pointofsale.entities.Product;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@DeclareRoles({"Cashier"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"Cashier"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"Cashier"})})
@WebServlet(name = "ProcessSale", value = "/ProcessSale")
public class ProcessSale extends HttpServlet {
    @Inject
    ProductBean productBean;
    @Inject
    InvoiceBean invoiceBean;
    @Inject
    ReceiptBean receiptBean;
    @Inject
    ReceiptProductsItemBean receiptProductsItemBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Double total = new Double(0);

        if (!invoiceBean.getProductIds().isEmpty()) {
            HashMap<ProductDto, Long> IdsToQuantity = invoiceBean.getIdsToQuantity();
            total = invoiceBean.calcTotal(IdsToQuantity);
            request.setAttribute("invoices", IdsToQuantity);
        }
        request.setAttribute("total", total);
        request.getRequestDispatcher("/WEB-INF/pages/processSale.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String valid = request.getParameter("form1");

        if (valid.equals("form1")) {

            String name = request.getParameter("search_input");
            ProductDto product = productBean.findProductByName(name);
            if (product == null) {
                request.setAttribute("message", "No product found.");
            }
            request.setAttribute("product", product);
            Double total = new Double(0);

            if (!invoiceBean.getProductIds().isEmpty()) {
                HashMap<ProductDto, Long> IdsToQuantity = invoiceBean.getIdsToQuantity();
                total = invoiceBean.calcTotal(IdsToQuantity);
                request.setAttribute("invoices", IdsToQuantity);
            }
            request.setAttribute("total", total);
            request.getRequestDispatcher("/WEB-INF/pages/processSale.jsp").forward(request, response);

        } else if (valid.equals("form2")) {

            Long productIdAsLong = Long.valueOf(request.getParameter("product-id"));
            Long quantity = Long.valueOf(request.getParameter("quantity"));
            ProductDto product = productBean.findProductById(productIdAsLong);

            if (productIdAsLong != null) {
                invoiceBean.addQuantityAndID(product, quantity);
            }
            if(invoiceBean.getIdsToQuantity().containsKey(product)){
                invoiceBean.substractQuantity(product,quantity);
            }

        } else if(valid.equals("form3")){
            receiptBean.createReceipt(request.getRemoteUser(), invoiceBean.calcTotal(invoiceBean.getIdsToQuantity()));
            invoiceBean.createReceiptProductsItems();
            invoiceBean.getIdsToQuantity().clear();

        }

        response.sendRedirect(request.getContextPath() + "/ProcessSale");
    }
}
