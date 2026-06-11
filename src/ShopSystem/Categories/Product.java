package ShopSystem.Categories;

import ShopSystem.Exception.InvalidPriceException;
import ShopSystem.Exception.InvalidQuantityException;
import ShopSystem.Exception.ProductOutOfStockException;
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
    private int quantity;

    public Product(String title, double price, String description) {
        this(title, price, description, 1);
    }

    public Product(String title, double price, String description, int quantity) {
        if (price < 0) throw new InvalidPriceException(price);
        this.id = idCounter++;
        this.title = title;
        this.price = price;
        this.description = description;
        setQuantity(quantity);
    }

    @Override
    public double getFinalPrice() {
        return price * (paymentStatus == PaymentStatus.PAID ? 0.95 : 1.0);
    }

    public void pay(double amount, int qty) {
        try {
            if (qty <= 0) {
                throw new InvalidQuantityException(qty);
            }
            if (quantity < qty) {
                paymentStatus = PaymentStatus.FAILED;
                throw new ProductOutOfStockException(quantity, qty);
            }
            if (amount >= price * qty) {
                quantity -= qty;
                if (quantity == 0) {
                    paymentStatus = PaymentStatus.PAID;
                }
                System.out.println("Оплата прошла успешно! Куплено: " + qty + " шт.");
            } else {
                paymentStatus = PaymentStatus.FAILED;
                System.out.println("Недостаточно средств для оплаты товара!");
            }
        } catch (InvalidQuantityException | ProductOutOfStockException e) {
            System.out.println("Ошибка оплаты: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void pay(double amount) {
        pay(amount, 1);
    }

    @Override
    public boolean isPaid() {
        return quantity == 0;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) throw new InvalidQuantityException(quantity);
        this.quantity = quantity;
    }

    public void addQuantity(int amount) {
        this.quantity += Math.max(0, amount);
    }

    public boolean isInStock() {
        return quantity > 0;
    }

    public void setInStock(boolean inStock) {
        if (!inStock) this.quantity = 0;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) throw new InvalidPriceException(price);
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus status) {
        this.paymentStatus = status;
    }

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