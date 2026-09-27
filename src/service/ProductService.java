package service;


import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) { products.add(product); }
    public List<Product> getAllProducts() { return products; }

    public Product searchByName(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }


}
