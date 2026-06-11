package ShopSystem.Pattern.Strategy;

import ShopSystem.Categories.Product;
// O - OpenClosed интерфейс для создания новых платежных классов, без изменения старого кода
public interface PaymentStrategy {
    boolean processPayment(Product product, double amount);
    String getStrategyName();
}