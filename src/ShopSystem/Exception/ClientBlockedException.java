package ShopSystem.Exception;

public class ClientBlockedException extends ShopSystemException {
    public ClientBlockedException(String clientName) {
        super("Клиент '" + clientName + "' заблокирован! Операция невозможна.");
    }
}
