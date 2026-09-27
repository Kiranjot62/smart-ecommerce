package service;

import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders = new ArrayList<>();

    public void placeOrder(Order order) { orders.add(order); }
    public List<Order> getAllOrders() { return orders; }
}
