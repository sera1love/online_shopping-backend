package ShopSystem;

import ShopSystem.interface_OJnS.Payable;
import ShopSystem.interface_OJnS.OrderStatus.PaymentStatus;
import java.util.Objects;

public abstract class Product implements Payable {
    private static int idCounter = 1;
    private final int id;
    private final String title;
    private double price;
    private final String description;
    private PaymentStatus paymentStatus = PaymentStatus.NEW;
    private int quantity; // <-- ДОБАВЛЕНО: количество на складе

    public Product(String title, double price, String description) {
        this(title, price, description, 1); // по умолчанию 1 штука
    }

    // <-- ДОБАВЛЕН: конструктор с количеством
    public Product(String title, double price, String description, int quantity) {
        this.id = idCounter++;
        this.title = title;
        this.price = price;
        this.description = description;
        this.quantity = Math.max(0, quantity);
    }

    @Override
    public double getFinalPrice() {
        return price * (paymentStatus == PaymentStatus.PAID ? 0.95 : 1.0);
    }

    // <-- ИЗМЕНЕН: метод pay теперь принимает количество
    public void pay(double amount, int qty) {
        if (quantity < qty) {
            System.out.println("Недостаточно товара на складе! Доступно: " + quantity);
            paymentStatus = PaymentStatus.FAILED;
            return;
        }
        if (amount >= price * qty) {
            quantity -= qty;
            if (quantity == 0) {
                paymentStatus = PaymentStatus.PAID;
            }
            System.out.println("Оплата прошла успешно! Куплено: " + qty + " шт.");
        } else {
            paymentStatus = PaymentStatus.FAILED;
            System.out.println("Недостаточно средств!");
        }
    }

    @Override
    public void pay(double amount) {
        pay(amount, 1); // для совместимости
    }

    @Override
    public boolean isPaid() {
        return quantity == 0; // товар "оплачен" если закончился
    }

    // <-- ДОБАВЛЕНЫ: методы для работы с количеством
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = Math.max(0, quantity); }
    public void addQuantity(int amount) { this.quantity += Math.max(0, amount); }
    public boolean isInStock() { return quantity > 0; }
    public void setInStock(boolean inStock) {
        if (!inStock) this.quantity = 0;
    }

    // Getters и Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDescription() { return description; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus status) { this.paymentStatus = status; }

    public abstract boolean isSubCategory();
    public abstract void showInfo();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price);
    }

    @Override
    public String toString() {
        return String.format("Product[id=%d, title='%s', price=%.2f, qty=%d, status=%s]",
                id, title, price, quantity, paymentStatus);
    }
}