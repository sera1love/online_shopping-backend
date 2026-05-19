package ShopSystem;
public class GardenItem extends Product {
    public GardenItem(String title, double price, String description) { super(title, price, description); }
    @Override
    public boolean isSubCategory() { return true; }
    @Override public void showInfo() {
        System.out.printf("[Сад] %s%n Цена: %.0fр | ID: %d%n Описание: %s%n", getTitle(), getPrice(), getId(), getDescription());
    }
}