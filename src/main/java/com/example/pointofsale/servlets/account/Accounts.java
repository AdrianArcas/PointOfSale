package com.example.pointofsale.servlets.account;

import com.example.pointofsale.common.AccountDto;
import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.ejb.AccountBean;
import com.example.pointofsale.ejb.DirectorNotificationBean;
import com.example.pointofsale.ejb.ProductBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@DeclareRoles({"Manager", "Cashier","Director"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"Manager"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed =
                {"Manager"})})
@WebServlet(name = "Accounts", value = "/Accounts")
public class Accounts extends HttpServlet {
    @Inject
    AccountBean accountBean;
    @Inject
    DirectorNotificationBean directorNotificationBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<AccountDto> accounts = accountBean.findAllAccounts();
        request.setAttribute("accounts", accounts);
        request.getRequestDispatcher("/WEB-INF/pages/accounts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long accountId = Long.parseLong(request.getParameter("account_id"));
        Long whoAddedOrDeletedAccID=accountBean.getAccountIdByUsername(request.getRemoteUser());
        Long addedOrDeletedAccID=accountId;
        directorNotificationBean.createNotification(whoAddedOrDeletedAccID,addedOrDeletedAccID ,"delete" );

        response.sendRedirect(request.getContextPath() + "/Accounts");
    }
}
