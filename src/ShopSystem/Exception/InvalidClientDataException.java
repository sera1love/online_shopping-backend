package ShopSystem.Exception;

public class InvalidClientDataException extends ShopSystemException {
    public InvalidClientDataException(String message) {
        super("Некорректные данные клиента: " + message);
    }
}