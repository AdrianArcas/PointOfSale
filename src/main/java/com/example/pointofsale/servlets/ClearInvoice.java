package com.example.pointofsale.servlets;

import com.example.pointofsale.ejb.InvoiceBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
@DeclareRoles({"Manager", "Cashier","Director"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"Cashier"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed =
                {"Cashier"})})
@WebServlet(name = "ClearInvoice", value = "/ClearInvoice")
public class ClearInvoice extends HttpServlet {
    @Inject
    InvoiceBean invoiceBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        invoiceBean.ResetInvoice();
        String page=request.getParameter("page");
        if(page.equals("sale")){
            response.sendRedirect(request.getContextPath() + "/ProcessSale");
            return;
        }
            response.sendRedirect(request.getContextPath() + "/ManageReturn");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
