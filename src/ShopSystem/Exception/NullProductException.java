package ShopSystem.Exception;

public class NullProductException extends ShopSystemException {
    public NullProductException() {
        super("Попытка добавить null-товар в инвентарь запрещена!");
    }
}