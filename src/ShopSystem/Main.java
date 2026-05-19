package ShopSystem;

public class Main {
    public static void main(String[] args) {
        // создание товаров
        ShopInventory.addProduct(new MobileDevice("Xiaomi Galaxy S200 Ultra", 190000, "Смартфон с процессором Snapdragon SQ+ 656GHz и видеокартой RTX 90690Ti Super"));
        ShopInventory.addProduct(new MobileDevice("iPhone 666 Pro Google Ultra Max", 580000, "Смартфон от Samsung Wall S600"));
        ShopInventory.addProduct(new MobileDevice("HONOR X8 4G", 35000, "Смартафон средне-бюджетного класса под такси"));
        ShopInventory.addProduct(new MobileDevice("POCO CHOCO COCO COLA POLA MEGA ULTRA XIAOMI PRO MAX", 186700000, "Телефон, самолет, танк, пельмень, дом, кровать, аптека, 100000 в одном"));
        ShopInventory.addProduct(new GardenItem("Лопата Универсал", 1500, "Для дома, дачи и кладбища"));
        ShopInventory.addProduct(new GardenItem("Грабли LEOMAX", 1750, "Для огорода, могилы, бетона, с встроенным фонариком из высококачественного пластика"));
        ShopInventory.addProduct(new GardenItem("Садовый Шланг 'Малина' 25м", 560, "Отлично подойдет для полива малины, земляники маслины, подливы и всего огорода! А также бассейнов и уличшых душевых!"));
        ShopInventory.addProduct(new GardenItem("Удобрение 'Дачный домик' 10кг", 1000, "Навалили лучшего чернозема и навоза, специально для вашего огорода!"));
        ShopInventory.addProduct(new GardenItem("Секатор 'Надежда'", 1250, "Крупный, Надежный, Нержавейка с острой заточкой! Надейтесь, что не откусит пальчик!"));
        ShopInventory.addProduct(new GardenItem("Биотуалет 'Дружина'", 3750, "Лучший друг на даче и в огороде! Всегда поможет если съел гнилой малинки! Смотри не промахнись!"));
        ShopInventory.addProduct(new GardenItem("Топор-Колун 'Витязь'", 2970, "Топор-Колун Витязь, лучший друг для дома, сада и дачи! Поможет нарубить дров на всю Ивановку!"));
        ShopInventory.addProduct(new Electronic("Настольная лампа LEOMAX", 3000, "Светодиодная лампа сверхъяркости, защищает от нечисти и темноты!"));
        ShopInventory.addProduct(new Electronic("Microslop Digma Pro", 42423, "Microslop Digma Pro лучших друг для дома и офиса! Его немощный Ryzen 7 5700U не способен даже на браузер! 16GB ОЗУ заставляют его плясать лезгинку чтобы запустить больше 1 программы! По вкусной скидочке!"));
        ShopInventory.addProduct(new Electronic("Microslop China Home", 32248, "Microslop China Home надержный ноутбук для игр и офисных задач! Установленный yzen 5 3500U, 16GB DDR4 и RX Vega 8 позволят вам запустить пасьянс, косынку и сапер! Красивый дизайн с хромированными вставками покажут всем во круг кто приехал с колхоза! По лучшей цене на рынке!"));
        ShopInventory.addProduct(new Electronic("FSI THINK 15 B3RGE-3540XRU", 97962, "Супер производительный ноутбук на i5 210H и RTX 4050 с помозью которых вы сможете запустить Minecraft Classic и играть в консольные 30 кадров! Лучший ноутбук для учебы и просмотра видео в 144p!"));
        ShopInventory.addProduct(new Electronic("ACUR Aspine Pro AG15-36H-47FD", 34486, "14' дюймовый офисный ноутбук на i3 N355 и 8GB DDR4, лучший ноутбук, за лучшую цену с Русской раскладкой!"));
        ShopInventory.addProduct(new Electronic("Thanderobolt Ranke 17 G8 Pro Max", 132064, "Топовый игровой ноутбук для киберкотлет! В нем установлен топовый процессор iрсений 13620H, 16GB DDR5, 1TB SSD SUPER FAST EDITION и RTX 5060 с 8GB собственной памяти! За скромный прайс для бедняков!"));
        ShopInventory.addProduct(new Electronic("Relotech Системный блок", 25597, "ЛУЧШИЙ ИГРОВОЙ ПК В МИРЕ С Аналогом Intel Core i7, с аналогом GTX 1060, 16GB DDR4 и 1TB SSD"));
        ShopInventory.addProduct(new Electronic("Rolobech Bullet V3 Aquarium", 83907, "Топовый игровой комп на Ryzen 5 7500F и RTX 5060 8GB в прекрасном прозрачном корпусе и водяным охлаждением!"));
        ShopInventory.addProduct(new Electronic("Электро Чайник 'Варила'", 5670, "Чайник 'Варила' наварит вам лучшего кипятка с минимальным количеством биодобавок!"));
        ShopInventory.addProduct(new Electronic("Синтезатор 'Андрей'", 78235, "Синтезатор 'Андрей' испускает лишь чистый звук ведь не имеет динамиков и подключается к вашей аудиосистеме!"));
        ShopInventory.addProduct(new Electronic("Монитор 56' 'Глядило'", 3783467, "Настолько больщой монитор, что вам придется высматривать картинку в 10 метрах от него лишь бы увидеть хоть что-то!"));
        ShopInventory.addProduct(new Electronic("Механическая клавиатура 'Магний'", 10500, "Механическая клавиатура 'Магний' является самой тихой и тактильно приятной клавиатурой в стране!"));
        ShopInventory.addProduct(new Electronic("Игровая мышь Bluuno G8", 3450, "Bluuno G8 позволит максимально четко играть в игрульки на вашем говяном RGB компуктере в любименький сапер с минимальной задержкой на рынке в 10с!"));
        ShopInventory.addProduct(new Electronic("Наушники Sonya P354-15-34w-fs", 15690, "Лучшие на рынке наушники Sonya выдают максимально чистый и громкий звук, с приятным бассом, что разрывает твою халупу в клочья!"));
        ShopInventory.addProduct(new Electronic("Микрофон 'Союз Мультфильм'", 7840, "Лучший электретный микрофон на рынке, что позволит вашим друзьям в Skype услышать хоть что-то!"));

        // категории
        Catalog.addCategory(new Category("Электроника", 0, "Электро-товары") {
            @Override public void showInfo() { System.out.println("[Категория] " + getTitle() + " | " + getDescription()); }
        });
        Catalog.addCategory(new Category("Дача и сад", 0, "Товары для дома и дачи") {
            @Override public void showInfo() { System.out.println("[Категория] " + getTitle() + " | " + getDescription()); }
        });

        // запуск
        Menu.start();
    }
}