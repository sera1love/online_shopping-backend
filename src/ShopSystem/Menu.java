package ShopSystem;
import ShopSystem.ClientSystem.Clients;
import ShopSystem.comporators.PriceComparator;
import ShopSystem.comporators.TypeFilterComparator;
import ShopSystem.interface_OJnS.ClientStatus;
import ShopSystem.interface_OJnS.StatusValidator;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        Clients.initClient();
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Выберите действие: ", 1, 11);
            switch (choice) {
                case 1 -> Catalog.getInstance().showCategories();
                case 2 -> ShopInventory.printAll();
                case 3 -> sortProducts();
                case 4 -> compareProducts();
                case 5 -> Clients.purchaseMenu();
                case 6 -> Clients.showPurchaseHistory();
                case 7 -> Clients.topUpBalance();
                case 8 -> Clients.advancedProductFilter();
                case 9 -> checkClientStatusMenu();
                case 10 -> findExtremes();
                case 11 -> { System.out.println("Выход из программы."); running = false; }
            }
            if (running) System.out.println("=".repeat(50));
        }
    }

    private static void printMenu() {
        String status = Clients.isInitialized() ? Clients.getClient().getWallet().getFinalStatus() : "Клиент не активен";
        System.out.printf("""
        МАГАЗИН - ГЛАВНОЕ МЕНЮ
        %s
        ----------------------------------
        1) Категории
        2) Товары
        3) Сортировка
        4) Сравнение товаров
        5) Купить товар
        6) История покупок
        7) Пополнить баланс
        8) Фильтр товаров
        9) Проверить статус клиента
        10) Проверить самый дешевый/дорогой
        11) Выход
        """, status);
    }

    private static void checkClientStatusMenu() {
        if (!Clients.isInitialized()) { System.out.println("Клиент не создан"); return; }
        System.out.println("""
        Проверка статуса:
        1) Активен или VIP
        2) Заблокирован
        3) Любой статус кроме Inactive
        """);
        int choice = getIntInput("Выбор: ", 1, 3);
        var validator = switch (choice) {
            case 1 -> (ShopSystem.interface_OJnS.StatusValidator<ShopSystem.interface_OJnS.ClientStatus>) s -> s == ShopSystem.interface_OJnS.ClientStatus.ACTIVE || s == ShopSystem.interface_OJnS.ClientStatus.VIP;
            case 2 -> (ShopSystem.interface_OJnS.StatusValidator<ShopSystem.interface_OJnS.ClientStatus>) s -> s == ShopSystem.interface_OJnS.ClientStatus.BLOCKED;
            default -> (ShopSystem.interface_OJnS.StatusValidator<ShopSystem.interface_OJnS.ClientStatus>) s -> s != ShopSystem.interface_OJnS.ClientStatus.INACTIVE;
        };
        boolean result = Clients.checkClientStatus(validator);
        System.out.println(result ? "Статус соответствует!" : "Статус не соответствует.");
    }

    // StreamAPI поиск минимальной и максимальной цены
    private static void findExtremes() {
        Product cheapest = ShopInventory.findCheapest();
        Product mostExpensive = ShopInventory.findMostExpensive();
        System.out.println("Самый дешевый: " + (cheapest != null ? cheapest.getTitle() + " (" + cheapest.getPrice() + "р)" : "Нет товаров"));
        System.out.println("Самый дорогой: " + (mostExpensive != null ? mostExpensive.getTitle() + " (" + mostExpensive.getPrice() + "р)" : "Нет товаров"));
    }

    private static void sortProducts() {
        List<Product> sorted = ShopInventory.getProducts();
        if (sorted.isEmpty()) { System.out.println("Нет товаров для сортировки."); return; }
        System.out.println("""
        СОРТИРОВКА ТОВАРОВ:
        1) По цене (возрастание)
        2) По цене (убывание)
        3) По названию (А-Я)
        4) Только электроника
        5) Только товары для сада
        """);
        int sortChoice = getIntInput("Ваш выбор: ", 1, 5);
        switch (sortChoice) {
            case 1 -> sorted.sort(Comparator.comparingDouble(Product::getPrice));
            case 2 -> sorted.sort(Comparator.comparingDouble(Product::getPrice).reversed());
            case 3 -> sorted.sort(Comparator.comparing(Product::getTitle, String.CASE_INSENSITIVE_ORDER));
            case 4 -> sorted.removeIf(p -> !(p instanceof Electronic));
            case 5 -> sorted.removeIf(p -> !(p instanceof GardenItem));
        }
        System.out.println("Отсортированный список:");
        sorted.forEach(Product::showInfo);
    }

    private static void compareProducts() {
        List<Product> products = ShopInventory.getProducts();
        if (products.size() < 2) { System.out.println("Нужно минимум 2 товара для сравнения."); return; }
        System.out.println("Доступные товары:");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("%d) %s - %.0fр (%s)%n", i+1, p.getTitle(), p.getPrice(), p.getClass().getSimpleName());
        }
        int idx1 = getIntInput("Первый товар (номер): ", 1, products.size()) - 1;
        int idx2 = getIntInput("Второй товар (номер): ", 1, products.size()) - 1;
        Product c1 = products.get(idx1);
        Product c2 = products.get(idx2);
        System.out.println("Результат сравнения:");
        System.out.printf("Товар 1: %s | Цена: %.0fр%n", c1.getTitle(), c1.getPrice());
        System.out.printf("Товар 2: %s | Цена: %.0fр%n", c2.getTitle(), c2.getPrice());
        if (c1.getPrice() != c2.getPrice()) {
            double diff = Math.abs(c1.getPrice() - c2.getPrice());
            String cheaper = c1.getPrice() < c2.getPrice() ? c1.getTitle() : c2.getTitle();
            System.out.printf("Разница: %.0fр | Выгоднее: %s%n", diff, cheaper);
        }
    }

    private static int getIntInput(String prompt, int min, int max) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) { System.out.print("Введите число от " + min + " до " + max + ": "); scanner.next(); }
        int val = scanner.nextInt(); scanner.nextLine();
        return Math.max(min, Math.min(max, val));
    }
}