package ShopSystem.ClientSystem;

import ShopSystem.Product;
import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    private final Wallet wallet;
    private final List<PurchaseRecord> purchaseHistory;

    public Client(String name, String phone, double initialBalance) {
        super(name, phone);
        this.wallet = new Wallet(initialBalance);
        this.purchaseHistory = new ArrayList<>();
    }

    public Wallet getWallet() { return wallet; }

    public List<PurchaseRecord> getPurchaseHistory() {
        return new ArrayList<>(purchaseHistory);
    }

    // покупка товара
    public boolean buyProduct(Product product) {
        if (product.isPaid()) {
            System.out.println("Товар уже оплачен!");
            return false;
        }

        double finalPrice = product.getFinalPrice();

        if (wallet.withdraw(finalPrice)) {
            product.pay(finalPrice); // отмечаем как оплаченный
            purchaseHistory.add(new PurchaseRecord(product, finalPrice));

            System.out.println("Покупка успешна!");
            System.out.println("Товар: " + product.getTitle());
            System.out.println("Списано: " + finalPrice + "р");
            System.out.println(wallet.getFinalStatus());
            return true;
        } else {
            System.out.println("Недостаточно средств!");
            System.out.println("Нужно: " + finalPrice + "р");
            System.out.println("Доступно: " + wallet.checkBalance() + "р");
            return false;
        }
    }

    // пополнение баланса клиента
    public void topUp(double amount) {
        wallet.deposit(amount);
    }

    @Override
    public String toString() {
        return super.toString() + " | " + wallet.getFinalStatus();
    }
}
