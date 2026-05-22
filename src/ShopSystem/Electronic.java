package ShopSystem;

public class Electronic extends Product {
    public Electronic(String title, double price, String description) {
        super(title, price, description);
    }

    public Electronic(String title, double price, String description, int quantity) {
        super(title, price, description, quantity);
    }

    @Override
    public boolean isSubCategory() { return true; }

    @Override
    public void showInfo() {
        System.out.printf("[Электроника] %s%n Цена: %.0fр | ID: %d | В наличии: %d шт.%n Описание: %s%n",
                getTitle(), getPrice(), getId(), getQuantity(), getDescription());
    }
}