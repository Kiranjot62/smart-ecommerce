package model;

public class Electronics extends Product{
    private final String brand;

    public Electronics(String id, String name, double price, int stock, String brand) {
        super(id, name, price, stock);
        this.brand = brand;
    }

    public void displayDetails() {
        System.out.println("Electronics: " + getName() + " | Brand: " + brand + " | Price: " + getPrice());
    }
}
