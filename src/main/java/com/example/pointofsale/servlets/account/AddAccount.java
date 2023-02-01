package com.example.pointofsale.servlets.account;

import com.example.pointofsale.ejb.AccountBean;
import com.example.pointofsale.ejb.DirectorNotificationBean;
import com.example.pointofsale.entities.NotificationAccountsDirector;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Arrays;
@DeclareRoles({"Manager", "Cashier","Director"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"Manager"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed =
                {"Manager"})})
@WebServlet(name = "AddAccount", value = "/AddAccount")
public class AddAccount extends HttpServlet {
    @Inject
    AccountBean accountBean;
    @Inject
    DirectorNotificationBean directorNotificationBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("userGroups", new String[] {"Director", "Manager", "Cashier" });
        request.getRequestDispatcher("/WEB-INF/pages/addAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userGroup = request.getParameter("user_group");

        accountBean.createUser(username, email, password, userGroup);

        Long whoAddedOrDeletedAccID=accountBean.getAccountIdByUsername(request.getRemoteUser());
        Long addedOrDeletedAccID=accountBean.getAccountIdByUsername(username);
        directorNotificationBean.createNotification(whoAddedOrDeletedAccID,addedOrDeletedAccID ,"add" );


        response.sendRedirect(request.getContextPath() + "/Accounts");
    }
}
