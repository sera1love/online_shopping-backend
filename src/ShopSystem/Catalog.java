package ShopSystem;
import java.util.ArrayList;
import java.util.List;


public class Catalog {
    private static final List<Category> categories = new ArrayList<>();
    public static void addCategory(Category category) { categories.add(category); }
    public static void showCategories() {
        int catCount = 0, subCount = 0;
        for (Category c : categories) {
            c.showInfo();
            if (c.isSubCategory()) subCount++; else catCount++;
        }
        System.out.println("Статистика: Категорий - " + catCount + ", Саб-категорий - " + subCount);
    }
}