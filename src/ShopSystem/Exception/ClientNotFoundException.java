package ShopSystem.Exception;

public class ClientNotFoundException extends ShopSystemException {
    public ClientNotFoundException(int id) {
        super("Клиент с ID " + id + " не найден!");
    }
}
