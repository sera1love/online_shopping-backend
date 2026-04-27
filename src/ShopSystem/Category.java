package ShopSystem;

import java.util.Objects;

public abstract class Category implements Comparable<Category> {
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

    // реализация метода showInfo
    public abstract void showInfo();

    // проверка на сабкатегорию для счетчика
    public boolean isSubCategory() {
        return false;
    }

    // гетеры сетеры
    // получение ID
    public int getID() {return id;}
    public void setID(int id) {this.id = id;}

    // получение названия
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    // получение описания
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    // получение цены
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category that = (Category) o;
        return Double.compare(that.price, price) == 0 &&
                id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, description);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int compareTo(Category other) {
        if (other == null) return 1;
        return Double.compare(this.price, other.price); // по возрастанию цены
    }
}