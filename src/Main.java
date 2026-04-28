import ShopSystem.*;

public class Main {
    public static void main(String[] args) {
        // создание товаров
        // телефон
        MobileDevice phone = new MobileDevice("Xiaomi Galaxy S200 Ultra", 190000, "Смартфон "
                + "созданный совместно с компаниями Xiaomi, Samsung и Vivo, внутрь встроен новый процессор "
                + "MediaTek Snapdragon SQ4+ на 16 ядер с частотой 4.5GHz и встроенной RTX 3050");

        // лопата
        GardenItem shovel = new GardenItem("Лопата", 1500, "Универсальный инструмент для дома "
                + "Дачи, огорода и кладбища, по приятной цене!");

        // лампа
        Electronic lamp = new Electronic("Настольная лампа", 3000, "Светодиодная лампа Xiaomi "
                + "Leomax Ultra, яркость этой лампы позволит увидеть ближайшие 5 городов!");

        // добавляем товары в каталог
        Product.addProduct(phone);
        Product.addProduct(shovel);
        Product.addProduct(lamp);

        // категории
        Catalog.addCategory(new Category("Электроника", 0, "Электро-товары") {
            @Override
            public double getFinalPrice() {
                return 0;
            }

            @Override
            public void pay(double amount) {

            }

            @Override
            public boolean isPaid() {
                return false;
            }

            @Override public void showInfo() {
                System.out.println("[Категория] " + getTitle() +
                        " | " + getDescription() + " (ID: " + getID() + ")");
            }
        });
        Catalog.addCategory(new Category("Дача и сад", 0, "Товары для дома и дачи") {
            @Override
            public double getFinalPrice() {
                return 0;
            }

            @Override
            public void pay(double amount) {

            }

            @Override
            public boolean isPaid() {
                return false;
            }

            @Override public void showInfo() {
                System.out.println("[Категория] " + getTitle() +
                        " | " + getDescription() + " (ID: " + getID() + ")");
            }
        });

        // запуск меню
        Menu.start();
    }
}