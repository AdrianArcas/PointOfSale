package com.example.pointofsale.servlets.account;

import com.example.pointofsale.common.AccountPhotoDto;
import com.example.pointofsale.common.ProductPhotoDto;
import com.example.pointofsale.ejb.AccountBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AccountPhoto", value = "/AccountPhoto")
public class AccountPhoto extends HttpServlet {
    @Inject
    AccountBean accountBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer accountId = Integer.parseInt(request.getParameter("account_id"));
        AccountPhotoDto photo = accountBean.findPhotoByAccountId(accountId);
        if(photo != null){
            response.setContentType(photo.getFileType());
            response.setContentLength(photo.getFileContent().length);
            response.getOutputStream().write(photo.getFileContent());
        }else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
