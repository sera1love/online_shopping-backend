package ShopSystem.interface_OJnS;
// I - Interface Segregation, интерфейс используется только для определенного действия
public interface Payable {
    double getFinalPrice();
    void pay(double amount);
    boolean isPaid();
}