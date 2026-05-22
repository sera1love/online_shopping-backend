package ShopSystem.ClientSystem;

import ShopSystem.Product;
import ShopSystem.interface_OJnS.ClientStatus;
import ShopSystem.interface_OJnS.OrderStatus.OrderStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client extends Person {
    private final Wallet wallet;
    private final List<PurchaseRecord> purchaseHistory = new ArrayList<>();
    private ClientStatus clientStatus = ClientStatus.ACTIVE;

    public Client(String name, String phone, double initialBalance) {
        super(name, phone);
        this.wallet = new Wallet(initialBalance);
    }

    public Wallet getWallet() { return wallet; }
    public ClientStatus getClientStatus() { return clientStatus; }
    public void setClientStatus(ClientStatus status) { this.clientStatus = status; }
    public List<PurchaseRecord> getPurchaseHistory() { return new ArrayList<>(purchaseHistory); }

    public boolean buyProduct(Product product, int quantity) {
        if (clientStatus == ClientStatus.BLOCKED) {
            System.out.println("Клиент заблокирован! Покупка невозможна.");
            return false;
        }
        if (!product.isInStock()) {
            System.out.println("Товар закончился!");
            return false;
        }
        if (product.getQuantity() < quantity) {
            System.out.println("Недостаточно товара! Доступно: " + product.getQuantity() + " шт.");
            return false;
        }

        double totalPrice = product.getFinalPrice() * quantity;

        if (wallet.withdraw(totalPrice)) {
            product.pay(totalPrice, quantity);
            purchaseHistory.add(new PurchaseRecord(product, totalPrice, quantity, OrderStatus.NEW));
            System.out.printf("Покупка успешна! Куплено: %d шт. | Списание: %.2fр%n",
                    quantity, totalPrice);
            return true;
        } else {
            System.out.println("Недостаточно средств!");
            return false;
        }
    }

    // для совместимости
    // L - Liskov Substitution, наследник работает везде, где работает родитель
    public boolean buyProduct(Product product) {
        return buyProduct(product, 1);
    }

    public void topUp(double amount) {
        if (clientStatus == ClientStatus.BLOCKED) {
            System.out.println("Пополнение недоступно");
            return;
        }
        wallet.deposit(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(wallet, client.wallet) &&
                clientStatus == client.clientStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wallet, clientStatus);
    }

    @Override
    public String toString() {
        return super.toString() + " | " + wallet.getFinalStatus() +
                " | Статус: " + clientStatus.getLabel();
    }
}