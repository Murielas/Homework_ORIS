package ru.itis.Servlets;

import ru.itis.entities.Cart;
import ru.itis.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/do-order")
public class DoOrderServlet extends HttpServlet {
    private Map<Integer, Product> getProductCatalog() {
        Map<Integer, Product> catalog = new HashMap<>();
        catalog.put(11, new Product(11,"Тёмный роман", 3400.0));
        catalog.put(12, new Product(12,"Алые грёзы", 3500.0));
        catalog.put(13, new Product(13,"Тёплый мрамор", 3000.0));
        return catalog;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        Map<Integer, Product> prodCatalog = getProductCatalog();
        saveOrderToFile(cart, prodCatalog);
        session.removeAttribute("cart");
        response.sendRedirect("/cart");
    }

    private void saveOrderToFile(Cart cart, Map<Integer, Product> prodCatalog) {
        try {
            String appPath = getServletContext().getRealPath("");
            File file = new File(appPath, "orders.txt");
            PrintWriter pw = new PrintWriter(new FileWriter(file, true));
            pw.println("Заказ:");

            for (Map.Entry<Integer, Integer> entry : cart.getListForCart().entrySet()) {
                Integer id = entry.getKey();
                Integer count = entry.getValue();
                Product product = prodCatalog.get(id);

                if (product != null) {
                    pw.println("\"" + product.getName() + "\"" + " по цене: " + product.getPrice() + " x" + count);
                }
            }
            pw.println("Итого: " + cart.getTotalPrice(prodCatalog) + " рублей");
            pw.close();
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}