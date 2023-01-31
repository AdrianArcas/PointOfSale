package com.example.pointofsale.servlets.notification;

import com.example.pointofsale.ejb.AccountBean;
import com.example.pointofsale.ejb.DirectorNotificationBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ApproveDirectorNotification", value = "/ApproveDirectorNotification")
public class ApproveDirectorNotification extends HttpServlet {
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
        accountBean.activateAccountByUsername(username);
        } else if (action.equals("delete")) {
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
