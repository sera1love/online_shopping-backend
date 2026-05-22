package ShopSystem;

import ShopSystem.Pattern.Factories.ProductFactory;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ShopInventory {
    private static final List<Product> products = new ArrayList<>();
    private static final Random random = new Random();

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public static void generateRandomProducts(int minCount, int maxCount) {
        int count = random.nextInt(maxCount - minCount + 1) + minCount;

        String[] mobileNames = {
                "Xiaomi Galaxy S200 Ultra", "iPhone 666 Pro Max", "HONOR X8 4G",
                "Samsung Galaxy Z Fold 99", "POCO MEGA ULTRA", "OnePlus 50 Pro"
        };

        String[] electronicNames = {
                "Настольная лампа LEOMAX", "Ноутбук Microslop Digma Pro",
                "Монитор 56' Глядило'", "Клавиатура Магний", "Мышь Bluuno G8",
                "Наушники Sonya P354", "Чайник Варила'", "Системный блок Relotech"
        };

        String[] gardenNames = {
                "Лопата Универсал", "Грабли LEOMAX", "Шланг Малина 25м",
                "Удобрение Дачный домик'", "Секатор Надежда'", "Топор-Колун Витязь'",
                "Биотуалет Дружина'"
        };

        System.out.println("Генерируем " + count + " случайных товаров...");

        for (int i = 0; i < count; i++) {
            int type = random.nextInt(3); // 0-mobile, 1-electronic, 2-garden
            int quantity = random.nextInt(10) + 1; // от 1 до 10 штук
            double price = (random.nextInt(500) + 1) * 100; // от 100 до 50000

            Product product = switch (type) {
                case 0 -> ProductFactory.createProduct("mobile",
                        mobileNames[random.nextInt(mobileNames.length)],
                        price,
                        "Смартфон с процессором Snapdragon SQ+ " + random.nextInt(1000) + "GHz");
                case 1 -> ProductFactory.createProduct("electronic",
                        electronicNames[random.nextInt(electronicNames.length)],
                        price,
                        "Электроника для дома и офиса");
                case 2 -> ProductFactory.createProduct("garden",
                        gardenNames[random.nextInt(gardenNames.length)],
                        price,
                        "Товар для дачи и сада");
                default -> null;
            };

            if (product != null) {
                product.setQuantity(quantity);
                products.add(product);
            }
        }

        System.out.println("Сгенерировано товаров: " + count);
    }

    // StreamAPI фильтрация
    public static List<Product> filterByPrice(double min, double max) {
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }

    public static List<Product> filterByName(String keyword) {
        return products.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static List<Product> filterWithDiscount() {
        return products.stream()
                .filter(p -> p.getFinalPrice() < p.getPrice())
                .collect(Collectors.toList());
    }

    public static List<Product> filterInStock() {
        return products.stream()
                .filter(Product::isInStock)
                .collect(Collectors.toList());
    }

    public static boolean checkAvailability(String title) {
        return products.stream()
                .anyMatch(p -> p.getTitle().equalsIgnoreCase(title) && p.isInStock());
    }

    public static Product findCheapest() {
        return products.stream()
                .min(Comparator.comparingDouble(Product::getPrice))
                .orElse(null);
    }

    public static Product findMostExpensive() {
        return products.stream()
                .max(Comparator.comparingDouble(Product::getPrice))
                .orElse(null);
    }

    public static void printAll() {
        products.forEach(Product::showInfo);
    }
}