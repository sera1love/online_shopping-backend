package ShopSystem.ClientSystem;

import ShopSystem.Product;
import ShopSystem.Exception.*;
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

    public Wallet getWallet() {
        return wallet;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(ClientStatus status) {
        this.clientStatus = status;
    }

    public List<PurchaseRecord> getPurchaseHistory() {
        return new ArrayList<>(purchaseHistory);
    }

    public boolean buyProduct(Product product, int quantity) {
        try {
            if (clientStatus == ClientStatus.BLOCKED) {
                throw new ClientBlockedException(getName());
            }

            if (product == null) {
                throw new ProductNotFoundException("null");
            }

            if (quantity <= 0) {
                throw new InvalidQuantityException(quantity);
            }

            if (!product.isInStock()) {
                throw new ProductOutOfStockException(0, quantity);
            }

            if (product.getQuantity() < quantity) {
                throw new ProductOutOfStockException(product.getQuantity(), quantity);
            }

            double totalPrice = product.getFinalPrice() * quantity;

            if (wallet.withdraw(totalPrice)) {
                product.pay(totalPrice, quantity);
                purchaseHistory.add(new PurchaseRecord(product, totalPrice, quantity, OrderStatus.NEW));
                System.out.printf("Покупка успешна! Куплено: %d шт. | Списание: %.2fр%n",
                        quantity, totalPrice);
                return true;
            }
            return false;

        } catch (ClientBlockedException | ProductNotFoundException |
                 ProductOutOfStockException | InvalidQuantityException e) {
            System.out.println("Ошибка покупки: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Критическая ошибка при покупке: " + e.getMessage());
            return false;
        }
    }

    public boolean buyProduct(Product product) {
        return buyProduct(product, 1);
    }

    public void topUp(double amount) {
        try {
            if (clientStatus == ClientStatus.BLOCKED) {
                throw new ClientBlockedException(getName());
            }
            wallet.deposit(amount);
            System.out.printf("Баланс пополнен на %.2fр%n", amount);
        } catch (ClientBlockedException | InvalidAmountException e) {
            System.out.println("Ошибка пополнения: " + e.getMessage());
        }
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