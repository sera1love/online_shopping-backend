package ShopSystem.Pattern.Strategy;

import ShopSystem.Product;
import ShopSystem.ClientSystem.Wallet;

public class WalletPayment implements PaymentStrategy {
    private final Wallet wallet;

    public WalletPayment(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean processPayment(Product product, double amount) {
        if (wallet.hasAmountMoney(amount)) {
            wallet.withdraw(amount);
            product.pay(amount);
            System.out.println("Оплата с кошелька успешна!");
            return true;
        }
        System.out.println("Недостаточно средств в кошельке!");
        return false;
    }

    @Override
    public String getStrategyName() {
        return "Кошелек";
    }
}