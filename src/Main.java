public class Main {
    public static void main(String[] args) {
        // TODO catalog - Сделать новый каталог для магазина, 1 поле - ID будет идти в формате int, 2 Title - в формате String,
        //  3 Price - в формате double. Сделать полную инкапсуляцию и конструктор, в каталоге создаем 2 объекта(каталог1/2)

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

        // Добавляем в общий список
        Catalog.addCategory(phone);
        Catalog.addCategory(shovel);
        Catalog.addCategory(lamp);

        // Добавляем базовые категории
        Catalog.addCategory(new Category("Электроника", 0, "Электро-товары"));
        Catalog.addCategory(new Category("Дача и сад", 0, "Товары для дома и дачи"));


        // вывод категорий и товаров
        System.out.println("Список категорий и товаров:");
        Catalog.showCategories();
    }
}

// TODO - этап 2:
//  2.1 - в классе Product (добавить) должно быть поле Description - описание;
//  Product class должен быть абстрактным
//  2.2 - Category (добавить): 1 - ID, 2 - Title, 3 - Description, список ArrayLisr - сохранит в себе список категорий
//  2.3 - автогенерация ID, каждый новый шаг - новое ID которое генерируется самостоятельно
//  2.4 - наследники Electronic, GardenItem
//  2.5 - создаем новый класс: MobileDevice, должен быть наследником электроники
//  2.6 - создаем несколько объектов из новых классов в main, добавляем
//  2.7 - в Category нужно добавить AddCategory, ShowCategory

// TODO - этап 3:
//  3.1 - нужно создать класс Category (уже есть), добавить ADD_Category, SHOW_Category
//  3.2 - нужно создать счетчик в Category: 1 - сколько категорий, 2 - сколько sub категорий