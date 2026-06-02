package ShopSystem.Exception;

public class ClientNotInitializedException extends ShopSystemException {
    public ClientNotInitializedException() {
        super("Клиент не инициализирован! Сначало выполните создание клиента.");
    }
}
