package ShopSystem.ClientSystem;
import ShopSystem.Product;
import ShopSystem.interface_OJnS.OrderStatus.OrderStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PurchaseRecord {
    private final Product product;
    private final double amount;
    private final LocalDateTime purchaseDate;
    private OrderStatus orderStatus;

    public PurchaseRecord(Product product, double amount, OrderStatus status) {
        this.product = product;
        this.amount = amount;
        this.purchaseDate = LocalDateTime.now();
        this.orderStatus = status;
    }
    public PurchaseRecord(Product product, double amount) { this(product, amount, OrderStatus.NEW); }

    // геттеры и сеттеры
    public Product getProduct() { return product; }
    public double getAmount() { return amount; }
    public LocalDateTime getDate() { return purchaseDate; }
    public OrderStatus getOrderStatus() { return orderStatus; }
    public void setOrderStatus(OrderStatus status) { this.orderStatus = status; }

    @Override public String toString() {
        String date = purchaseDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        return String.format("%s | %s | %.2fр | Статус: %s", date, product.getTitle(), amount, orderStatus.getLabel());
    }
}