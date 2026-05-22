package ShopSystem;

public class MobileDevice extends Electronic {
    public MobileDevice(String title, double price, String description) {
        super(title, price, description);
    }

    public MobileDevice(String title, double price, String description, int quantity) {
        super(title, price, description, quantity);
    }

    @Override
    public void showInfo() {
        System.out.printf("[Смартфоны] %s%n Цена: %.0fр | ID: %d | В наличии: %d шт.%n Описание: %s%n",
                getTitle(), getPrice(), getId(), getQuantity(), getDescription());
    }
}