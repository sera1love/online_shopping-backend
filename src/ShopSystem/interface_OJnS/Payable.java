package ShopSystem.interface_OJnS;

public interface Payable {
    double getFinalPrice();
    void pay(double amount);
    boolean isPaid();
}
