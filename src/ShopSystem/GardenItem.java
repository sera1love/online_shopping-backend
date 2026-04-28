package ShopSystem;

public class GardenItem extends Category {
    public GardenItem(String title, double price, String description) {
        super(title, price, description);
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

    @Override
    public double getFinalPrice() {
        return 0;
    }

    @Override
    public void pay(double amount) {

    }

    @Override
    public boolean isPaid() {
        return false;
    }
}