package ShopSystem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShopInventory {
    private static final List<Product> products = new ArrayList<>();

    // добавление товара
    public static void addProduct(Product product) { products.add(product); }
    public static List<Product> getProducts() { return new ArrayList<>(products); }

    // StreamAPI фильтрация
    public static List<Product> filterByPrice(double min, double max) {
        return products.stream().filter(p -> p.getPrice() >= min && p.getPrice() <= max).collect(Collectors.toList());
    }
    public static List<Product> filterByName(String keyword) {
        return products.stream().filter(p -> p.getTitle().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
    }
    public static List<Product> filterWithDiscount() {
        return products.stream().filter(p -> p.getFinalPrice() < p.getPrice()).collect(Collectors.toList());
    }
    public static List<Product> filterInStock() {
        return products.stream().filter(Product::isInStock).collect(Collectors.toList());
    }
    public static boolean checkAvailability(String title) {
        return products.stream().anyMatch(p -> p.getTitle().equalsIgnoreCase(title) && p.isInStock());
    }
    // проверка доступности товара по названию
    public static Product findCheapest() {
        return products.stream().min(Comparator.comparingDouble(Product::getPrice)).orElse(null);
    }
    // проверка самого дорогого товара
    public static Product findMostExpensive() {
        return products.stream().max(Comparator.comparingDouble(Product::getPrice)).orElse(null);
    }
    // вывод всех товаров
    public static void printAll() { products.forEach(Product::showInfo); }
}