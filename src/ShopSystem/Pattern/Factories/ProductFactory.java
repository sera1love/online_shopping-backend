package ShopSystem.Pattern.Factories;

import ShopSystem.*;
import ShopSystem.Exception.InvalidProductTypeException;

public class ProductFactory {
    public static Product createProduct(String type, String title, double price, String description) {
        try {
            return switch (type.toLowerCase()) {
                case "mobile" -> new MobileDevice(title, price, description);
                case "electronic" -> new Electronic(title, price, description);
                case "garden" -> new GardenItem(title, price, description);
                default -> throw new InvalidProductTypeException(type);
            };
        } catch (InvalidProductTypeException e) {
            System.out.println("Ошибка создания товара: " + e.getMessage());
            throw e;
        }
    }
}