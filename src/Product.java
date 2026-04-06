public abstract class Product {
    private static int idgen = 1;

    private int id;
    private String title;
    private double price;
    private String description;

    public Product() {}
    public Product(String title, double price, String description) {
        this.id = idgen++; // генерация ID
        this.title = title;
        this.price = price;
        this.description = description;
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

}