package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() { return items; }

    public double calculateTotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}
