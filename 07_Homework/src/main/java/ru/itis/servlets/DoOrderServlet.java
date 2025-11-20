package ru.itis.servlets;

import ru.itis.dao.ProductData;
import ru.itis.entities.Cart;
import ru.itis.entities.Product;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/do-order")
public class DoOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        saveOrderToFile(cart);
        session.removeAttribute("cart");
        response.sendRedirect("/cart");
    }

    private void saveOrderToFile(Cart cart) {
        try {
            String appPath = getServletContext().getRealPath("");
            File file = new File(appPath, "orders.txt");
            PrintWriter pw = new PrintWriter(new FileWriter(file, true));
            pw.println("Заказ:");

            for (Map.Entry<Long, Integer> entry : cart.getListForCart().entrySet()) {
                Long id = entry.getKey();
                Integer count = entry.getValue();
                Product product = ProductData.findProductById(id);

                if (product != null) {
                    pw.println("\"" + product.getName() + "\"" + " по цене: " + product.getPrice() + " x" + count);
                }
            }
            pw.println("Итого: " + cart.getTotalPrice() + " рублей");
            pw.close();
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}