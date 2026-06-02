package ShopSystem.Exception;

public class InsufficientFundsException extends ShopSystemException {
    public InsufficientFundsException(double balance, double required) {
        super(String.format("Недостаточно средств! Баланс %.2fp, требуется: %.2fp", balance, required));
    }
}
