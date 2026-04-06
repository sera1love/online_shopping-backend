import java.util.ArrayList;

class Category {
    private static int idgen = 1;

    private int id;
    private String title;
    private String description;

    // список для хранения всех категорий
    private static ArrayList<Category> categories = new ArrayList<>();

    public Category() {}
    public Category(String title, String description) {
        this.id = idgen++;
        this.title = title;
        this.description = description;
    }

    public static void addCategory(Category category) {
        categories.add(category);
    }

    public static void showCategories() {
        for (Category c : categories) {
            System.out.println("ID: " + c.id + " | Категория: " + c.title + " (" + c.description + ")");
        }
    }
}