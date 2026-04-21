package ShopSystem;

public abstract class Category {
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

    // абстрактный метод showInfo
    public abstract void showInfo();

    public boolean isSubCategory() {
        return false;
    }

    // геттеры и сеттеры
    // получение id
    public int getID() {
        return this.id;}
    public int setID(int id) {
        return this.id = id;}

    // получение названия
    public String getTitle() {
        return this.title;}
    public String setTitle(String title) {
        return this.title = title;}

    // получение описания
    public String getDescription() {
        return this.description;}
    public String setDescription(String description) {
        return this.description = description;}

    // получение цены
    public double getPrice() {
        return this.price;}
    public double setPrice(double price) {
        return this.price = price;}

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category that = (Category) o;
        return price == that.price;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}