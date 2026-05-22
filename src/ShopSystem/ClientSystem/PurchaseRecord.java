package ShopSystem.ClientSystem;

import ShopSystem.Product;
import ShopSystem.interface_OJnS.OrderStatus.OrderStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class PurchaseRecord {
    private final Product product;
    private final double amount;
    private final int quantity;
    private final LocalDateTime purchaseDate;
    private final OrderStatus orderStatus;
    private final String transactionId;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private static int transactionCounter = 1;

    public PurchaseRecord(Product product, double amount, int quantity, OrderStatus status) {
        this.product = product;
        this.amount = amount;
        this.quantity = quantity;
        this.purchaseDate = LocalDateTime.now();
        this.orderStatus = status;
        this.transactionId = "TX-" + String.format("%06d", transactionCounter++);
    }

    public PurchaseRecord(Product product, double amount) {
        this(product, amount, 1, OrderStatus.NEW);
    }

    public Product getProduct() { return product; }
    public double getAmount() { return amount; }
    public int getQuantity() { return quantity; } // <-- ДОБАВЛЕН
    public LocalDateTime getDate() { return purchaseDate; }
    public OrderStatus getOrderStatus() { return orderStatus; }
    public String getTransactionId() { return transactionId; }

    public void printTransactionDetails() {
        System.out.printf("%n=== ТРАНЗАКЦИЯ ===%n");
        System.out.printf("ID транзакции: %s%n", transactionId);
        System.out.printf("Товар: %s (ID: %d)%n", product.getTitle(), product.getId());
        System.out.printf("Количество: %d шт.%n", quantity);
        System.out.printf("Цена за единицу: %.2fр%n", amount / quantity);
        System.out.printf("Сумма: %.2fр%n", amount);
        System.out.printf("Дата: %s%n", purchaseDate.format(FORMATTER));
        System.out.printf("Статус: %s%n", orderStatus.getLabel());
        System.out.printf("==================%n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseRecord)) return false;
        PurchaseRecord that = (PurchaseRecord) o;
        return Double.compare(that.amount, amount) == 0 &&
                quantity == that.quantity &&
                Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(product.getId(), that.product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, product.getId(), amount, quantity);
    }

    @Override
    public String toString() {
        String date = purchaseDate.format(FORMATTER);
        return String.format("[%s] %s | %d шт. | %.2fр | %s",
                transactionId, date, quantity, amount,
                product.getTitle());
    }
}