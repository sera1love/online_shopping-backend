package ShopSystem;
import java.util.Objects;

public abstract class Category implements Comparable<Category> {
    private static int idgen = 1;
    private final int id;
    private String title, description;
    private double price;

    public Category(String title, double price, String description) {
        this.id = idgen++;
        this.title = title;
        this.price = price;
        this.description = description;
    }


    public abstract void showInfo();
    public boolean isSubCategory() { return false; }

    // геттеры и сеттеры
    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category that = (Category) o;
        return Double.compare(that.price, price) == 0 &&
                id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
    }
    @Override public int hashCode() { return Objects.hash(id, title, price, description); }
    @Override public int compareTo(Category other) { return Double.compare(this.price, other.price); }
}