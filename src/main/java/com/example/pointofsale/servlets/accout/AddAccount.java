package com.example.pointofsale.servlets.accout;

import com.example.pointofsale.ejb.AccountBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "AddAccount", value = "/AddAccount")
public class AddAccount extends HttpServlet {
    @Inject
    AccountBean accountBean;
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
        response.sendRedirect(request.getContextPath() + "/Products");
    }
}
