package ru.itis.servlets;

import ru.itis.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
        if (userService == null) {
            throw new ServletException("UserService not found in ServletContext");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String errorMessage = request.getParameter("error");
        String warningMessage = "";
        if (errorMessage != null) {
            warningMessage = errorMessage;
        }

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"ru\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Цветочный Everbloom</title>");
        out.println("<link rel=\"stylesheet\" href=\"/src/main/webapp/css/styles.css\">");
        out.println("<link rel=\"stylesheet\" href=\"/src/main/webapp/css/personal-account.css\">");
        out.println("<link rel=\"stylesheet\" href=\"/src/main/webapp/css/menu.css\">");

        out.println("<link rel=\"icon\" type=\"image/x-icon\" href=\"/src/main/webapp/images/icon-sunflower.ico\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<header>");
        out.println("<nav>");
        out.println("<ul class=\"menu\">");
        out.println("<li> <a href=\"/index\"> <img id=\"nav_icon\" src=\"/src/main/webapp/images/icon-sunflower.png\"/> </a> </li>");
        out.println("<li> <a href=\"/index\"> Главная </a> </li>");
        out.println("<li class=\"button_catalog\"> <a id=\"button_menu\" class=\"close\" onclick=\"openCatalog()\"> Каталог </a>");
        out.println("<ul class=\"catalog\">");
        out.println("<li> <a> Цветы </a> </li>");
        out.println("<li> <a href=\"/catalog-bouquets\"> Букеты </a> </li>");
        out.println("<li> <a> Подарки </a> </li>");
        out.println("<li> <a> Конструктор </a> </li>");
        out.println("</ul>");
        out.println("</li>");
        out.println("<li> <a href=\"about.html\"> О нас </a> </li>");
        out.println("<li> <a> Доставка </a> </li>");
        out.println("<li> <a href=\"contact.html\"> Контакты </a> </li>");
        out.println("<li> <a href=\"/account\"> Личный кабинет </a> </li>");
        out.println("<li> <a href=\"/cart\"> Корзина </a> </li>");
        out.println("<li> <img onclick=\"changeThemeDark()\" id=\"theme_icon_dark\" src=\"/src/main/webapp/images/icon-theme-d.png\"> </li>");
        out.println("<li> <img onclick=\"changeThemeLight()\" id=\"theme_icon_light\" src=\"/src/main/webapp/images/icon-theme-l.png\"> </li>");
        out.println("</ul>");
        out.println("</nav>");
        out.println("</header>");

        out.println("<div class=\"personal_account\">");
        out.println("<form action=\"/registration\" method=\"POST\">");
        out.println("<p> Регистрация </p>");
        out.println("<label for=\"username\"> Имя пользователя: </label>");
        out.println("<input id=\"username\" type=\"text\" name=\"login\"/>");
        out.println("<label for=\"password\"> Пароль: </label>");
        out.println("<input id=\"password\" type=\"password\" name=\"password\"/>");
        out.println("<br>");
        if (!warningMessage.isEmpty()) {
            out.println("<p id=\"warning_message\">" + warningMessage + "</p>");
        }
        out.println("<input id=\"button\" type=\"submit\" value=\"Зарегистрироваться\" />");
        out.println("<p> <a href=\"/login\"> Хотите войти в аккаунт? </a> </p>");
        out.println("</form>");
        out.println("</div>");

        out.println("<footer>");
        out.println("<p> © Everbloom 2025 </p>");
        out.println("</footer>");

        out.println("<script src=\"/src/main/webapp/js/script.js\"></script>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || login.trim().isEmpty()
            || password == null || password.trim().isEmpty()) {
            response.sendRedirect("/registration?error=" + URLEncoder.encode("Не все поля заполнены", "UTF-8"));
            return;
        }

        if (!isValidUsername(login)) {
            response.sendRedirect("/registration?error=" + URLEncoder.encode("Имя пользователя может содержать только латинские буквы, цифры и нижнее подчёркивание. Длина: 4-20 символов", "UTF-8"));
            return;
        }

        if (!isValidPassword(password)) {
            response.sendRedirect("/registration?error=" + URLEncoder.encode("Пароль должен содержать как минимум одну заглавную букву, одну строчную букву и одну цифру. Длина: 6-12 символов", "UTF-8"));
            return;
        }

        try {
            userService.saveNewUser(login, password);
            response.sendRedirect("/login");
        } catch (IllegalArgumentException e) {
            response.sendRedirect("/registration?error=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
        } catch (Exception e) {
            response.sendRedirect("/registration?error=Registration failed");
        }
    }

    private boolean isValidUsername(String username) {
        return username != null && username.matches("^[a-zA-Z0-9_]{4,20}$");
    }

    private boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,12}$");
    }
}
