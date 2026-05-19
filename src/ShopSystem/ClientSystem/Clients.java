package ShopSystem.ClientSystem;
import ShopSystem.Product;
import ShopSystem.ShopInventory;
import ShopSystem.interface_OJnS.ClientStatus;
import ShopSystem.interface_OJnS.OrderStatus.OrderStatus;
import ShopSystem.interface_OJnS.ProductFilter;
import ShopSystem.interface_OJnS.StatusValidator;
import java.util.List;
import java.util.Scanner;

public class Clients {
    private static Client client = null;
    private static final Scanner scanner = new Scanner(System.in);

    public static void initClient() {
        if (client != null) { System.out.println("Клиент уже создан: " + client.getName()); return; }
        System.out.println("Создание покупателя");
        System.out.print("Имя: "); String name = scanner.nextLine();
        System.out.print("Телефон: "); String phone = scanner.nextLine();
        System.out.print("Начальный баланс (р): "); double balance = readDouble(0);
        scanner.nextLine();
        client = new Client(name, phone, balance);
        System.out.println("Клиент создан: " + client.getName());
    }

    public static boolean buyProduct(Product product) {
        if (client == null) { System.out.println("Сначала инициализируйте клиента!"); return false; }
        return client.buyProduct(product);
    }

    public static void showProductsForSale() {
        if (ShopInventory.getProducts().isEmpty()) { System.out.println("Товаров нет в наличии"); return; }
        System.out.println("Доступные товары:");
        for (int i = 0; i < ShopInventory.getProducts().size(); i++) {
            Product p = ShopInventory.getProducts().get(i);
            String status = p.isPaid() ? "ОПЛАЧЕНО" : (p.isInStock() ? "В продаже" : "Нет в наличии");
            System.out.printf("%d) %s - %.0fр (%s)%n", i+1, p.getTitle(), p.getPrice(), status);
        }
    }

    public static void purchaseMenu() {
        if (client == null) { System.out.println("Сначала выполните инициализацию клиента!"); return; }
        System.out.println("ПОКУПКИ: " + client.getName());
        System.out.println(client.getWallet().getFinalStatus());
        showProductsForSale();
        System.out.print("Введите номер товара (0 - отмена): ");
        int choice = getIntInput("", 0, ShopInventory.getProducts().size());
        if (choice > 0 && choice <= ShopInventory.getProducts().size()) {
            buyProduct(ShopInventory.getProducts().get(choice - 1));
        }
    }

    public static void showPurchaseHistory() {
        if (client == null) { System.out.println("Клиент не создан"); return; }
        List<PurchaseRecord> history = client.getPurchaseHistory();
        if (history.isEmpty()) { System.out.println("История покупок пуста"); return; }
        System.out.println("История: " + client.getName());
        double total = history.stream().mapToDouble(PurchaseRecord::getAmount).sum();
        history.forEach(r -> System.out.println("  " + r));
        System.out.printf("Всего потрачено: %.2fр%n", total);
    }

    public static void topUpBalance() {
        if (client == null) { System.out.println("Клиент не создан"); return; }
        System.out.print("Сумма пополнения (р): "); double amount = readDouble(0);
        scanner.nextLine();
        if (amount > 0) { client.topUp(amount); System.out.println(client.getWallet().getFinalStatus()); }
    }

    public static boolean checkClientStatus(StatusValidator<ClientStatus> validator) {
        return client != null && validator.validate(client.getClientStatus());
    }

    public static void showHistoryByStatus(OrderStatus targetStatus) {
        if (client == null) return;
        var filtered = client.getPurchaseHistory().stream().filter(r -> r.getOrderStatus() == targetStatus).toList();
        if (filtered.isEmpty()) System.out.println("Заказов со статусом не найдено.");
        else filtered.forEach(r -> System.out.println("  " + r));
    }

    public static void advancedProductFilter() {
        System.out.println("""
        Фильтр товаров:
        1) Только в наличии
        2) Только оплаченные (со скидкой)
        3) Цена до ...
        4) Название содержит ключевое слово
        5) Комбинированный: в наличии + до 10000р
        0) Назад
        """);
        int choice = getIntInput("Ваш выбор: ", 0, 5);
        ProductFilter filter = switch (choice) {
            case 1 -> Product::isInStock;
            case 2 -> Product::isPaid;
            case 3 -> {
                System.out.print("До: ");
                double keypay = scanner.nextDouble();
                yield p -> p.getPrice() <= keypay;
            }
            case 4 -> { System.out.print("Введите часть названия: "); String kw = scanner.nextLine().toLowerCase(); yield p -> p.getTitle().toLowerCase().contains(kw); }
            case 5 -> ((ProductFilter) Product::isInStock).and(p -> p.getPrice() <= 10000);
            default -> p -> true;
        };
        var filtered = ShopInventory.getProducts().stream().filter(filter).toList();
        if (filtered.isEmpty()) System.out.println("Ничего не найдено");
        else {
            System.out.println("Найдено товаров: " + filtered.size());
            filtered.forEach(p -> System.out.printf("- %s - %.0fр%n", p.getTitle(), p.getFinalPrice()));
        }
    }

    public static String getFinalStatus() { return client != null ? client.getWallet().getFinalStatus() : "Клиент не создан"; }
    public static Client getClient() { return client; }
    public static boolean isInitialized() { return client != null; }
    public static void reset() { client = null; }

    private static double readDouble(double min) {
        while (!scanner.hasNextDouble()) { System.out.print("Введите число >= " + min + ": "); scanner.next(); }
        return Math.max(min, scanner.nextDouble());
    }
    private static int getIntInput(String prompt, int min, int max) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) { System.out.print("Введите число от " + min + " до " + max + ": "); scanner.next(); }
        int val = scanner.nextInt(); scanner.nextLine();
        return Math.max(min, Math.min(max, val));
    }
}