package ru.itis.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.entities.Product;
import ru.itis.service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/catalog/*")
public class CatalogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathLine = request.getPathInfo();
        String category = null;

        if (pathLine != null && pathLine.length() > 1) {
            category = pathLine.substring(1);
        }

        String contextPath = request.getContextPath();
        String fullPath = request.getRequestURI();
        String returnPath = fullPath.substring(contextPath.length());

        List<Product> products = ProductService.getProductByCategory(category);
        request.setAttribute("products", products);
        request.setAttribute("requiredCategory", category);
        request.setAttribute("returnPath", returnPath);
        request.getRequestDispatcher("/src/main/webapp/jsp/catalog.jsp").forward(request, response);
    }
}
