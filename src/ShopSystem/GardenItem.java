package ShopSystem;

public class GardenItem extends Product {
    public GardenItem(String title, double price, String description) {
        super(title, price, description);
    }

    public GardenItem(String title, double price, String description, int quantity) {
        super(title, price, description, quantity);
    }

    @Override
    public boolean isSubCategory() { return true; }

    @Override
    public void showInfo() {
        System.out.printf("[Сад] %s%n Цена: %.0fр | ID: %d | В наличии: %d шт.%n Описание: %s%n",
                getTitle(), getPrice(), getId(), getQuantity(), getDescription());
    }
}