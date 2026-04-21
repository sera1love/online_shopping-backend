package ShopSystem;

import java.util.ArrayList;

public class Catalog {
    private static ArrayList<Category> categori = new ArrayList<>();

    public static void addCategory(Category category) {
        categori.add(category);
    }

    public static void showCategories() {
        int catCount = 0;
        int subCount = 0;

        for (Category c : categori) {
            // вывод через полиморфный метод showInfo
            c.showInfo();

            if (c.isSubCategory()) subCount++;
            else catCount++;
        }
        System.out.println("\nСтатистика: Категорий - " + catCount + ", Саб-категорий - " + subCount);
    }
}