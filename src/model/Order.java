package model;

import java.util.List;

public class Order {
    private final String id;
    private final Customer customer;
    private final List<CartItem> items;

    public Order(String id, Customer customer, List<CartItem> items) {
        this.id = id;
        this.customer = customer;
        this.items = items;
    }

    public void displayOrder() {
        System.out.println("Order ID: " + id + " | Customer: " + customer.getName());
        items.forEach(item -> System.out.println(item.getProduct().getName() + " x " + item.getQuantity()));
        System.out.println("Total: " + items.stream().mapToDouble(CartItem::getTotalPrice).sum());
    }
}
