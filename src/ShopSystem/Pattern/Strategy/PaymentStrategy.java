package ShopSystem.Pattern.Strategy;

import ShopSystem.Product;

public interface PaymentStrategy {
    boolean processPayment(Product product, double amount);
    String getStrategyName();
}