package ru.itis.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.dao.ProductData;
import ru.itis.entities.Product;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/product/*")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathLine = request.getPathInfo();
        String productName = null;
        Product product = null;

        if (pathLine != null && pathLine.length() > 1) {
            productName = pathLine.substring(1);
        }

        String contextPath = request.getContextPath();
        String fullPath = request.getRequestURI();
        String returnPath = fullPath.substring(contextPath.length());

        try {
            product = ProductData.findProductByName(productName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("product", product);
        request.setAttribute("returnPath", returnPath);
        request.getRequestDispatcher("/src/main/webapp/jsp/product.jsp").forward(request, response);
    }
}
