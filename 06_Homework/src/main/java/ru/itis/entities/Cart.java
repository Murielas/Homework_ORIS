package ru.itis.entities;

import ru.itis.dao.ProductData;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Long, Integer> listForCart;

    public Cart() {
        this.listForCart = new HashMap<>();
    }

    public Map<Long, Integer> getListForCart() {
        return listForCart;
    }

    public void addProduct(Long id) {
        listForCart.put(id, listForCart.getOrDefault(id, 0) + 1);
    }

    public void removeProduct(Long id) {
        if (listForCart.containsKey(id)) {
            int count = listForCart.get(id);
            if (count > 1) {
                listForCart.put(id, count - 1);
            } else {
                listForCart.remove(id);
            }
        }
    }

    public double getTotalPrice() throws SQLException {
        double price = 0.0;
        for (Map.Entry<Long, Integer> entry : listForCart.entrySet()) {
            Long id = entry.getKey();
            Integer count = entry.getValue();

            price += ProductData.findProductById(id).getPrice() * count;

        }
        return price;
    }

}
