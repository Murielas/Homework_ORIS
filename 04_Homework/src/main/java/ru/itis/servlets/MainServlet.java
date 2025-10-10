package ru.itis.Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/index")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String PAGE = "src/main/webapp/index.html";
        RequestDispatcher dispatcher = request.getRequestDispatcher(PAGE);
        dispatcher.forward(request, response);
    }
}