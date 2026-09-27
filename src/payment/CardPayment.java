package payment;

public class CardPayment implements Payment{
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Card.");
    }
}
