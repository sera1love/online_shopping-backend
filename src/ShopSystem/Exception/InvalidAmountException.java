package ShopSystem.Exception;

public class InvalidAmountException extends ShopSystemException {
    public InvalidAmountException(double amount) {
        super("Некорректная сумма: " + amount + ". Сумма должна быть положительной!");
    }
}
