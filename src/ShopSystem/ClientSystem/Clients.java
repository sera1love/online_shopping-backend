package ShopSystem.ClientSystem;

import ShopSystem.Product;
import ShopSystem.Category;
import ShopSystem.interface_OJnS.Finansable;
import java.util.List;
import java.util.Scanner;

public class Clients implements Finansable {
    private static Client client = null;
    private static final Scanner scanner = new Scanner(System.in);

    // инициализация клиента
    public static void initClient() {
        if (client != null) {
            System.out.println("Клиент уже инициализирован: " + client.getName());
            return;
        }

        System.out.println("\nСоздание покупателя");
        System.out.print("Имя: ");
        String name = scanner.nextLine();
        System.out.print("Телефон: ");
        String phone = scanner.nextLine();

        System.out.print("Начальный баланс (р): ");
        double balance = readDouble(0);
        scanner.nextLine();

        client = new Client(name, phone, balance);
        System.out.println("Клиент создан: " + client.getName());
    }

    // покупка товаров
    public static boolean buyProduct(Product product) {
        if (client == null) {
            System.out.println("Сначала инициализируйте клиента!");
            return false;
        }
        return client.buyProduct(product);
    }

    // показать товары для покупки
    public static void showProductsForSale() {
        if (Product.productList.isEmpty()) {
            System.out.println("Товаров нет в наличии");
            return;
        }
        System.out.println("\nДоступные товары:");
        for (int i = 0; i < Product.productList.size(); i++) {
            Category c = Product.productList.get(i);
            if (c instanceof Product p) {
                String status = p.isPaid() ? "ОПЛАЧЕНО" : "В продаже";
                System.out.printf("%d) %s — %.0fр (%s)%n",
                        i+1, c.getTitle(), c.getPrice(), status);
            }
        }
    }

    // меню покупок
    public static void purchaseMenu() {
        if (client == null) {
            System.out.println("Сначала выполните инициализацию клиента!");
            return;
        }
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ПОКУПКИ: " + client.getName());
        System.out.println(client.getWallet().getFinalStatus());
        System.out.println("=".repeat(50));

        showProductsForSale();
        System.out.print("\nВведите номер товара (0 - отмена): ");
        int choice = getIntInput("", 0, Product.productList.size());

        if (choice > 0 && choice <= Product.productList.size()) {
            Category selected = Product.productList.get(choice - 1);
            if (selected instanceof Product product) {
                buyProduct(product);
            }
        }
    }

    // история покупок
    public static void showPurchaseHistory() {
        if (client == null) {
            System.out.println("Клиент не инициализирован");
            return;
        }
        List<PurchaseRecord> history = client.getPurchaseHistory();
        if (history.isEmpty()) {
            System.out.println("История покупок пуста");
            return;
        }
        System.out.println("\nИстория: " + client.getName());
        System.out.println("-".repeat(45));
        double total = 0;
        for (PurchaseRecord record : history) {
            System.out.println(record);
            total += record.getAmount();
        }
        System.out.printf("%nВсего: %.2fр%n", total);
    }

    // пополнение баланса
    public static void topUpBalance() {
        if (client == null) {
            System.out.println("Клиент не инициализирован");
            return;
        }
        System.out.print("\nСумма пополнения (р): ");
        double amount = readDouble(0);
        scanner.nextLine();

        if (amount > 0) {
            client.topUp(amount);
            System.out.println("Баланс пополнен!");
            System.out.println(client.getWallet().getFinalStatus());
        } else {
            System.out.println("Сумма должна быть > 0");
        }
    }

    // реализация Finansable
    @Override
    public double checkBalance() {
        return client != null ? client.getWallet().checkBalance() : 0;
    }

    @Override
    public boolean hasAmountMoney(double amount) {
        return client != null && client.getWallet().hasAmountMoney(amount);
    }

    @Override
    public String getFinalStatus() {
        return client != null
                ? client.getWallet().getFinalStatus()
                : "Клиент не инициализирован";
    }

    // вспомогательные методы
    private static double readDouble(double min) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Введите число >= " + min + ": ");
            scanner.next();
        }
        return Math.max(min, scanner.nextDouble());
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

    // геттеры
    // получение клиента
    public static Client getClient() { return client; }
    // инициализация клиента
    public static boolean isInitialized() { return client != null; }

    // сброс (для тестов)
    public static void reset() { client = null; }
}