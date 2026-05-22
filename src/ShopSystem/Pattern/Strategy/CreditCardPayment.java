package ShopSystem.Pattern.Strategy;

import ShopSystem.Product;

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(Product product, double amount) {
        System.out.println("Обработка оплаты картой: " + amount + "р");
        product.pay(amount);
        return product.isPaid();
    }

    @Override
    public String getStrategyName() {
        return "Кредитная карта";
    }
}