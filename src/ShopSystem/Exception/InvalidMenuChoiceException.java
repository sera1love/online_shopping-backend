package ShopSystem.Exception;

public class InvalidMenuChoiceException extends ShopSystemException {
    public InvalidMenuChoiceException(int choice, int min, int max) {
        super("Некорректный пункт меню: " + choice + ". Ожидается число от " + min + " до " + max + ".");
    }
}