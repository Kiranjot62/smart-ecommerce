package payment;

public class UPIPayment implements Payment{
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via UPI.");
    }
}
