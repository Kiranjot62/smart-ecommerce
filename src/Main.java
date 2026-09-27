import model.*;
import service.*;
import payment.*;
import util.IdGenerator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductService productService = new ProductService();
        OrderService orderService = new OrderService();

        System.out.println("=== Welcome to Smart E-Commerce ===");
        System.out.println("Are you a: ");
        System.out.println("1. Seller");
        System.out.println("2. Customer");
        System.out.print("Enter choice: ");
        int roleChoice = sc.nextInt();
        sc.nextLine();

        switch (roleChoice) {
            case 1: // Seller flow
                System.out.print("How many products do you want to add? ");
                int productCount = sc.nextInt();
                sc.nextLine();

                for (int i = 0; i < productCount; i++) {
                    System.out.println("\nEnter details for Product " + (i + 1));
                    System.out.print("Enter product category (e.g., Electronics, Clothing, Cosmetics, Books): ");
                    String category = sc.nextLine();

                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter stock: ");
                    int stock = sc.nextInt();
                    sc.nextLine();

                    Product product;
                    // Decide type dynamically
                    if (category.equalsIgnoreCase("Electronics")) {
                        System.out.print("Enter brand: ");
                        String brand = sc.nextLine();
                        product = new Electronics(IdGenerator.generateId(), name, price, stock, brand);
                    } else if (category.equalsIgnoreCase("Clothing")) {
                        System.out.print("Enter size: ");
                        String size = sc.nextLine();

                        product = new Clothing(IdGenerator.generateId(), name, price, stock, size);
                    } else {
                        // Generic product for other categories
                        product = new Product(IdGenerator.generateId(), name, price, stock) {
                            @Override
                            public void displayDetails() {
                                System.out.println(category + ": " + getName() + " | Price: " + getPrice());
                            }
                        };
                    }

                    productService.addProduct(product);
                    System.out.println("Product added successfully!");
                }
                break;

            case 2: // Customer flow
                System.out.print("\nEnter your name: ");
                String name = sc.nextLine();
                System.out.print("Enter your email: ");
                String email = sc.nextLine();
                Customer customer = new Customer(IdGenerator.generateId(), name, email);

                Cart cart = new Cart();

                while (true) {
                    System.out.println("\nAvailable Products:");
                    productService.getAllProducts().forEach(Product::displayDetails);

                    System.out.print("\nEnter product name to add to cart (or 'checkout' to finish): ");
                    String choice = sc.nextLine();

                    if (choice.equalsIgnoreCase("checkout")) break;

                    Product selected = productService.searchByName(choice);
                    if (selected != null) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        sc.nextLine();
                        if (qty <= selected.getStock()) {
                            cart.addItem(selected, qty);
                            selected.setStock(selected.getStock() - qty);
                            System.out.println(qty + " " + selected.getName() + " added to cart.");
                        } else {
                            System.out.println("Not enough stock available!");
                        }
                    } else {
                        System.out.println("Product not found!");
                    }
                }

                Order order = new Order(IdGenerator.generateId(), customer, cart.getItems());
                orderService.placeOrder(order);

                System.out.println("\n=== Order Summary ===");
                order.displayOrder();

                System.out.print("\nChoose payment method (1-UPI, 2-Card): ");
                int payChoice = sc.nextInt();
                Payment payment = (payChoice == 1) ? new UPIPayment() : new CardPayment();
                payment.pay(cart.calculateTotal());

                System.out.println("\nThank you for shopping with us!");
                break;

            default:
                System.out.println("Invalid choice! Please restart the app.");
        }

        sc.close();

    }
}