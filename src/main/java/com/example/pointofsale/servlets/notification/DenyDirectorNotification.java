package com.example.pointofsale.servlets.notification;

import com.example.pointofsale.ejb.AccountBean;
import com.example.pointofsale.ejb.DirectorNotificationBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
@DeclareRoles({"Manager", "Cashier","Director"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"Director"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed =
                {"Director"})})
@WebServlet(name = "DenyDirectorNotification", value = "/DenyDirectorNotification")
public class DenyDirectorNotification extends HttpServlet {
    @Inject
    AccountBean accountBean;
    @Inject
    DirectorNotificationBean directorNotificationBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String notificationID = request.getParameter("id");
        String username = request.getParameter("username");
        if(action.equals("add")){

            accountBean.deleteAccountById(accountBean.getAccountIdByUsername(username));
            accountBean.deleteUserGroupByUsername(username);
        }
        directorNotificationBean.deleteNotificationById(Long.valueOf(notificationID));
        response.sendRedirect(request.getContextPath() + "/DirectorNotifications");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
