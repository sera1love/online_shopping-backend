package ShopSystem;

import ShopSystem.Pattern.Factories.ProductFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Catalog {
    private static Catalog instance;
    private static int idCounter = 1;

    private final int id;
    private String title;
    private double price;
    private final List<Category> categories;

    private Catalog() {
        this.id = idCounter++;
        this.title = "Главный каталог";
        this.price = 0.0;
        this.categories = new ArrayList<>();
    }

    public static synchronized Catalog getInstance() {
        if (instance == null) {
            instance = new Catalog();
        }
        return instance;
    }

    public void addCategory(Category category) {
        if (category != null && !categories.contains(category)) {
            categories.add(category);
            System.out.println("Добавлена категория: " + category.getTitle());
        }
    }

    public void showCategories() {
        if (categories.isEmpty()) {
            System.out.println("Каталог пуст");
            return;
        }

        long catCount = categories.stream().filter(c -> !c.isSubCategory()).count();
        long subCount = categories.stream().filter(Category::isSubCategory).count();

        System.out.println("\n=== КАТАЛОГ: " + title + " (ID: " + id + ") ===");
        categories.forEach(Category::showInfo);
        System.out.println("\nСтатистика:");
        System.out.printf("Категорий: %d | Саб-категорий: %d%n", catCount, subCount);
    }

    public List<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    // геттеры и сеттеры
    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Catalog)) return false;
        Catalog catalog = (Catalog) o;
        return id == catalog.id && Objects.equals(title, catalog.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return String.format("Catalog[id=%d, title='%s', categories=%d]",
                id, title, categories.size());
    }
}