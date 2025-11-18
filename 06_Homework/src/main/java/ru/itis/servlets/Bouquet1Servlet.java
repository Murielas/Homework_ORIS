package ru.itis.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bouquet-1")
public class Bouquet1Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String PAGE = "src/main/webapp/bouquet-1.html";
        RequestDispatcher dispatcher = request.getRequestDispatcher(PAGE);
        dispatcher.forward(request, response);
    }
}
