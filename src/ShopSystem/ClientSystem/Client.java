package ShopSystem.ClientSystem;
import ShopSystem.Product;
import ShopSystem.interface_OJnS.ClientStatus;
import ShopSystem.interface_OJnS.OrderStatus.OrderStatus;
import java.util.ArrayList;
import java.util.List;

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

    public boolean buyProduct(Product product) {
        if (clientStatus == ClientStatus.BLOCKED) {
            System.out.println("Клиент заблокирован! Покупка невозможна."); return false;
        }
        if (!product.isInStock()) {
            System.out.println("Товар закончился!"); return false;
        }
        if (product.isPaid()) {
            System.out.println("Товар уже оплачен или зарезервирован!"); return false;
        }
        double finalPrice = product.getFinalPrice();
        if (wallet.withdraw(finalPrice)) {
            product.pay(finalPrice);
            product.setInStock(false);
            purchaseHistory.add(new PurchaseRecord(product, finalPrice, OrderStatus.NEW));
            System.out.println("Покупка успешна! Списание: " + finalPrice + "р");
            return true;
        } else {
            System.out.println("Недостаточно средств!"); return false;
        }
    }

    public void topUp(double amount) {
        if (clientStatus == ClientStatus.BLOCKED) { System.out.println("Пополнение недоступно"); return; }
        wallet.deposit(amount);
    }
    @Override public String toString() {
        return super.toString() + " | " + wallet.getFinalStatus() + " | Статус: " + clientStatus.getLabel();
    }
}