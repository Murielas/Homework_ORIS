package ru.itis.servlets;


import ru.itis.dao.CategoryData;
import ru.itis.dao.ProductCategoryData;
import ru.itis.dao.ProductData;
import ru.itis.entities.CategoryEntity;
import ru.itis.entities.Product;
import ru.itis.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/catalog-bouquets")
public class BouquetCatalogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        boolean userIsAdmin = isAdmin != null && isAdmin;
        List<CategoryEntity> categories = null;

        try {
            categories = CategoryService.getCategories();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String nameCategory = "Букеты";
        Long categoryId = null;
        try {
            categoryId = CategoryData.findCategoryByName(nameCategory).getId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"ru\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Цветочный Everbloom</title>");
        out.println("<link rel=\"stylesheet\" href=\"/src/main/webapp/css/styles.css\">");
        out.println("<link rel=\"stylesheet\" href=\"/src/main/webapp/css/catalog.css\">");
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

        out.println("<h1 id=\"h1_catalog\"> Каталог букетов </h1>");
        out.println("<div class=\"catalog_section\">");
        if (userIsAdmin) {
            out.println("<button onclick=\"openCreatingMenu()\" id=\"create_button\"> Добавить товар </button>");
            out.println("<div id=\"creating_menu\">");
            out.println("<h3> Создать новый товар: </h3>");
            out.println("<form action=\"catalog-bouquets\" method=\"POST\">");
            out.println("<input type=\"hidden\" name=\"operation\" value=\"create\"/>");
            out.println("<label for=\"product_name\"> Название товара: </label>");
            out.println("<input id=\"product_name\" type=\"text\" name=\"product_name\"/>");
            out.println("<br>");

            out.println("<label> Категория: </label>");
            if (categories != null) {
                for (CategoryEntity category : categories) {
                    out.println("<label>");
                    out.println("<input type=\"checkbox\" name=\"categories\" value=" + category.getId() + ">");
                    out.println(category.getName());
                    out.println("</label>");
                }
            }

            out.println("<br>");
            out.println("<label for=\"product_price\"> Стоимость товара: </label>");
            out.println("<input id=\"product_price\" type=\"number\" name=\"product_price\"/>");
            out.println("<br>");
            out.println("<label for=\"product_description\"> Описание товара: </label>");
            out.println("<br>");
            out.println("<textarea id=\"product_description\" name=\"product_description\" rows=\"5\"></textarea>");
            out.println("<br>");
            out.println("<label for=\"photo_name\"> Имя файла фотографии с расширением: </label>");
            out.println("<input id=\"photo_name\" type=\"text\" name=\"photo_name\"/>");
            out.println("<input id=\"button\" type=\"submit\" value=\"Сохранить товар\"/>");
            out.println("</form>");
            out.println("</div>");
        }


        out.println("<div class=\"products_container\">");
        List<Product> products = null;
        try {
            products = ProductCategoryData.findProductsByCategory(categoryId);
        } catch (SQLException e) {
            System.out.println("Ошибка: " + e);
            products = new ArrayList<>();
        }

        if (products != null) {
            for (Product product : products) {
                out.println("<div class=\"product_card\">");
                out.println("<img class=\"product_image\" src=\"" + product.getPhotoPath() + "\">");
                out.println("<p>" + product.getName() + "</p>");
                out.println("<p>" + product.getPrice() + "</p>");
                out.println("<form action=\"/add-cart\" method=\"post\">");
                out.println("<input type=\"hidden\" name=\"id\" value=\"" + product.getId() + "\">");
                out.println("<input type=\"hidden\" name=\"name\" value=\"" + product.getName() + "\">");
                out.println("<input type=\"hidden\" name=\"price\" value=\"" + product.getPrice() + "\">");
                out.println("<input type=\"hidden\" name=\"returnUrl\" value=\"/catalog-bouquets\">");
                out.println("<button type=\"submit\" class=\"buy_button\"> В корзину </button>");
                out.println("</form>");

                if (userIsAdmin) {
                    out.println("<form action=\"/delete-product\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"id\" value=\"" + product.getId() + "\">");
                    out.println("<input type=\"hidden\" name=\"returnUrl\" value=\"/catalog-bouquets\">");
                    out.println("<button type=\"submit\" class=\"delete_button\"> Удалить </button>");
                    out.println("</form>");
                    out.println("<button onclick=\"openUpdatingMenu(" + product.getId() + ")\" id=\"update_button\"> Изменить </button>");
                }

                out.println("</div>");

                out.println("<div id=\"updating_menu_" + product.getId() + "\" class=\"updating_menu\">");
                out.println("<h3> Редактировать товар: </h3>");
                out.println("<form action=\"catalog-bouquets\" method=\"POST\">");
                out.println("<input type=\"hidden\" name=\"operation\" value=\"update\"/>");
                out.println("<input id=\"product_id\" type=\"hidden\" name=\"product_id\"  value=\"" + product.getId() + "\"/>");
                out.println("<label for=\"product_name_" + product.getId() + "\"> Название товара: </label>");
                out.println("<input id=\"product_name_" + product.getId() + "\" type=\"text\" name=\"product_name\" class=\"product_name\" value=\"" + product.getName() + "\"/>");
                out.println("<br>");
                out.println("<label for=\"product_price_" + product.getId() + "\"> Стоимость товара: </label>");
                out.println("<input id=\"product_price_" + product.getId() + "\" type=\"number\" name=\"product_price\" class=\"product_price\" value=\"" + product.getPrice() + "\"/>");
                out.println("<br>");
                out.println("<label for=\"product_description_" + product.getId() + "\"> Описание товара: </label>");
                out.println("<br>");
                out.println("<textarea id=\"product_description_" + product.getId() + "\" name=\"product_description\" class=\"product_description\" rows=\"5\">" + product.getDescription() + "</textarea>");
                out.println("<br>");
                out.println("<label for=\"photo_name_" + product.getId() + "\"> Имя файла фотографии с расширением: </label>");
                out.println("<input id=\"photo_name_" + product.getId() + "\" type=\"text\" name=\"photo_name\" class=\"photo_name\" value=\"" + extractFileName(product.getPhotoPath()) + "\"/>");
                out.println("<input id=\"button\" type=\"submit\" value=\"Сохранить товар\"/>");
                out.println("</form>");
                out.println("</div>");
            }
        }

        out.println("</div>");

        out.println("</div>");
        out.println("<footer>");
        out.println("<p> © Everbloom 2025 </p>");
        out.println("</footer>");

        out.println("<script src=\"/src/main/webapp/js/script.js\"></script>");
        out.println("<script src=\"/src/main/webapp/js/catalog-button.js\"></script>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String operation = request.getParameter("operation");

        if ("update".equals(operation)) {
            String name = request.getParameter("product_name");
            double price = Double.parseDouble(request.getParameter("product_price"));
            String description = request.getParameter("product_description");
            String photo = request.getParameter("photo_name");
            Long idProduct = Long.valueOf(request.getParameter("product_id"));
            try {
                ProductData.updateProduct(idProduct, name, price, description, photo);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("/catalog-bouquets");
        } else {
            String name = request.getParameter("product_name");
            double price = Double.parseDouble(request.getParameter("product_price"));
            String description = request.getParameter("product_description");
            String photo = request.getParameter("photo_name");
            Long idProduct = null;
            try {
                idProduct = ProductData.createProduct(name, price, description, photo);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String[] categoryId = request.getParameterValues("categories");

            if (categoryId != null) {
                for (String idCategory : categoryId) {
                    Long id = Long.parseLong(idCategory);
                    ProductCategoryData.addCategoryToProduct(idProduct, id);
                }
            }
            response.sendRedirect("/catalog-bouquets");
        }

    }

    private String extractFileName(String photoPath) {
        if (photoPath == null || photoPath.isEmpty()) {
            return "";
        }
        return photoPath.substring(photoPath.lastIndexOf("/") + 1);
    }
}
