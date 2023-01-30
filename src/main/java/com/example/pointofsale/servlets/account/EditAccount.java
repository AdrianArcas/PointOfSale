package com.example.pointofsale.servlets.account;

import com.example.pointofsale.common.AccountDto;
import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.ejb.AccountBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "EditAccount", value = "/EditAccount")
public class EditAccount extends HttpServlet {
    @Inject
    AccountBean accountBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long accountId = Long.parseLong(request.getParameter("account_id"));
        AccountDto accountDto = accountBean.findAccountById(accountId);
        request.setAttribute("account", accountDto);
        request.setAttribute("userGroups", new String[] {"Director", "Manager", "Cashier" });
        request.getRequestDispatcher("/WEB-INF/pages/editAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userGroup = request.getParameter("user_group");
        Boolean is_active = Boolean.valueOf(request.getParameter("is_active"));
        Long account_id = Long.valueOf(request.getParameter("account_id"));
        accountBean.updateAccount(account_id, username, email, password, userGroup, is_active);

        response.sendRedirect(request.getContextPath() + "/Accounts");
    }
}
