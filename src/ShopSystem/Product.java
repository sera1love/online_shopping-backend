package ShopSystem;
import ShopSystem.interface_OJnS.Payable;
import ShopSystem.interface_OJnS.OrderStatus.PaymentStatus;

// класс продукта, не расширяет Category (нарушение SRP/LSP исправлено)
public abstract class Product implements Payable {
    private static int idCounter = 1;
    private final int id;
    private final String title;
    private double price;
    private final String description;
    private PaymentStatus paymentStatus = PaymentStatus.NEW;
    private boolean inStock = true;

    public Product(String title, double price, String description) {
        this.id = idCounter++;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    @Override public double getFinalPrice() {
        return price * (paymentStatus == PaymentStatus.PAID ? 0.95 : 1.0);
    }

    @Override public void pay(double amount) {
        if (!inStock) { System.out.println("Товара нет в наличии."); paymentStatus = PaymentStatus.FAILED; return; }
        if (amount >= price) { paymentStatus = PaymentStatus.PAID; System.out.println("Оплата прошла успешно!"); }
        else { paymentStatus = PaymentStatus.FAILED; System.out.println("Недостаточно средств!"); }
    }

    @Override public boolean isPaid() { return paymentStatus == PaymentStatus.PAID; }

    // геттеры и сеттеры
    public int getId() { return id; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDescription() { return description; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus status) { this.paymentStatus = status; }
    public boolean isInStock() { return inStock; }
    public void setInStock(boolean inStock) { this.inStock = inStock; }

    public abstract boolean isSubCategory();

    public abstract void showInfo();
}