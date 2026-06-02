package ShopSystem.Exception;

public class ShopSystemException extends RuntimeException {
    public ShopSystemException(String message) {
        super(message);
    }

    public ShopSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
