package ShopSystem.ClientSystem;

import ShopSystem.Product;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PurchaseRecord {
    private final Product product;
    private final double amount;
    private final LocalDateTime purchaseDate;

    public PurchaseRecord(Product product, double amount) {
        this.product = product;
        this.amount = amount;
        this.purchaseDate = LocalDateTime.now();
    }

    public Product getProduct() { return product; }
    public double getAmount() { return amount; }
    public LocalDateTime getDate() { return purchaseDate; }

    @Override
    public String toString() {
        String date = purchaseDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        return String.format("%s | %s | %.2fр",
                date, product.getTitle(), amount);
    }
}