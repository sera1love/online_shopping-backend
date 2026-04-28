package ShopSystem;
import ShopSystem.interface_OJnS.Payable;
import java.util.ArrayList;

public abstract class Product extends Category implements Payable {

    // список товаров в виде ArrayList
    public static ArrayList<Category> productList = new ArrayList<>();
    public static void addProduct(Category product) { // Теперь принимаем только Product и его наследников
        productList.add(product);
    }

    public Product(String title, double price, String description) {
        super(title, price, description);
    }

    private boolean paid = false;

    @Override
    public double getFinalPrice() {
        return getPrice() * (paid ? 0.95 : 1.0); // скидка 5% после оплаты
    }

    @Override
    public void pay(double amount) {
        if (amount >= getPrice()) {
            paid = true;
            System.out.println("Оплата прошла успешно");
        } else {
            System.out.println("Недостаточно средств");
        }
    }

    @Override
    public boolean isPaid() {
        return paid;
    }

    // абстрактный метод
    @Override
    public abstract void showInfo();

    // доступ к списку
    public static void getProductList() {
        for (Category c : productList) {
            c.showInfo();
        }
    }
}