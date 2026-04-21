package ShopSystem;
import ShopSystem.interface_OJnS.Payable;
import java.util.ArrayList;

public abstract class Product extends Category implements Payable {

    // список товаров в виде ArrayList
    private static ArrayList<Category> productList = new ArrayList<>();
    public static void addProduct(Category product) {
        productList.add(product);
    }

    public Product(String title, double price, String description) {
        super(title, price, description);
    }

    // абстрактный метод
    @Override
    public abstract void showInfo();

    // доступ к списку
    public static void getProductList() {
        for (Category v : productList) {
            v.showInfo();
        }
    }
}