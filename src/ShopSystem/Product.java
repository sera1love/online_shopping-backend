package ShopSystem;

import ShopSystem.interface_OJnS.Payable;
import ShopSystem.interface_OJnS.OrderStatus.PaymentStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Product extends Category implements Payable {
    // создания списка для продуктов
    public static ArrayList<Product> productList = new ArrayList<>();

    // добавление продуктов в список
    public static void addProduct(Product product) {
        productList.add(product);
    }

    public Product(String title, double price, String description) {
        super(title, price, description);
    }

    private PaymentStatus paymentStatus = PaymentStatus.NEW;
    private boolean inStock = true; // проверка наличия товара

    // получение финальной цены товара
    @Override
    public double getFinalPrice() {
        return getPrice() * (paymentStatus == PaymentStatus.PAID ? 0.95 : 1.0);
    }

    // оплата товара
    @Override
    public void pay(double amount) {
        if (!inStock) {
            System.out.println("Товара нет в наличии.");
            paymentStatus = PaymentStatus.FAILED;
            return;
        }
        if (amount >= getPrice()) {
            paymentStatus = PaymentStatus.PAID;
            System.out.println("Оплата прошла успешно!");
        } else {
            paymentStatus = PaymentStatus.FAILED;
            System.out.println("Недостаточно средств!");
        }
    }

    // возврат оплачен ли товар
    @Override
    public boolean isPaid() {
        return paymentStatus == PaymentStatus.PAID;
    }

    // StreamAPI фильтры
    // фильтр товаров по цене
    public static List<Product> filterByPrice(double min, double max) {
        return productList.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }

    // фильтр товаров по названию
    public static List<Product> filterByName(String keyword) {
        return productList.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // фильтр товаров по скидке на них
    public static List<Product> filterWithDiscount() {
        return productList.stream()
                .filter(Product::isPaid) // только оплаченные = со скидкой
                .collect(Collectors.toList());
    }

    // фильтр товаров в наличии
    public static List<Product> filterInStock() {
        return productList.stream()
                .filter(Product::isInStock)
                .collect(Collectors.toList());
    }

    // проверка доступности товара
    public static boolean checkAvailability(String title) {
        return productList.stream()
                .anyMatch(p -> p.getTitle().equalsIgnoreCase(title) && p.isInStock());
    }

    // геттеры/сеттеры
    // получение статуса оплаты
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus status) { this.paymentStatus = status; }

    // получение товаров в наличии
    public boolean isInStock() { return inStock; }
    public void setInStock(boolean inStock) { this.inStock = inStock; }

    // вывод через абстрактный метод showInfo
    @Override
    public abstract void showInfo();

    // получение списка продуктов
    public static void getProductList() {
        productList.forEach(Category::showInfo);
    }
}