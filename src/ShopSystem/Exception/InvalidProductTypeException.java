package ShopSystem.Exception;

public class InvalidProductTypeException extends ShopSystemException {
    public InvalidProductTypeException(String type) {
        super("Неизвестный тип товара '" + type + "'. Допустимые mobile, electronic, garden.");
    }
}
