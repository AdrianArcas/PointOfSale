package com.example.pointofsale.servlets.account;

import com.example.pointofsale.ejb.AccountBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CheckAccount", value = "/CheckAccount")
public class CheckAccount extends HttpServlet {
    @Inject
    AccountBean accountBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getRemoteUser()!=null&&!accountBean.isActiveByUsername(request.getRemoteUser())){
            request.logout();
            request.getSession().invalidate();
            request.setAttribute("message","Account is not active yet");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
