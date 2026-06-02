package ShopSystem.Exception;

public class ProductNotFoundException extends ShopSystemException {
    public ProductNotFoundException(String title) {
        super("Товар не найден: " + title);
    }

    public ProductNotFoundException(int id) {
        super("Товар с ID " + id + " не найден!");
    }
}
