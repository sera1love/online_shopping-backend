package ShopSystem.Exception;

public class ProductOutOfStockException extends ShopSystemException {
    public ProductOutOfStockException(int available, int requested) {
        super(String.format("Недостаточно товара! В наличии: %d шт., запрошено: %d шт.", available, requested));
    }
}
