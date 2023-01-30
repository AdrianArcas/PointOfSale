package com.example.pointofsale.servlets.account;

import com.example.pointofsale.common.AccountDto;
import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.ejb.AccountBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
@MultipartConfig
@WebServlet(name = "AddAccountPhoto", value = "/AddAccountPhoto")
public class AddAccountPhoto extends HttpServlet {
    @Inject
    AccountBean accountBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long accountId = Long.parseLong(request.getParameter("account_id"));
        AccountDto accountDto = accountBean.findAccountById(accountId);
        request.setAttribute("account", accountDto);

        request.getRequestDispatcher("/WEB-INF/pages/addAccountPhoto.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long accountId = Long.parseLong(request.getParameter("account_id"));

        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String fileType = filePart.getContentType();
        long fileSize = filePart.getSize();
        byte[] fileContent = new byte[(int) fileSize];
        filePart.getInputStream().read(fileContent);

        accountBean.addPhotoToAccount(accountId, fileName, fileType, fileContent);

        response.sendRedirect(request.getContextPath() + "/Accounts");

    }
}
