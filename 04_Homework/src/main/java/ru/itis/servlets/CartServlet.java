package ru.itis.Servlets;

import ru.itis.entities.Cart;
import ru.itis.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private Map<Integer, Product> getProductCatalog() {
        Map<Integer, Product> catalog = new HashMap<>();
        catalog.put(11, new Product(11,"Тёмный роман", 3400.0));
        catalog.put(12, new Product(12,"Алые грёзы", 3500.0));
        catalog.put(13, new Product(13,"Тёплый мрамор", 3000.0));
        return catalog;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Map<Integer, Product> prodCatalog = getProductCatalog();

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"ru\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Цветочный Everbloom</title>");
        out.println("<link rel=\"stylesheet\" href=\"/src/main/webapp/css/styles.css\">");
        out.println("<link rel=\"stylesheet\" href=\"/src/main/webapp/css/cart.css\">");
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
        out.println("<li> <a> Букеты </a> </li>");
        out.println("<li> <a> Подарки </a> </li>");
        out.println("<li> <a> Конструктор </a> </li>");
        out.println("</ul>");
        out.println("</li>");
        out.println("<li> <a href=\"about.html\"> О нас </a> </li>");
        out.println("<li> <a> Доставка </a> </li>");
        out.println("<li> <a href=\"contact.html\"> Контакты </a> </li>");
        out.println("<li> <a href=\"personal-account.html\"> Личный кабинет </a> </li>");
        out.println("<li> <a href=\"/cart\"> Корзина </a> </li>");
        out.println("<li> <img onclick=\"changeThemeDark()\" id=\"theme_icon_dark\" src=\"/src/main/webapp/images/icon-theme-d.png\"> </li>");
        out.println("<li> <img onclick=\"changeThemeLight()\" id=\"theme_icon_light\" src=\"/src/main/webapp/images/icon-theme-l.png\"> </li>");
        out.println("</ul>");
        out.println("</nav>");
        out.println("</header>");

        out.println("<h1> Ваша корзина <h1>");
        if (cart == null) {
            out.println("<p> В корзине пусто, добавьте товары </p>");
        } else {
            out.println("<div class=\"Cart\">");
            double price = 0;
            for (Map.Entry<Integer, Integer> entry : cart.getListForCart().entrySet()) {
                Integer id = entry.getKey();
                Integer count = entry.getValue();
                Product product = prodCatalog.get(id);

                if (product != null) {
                    out.println("<div class=\"Product\">");
                    out.println("<p>" + product.getName() + "   x" + count + "   -----  " + product.getPrice() + "  руб </p>");
                    out.println("</div>");
                }
            }
            out.println("<div class=\"Price\">");
            out.println("<p> Итого:  " + cart.getTotalPrice(prodCatalog) + " рублей </p>");
            out.println("</div>");

            out.println("<form action=\"/do-order\" method=\"post\">");
            out.println("<button id=\"order_button\"> Сделать заказ </button>");
            out.println("</form>");

        }
        out.println("<footer>");
        out.println("<p> © Everbloom 2025 </p>");
        out.println("</footer>");

        out.println("<script src=\"/src/main/webapp/js/script.js\"></script>");
        out.println("</body>");
        out.println("</html>");
    }
}