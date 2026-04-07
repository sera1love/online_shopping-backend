import java.util.ArrayList;

public class Category {
    private static int idgen = 1;
    private int id;
    private String title;
    private String description;
    private double price;


    private static ArrayList<Category> categories = new ArrayList<>();

    public Category(String title, double price, String description) {
        this.id = idgen++;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    // получение id
    public int getID() {
        return this.id;
    }
    public int setID(int id) {
        return this.id = id;
    }

    // получение названий
    public String getTitle() {
        return this.title;
    }
    public String setTitle(String title) {
        return this.title = title;
    }

    // получение цены
    public double getPrice() {
        return this.price;
    }
    public double setPrice(double price) {
        return this.price = price;
    }

    // получение описания
    public String getDescription() {
        return this.description;
    }
    public String setDescription(String description) {
        return this.description = description;
    }

    public static void addCategory(Category category) {
        categories.add(category);
    }

    public static void showCategories() {
        for (Category c : categories) {
            System.out.println("ID: " + c.id + " | Название: " + c.title + " | Цена: " + c.description);
        }
    }
}