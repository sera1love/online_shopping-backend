package ShopSystem.ClientSystem;

import ShopSystem.Exception.InsufficientFundsException;
import ShopSystem.Exception.ProductOutOfStockException;
import ShopSystem.Product;
import ShopSystem.ShopInventory;
import ShopSystem.Pattern.Factories.ProductFactory;
import ShopSystem.Pattern.Factories.ClientFactory;
import ShopSystem.Pattern.Strategy.PaymentStrategy;
import ShopSystem.Pattern.Strategy.WalletPayment;
import ShopSystem.interface_OJnS.ClientStatus;
import ShopSystem.interface_OJnS.OrderStatus.OrderStatus;
import ShopSystem.interface_OJnS.ProductFilter;
import ShopSystem.interface_OJnS.StatusValidator;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

// D - Dependency Inversion, зависит от абстракции, а не от конкретики
public class Clients {
    private static final List<Client> clients = new ArrayList<>();
    private static Client currentClient = null;

    private static final Scanner scanner = new Scanner(System.in);
    private static PaymentStrategy paymentStrategy;


    public static void initClient() {
        // Этот метод теперь используется как "Создать нового"
        System.out.println("Создание покупателя");
        try {
            System.out.print("Имя: ");
            String name = scanner.nextLine();
            System.out.print("Телефон: ");
            String phone = scanner.nextLine();
            System.out.print("Начальный баланс (р): ");
            double balance = readDouble(0);
            scanner.nextLine();

            Client newClient = ClientFactory.createClient(name, phone, balance);
            clients.add(newClient); // Добавляем в общий список

            // Если это первый клиент или мы сбросили выбор, делаем его текущим
            if (currentClient == null) {
                currentClient = newClient;
                paymentStrategy = new WalletPayment(currentClient.getWallet());
            }
            System.out.println("Клиент создан и добавлен в базу: " + newClient.getName());
        } catch (Exception e) {
            System.out.println("Ошибка при создании клиента: " + e.getMessage());
        }
    }

    public static boolean buyProduct(Product product) {
        if (currentClient == null) {
            System.out.println("Сначала выберите клиента!");
            return false;
        }
        return paymentStrategy.processPayment(product, product.getFinalPrice());
    }

    public static void setPaymentStrategy(PaymentStrategy strategy) {
        if (currentClient != null && strategy != null) {
            paymentStrategy = strategy;
            System.out.println("Стратегия оплаты изменена на: " + strategy.getStrategyName());
        }
    }

    public static void showProductsForSale() {
        ShopInventory.generateRandomProducts(15, 30);
        ShopInventory.addProduct(ProductFactory.createProduct("mobile", "Xiaomi Galaxy S200 Ultra", 190000, "Смартфон с процессором Snapdragon SQ+ 656GHz"));
        ShopInventory.getProducts().get(ShopInventory.getProducts().size() - 1).setQuantity(5);
        ShopInventory.addProduct(ProductFactory.createProduct("mobile", "iPhone 666 Pro Google Ultra Max", 580000, "Смартфон от Samsung Wall S600"));
        ShopInventory.getProducts().get(ShopInventory.getProducts().size() - 1).setQuantity(3);
        ShopInventory.addProduct(ProductFactory.createProduct("garden", "Лопата Универсал", 1500, "Для дома, дачи и кладбища"));
        ShopInventory.getProducts().get(ShopInventory.getProducts().size() - 1).setQuantity(20);

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
        if (currentClient == null) {
            System.out.println("Сначала выберите активного клиента!");
            return;
        }

        try {
            System.out.println("\nПОКУПКИ: " + currentClient.getName());
            System.out.println(currentClient.getWallet().getFinalStatus());
            showProductsForSale();

            System.out.print("Введите номер товара (0 - отмена): ");
            int choice = getIntInput("", 0, ShopInventory.getProducts().size());

            if (choice > 0 && choice <= ShopInventory.getProducts().size()) {
                Product product = ShopInventory.getProducts().get(choice - 1);

                if (!product.isInStock()) {
                    throw new ProductOutOfStockException(0, 1);
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
                        boolean success = currentClient.buyProduct(product, quantity);
                        if (success) {
                            List<PurchaseRecord> history = currentClient.getPurchaseHistory();
                            if (!history.isEmpty()) {
                                history.get(history.size() - 1).printTransactionDetails();
                            }
                        }
                    } else {
                        System.out.println("Покупка отменена");
                    }
                }
            }
        } catch (ProductOutOfStockException | InsufficientFundsException e) {
            System.out.println("Ошибка операции: " + e.getMessage());
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println("Некорректный ввод или товар не найден: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Критическая ошибка: " + e.getMessage());
        }
    }

    public static void showPurchaseHistory() {
        if (currentClient == null) {
            System.out.println("Клиент не выбран");
            return;
        }
        List<PurchaseRecord> history = currentClient.getPurchaseHistory();
        if (history.isEmpty()) {
            System.out.println("История покупок пуста");
            return;
        }
        System.out.println("\nИстория покупок: " + currentClient.getName());
        double total = history.stream().mapToDouble(PurchaseRecord::getAmount).sum();
        history.forEach(PurchaseRecord::printTransactionDetails);
        System.out.printf("%nВсего потрачено: %.2fр%n", total);
    }

    public static void topUpBalance() {
        if (currentClient == null) {
            System.out.println("Клиент не выбран");
            return;
        }
        System.out.print("Сумма пополнения (р): ");
        double amount = readDouble(0);
        scanner.nextLine();
        if (amount > 0) {
            currentClient.topUp(amount);
            System.out.println(currentClient.getWallet().getFinalStatus());
        }
    }

    public static boolean checkClientStatus(StatusValidator<ClientStatus> validator) {
        return currentClient != null && validator.validate(currentClient.getClientStatus());
    }

    public static void showHistoryByStatus(OrderStatus targetStatus) {
        if (currentClient == null) return;

        Predicate<PurchaseRecord> filter = r -> r.getOrderStatus() == targetStatus;
        var filtered = currentClient.getPurchaseHistory().stream()
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
        return currentClient != null ? currentClient.getWallet().getFinalStatus() : "Клиент не выбран";
    }

    public static Client getClient() { return currentClient; }
    public static boolean isInitialized() { return currentClient != null; }

    public static void reset() {
        currentClient = null;
        paymentStrategy = null;
    }



    public static void showAllClients() {
        if (clients.isEmpty()) {
            System.out.println("База клиентов пуста.");
            return;
        }
        System.out.println("\n=== СПИСОК КЛИЕНТОВ ===");
        for (Client c : clients) {
            String marker = (c == currentClient) ? " <-- АКТИВНЫЙ" : "";
            String blockMark = (c.getClientStatus() == ClientStatus.BLOCKED) ? " [ЗАБЛОКИРОВАН]" : "";
            System.out.printf("ID: %d | %s%s%s%n", c.getId(), c.getName(), blockMark, marker);
        }
        System.out.println("======================");
    }

    public static void selectClient() {
        showAllClients();
        if (clients.isEmpty()) return;

        System.out.print("Введите ID клиента для выбора (0 - отмена): ");
        int id = getIntInput("", 0, Integer.MAX_VALUE);

        if (id == 0) return;

        Client found = null;
        for (Client c : clients) {
            if (c.getId() == id) {
                found = c;
                break;
            }
        }

        if (found != null) {
            currentClient = found;
            paymentStrategy = new WalletPayment(currentClient.getWallet());
            System.out.println("Выбран клиент: " + currentClient.getName());
        } else {
            System.out.println("Клиент с таким ID не найден.");
        }
    }

    public static void toggleClientBlock() {
        showAllClients();
        if (clients.isEmpty()) return;

        System.out.print("Введите ID клиента (0 - отмена): ");
        int id = getIntInput("", 0, Integer.MAX_VALUE);
        if (id == 0) return;

        for (Client c : clients) {
            if (c.getId() == id) {
                if (c.getClientStatus() == ClientStatus.BLOCKED) {
                    c.setClientStatus(ClientStatus.ACTIVE);
                    System.out.println("Клиент " + c.getName() + " разблокирован.");
                } else {
                    c.setClientStatus(ClientStatus.BLOCKED);
                    System.out.println("Клиент " + c.getName() + " заблокирован.");
                }
                return;
            }
        }
        System.out.println("Клиент не найден.");
    }

    public static void deleteClient() {
        showAllClients();
        if (clients.isEmpty()) return;

        System.out.print("Введите ID клиента для удаления (0 - отмена): ");
        int id = getIntInput("", 0, Integer.MAX_VALUE);
        if (id == 0) return;

        boolean removed = clients.removeIf(c -> c.getId() == id);
        if (removed) {
            System.out.println("Клиент удален из базы.");
            // Если удалили текущего, сбрасываем выбор
            if (currentClient != null && currentClient.getId() == id) {
                reset();
                System.out.println("Текущий клиент сброшен.");
            }
        } else {
            System.out.println("Клиент не найден.");
        }
    }

    // --- ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ---
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