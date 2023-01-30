package com.example.pointofsale.servlets.notification;

import com.example.pointofsale.common.NotificationDto;
import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.ejb.NotificationBean;
import com.example.pointofsale.entities.NotificationAccountsDirector;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Notifications", value = "/Notifications")
public class Notifications extends HttpServlet {
    @Inject
    NotificationBean notificationBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<NotificationDto> notifications = notificationBean.findAllNotifications();
        request.setAttribute("notifications", notifications);
        request.getRequestDispatcher("/WEB-INF/pages/notification.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
