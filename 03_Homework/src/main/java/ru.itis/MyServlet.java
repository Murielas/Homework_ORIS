package ru.itis;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>Вход в личный кабинет</h1>");
        out.println("<form method=\"post\" action=\"/hello\">");
        out.println("<label for=\"username\"> Имя пользователя: </label>");
        out.println("<input id=\"username\" type=\"text\" name=\"username\"/>");
        out.println("<br>");
        out.println("<br>");
        out.println("<label for=\"password\"> Пароль: </label>");
        out.println("<input id=\"password\" type=\"password\" name=\"password\"/>");
        out.println("<br>");

        String error = request.getParameter("error");
        if (error != null) {
            out.println("<p style='color: red;'>" + error + "</p>");
        }

        out.println("<br>");
        out.println("<input type=\"submit\">");
        out.println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("Имя пользователя: " + username);
        System.out.println("Пароль: " + password);
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            response.sendRedirect("/hello?error=Error: Empty");
        } else if (username.length() < 4) {
            response.sendRedirect("/hello?error=Error: Username must be longer than 4 characters");
        } else if (password.length() < 6) {
            response.sendRedirect("/hello?error=Error: Password must be longer than 6 characters");
        } else {
            saveToFile(username, password);
            response.sendRedirect("thanks");
        }

    }

    private void saveToFile(String username, String password) {
        try {
            String appPath = getServletContext().getRealPath("");
            File file = new File(appPath, "database.txt");
            PrintWriter pw = new PrintWriter(new FileWriter(file, true));
            String line = "Имя пользователя: " + username + ", пароль: " + password;
            pw.println(line);
            pw.close();
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}