package ShopSystem;
import ShopSystem.interface_OJnS.Payable;

public class Electronic extends Category implements Payable {
    public Electronic(String title, double price, String description) {
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
    public boolean isSubCategory() {return true;}

    @Override
    public void showInfo() {
        System.out.println("[Умный дом] - " + getTitle() + "\n "
                + "Цена: " + getPrice() + "р \n "
                + "(ID: " + getID() + ") \n "
                + "Описание: " + getDescription() + "\n "
                + "Статус оплаты: ");
    }
}