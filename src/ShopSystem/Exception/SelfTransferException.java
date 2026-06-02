package ShopSystem.Exception;

public class SelfTransferException extends ShopSystemException {
    public SelfTransferException() {
        super("Попытка перевода средств самому себе запрещена!");
    }
}
