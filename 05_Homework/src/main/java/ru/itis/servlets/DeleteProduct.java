package ru.itis.servlets;

import ru.itis.dao.ProductData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-product")
public class DeleteProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idProduct = Long.valueOf(request.getParameter("id"));
        String returnUrl = request.getParameter("returnUrl");

        HttpSession session = request.getSession();
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

        if (isAdmin == null || !isAdmin) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Доступ запрещён");
            return;
        }

        try {
            ProductData.removeProduct(idProduct);
            response.sendRedirect(returnUrl);
        } catch (SQLException e) {
            response.sendRedirect(returnUrl + "?error=delete_failed");
        }

    }
}
