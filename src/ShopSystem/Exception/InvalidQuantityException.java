package ShopSystem.Exception;

public class InvalidQuantityException extends ShopSystemException{
    public InvalidQuantityException(int quantity) {
        super("Некорректное количество: " + quantity + ". Количество должно быть больше 0!");
    }
}
