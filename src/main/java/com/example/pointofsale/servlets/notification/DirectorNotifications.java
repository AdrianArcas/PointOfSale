package com.example.pointofsale.servlets.notification;

import com.example.pointofsale.common.DirectorNotificationDto;
import com.example.pointofsale.ejb.DirectorNotificationBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
@DeclareRoles({"Manager", "Cashier","Director"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"Director"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed =
                {"Director"})})
@WebServlet(name = "DirectorNotifications", value = "/DirectorNotifications")
public class DirectorNotifications extends HttpServlet {
    @Inject
    DirectorNotificationBean notificationBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DirectorNotificationDto> notifications = notificationBean.findAllNotifications();
        request.setAttribute("notifications", notifications);
        request.getRequestDispatcher("/WEB-INF/pages/directorNotification.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
