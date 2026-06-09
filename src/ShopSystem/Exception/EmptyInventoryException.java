package ShopSystem.Exception;

public class EmptyInventoryException extends ShopSystemException {
    public EmptyInventoryException() {
        super("Инвентарь пуст! Нечего сортировать или сравнивать.");
    }
}