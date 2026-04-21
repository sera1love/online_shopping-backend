import ShopSystem.*;

public class Main {
    public static void main(String[] args) {
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

        // добавляем в список продуктов
        Product.addProduct(phone);
        Product.addProduct(shovel);
        Product.addProduct(lamp);

        // добавляем базовые категории
        Catalog.addCategory(new Category("Электроника", 0, "Электро-товары") {
            @Override
            public void showInfo() {
                System.out.println("[Категория] " + getTitle() + ", [Описание] " + getDescription() + " (ID: " + getID() + ")");
            }
        });
        Catalog.addCategory(new Category("Дача и сад", 0, "Товары для дома и дачи") {
            @Override
            public void showInfo() {
                System.out.println("[Категория] " + getTitle() + ", [Описание] " + getDescription() + " (ID: " + getID() + ")");
            }
        });


        Catalog.showCategories();
        Product.getProductList();

        System.out.println("\nСравнение:");
        Electronic lamp2 = new Electronic("Лампа Xiaomi", 3100, "Свет на 5 городов");
        System.out.println("lamp.equals(lamp2): " + lamp.equals(lamp2));
        System.out.println("lamp.toString(): " + lamp);
    }
}

// TODO - этап 1:
//  1.1 - Сделать новый каталог для магазина, 1 поле - ID будет идти в формате int, 2 Title - в формате String,
//  1.2 - 3 Price - в формате double. Сделать полную инкапсуляцию и конструктор, в каталоге создаем 2 объекта(каталог1/2)

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

// TODO - этап 4 (полиморфизм)
//  4.1 - надо в Category добавить абстрактный метод Show info, который мы будем переобразовывать
//  4.2 - создать несколько разных товаров в Product в списке в виде Array list
//  4.3 - надо в show info что бы весть список показать на экране

// TODO - этап 5:
//  5.1 - создать интерфейс payble(помогает объектам товарам, 3 абстрактных метода:
//   5.1.1 - double(getFinalPrice),
//   5.1.2 - void(pay принимает double, можно назвать amount) - оплата товаров
//   5.1.3 - возвращает boolean(itsPayt)
//  5.2 создать интерфейс finansable(помогает объектам-клиентам), принимет 3 метода:
//   5.2.1 CheckBalance - double
//   5.2.2 HasAmountMoney - boolean
//   5.2.3 GetFinalStatus - String
//  5.3 Необходимо все классы расширять с помощью equals, hashCode, toString, insteadof, DON'T REPEAT
//  5.4 создаете объекты в классах и сравниваете