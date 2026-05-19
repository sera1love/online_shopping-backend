package ShopSystem;
public class Electronic extends Product {
    public Electronic(String title, double price, String description) { super(title, price, description); }
    @Override public boolean isSubCategory() { return true; }
    @Override public void showInfo() {
        System.out.printf("[Электроника] %s%n Цена: %.0fр | ID: %d%n Описание: %s%n", getTitle(), getPrice(), getId(), getDescription());
    }
}