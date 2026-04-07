import java.util.ArrayList;

public class Category {
    private static int idgen = 1;
    private int id;
    private String title;
    private String description;
    private double price;

    public Category(String title, double price, String description) {
        this.id = idgen++;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public boolean isSubCategory() {
        return false;
    }

    public void printInfo() {
        System.out.println("[Категория] " + title + " (ID: " + id + ")");
    }

    // гет сеты
    // получение ID
    public int getID() {
        return this.id;
    }
    public int setID(int id) {
        return this.id = id;
    }

    // получение названия
    public String getTitle() {
        return this.title;
    }
    public String setTitle(String title) {
        return this.title = title;
    }

    // получение описания
    public String getDescription() {
        return this.description;
    }
    public String setDescription(String description) {
        return this.description = description;
    }

    // получение цены
    public double getPrice() {
        return this.price;
    }
    public double setPrice(double price) {
        return this.price = price;
    }
}
