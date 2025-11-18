package ru.itis.servlets;

import ru.itis.entities.UserEntity;
import ru.itis.model.User;
import ru.itis.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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
        out.println("<form action=\"/login\" method=\"POST\">");
        out.println("<p> Вход в личный кабинет </p>");
        out.println("<label for=\"username\"> Имя пользователя: </label>");
        out.println("<input id=\"username\" type=\"text\" name=\"login\"/>");
        out.println("<label for=\"password\"> Пароль: </label>");
        out.println("<input id=\"password\" type=\"password\" name=\"password\"/>");
        out.println("<br>");
        if (!warningMessage.isEmpty()) {
            out.println("<p id=\"warning_message\">" + warningMessage + "</p>");
        }
        out.println("<input id=\"button\" type=\"submit\" value=\"Войти\" />");
        out.println("<p> <a href=\"/registration\"> Ещё нет аккаунта? Зарегистрируйтесь </a> </p>");
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

        User user = userService.authenticateUser(login, password);

        if (user == null) {
            response.sendRedirect("/login?error=" + URLEncoder.encode("Такого пользователя не существует", "UTF-8"));
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.sendRedirect("/index");
        }
    }
}
