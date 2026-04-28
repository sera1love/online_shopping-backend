package ShopSystem;

import ShopSystem.ClientSystem.Clients;
import ShopSystem.comporators.*;
import java.util.*;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        // инициализация клиента
        Clients.initClient();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Выберите действие: ", 1, 8);

            switch (choice) {
                case 1 -> viewCategories();
                case 2 -> viewProducts();
                case 3 -> sortProducts();
                case 4 -> compareProducts();
                case 5 -> Clients.purchaseMenu();
                case 6 -> Clients.showPurchaseHistory();
                case 7 -> Clients.topUpBalance();
                case 8 -> {
                    System.out.println("Выход из программы. Спасибо за покупки!");
                    running = false;
                }
            }
            if (running) System.out.println("\n" + "=".repeat(50) + "\n");
        }
    }

    private static void printMenu() {
        // показывает баланс клиента в меню
        String status = Clients.isInitialized()
                ? Clients.getClient().getWallet().getFinalStatus()
                : "Клиент не активен";

        System.out.println("""
                \nМАГАЗИН — ГЛАВНОЕ МЕНЮ
                %s
                ----------------------------------
                1) Категории
                2) Товары
                3) Сортировка
                4) Сравнение товаров
                5) Купить товар
                6) История покупок
                7) Пополнить баланс
                8) Выход
                """.formatted(status));
    }

    private static int getIntInput(String prompt, int min, int max) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Введите число от " + min + " до " + max + ": ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return Math.max(min, Math.min(max, value));
    }

    private static void viewCategories() {
        System.out.println("\nКатегории:");
        Catalog.showCategories();
    }

    private static void viewProducts() {
        System.out.println("\nТовары:");
        if (Product.productList.isEmpty()) {
            System.out.println("Список товаров пуст.");
            return;
        }
        Product.getProductList();
    }

    private static void sortProducts() {
        if (Product.productList.isEmpty()) {
            System.out.println("Нет товаров для сортировки.");
            return;
        }

        System.out.println("""
                СОРТИРОВКА ТОВАРОВ:
                1) По цене (возрастание)
                2) По цене (убывание)
                3) По названию (А-Я)
                4) Только электроника
                5) Только товары для сада
                """);

        int sortChoice = getIntInput("Ваш выбор: ", 1, 5);
        List<Category> sorted = new ArrayList<>(Product.productList);

        switch (sortChoice) {
            case 1 -> sorted.sort(new PriceComparator(true));
            case 2 -> sorted.sort(new PriceComparator(false));
            case 3 -> sorted.sort(Comparator.comparing(Category::getTitle, String.CASE_INSENSITIVE_ORDER));
            case 4 -> sorted = TypeFilterComparator.filterByType(Product.productList, Electronic.class, true);
            case 5 -> sorted = TypeFilterComparator.filterByType(Product.productList, GardenItem.class, true);
        }

        System.out.println("\nОтсортированный список:");
        for (Category c : sorted) c.showInfo();
    }

    private static void compareProducts() {
        if (Product.productList.size() < 2) {
            System.out.println("Нужно минимум 2 товара для сравнения.");
            return;
        }

        System.out.println("\nДоступные товары:");
        for (int i = 0; i < Product.productList.size(); i++) {
            Category c = Product.productList.get(i);
            System.out.printf("%d) %s — %.0fр (%s)%n",
                    i+1, c.getTitle(), c.getPrice(), c.getClass().getSimpleName());
        }

        int idx1 = getIntInput("Первый товар (номер): ", 1, Product.productList.size()) - 1;
        int idx2 = getIntInput("Второй товар (номер): ", 1, Product.productList.size()) - 1;

        Category c1 = Product.productList.get(idx1);
        Category c2 = Product.productList.get(idx2);

        if (!canBeCompared(c1, c2)) {
            System.out.println("ОШИБКА: Нельзя сравнить товары разных категорий!");
            System.out.println("   \"" + c1.getTitle() + "\" (" + c1.getClass().getSimpleName() + ")");
            System.out.println("   vs");
            System.out.println("   \"" + c2.getTitle() + "\" (" + c2.getClass().getSimpleName() + ")");
            System.out.println("Совет: Сравнивайте товары одной категории!");
            return;
        }

        System.out.println("\nРезультат сравнения:");
        System.out.printf("Товар 1: %s%n", c1.getTitle());
        System.out.printf("  Цена: %.0fр | ID: %d%n", c1.getPrice(), c1.getID());
        System.out.printf("Товар 2: %s%n", c2.getTitle());
        System.out.printf("  Цена: %.0fр | ID: %d%n", c2.getPrice(), c2.getID());
        System.out.printf("Равны? %s%n", c1.equals(c2));

        if (c1.getPrice() != c2.getPrice()) {
            double diff = Math.abs(c1.getPrice() - c2.getPrice());
            String cheaper = c1.getPrice() < c2.getPrice() ? c1.getTitle() : c2.getTitle();
            System.out.printf("Разница в цене: %.0fр | Выгоднее: %s%n", diff, cheaper);
        }
    }

    // сравнивать можно только совместимые типы (этап 6.4)
    private static boolean canBeCompared(Category c1, Category c2) {
        if (c1.getClass() == c2.getClass()) return true;
        if (c1 instanceof Electronic && c2 instanceof Electronic) return true;
        if (c1 instanceof GardenItem && c2 instanceof GardenItem) return true;
        if (!c1.isSubCategory() && !c2.isSubCategory()) return true;
        return false;
    }
}