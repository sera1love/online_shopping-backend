package ShopSystem;
public class MobileDevice extends Electronic {
    public MobileDevice(String title, double price, String description) { super(title, price, description); }
    @Override public void showInfo() {
        System.out.printf("[Смартфоны] %s%n Цена: %.0fр | ID: %d%n Описание: %s%n", getTitle(), getPrice(), getId(), getDescription());
    }
}