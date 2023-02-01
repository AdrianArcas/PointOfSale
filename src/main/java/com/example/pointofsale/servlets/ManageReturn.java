package com.example.pointofsale.servlets;

import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.common.ReceiptDto;
import com.example.pointofsale.ejb.InvoiceBean;
import com.example.pointofsale.ejb.ProductBean;
import com.example.pointofsale.ejb.ReceiptBean;
import com.example.pointofsale.ejb.ReceiptProductsItemBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@DeclareRoles({"Cashier"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"Cashier"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"Cashier"})})
@WebServlet(name = "ManageReturn", value = "/ManageReturn")
public class ManageReturn extends HttpServlet {
    @Inject
    InvoiceBean invoiceBean;
    @Inject
    ReceiptProductsItemBean receiptProductsItemBean;
    @Inject
    ProductBean productBean;
    @Inject
    ReceiptBean receiptBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Double total = new Double(0);

        if (!invoiceBean.getProductIds().isEmpty()) {
            HashMap<ProductDto, Long> IdsToQuantity = invoiceBean.getIdsToQuantity();
            total = invoiceBean.calcTotal(IdsToQuantity);
            request.setAttribute("invoices", IdsToQuantity);
        }
        request.setAttribute("total", total);
        request.getRequestDispatcher("/WEB-INF/pages/manageReturn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String valid = request.getParameter("form1");
        String receiptId = request.getParameter("receipt_ids");
//       exemplu cod ptr string de checkboxes ptr return
        String[] productIdsAsString =  request.getParameterValues("products_ids");

        if (valid.equals("form1")) {
            try {
                Long id = Long.parseLong(request.getParameter("search_input"));
                ReceiptDto receipt = receiptBean.findReceiptById(id);
                if (receipt == null) {
                    request.setAttribute("message", "No receipt found.");
                }
                request.setAttribute("receipt", receipt);
                request.getRequestDispatcher("/WEB-INF/pages/manageReturn.jsp").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("message", "No receipt found.");
            }

        } else if (valid.equals("form2")) {
            Long idReceipt = Long.parseLong(receiptId);
                 if (idReceipt != null) {
                HashMap<ProductDto, Long> productsAndQuantityById = receiptProductsItemBean.getProductsByReceiptId(idReceipt);
                invoiceBean.setIdsToQuantity(productsAndQuantityById);
                invoiceBean.setRecipeID(idReceipt);
                if (!invoiceBean.getProductIds().isEmpty()) {
                    HashMap<ProductDto, Long> IdsToQuantity = invoiceBean.getIdsToQuantity();
                    request.setAttribute("invoices", IdsToQuantity);
                }

            }


        } else if (valid.equals("form3")) {
            if (productIdsAsString != null) {
                List<Long> productIds = new ArrayList<>();
                for (String productIdAsString : productIdsAsString) {
                    productIds.add(Long.parseLong(productIdAsString));
                }
                invoiceBean.returnProducts(productIds);
                invoiceBean.ResetInvoiceSell();
            }
        }
        response.sendRedirect(request.getContextPath() + "/ManageReturn");
    }
}
