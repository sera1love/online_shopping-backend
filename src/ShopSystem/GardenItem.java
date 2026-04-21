package ShopSystem;
import ShopSystem.interface_OJnS.Payable;

public class GardenItem extends Category implements Payable {
    public GardenItem(String title, double price, String description) {
        super(title, price, description);
    }

    @Override
    public double getFinalPrice() {
        return getPrice();
    }

    @Override
    public void pay(double amount) {
    }

    @Override
    public boolean isPaid() {
        return false;
    }

    @Override
    public boolean isSubCategory() {
        return true;
    }

    @Override
    public void showInfo() {
        System.out.println("[Сад] - " + getTitle() + "\n "
                + "Цена - " + getPrice() + "\n "
                + "(ID: " + getID() + ") " + "\n "
                + "Описание: " + getDescription() + "\n "
                + "Статус оплаты: ");
    }
}