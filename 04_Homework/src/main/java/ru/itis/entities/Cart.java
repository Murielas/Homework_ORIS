package ru.itis.entities;

import java.util.*;

public class Cart {
    private Map<Integer, Integer> listForCart;

    public Cart() {
        this.listForCart = new HashMap<>();
    }

    public Map<Integer, Integer> getListForCart() {
        return listForCart;
    }

    public void addProduct(Integer id) {
        listForCart.put(id, listForCart.getOrDefault(id, 0) + 1);
    }

    public void removeProduct(Integer id) {
        if (listForCart.containsKey(id)) {
            int count = listForCart.get(id);
            if (count > 1) {
                listForCart.put(id, count - 1);
            } else {
                listForCart.remove(id);
            }
        }
    }

    public double getTotalPrice(Map<Integer, Product> prods) {
        double price = 0.0;
        for (Map.Entry<Integer, Integer> entry : listForCart.entrySet()) {
            Integer id = entry.getKey();
            Integer count = entry.getValue();
            Product prod = prods.get(id);
            if (prod != null) {
                price += prod.getPrice() * count;
            }
        }
        return price;
    }

}
