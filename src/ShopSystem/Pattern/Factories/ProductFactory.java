package ShopSystem.Pattern.Factories;

import ShopSystem.Electronic;
import ShopSystem.GardenItem;
import ShopSystem.MobileDevice;
import ShopSystem.Product;

public class ProductFactory {
    public static Product createProduct(String type, String title, double price, String description) {
        return switch (type.toLowerCase()) {
            case "mobile" -> new MobileDevice(title, price, description);
            case "electronic" -> new Electronic(title, price, description);
            case "garden" -> new GardenItem(title, price, description);
            default -> throw new IllegalArgumentException("Неизвестный тип товара: " + type);
        };
    }
}