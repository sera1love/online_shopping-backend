package ShopSystem;

import ShopSystem.Pattern.Factories.ProductFactory;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = Catalog.getInstance();
        ShopInventory.generateRandomProducts(15, 30);
        ShopInventory.addProduct(ProductFactory.createProduct("mobile", "Xiaomi Galaxy S200 Ultra", 190000, "Смартфон с процессором Snapdragon SQ+ 656GHz"));
        ShopInventory.getProducts().get(ShopInventory.getProducts().size() - 1).setQuantity(5);
        ShopInventory.addProduct(ProductFactory.createProduct("mobile", "iPhone 666 Pro Google Ultra Max", 580000, "Смартфон от Samsung Wall S600"));
        ShopInventory.getProducts().get(ShopInventory.getProducts().size() - 1).setQuantity(3);
        ShopInventory.addProduct(ProductFactory.createProduct("garden", "Лопата Универсал", 1500, "Для дома, дачи и кладбища"));
        ShopInventory.getProducts().get(ShopInventory.getProducts().size() - 1).setQuantity(20);

        // категории через Singleton
        catalog.addCategory(new Category("Электроника", 0, "Электро-товары") {
            @Override
            public void showInfo() {
                System.out.println("[Категория] " + getTitle() + " | " + getDescription());
            }
        });

        catalog.addCategory(new Category("Дача и сад", 0, "Товары для дома и дачи") {
            @Override
            public void showInfo() {
                System.out.println("[Категория] " + getTitle() + " | " + getDescription());
            }
        });

        Menu.start();
    }
}