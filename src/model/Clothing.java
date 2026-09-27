package model;

public class Clothing extends Product{
    private final String size;

    public Clothing(String id, String name, double price, int stock, String size) {
        super(id, name, price, stock);
        this.size = size;
    }

    @Override
    public void displayDetails() {
        System.out.println("Clothing: " + getName() + " | Size: " + size + " | Price: " + getPrice());
    }
}
