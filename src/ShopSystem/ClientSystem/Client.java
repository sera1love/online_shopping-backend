package ShopSystem.ClientSystem;

import ShopSystem.Product;
import ShopSystem.interface_OJnS.ClientStatus;
import ShopSystem.interface_OJnS.OrderStatus.OrderStatus;
import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    private final Wallet wallet;
    private final List<PurchaseRecord> purchaseHistory;
    private ClientStatus clientStatus = ClientStatus.ACTIVE; // 7.2

    public Client(String name, String phone, double initialBalance) {
        super(name, phone);
        this.wallet = new Wallet(initialBalance);
        this.purchaseHistory = new ArrayList<>();
    }

    // получение информации о кошельке
    public Wallet getWallet() { return wallet; }

    // получение статуса клиента
    public ClientStatus getClientStatus() { return clientStatus; }
    public void setClientStatus(ClientStatus status) { this.clientStatus = status; }

    // запись о покупке в историю покупок
    public List<PurchaseRecord> getPurchaseHistory() {
        return new ArrayList<>(purchaseHistory);
    }

    // проверка статуса перед покупкой
    public boolean buyProduct(Product product) {
        if (clientStatus == ClientStatus.BLOCKED) {
            System.out.println("Клиент заблокирован! Покупка невозможна.");
            return false;
        }
        if (!product.isInStock()) {
            System.out.println("Товар закончился!");
            return false;
        }
        if (product.isPaid()) {
            System.out.println("Товар уже оплачен!");
            return false;
        }

        double finalPrice = product.getFinalPrice();
        if (wallet.withdraw(finalPrice)) {
            product.pay(finalPrice);
            purchaseHistory.add(new PurchaseRecord(product, finalPrice, OrderStatus.NEW));
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

    // проверка на блокировку клиента
    public void topUp(double amount) {
        if (clientStatus == ClientStatus.BLOCKED) {
            System.out.println("Пополнение недоступно для заблокированного клиента");
            return;
        }
        wallet.deposit(amount);
    }

    // вывод статуса через toString
    @Override
    public String toString() {
        return super.toString() + " | " + wallet.getFinalStatus() + " | Статус: " + clientStatus.getLabel();
    }
}