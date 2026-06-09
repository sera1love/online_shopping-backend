package ShopSystem.Exception;

public class InvalidPriceException extends ShopSystemException {
    public InvalidPriceException(double price) {
        super("Некорректная цена: " + price + ". Цена не может быть отрицательной!");
    }
}