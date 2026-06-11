package ShopSystem.StartUp;

import ShopSystem.Pattern.Factories.ProductFactory;
import ShopSystem.Categories.Catalog;
import ShopSystem.Categories.Category;
import ShopSystem.Categories.Product;
import ShopSystem.Categories.ShopInventory;
import ShopSystem.interface_OJnS.Menu;

import java.util.Random;


public class ShopApplication {
    private static final Random random = new Random();
    public static void start() {
        // инициализация Singleton каталога
        Catalog catalog = Catalog.getInstance();
        seedCategories(catalog);
        seedProducts();
        Menu.start();
    }

    private static void seedCategories(Catalog catalog) {
        catalog.addCategory(new Category("Электроника", 0, "Электро-товары") {
            @Override public void showInfo() {
                System.out.println("[Категория] " + getTitle() + " | " + getDescription());
            }
        });

        catalog.addCategory(new Category("Дача и сад", 0, "Товары для дома и дачи") {
            @Override public void showInfo() {
                System.out.println("[Категория] " + getTitle() + " | " + getDescription());
            }
        });
    }

    private static void seedProducts() {
        ShopInventory.generateRandomProducts(15, 30);
        addProductWithQty("mobile", "Xiaomi Galaxy S200 Ultra", 190000, "Смартфон с процессором Snapdragon SQ+ 656GHz", 5);
        addProductWithQty("mobile", "iPhone 666 Pro Google Ultra Max", 580000, "Смартфон от Samsung Wall S600", 3);
        addProductWithQty("garden", "Лопата Универсал", 1500, "Для дома, дачи и кладбища", 20);
        addProductWithQty("electronic", "Настольная лампа LEOMAX", 3000, "Светодиодная лампа сверхъяркости", 12);
    }

    private static void addProductWithQty(String type, String title, double price, String desc, int qty) {
        Product product = ProductFactory.createProduct(type, title, price, desc);
        product.setQuantity(qty);
        ShopInventory.addProduct(product);
    }
}