package ShopSystem.ClientSystem;

import ShopSystem.Product;
import ShopSystem.ShopInventory;
import ShopSystem.Pattern.Factories.ClientFactory;
import ShopSystem.interface_OJnS.ClientStatus;
import ShopSystem.interface_OJnS.OrderStatus.OrderStatus;
import ShopSystem.interface_OJnS.ProductFilter;
import ShopSystem.interface_OJnS.StatusValidator;
import ShopSystem.Pattern.Strategy.PaymentStrategy;
import ShopSystem.Pattern.Strategy.WalletPayment;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Clients {
    private static Client client = null;
    private static final Scanner scanner = new Scanner(System.in);
    private static PaymentStrategy paymentStrategy;

    public static void initClient() {
        if (client != null) {
            System.out.println("Клиент уже создан: " + client.getName());
            return;
        }
        System.out.println("Создание покупателя");
        System.out.print("Имя: ");
        String name = scanner.nextLine();
        System.out.print("Телефон: ");
        String phone = scanner.nextLine();
        System.out.print("Начальный баланс (р): ");
        double balance = readDouble(0);
        scanner.nextLine();

        // используем Factory
        client = ClientFactory.createClient(name, phone, balance);

        // Dependency Injection - внедряем стратегию оплаты
        paymentStrategy = new WalletPayment(client.getWallet());

        System.out.println("Клиент создан: " + client.getName());
    }

    public static boolean buyProduct(Product product) {
        if (client == null) {
            System.out.println("Сначала инициализируйте клиента!");
            return false;
        }

        // используем внедренную стратегию
        return paymentStrategy.processPayment(product, product.getFinalPrice());
    }

    public static void setPaymentStrategy(PaymentStrategy strategy) {
        if (client != null && strategy != null) {
            paymentStrategy = strategy;
            System.out.println("Стратегия оплаты изменена на: " + strategy.getStrategyName());
        }
    }

    public static void showProductsForSale() {
        List<Product> products = ShopInventory.getProducts();
        if (products.isEmpty()) {
            System.out.println("Товаров нет в наличии");
            return;
        }

        System.out.println("\nДоступные товары:");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            String status = p.isInStock()
                    ? String.format("В наличии: %d шт.", p.getQuantity())
                    : "Нет в наличии";
            System.out.printf("%d) %s - %.0fр (%s)%n",
                    i + 1, p.getTitle(), p.getPrice(), status);
        }
    }

    public static void purchaseMenu() {
        if (client == null) {
            System.out.println("Сначала выполните инициализацию клиента!");
            return;
        }

        System.out.println("\nПОКУПКИ: " + client.getName());
        System.out.println(client.getWallet().getFinalStatus());
        showProductsForSale();

        System.out.print("Введите номер товара (0 - отмена): ");
        int choice = getIntInput("", 0, ShopInventory.getProducts().size());

        if (choice > 0 && choice <= ShopInventory.getProducts().size()) {
            Product product = ShopInventory.getProducts().get(choice - 1);

            if (!product.isInStock()) {
                System.out.println("Товар закончился!");
                return;
            }

            System.out.printf("\nТовар: %s%n", product.getTitle());
            System.out.printf("Цена за единицу: %.2fр%n", product.getFinalPrice());
            System.out.printf("В наличии: %d шт.%n", product.getQuantity());

            System.out.print("Введите количество (0 - отмена): ");
            int quantity = getIntInput("", 0, product.getQuantity());

            if (quantity > 0) {
                double totalPrice = product.getFinalPrice() * quantity;
                System.out.printf("Итого к оплате: %.2fр%n", totalPrice);
                System.out.print("Подтверждаете покупку? (1 - да, 0 - нет): ");
                int confirm = getIntInput("", 0, 1);

                if (confirm == 1) {
                    boolean success = client.buyProduct(product, quantity);

                    if (success) {
                        List<PurchaseRecord> history = client.getPurchaseHistory();
                        if (!history.isEmpty()) {
                            history.get(history.size() - 1).printTransactionDetails();
                        }
                    }
                } else {
                    System.out.println("Покупка отменена");
                }
            }
        }
    }

    public static void showPurchaseHistory() {
        if (client == null) {
            System.out.println("Клиент не создан");
            return;
        }

        List<PurchaseRecord> history = client.getPurchaseHistory();
        if (history.isEmpty()) {
            System.out.println("История покупок пуста");
            return;
        }

        System.out.println("\nИстория покупок: " + client.getName());
        double total = history.stream().mapToDouble(PurchaseRecord::getAmount).sum();

        history.forEach(PurchaseRecord::printTransactionDetails);

        System.out.printf("%nВсего потрачено: %.2fр%n", total);
    }

    public static void topUpBalance() {
        if (client == null) {
            System.out.println("Клиент не создан");
            return;
        }

        System.out.print("Сумма пополнения (р): ");
        double amount = readDouble(0);
        scanner.nextLine();

        if (amount > 0) {
            client.topUp(amount);
            System.out.println(client.getWallet().getFinalStatus());
        }
    }

    public static boolean checkClientStatus(StatusValidator<ClientStatus> validator) {
        return client != null && validator.validate(client.getClientStatus());
    }

    public static void showHistoryByStatus(OrderStatus targetStatus) {
        if (client == null) return;

        Predicate<PurchaseRecord> filter = r -> r.getOrderStatus() == targetStatus;
        var filtered = client.getPurchaseHistory().stream()
                .filter(filter)
                .toList();

        if (filtered.isEmpty()) {
            System.out.println("Заказов со статусом " + targetStatus.getLabel() + " не найдено.");
        } else {
            System.out.println("\nЗаказы со статусом " + targetStatus.getLabel() + ":");
            filtered.forEach(r -> System.out.println("  " + r));
        }
    }

    public static void findFirstProduct(Predicate<Product> condition) {
        var found = ShopInventory.getProducts().stream()
                .filter(condition)
                .findFirst();

        if (found.isPresent()) {
            Product p = found.get();
            System.out.println("\nНайден товар:");
            p.showInfo();
        } else {
            System.out.println("Товар не найден");
        }
    }

    public static void advancedProductFilter() {
        System.out.println("""
        
        Фильтр товаров:
        1) Только в наличии
        2) Только оплаченные (со скидкой)
        3) Цена до ...
        4) Название содержит ключевое слово
        5) Комбинированный: в наличии + до 10000р
        6) Найти первый товар по условию (StreamAPI findFirst)
        0) Назад
        """);

        int choice = getIntInput("Ваш выбор: ", 0, 6);

        if (choice == 6) {
            System.out.print("Минимальная цена для поиска: ");
            double minPrice = readDouble(0);
            scanner.nextLine();
            findFirstProduct(p -> p.getPrice() >= minPrice && p.isInStock());
            return;
        }

        ProductFilter filter = switch (choice) {
            case 1 -> Product::isInStock;
            case 2 -> Product::isPaid;
            case 3 -> {
                System.out.print("До: ");
                double keypay = scanner.nextDouble();
                scanner.nextLine();
                yield p -> p.getPrice() <= keypay;
            }
            case 4 -> {
                System.out.print("Введите часть названия: ");
                String kw = scanner.nextLine().toLowerCase();
                yield p -> p.getTitle().toLowerCase().contains(kw);
            }
            case 5 -> ((ProductFilter) Product::isInStock).and(p -> p.getPrice() <= 10000);
            default -> p -> true;
        };

        var filtered = ShopInventory.getProducts().stream()
                .filter(filter)
                .toList();

        if (filtered.isEmpty()) {
            System.out.println("Ничего не найдено");
        } else {
            System.out.println("\nНайдено товаров: " + filtered.size());
            filtered.forEach(p -> System.out.printf("- %s - %.0fр%n",
                    p.getTitle(), p.getFinalPrice()));
        }
    }

    public static String getFinalStatus() {
        return client != null ? client.getWallet().getFinalStatus() : "Клиент не создан";
    }

    public static Client getClient() { return client; }
    public static boolean isInitialized() { return client != null; }

    public static void reset() {
        client = null;
        paymentStrategy = null;
    }

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
        int val = scanner.nextInt();
        scanner.nextLine();
        return Math.max(min, Math.min(max, val));
    }
}